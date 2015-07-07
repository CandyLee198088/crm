package org.ssm.crm520.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.service.IWorkFlowService;
import org.ssm.crm520.util.CommonUtils;
import org.ssm.crm520.util.UserContext;

@Service
public class WorkFlowServiceImpl implements IWorkFlowService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	/**
	 * 部署流程;
	 */
	@Override
	public void deploy(InputStream inputStream, String name) {
		DeploymentBuilder builder = repositoryService.createDeployment();
		ZipInputStream zipInputStream = new ZipInputStream(inputStream);
		builder.name(name).addZipInputStream(zipInputStream);
		builder.deploy();
	}
	/**
	 * 流程定义列表;
	 */
	@Override
	public List<Map<String, Object>> processDefinitionList() {
		List<ProcessDefinition> pdList = repositoryService
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion().asc().list();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String[] properties = { "id", "name", "key", "version", "resourceName",
				"diagramResourceName", "description", "deploymentId" };
		for (ProcessDefinition processDefinition : pdList) {
			Map<String, Object> map = CommonUtils.obj2map(processDefinition,
					properties);
			listMap.add(map);
		}
		return listMap;
	}
	/**
	 * 开始流程;
	 */
	@Override
	public void startProcess(String processKey, Map<String, Object> varsMap) {
		String businessKey = varsMap.get("classType") + ":"
				+ varsMap.get("objId");
		runtimeService.startProcessInstanceByKey(processKey, businessKey,
				varsMap);
	}
	/**
	 * 删除流程
	 */
	@Override
	public void deleteProcess(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}
	/**
	 * 查看流程图;
	 */
	@Override
	public InputStream viewProcessPng(String deploymentId, String resourceName) {
		return repositoryService
				.getResourceAsStream(deploymentId, resourceName);
	}
	@Override
	public Map<String, Object> viewProcessPngCordinates(String taskId ) {
		Map<String, Object> map = new HashMap<String,Object>();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		String activityId = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult().getActivityId();
		ActivityImpl activity = pde.findActivity(activityId);
		map.put("inputStream", viewProcessPng(pde.getDeploymentId(),pde.getDiagramResourceName()));
		map.put("activity", activity);
		return map;
	}
	
	/**
	 * 查看当前登录人的任务列表;
	 */
	@Override
	public List<Map<String, Object>> personalTaskList() {
		Employee currentUser = UserContext.getUser();
		 List<Task> list = taskService.createTaskQuery()
				.taskAssignee(currentUser.getUsername())
				.orderByTaskCreateTime().desc().list();
		 List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
			String[] properties = { "id", "name", "assignee", "createTime", "description" };
			for (Task task : list) {
				Map<String, Object> map = CommonUtils.obj2map(task,
						properties);
				listMap.add(map);
			}
			return listMap;
	}
	/**
	 * 获取任务中的信息,包含流程变量中的信息;
	 */
	@Override
	public Map<String, Object> getTaskInfo(String taskId) {
		Map<String, Object> taskMap = new HashMap<String,Object>();
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processDefinitionId  = task.getProcessDefinitionId();
		String taskDefinitionKey = task.getTaskDefinitionKey();
		String taskFormKey = formService.getTaskFormKey(processDefinitionId, taskDefinitionKey);
		taskMap.put("formKey", taskFormKey);
		Long objId = (Long) taskService.getVariable(taskId, "objId");
		String className  = (String) taskService.getVariable(taskId, "classType");
		taskMap.put("objId", objId);
		taskMap.put("className", className.substring(0,1).toLowerCase()+className.substring(1));
		return taskMap;
	}
	/**
	 * 完成任务
	 */
	@Override
	public void completeTask(String id) {
		taskService.complete(id);
	}

}
