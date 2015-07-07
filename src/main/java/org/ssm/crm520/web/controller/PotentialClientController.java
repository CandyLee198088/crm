package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.PotentialClient;
import org.ssm.crm520.page.PotentialClientQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IPotentialClientService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/potentialClient")
public class PotentialClientController {

	@Autowired
	private IPotentialClientService potentialClientService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(PotentialClientQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<PotentialClient> results = potentialClientService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "potentialClient/potentialClient";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(PotentialClient potentialClient) {
		Assert.notNull(potentialClient, "potentialClient不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (potentialClient.getId() == null) {
				potentialClientService.save(potentialClient);
			} else {
				potentialClientService.update(potentialClient);
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
			potentialClientService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<PotentialClient> list() {
		return potentialClientService.getAll();
	}

}
