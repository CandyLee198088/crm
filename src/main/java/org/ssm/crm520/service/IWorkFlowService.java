package org.ssm.crm520.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface IWorkFlowService {
	void deploy(InputStream inputStream,String name);

	List<Map<String, Object>> processDefinitionList();
	
	void startProcess(String processKey,Map<String,Object> varsMap);

	void deleteProcess(String deploymentId);


	InputStream viewProcessPng(String deploymentId, String resourceName);

	List<Map<String, Object>> personalTaskList();

	Map<String, Object> getTaskInfo(String taskId);

	void completeTask(String id);
	/**
	 * @param taskId
	 * @return
	 */
	Map<String,Object> viewProcessPngCordinates(String taskId);
}
