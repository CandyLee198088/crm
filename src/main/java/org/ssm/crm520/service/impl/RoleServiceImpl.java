package org.ssm.crm520.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.domain.Menu;
import org.ssm.crm520.domain.Role;
import org.ssm.crm520.domain.Role_Function;
import org.ssm.crm520.domain.Role_Menu;
import org.ssm.crm520.mapper.RoleMapper;
import org.ssm.crm520.service.IRoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		IRoleService {
	
	private RoleMapper roleDao;
	
	@Autowired
	public void setBaseMapper(RoleMapper roleMapper) {
		super.setBaseMapper(roleMapper);
		
		this.roleDao = roleMapper;
	}
	
	@Override
	public void save(Role role) {
		//保存角色的时候 拿到返回的id.用于后面建立中间表
		roleDao.save(role);
		//保存到中间表去   使用service 
		 if(role.getId()!=null){
			this.saveRoleFunctionRelation(role);
		 }
		
	}
	
	
	@Override
	public void update(Role role) {
		//清除之前的关联权限
		Long roleId= role.getId();
		
		this.clearRoleFunctionRelation(roleId);
		
		roleDao.update(role);
		this.saveRoleFunctionRelation(role);
	}
	@Override
	public void updateMenu(Role role) {
		//清除之前的关联权限
		Long roleId= role.getId();
		
		this.clearRoleMenuRelation(roleId);
		
		roleDao.update(role);
		this.saveRoleMenuRelation(role);
	}
	
	@Override
	public void saveRoleFunctionRelation(Role role) {
		List<Function> functions = role.getFunctions();
		if(functions.size()==0){
			return;
		}
		//定义一个装中间表对象的一个集合,来装下面的所有中间表对象
		List<Role_Function> rfs = new ArrayList<Role_Function>(); 
		for (Function function : functions) {
			Long function_id = function.getId();
			if(function_id!=null){
				Role_Function role_Function = new Role_Function(role.getId(), function_id);
				rfs.add(role_Function);
			}
		}
		//然后执行传给dao
		roleDao.saveRoleFunctionRelation(rfs);
		
	}
	@Override
	public void saveRoleMenuRelation(Role role) {
		List<Menu> menus = role.getMenus();
		if(menus.size()==0){
			return;
		}
		//定义一个装中间表对象的一个集合,来装下面的所有中间表对象
		List<Role_Menu> rfs = new ArrayList<Role_Menu>(); 
		for (Menu menu : menus) {
			Long menu_id = menu.getId();
			if(menu_id!=null){
				Role_Menu role_Menu = new Role_Menu(role.getId(), menu_id);
				rfs.add(role_Menu);
			}
		}
		//然后执行传给dao
		roleDao.saveRoleMenuRelation(rfs);
		
	}

	
	@Override
	public void clearRoleFunctionRelation(Long id) {
		if(id!=null){
			roleDao.clearRoleFunctionRelation(id);
		}
	}
	@Override
	public void clearRoleMenuRelation(Long id) {
		if(id!=null){
			roleDao.clearRoleMenuRelation(id);
		}
	}
}
