package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.page.FunctionQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IFunctionService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/functionModel")
public class FunctionController {

	@Autowired
	private IFunctionService functionModelService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(FunctionQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Function> results = functionModelService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "/functionModel/list";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Function functionModel) {
		Assert.notNull(functionModel, "functionModel不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (functionModel.getId() == null) {
				functionModelService.save(functionModel);
			} else {
				functionModelService.update(functionModel);
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
			functionModelService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Function> list() {
		return functionModelService.getAll();
	}

}
