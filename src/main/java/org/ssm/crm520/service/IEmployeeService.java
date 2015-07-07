package org.ssm.crm520.service;

import org.ssm.crm520.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee> {

	Employee checkLogin(String username, String password);
	
	void saveEmployeeRoleRelation(Employee employee);
	
	void clearEmployeeRoleRelation(Long id);//清除t_employee_role中指定员工的关联角色
	
	Employee getManager(Long userId);
}
