package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Contract;
import org.ssm.crm520.page.ContractQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IContractService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/contract")
public class ContractController {
	@Autowired
	private IContractService service;
	
	@RequestMapping("/")
	public String index(){
		return "/contract/list";
	} 
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(ContractQuery query){
		System.out.println(query);
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Contract> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(Contract contract){
		System.out.println(contract);
		AjaxResult ar = null;
		if(contract!=null && contract.getId()!=null){
			service.update(contract);
			ar = new AjaxResult("合同更新成功");
		}else {
			service.save(contract);
			ar = new AjaxResult("合同保存成功");
		}
		return ar;
	} 
	
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(Long id){
		if(id!=null){
			service.delete(id);
		}
		return new AjaxResult("合同删除成功");
	} 
	
}
