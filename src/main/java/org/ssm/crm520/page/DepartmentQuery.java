package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;

/**
 * 部门查询对象的封装;
 * @author 张高强
 *
 */
public class DepartmentQuery extends BaseQuery {

	private String deptno;//按照部门编号查询
	private String name;//按照部门名称查询
	private Integer status;//按照部门状态查询

	public String getDeptno() {
		if (StringUtils.isNotBlank(deptno)) {
			return "%" + deptno + "%";
		}
		return null;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getName() {
		if (StringUtils.isNotBlank(name)) {
			return "%" + name + "%";
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
