package org.ssm.crm520.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Department;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.page.DepartmentQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IDepartmentService;

public class DepartmentServiceTest extends BaseServiceTest {
	
	@Autowired
	private IDepartmentService departmentService;
	@Test
	public void testCreateTable() {
		departmentService.createTable();
	}

	@Test
	public void testSave() {
		Department department=new Department();
		department.setDeptno("bianhao");
		department.setName("开发部");
		department.setStatus(1);
		
		Employee manager=new Employee();
		manager.setId(7L);
		department.setManager(manager);
		
		Department parent =new Department();
		parent.setId(2L);
		department.setParent(parent);
		
		departmentService.save(department);
		
	}

	@Test
	public void testDelete() {
		departmentService.delete(7L);
	}

	@Test
	public void testUpdate() {
		Department department=new Department();
		
		department.setId(7L);
		
		department.setDeptno("bianhao2");
		department.setName("开发部2");
		department.setStatus(1);
		
		Employee manager=new Employee();
		manager.setId(2L);
		department.setManager(manager);
	
		Department parent =new Department();
		parent.setId(2L);
		department.setParent(parent);
	
		
		departmentService.update(department);
		
	}

	@Test
	public void testGet() {
		Department department= departmentService.get(2L);
		System.out.println(department);
	}

	@Test
	public void testGetAll() {
		List<Department> list = departmentService.getAll();
		for (Department department : list) {
			System.out.println(department);
		}
	}

	@Test
	public void testFindQuery() {
		DepartmentQuery query=new DepartmentQuery();
//		query.setId(2L);
//		query.setDeptno("bianhao5");
//		query.setName("开发部44");
//		query.setPage(2);
//		query.setRows(3);
		 PageResult<Department> pageResult = departmentService.findByQuery(query);
		 List<Department> objs = pageResult.getObjs();
		for (Department department : objs) {
			System.out.println(department);
		}
		System.out.println(pageResult.getTotalCount());
	}

	@Test
	public void testFindCount() {
//		DepartmentQuery query=new DepartmentQuery();
//		query.setId(2L);
//		query.setDeptno("bianhao5");
//		query.setName("开发部44");

//		Long count = departmentService.findCount(query);
//		System.out.println(count);
	}

}
