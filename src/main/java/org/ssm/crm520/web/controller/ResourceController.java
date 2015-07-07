package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Resource;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.ResourceQuery;
import org.ssm.crm520.service.IResourceService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private IResourceService service;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(ResourceQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Resource> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "/resource/list";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Resource resource) {
		Assert.notNull(resource, "resource不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (resource.getId() == null) {
				service.save(resource);
			} else {
				service.update(resource);
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
			service.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Resource> list() {
		return service.getAll();
	}

}
