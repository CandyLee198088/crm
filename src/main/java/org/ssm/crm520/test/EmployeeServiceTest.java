package org.ssm.crm520.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.page.EmployeeQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IEmployeeService;

public class EmployeeServiceTest extends BaseServiceTest {
	@Autowired
	private IEmployeeService employeeService;
	@Test
	public void save() {
		Employee employee = new Employee();
		employee.setUsername("admin");
		employee.setTruename("张三");
		employee.setPassword("1");
		employee.setEmail("1@1.com");
		employee.setInTime(new Date());
		employee.setStatus(1L);
		employee.setTel("123123123");
		employeeService.save(employee);
	}

	@Test
	public void delete() {
		employeeService.delete(1L);
	}

	@Test
	public void update() {
		Employee employee = new Employee();
		employee.setUsername("ss");
		employee.setTruename("ss2");
		employee.setPassword("sss");
		employee.setId(2L);
		employeeService.update(employee);
	}

	@Test
	public void get() {
		System.out.println(employeeService.get(2L));
	}
	@Test
	public void getAll() {
		List<Employee> employees = employeeService.getAll();
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}
	@Test
	public void testsdf() throws Exception {
		EmployeeQuery query = new EmployeeQuery();
		
		PageResult<Employee> findByQuery = employeeService.findByQuery(query);
		System.out.println(findByQuery.getObjs());
		
		for (Employee employee : findByQuery.getObjs()) {
			System.out.println(1);
			System.out.println(employee);
		}
	}
	@Test
	public void testGetManager() throws Exception {
		Employee manager = employeeService.getManager(2L);
		System.out.println(manager);
	}
	
		

	/**
	 * 高级查询查询符合条件的数量
	 * @param query 查询条件
	 * @return 数量
	 */
	
}
