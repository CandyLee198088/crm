package org.ssm.crm520.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Employee_Role;
import org.ssm.crm520.domain.Role;
import org.ssm.crm520.mapper.EmployeeMapper;
import org.ssm.crm520.service.IEmployeeService;

/**
 * 员工管理实现类
 * @author 骆余海
 *
 */

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {
	
	private EmployeeMapper employeeMapper;
	@Autowired
	public void setBaseMapper(EmployeeMapper employeeMapper) {
		super.setBaseMapper(employeeMapper);
		this.employeeMapper = employeeMapper;
	}
	/**
	 * 检查登录的用户名和密码;
	 */
	@Override
	public Employee checkLogin(String username, String password) {
		Map<String,Object> map  =new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", password);
		List<Employee> users = employeeMapper.checkLogin(map);
		if(users!=null&&users.size()==1){
			return users.get(0);
		}
		return null;
	}
	
	@Override
	public void save(Employee employee) {
		employeeMapper.save(employee);
		
		if(employee.getId()!=null){
			this.saveEmployeeRoleRelation(employee);
		}
		
	}
	
	@Override
	public void update(Employee employee) {
		Long empId = employee.getId();
		this.clearEmployeeRoleRelation(empId);
		
		employeeMapper.update(employee);
		this.saveEmployeeRoleRelation(employee);
	}
	@Override
	public void saveEmployeeRoleRelation(Employee employee) {
		List<Role> roles = employee.getRoles();
		if(roles.size()==0){
			return;
		}
		List<Employee_Role> ers = new ArrayList<Employee_Role>();
		for (Role role : roles) {
			Long role_id = role.getId();
			if(role.getId()!=null){
				Employee_Role employee_Role = new Employee_Role(employee.getId(), role_id);
				ers.add(employee_Role);
			}
		}
		employeeMapper.saveEmployeeRoleRelation(ers);
	}
	
	@Override
	public void clearEmployeeRoleRelation(Long id) {
		if(id!=null){
			employeeMapper.clearEmployeeRoleRelation(id);
		}
	}
	@Override
	public Employee getManager(Long userId) {
		return employeeMapper.getManager(userId);
	}
}
