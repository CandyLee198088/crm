package org.ssm.crm520.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Menu;
import org.ssm.crm520.mapper.MenuMapper;
import org.ssm.crm520.service.IMenuService;

/**
 * 员工管理实现类
 * @author Administrator
 *
 */

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
	
	private MenuMapper menuMapper;
	
	@Autowired
	public void setBaseMapper(MenuMapper menuMapper) {
		super.setBaseMapper(menuMapper);
		this.menuMapper = menuMapper;
	}

	@Override
	public List<Menu> getAllParentMenus() {
		 List<Menu> allParentMenus = menuMapper.getAllParentMenus();
		 for (Menu menu : allParentMenus) {
			 menu.getAttributes().put("url", menu.getUrl());
		}
		 return allParentMenus;
	}

	@Override
	public List<Menu> getChildrenMenus(Long id) {
		 List<Menu> childrenMenus = menuMapper.getChildrenMenus(id);
		 for (Menu menu : childrenMenus) {
			 menu.getAttributes().put("url", menu.getUrl());
		}
		 return childrenMenus;
	}

	@Override
	public List<Menu> getAllParentMenus(Employee employee) {
		Assert.notNull(employee.getId());
		return menuMapper.getAllParentMenusByUser(employee.getId());
	}

	@Override
	public List<Menu> getChildrenMenus(Long id, Employee employee) {
		Assert.notNull(employee.getId());
		Map<String,Long> map  =new HashMap<String,Long>();
		map.put("id", id);
		map.put("employeeId", employee.getId());
		List<Menu> childrenMenus = menuMapper.getChildrenMenusByUser(map);
		for (Menu menu : childrenMenus) {
			 menu.getAttributes().put("url", menu.getUrl());
		}
		return childrenMenus;
	}

}
