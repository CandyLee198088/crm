package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.CustomerTransHistory;
import org.ssm.crm520.page.CustomerTransHistoryQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerTransHistoryService;
import org.ssm.crm520.util.AjaxResult;
/**
 * 客户移交的控制器
 * @author 张高强
 *
 */
@Controller
@RequestMapping("/customerTransHistory")
public class CustomerTransHistoryController {
	
	@Autowired
	private ICustomerTransHistoryService   customerTransHistoryService;
	
	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> json(CustomerTransHistoryQuery query) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<CustomerTransHistory> results = customerTransHistoryService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index(){
		return "customerTransHistory/customerTransHistory";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(CustomerTransHistory customerTransHistory,HttpSession session){
		if(customerTransHistory!=null&&customerTransHistory.getId()==null){
			//设置当前登录员工为营销人员
			customerTransHistoryService.save(customerTransHistory);
			return new AjaxResult("保存成功!");
		}else if(customerTransHistory!=null&&customerTransHistory.getId()!=null){
			customerTransHistoryService.update(customerTransHistory);
		}
		return new AjaxResult("更新成功!");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult ajaxResult=new AjaxResult();
		//有关联对象时不能删除
		try {
			customerTransHistoryService.delete(id);
		} catch (Exception e) {
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("有关联对象,不能删除!");
			return ajaxResult;
		}
		ajaxResult.setMessage("删除成功!");
		return ajaxResult;
	}


	@RequestMapping("/list")
	@ResponseBody
	public List<CustomerTransHistory> list(){
		return customerTransHistoryService.getAll();
	}

}
