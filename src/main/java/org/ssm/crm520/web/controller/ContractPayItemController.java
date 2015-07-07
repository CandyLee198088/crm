package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.ContractPayItem;
import org.ssm.crm520.page.ContractPayItemQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IContractPayItemService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/contractPayItem")
public class ContractPayItemController {
	@Autowired
	private IContractPayItemService service;
	
	@RequestMapping("/")
	public String index(){
		return "/contractPayItem/list";
	} 
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(ContractPayItemQuery query){
		System.out.println(query.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<ContractPayItem> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(ContractPayItem contractPayItem){
		System.out.println(contractPayItem);
		AjaxResult ar = null;
		if(contractPayItem!=null && contractPayItem.getId()!=null){
			service.update(contractPayItem);
			ar = new AjaxResult("合同付款明细更新成功");
		}else {
			service.save(contractPayItem);
			ar = new AjaxResult("合同付款明细保存成功");
		}
		return ar;
	} 
	
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(Long id){
		if(id!=null){
			service.delete(id);
		}
		return new AjaxResult("合同付款明细删除成功");
	} 
	
}
