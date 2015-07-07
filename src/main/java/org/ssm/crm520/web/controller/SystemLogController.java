package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.SystemLog;
import org.ssm.crm520.page.SystemLogQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ISystemLogService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/systemLog")
public class SystemLogController {

	@Autowired
	private ISystemLogService systemLogService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(SystemLogQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<SystemLog> results = systemLogService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "systemLog/systemLog";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(SystemLog systemLog) {
		Assert.notNull(systemLog, "systemLog不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (systemLog.getId() == null) {
				systemLogService.save(systemLog);
			} else {
				systemLogService.update(systemLog);
			}
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult as = new AjaxResult();
		try {
			systemLogService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<SystemLog> list() {
		return systemLogService.getAll();
	}

}
