package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.SystemDictionaryDetail;
import org.ssm.crm520.domain.SystemDictionaryType;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.SystemDictionaryTypeQuery;
import org.ssm.crm520.service.ISystemDictionaryDetailService;
import org.ssm.crm520.service.ISystemDictionaryTypeService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/systemDictionaryType")
public class SystemDictionaryTypeController {
	@Autowired
	private ISystemDictionaryTypeService service;
	
	@Autowired
	private ISystemDictionaryDetailService detailService;
	
	@ResponseBody
	@RequestMapping("/transf")
	public List<SystemDictionaryDetail> transf(Long id){
		SystemDictionaryType type = service.get(id);
		List<SystemDictionaryDetail> details = type.getDetails();
		return details;
	} 
	
	@RequestMapping("/")
	public String index(){
		return "/systemDictionaryType/list";
	} 
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(SystemDictionaryTypeQuery query){
		System.out.println(query.getStatus());
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<SystemDictionaryType> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	@ResponseBody
	@RequestMapping("/allDetails")
	public List<SystemDictionaryDetail> getAllDetails(){
		return detailService.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(SystemDictionaryType systemDictionaryType){
		AjaxResult ar = null;
		if(systemDictionaryType!=null && systemDictionaryType.getId()!=null){
			service.update(systemDictionaryType);
			ar = new AjaxResult("数据字典目录更新成功");
		}else {
			service.save(systemDictionaryType);
			ar = new AjaxResult("数据字典目录保存成功");
		}
		return ar;
	} 
	
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(Long id){
		if(id!=null){
			service.delete(id);
		}
		return new AjaxResult("数据字典目录删除成功");
	} 
	
}
