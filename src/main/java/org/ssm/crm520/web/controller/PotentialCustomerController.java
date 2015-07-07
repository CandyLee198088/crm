package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.PotentialCustomer;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.PotentialCustomerQuery;
import org.ssm.crm520.service.IPotentialCustomerService;

/**
 * 潜在客户的控制器
 * @author luoyuhai
 *
 */

@Controller
@RequestMapping("/potentialCustomer")
public class PotentialCustomerController{
	
	@Autowired
	private IPotentialCustomerService service;
	
	@RequestMapping("/")
	public String index(){
		return "potentialCustomer/potentialCustomer";
	} 
	
	@ResponseBody
	@RequestMapping("/query")
	public Map<String,Object> json(PotentialCustomerQuery query) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(query);
		PageResult<PotentialCustomer> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/list")
	public List<PotentialCustomer> list(){
		return service.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Map<String,Object> save(PotentialCustomer potentialCustomer){
		System.out.println(potentialCustomer);
		Map<String, Object> map = new HashMap<String, Object>();
		if(potentialCustomer.getId()==null){
			service.save(potentialCustomer);
			map.put("msg","保存成功!");
		}else{
			service.update(potentialCustomer);
			map.put("msg","更新成功!");
		}
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id != null) {
				service.delete(id);
				map.put("msg", "删除成功!");
			} else {
				map.put("msg", "删除失败!");
			}
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
}