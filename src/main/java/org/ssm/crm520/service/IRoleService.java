package org.ssm.crm520.service;

import org.ssm.crm520.domain.Role;

public interface IRoleService extends IBaseService<Role> {
	
	//拿到角色对象,可以创建多对多的中间表
	void saveRoleFunctionRelation(Role role);
	
	//更新角色,需要先清除以前中间表关联的权限
	void clearRoleFunctionRelation(Long id);
	void clearRoleMenuRelation(Long id);

	void saveRoleMenuRelation(Role role);

	void updateMenu(Role role);
}
