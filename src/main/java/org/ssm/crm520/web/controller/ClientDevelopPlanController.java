package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.ClientDevelopPlan;
import org.ssm.crm520.page.ClientDevelopPlanQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IClientDevelopPlanService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/clientDevelopPlan")
public class ClientDevelopPlanController {

	@Autowired
	private IClientDevelopPlanService clientDevelopPlanService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(ClientDevelopPlanQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<ClientDevelopPlan> results = clientDevelopPlanService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "clientDevelopPlan/clientDevelopPlan";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(ClientDevelopPlan clientDevelopPlan) {
		Assert.notNull(clientDevelopPlan, "clientDevelopPlan不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (clientDevelopPlan.getId() == null) {
				clientDevelopPlanService.save(clientDevelopPlan);
			} else {
				clientDevelopPlanService.update(clientDevelopPlan);
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
			clientDevelopPlanService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<ClientDevelopPlan> list() {
		return clientDevelopPlanService.getAll();
	}

}
