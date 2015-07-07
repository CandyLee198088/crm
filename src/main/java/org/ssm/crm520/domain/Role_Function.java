package org.ssm.crm520.domain;

//角色与权限的中间表
public class Role_Function {
	private Long role_id;//角色id
	private Long function_id;//权限id
	
	public Role_Function(){}
	public Role_Function(Long role_id, Long function_id) {
		super();
		this.role_id = role_id;
		this.function_id = function_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public Long getFunction_id() {
		return function_id;
	}
	public void setFunction_id(Long function_id) {
		this.function_id = function_id;
	}
}
