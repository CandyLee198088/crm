package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.CustomerTrace;
import org.ssm.crm520.page.CustomerTraceQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerTraceService;
import org.ssm.crm520.util.AjaxResult;
/**
 * 部门的控制器
 * @author 张高强
 *
 */
@Controller
@RequestMapping("/customerTrace")
public class CustomerTraceController {
	
	@Autowired
	private ICustomerTraceService   customerTraceService;
	
	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> json(CustomerTraceQuery query) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<CustomerTrace> results = customerTraceService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index(){
		return "customerTrace/customerTrace";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(CustomerTrace customerTrace,HttpSession session){
		if(customerTrace!=null&&customerTrace.getId()==null){
			//设置当前登录员工为营销人员
			customerTraceService.save(customerTrace);
			return new AjaxResult("保存成功!");
		}else if(customerTrace!=null&&customerTrace.getId()!=null){
			customerTraceService.update(customerTrace);
		}
		return new AjaxResult("更新成功!");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult ajaxResult=new AjaxResult();
		//有关联对象时不能删除
		try {
			customerTraceService.delete(id);
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
	public List<CustomerTrace> list(){
		return customerTraceService.getAll();
	}

}
