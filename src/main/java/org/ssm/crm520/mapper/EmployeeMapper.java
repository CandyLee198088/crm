package org.ssm.crm520.mapper;

import java.util.List;
import java.util.Map;

import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Employee_Role;
/**
 * 直接继承BaseMapper
 * @author Administrator
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

	List<Employee> checkLogin(Map<String,Object> map);

	void saveEmployeeRoleRelation(List<Employee_Role> list);

	void clearEmployeeRoleRelation(Long id);
	
	Employee getManager(Long id);
}
