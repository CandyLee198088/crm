package org.ssm.crm520.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.ssm.crm520.service.IWorkFlowService;
import org.ssm.crm520.util.AjaxResult;
import org.ssm.crm520.util.CommonUtils;

@Controller
@RequestMapping("/definition")
public class ProcessDefinitionController {
	@Autowired
	private IWorkFlowService workFlowService;

	@RequestMapping("/")
	public String index() {
		return "definition/list";
	}

	@RequestMapping("/showConfirmInfoForm")
	public String showConfirmInfoForm(String deploymentId) {
		return "definition/confirmInfoForm";
	}

	@RequestMapping("/showManagerConfirmForm")
	public String showManagerConfirmForm(String deploymentId) {
		return "definition/managerConfirmForm";
	}

	@RequestMapping("/task")
	public String taskIndex() {
		return "definition/personalTaskList";
	}

	@RequestMapping("/upload")
	@ResponseBody
	public AjaxResult upload(MultipartFile zipFile, String zipFileName)
			throws IOException {
		AjaxResult ar = new AjaxResult();
		try {
			workFlowService.deploy(zipFile.getInputStream(), zipFileName);
			ar.setMessage("部署成功!");
		} catch (Exception e) {
			e.printStackTrace();
			ar.setSuccess(false);
			ar.setMessage("部署失败!");
		}
		return ar;
	}

	@RequestMapping("/viewPng")
	public void viewPng(String deploymentId, String resourceName,
			HttpServletResponse resp) {
		resp.setContentType("image/png");
		try {
			String resourceNameISO = new String(
					resourceName.getBytes("ISO-8859-1"), "UTF-8");
			if (!resourceNameISO.contains("?")) {
				resourceName = resourceNameISO;
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputStream viewProcessPng = workFlowService.viewProcessPng(
				deploymentId, resourceName);
		try {
			OutputStream out = resp.getOutputStream();
			IOUtils.copy(viewProcessPng, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/viewCoordinates")
	public void viewProcessPngCordinates(String taskId,
			HttpServletResponse resp, HttpSession session) {
		resp.setContentType("image/png");
		Map<String, Object> map = workFlowService
				.viewProcessPngCordinates(taskId);
		try {
			OutputStream out = resp.getOutputStream();
			IOUtils.copy((InputStream) map.get("inputStream"), out);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/personalTaskList")
	@ResponseBody
	public Map<String, Object> personalTaskList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> personalTaskList = workFlowService
				.personalTaskList();
		map.put("total", personalTaskList.size());
		map.put("rows", personalTaskList);
		return map;
	}

	@RequestMapping("/locationInfo")
	@ResponseBody
	public Map<String, Object> locationInfo(String taskId) {
		Map<String, Object> map = workFlowService
				.viewProcessPngCordinates(taskId);
		ActivityImpl activity = (ActivityImpl) map.get("activity");
		return CommonUtils.obj2map(activity, new String[] { "width", "height",
				"x", "y" });
	}

	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> processDefinitionMap = workFlowService
				.processDefinitionList();
		map.put("total", processDefinitionMap.size());
		map.put("rows", processDefinitionMap);
		return map;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(String deploymentId) {
		AjaxResult ar = new AjaxResult();
		try {
			System.out.println("deploymentId:" + deploymentId);
			workFlowService.deleteProcess(deploymentId);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMessage("操作失败!");
		}
		return ar;
	}

	@RequestMapping("/completeTask")
	@ResponseBody
	public AjaxResult completeTask(String id) {
		AjaxResult ar = new AjaxResult();
		try {
			workFlowService.completeTask(id);
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMessage("操作失败!");
		}
		return ar;
	}

	@RequestMapping("/getTaskInfo")
	@ResponseBody
	public Map<String, Object> getTaskInfo(String taskId) {
		return workFlowService.getTaskInfo(taskId);
	}
}
