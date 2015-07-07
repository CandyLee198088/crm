package org.ssm.crm520.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Menu;
import org.ssm.crm520.page.MenuQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IMenuService;

public class MenuServiceTest extends BaseServiceTest {
	@Autowired
	private IMenuService menuService;


	@Test
	public void get() {
		System.out.println(menuService.get(1L));
	}
	@Test
	public void getAll() {
		List<Menu> menus = menuService.getAll();
		for (Menu menu : menus) {
			System.out.println(menu);
		}
	}
	@Test
	public void testfindRelations() throws Exception {
		List<Menu> list = menuService.getAllParentMenus();
		for (Menu menu : list) {
			System.out.println(menu);
			
		}
	}
	@Test
	public void testQuery() throws Exception {
		MenuQuery query = new MenuQuery();
		query.setRows(3);
		PageResult<Menu> findByQuery = menuService.findByQuery(query);
		for (Menu menu : findByQuery.getObjs()) {
			System.out.println(menu);
		}
	}
	@Test
	public void testMenu() throws Exception {
		Employee e = new Employee();
		e.setId(2L);
		List<Menu> list = menuService.getAllParentMenus(e);
		for (Menu menu : list) {
			System.out.println(menu);
		}
	}
	@Test
	public void testChildren() throws Exception {
		Employee e = new Employee();
		e.setId(2L);
		List<Menu> list = menuService.getChildrenMenus(1L, e);
		for (Menu menu : list) {
			System.out.println(menu);
		}
	}


}
