package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Role;
import org.ssm.crm520.page.EmployeeQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IEmployeeService;
import org.ssm.crm520.util.AjaxResult;

/**
 * 员工的控制器
 * @author luoyuhai
 *
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	@RequestMapping("/")
	public String index() {
		return "employee/employee";
	}

	@ResponseBody
	@RequestMapping("/query")
	public Map<String, Object> json(EmployeeQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(query);
		PageResult<Employee> results = service.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(Employee employee) {
		System.out.println(employee);
		Map<String, Object> map = new HashMap<String, Object>();
		if (employee.getId() == null) {
			service.save(employee);
			map.put("msg", "保存成功!");
		} else {
			service.update(employee);
			map.put("msg", "更新成功!");
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/saveRole")
	public AjaxResult saveRole(Employee employee) {
		AjaxResult ar = new AjaxResult();
		List<Role> roles = employee.getRoles();
		employee = service.get(employee.getId());
		employee.setRoles(roles);
		service.update(employee);
		ar.setMessage("更新角色成功!");
		return ar;
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