package org.ssm.crm520.mapper;

import java.util.List;

import org.ssm.crm520.domain.Role;
import org.ssm.crm520.domain.Role_Function;
import org.ssm.crm520.domain.Role_Menu;

public interface RoleMapper extends BaseMapper<Role> {
	//保存角色与权限的中间表
	void saveRoleFunctionRelation(List<Role_Function> list);

	void clearRoleFunctionRelation(Long id);
	void clearRoleMenuRelation(Long id);

	void saveRoleMenuRelation(List<Role_Menu> rfs);
	
}
