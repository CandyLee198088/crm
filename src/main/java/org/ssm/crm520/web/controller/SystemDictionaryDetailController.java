package org.ssm.crm520.web.controller;

import java.util.ArrayList;
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
import org.ssm.crm520.page.SystemDictionaryDetailQuery;
import org.ssm.crm520.service.ISystemDictionaryDetailService;
import org.ssm.crm520.service.ISystemDictionaryTypeService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/systemDictionaryDetail")
public class SystemDictionaryDetailController {
	@Autowired
	private ISystemDictionaryDetailService service;
	
	@Autowired
	private ISystemDictionaryTypeService typeService;
	
	@RequestMapping("/")
	public String index(){
		return "/systemDictionaryDetail/list";
	} 
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(SystemDictionaryDetailQuery query){
		Map<String, Object> map = new HashMap<String, Object>();
		if(query.getId()==null){
			List<SystemDictionaryDetail> list = new ArrayList<>();
			map.put("total", 0);
			map.put("rows", list);
			return map;
		}
		
		PageResult<SystemDictionaryDetail> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	@ResponseBody
	@RequestMapping("/list2")//客户信息录入需要
	public Map<String, Object> list2(SystemDictionaryDetailQuery query){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(query.getId());
		PageResult<SystemDictionaryDetail> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	@ResponseBody
	@RequestMapping("/allTypes")
	public List<SystemDictionaryType> getAllTypes(){
		return typeService.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(SystemDictionaryDetail systemDictionaryDetail){
		AjaxResult ar = null;
		if(systemDictionaryDetail!=null && systemDictionaryDetail.getId()!=null){
			service.update(systemDictionaryDetail);
			ar = new AjaxResult("数据字典目录更新成功");
		}else {
			service.save(systemDictionaryDetail);
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
