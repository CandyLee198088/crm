package org.ssm.crm520.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.domain.Role;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.RoleQuery;
import org.ssm.crm520.service.IRoleService;

public class RoleServiceTest extends BaseServiceTest {
	@Autowired
	private IRoleService roleService;
	@Test
	public void test() {
		
//		roleService.delete(3L);
		Role role = new Role();
		role.setId(1L);
		role.setName("超级管理员");
		List<Function> functions = new ArrayList<>();
		Function function = new Function();
		Function function1 = new Function();
		function.setId(1L);
		function1.setId(2L);
		functions.add(function1);
		functions.add(function);
		role.setFunctions(functions);
		
//		roleService.update(role);
//		roleService.delete(3L);
		
		System.out.println(roleService.get(1L));
		
	}
	@Test
	public void testQuery() throws Exception {
		RoleQuery query = new RoleQuery();
		PageResult<Role> result = roleService.findByQuery(query);
		for (Role role : result.getObjs()) {
			System.out.println(role);
		}
		System.out.println(result.getTotalCount());
		
	}

}
