package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.${domain};
import org.ssm.crm520.page.${domain}Query;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.I${domain}Service;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/${domainLower}")
public class ${domain}Controller {

	@Autowired
	private I${domain}Service ${domainLower}Service;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(${domain}Query query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<${domain}> results = ${domainLower}Service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "${domainLower}/${domainLower}";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(${domain} ${domainLower}) {
		Assert.notNull(${domainLower}, "${domainLower}不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (${domainLower}.getId() == null) {
				${domainLower}Service.save(${domainLower});
			} else {
				${domainLower}Service.update(${domainLower});
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
			${domainLower}Service.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<${domain}> list() {
		return ${domainLower}Service.getAll();
	}

}
