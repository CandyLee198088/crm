package org.ssm.crm520.domain;

public class Role_Menu {
	private Long role_id;//角色id
	private Long menu_id;//菜单id
	
	public Role_Menu(){}
	public Role_Menu(Long role_id, Long menu_id) {
		super();
		this.role_id = role_id;
		this.menu_id = menu_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public Long getMenu_id() {
		return menu_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public void setMenu_id(Long menu_id) {
		this.menu_id = menu_id;
	}
}
