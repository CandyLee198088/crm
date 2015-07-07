package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.page.DepositOrderQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IDepositOrderService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/depositOrder")
public class DepositOrderController {
	@Autowired
	private IDepositOrderService service;
	
	@RequestMapping("/")
	public String index(){
		return "/depositOrder/list";
	} 
	
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(DepositOrderQuery query){
		System.out.println(query);
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<DepositOrder> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	} 
	
	
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(DepositOrder depositOrder){
		System.out.println(depositOrder);
		AjaxResult ar = null;
		if(depositOrder!=null && depositOrder.getId()!=null){
			service.update(depositOrder);
			ar = new AjaxResult("定金订单更新成功");
		}else {
			service.save(depositOrder);
			ar = new AjaxResult("定金订单保存成功");
		}
		return ar;
	} 
	
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(Long id){
		if(id!=null){
			service.delete(id);
		}
		return new AjaxResult("定金订单删除成功");
	} 
	
}
