package org.ssm.crm520.service;

import java.util.List;

import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Menu;

public interface IMenuService extends IBaseService<Menu> {

	List<Menu> getAllParentMenus();

	List<Menu> getChildrenMenus(Long id);

	List<Menu> getAllParentMenus(Employee employee);

	List<Menu> getChildrenMenus(Long id,Employee employee);	
	
}
