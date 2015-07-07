package org.ssm.crm520.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象,暂时留位
 * @author 骆余海
 *
 */
public class Role extends IdEntity {
	private static final long serialVersionUID = 1L;
	private String sn;
	private String name;
	private List<Function> functions = new ArrayList<Function>();
	private List<Menu> menus = new ArrayList<Menu>();
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	@Override
	public String toString() {
		return "Role [sn=" + sn + ", name=" + name + ", functions=" + functions + ", id=" + id
				+ "]";
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
