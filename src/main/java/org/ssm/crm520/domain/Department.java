package org.ssm.crm520.domain;

import java.util.List;


/**
 * 部门的模型对象;
 * @author 张高强
 * 请补全字段注释;
 */
public class Department extends IdEntity {
	private static final long serialVersionUID = 1L;
	private String deptno;//部门编号
	private String name;//部门名称
	private Employee manager;//部门经理
	private Department parent;//上级部门
	
	private List<Department>  childs;//子部门
	private Integer status=0;//状态   0 正常 ，-1停用

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setChilds(List<Department> childs) {
		this.childs = childs;
	}
	public List<Department> getChilds() {
		return childs;
	}

	@Override
	public String toString() {
		return "Department [ id=" + id +",deptno=" + deptno + ", name=" + name + ", manager="
				+ manager + ", parent=" + parent + ", childs=" + childs
				+ ", status=" + status +  "]";
	}




}
