package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.page.CustomerQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerService;
import org.ssm.crm520.util.AjaxResult;
import org.ssm.crm520.util.UserContext;

/**
 * 部门的控制器
 * @author 张高强
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping("/startProcess")
	@ResponseBody
	public AjaxResult startProcess(Long id) {
		AjaxResult ar = new AjaxResult();
		try {
			customerService.startProcess(id);
		} catch (Exception e) {
			ar.setSuccess(false);
			ar.setMessage("启动失败!");
			e.printStackTrace();
		}
		return ar;
	}

	@RequestMapping("/json")
	@ResponseBody
	public Map<String, Object> json(CustomerQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Customer> results = customerService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "customer/customer";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Customer customer, HttpSession session) {
		//		System.out.println("=============="+customer.getGender());
		if (customer != null && customer.getId() == null) {
			//设置当前登录员工为营销人员
			customer.setSeller(UserContext.getUser());
			customerService.save(customer);
			return new AjaxResult("保存成功!");
		} else if (customer != null && customer.getId() != null) {
			customer.setSeller(UserContext.getUser());
			customerService.update(customer);
		}
		return new AjaxResult("更新成功!");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult ajaxResult = new AjaxResult();
		//有关联对象时不能删除
		try {
			customerService.delete(id);
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
	public List<Customer> list() {
		return customerService.getAll();
	}
	@RequestMapping("/get")
	@ResponseBody
	public Customer get(Long id) {
		return customerService.get(id);
	}

}
