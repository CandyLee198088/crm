package org.ssm.crm520.page;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 员工查询对象的封装
 * @author 骆余海
 *
 */
public class EmployeeQuery extends BaseQuery {
	private String username;// 员工账号
	private String truename;// 真名
	private String password;// 密码
	private String tel;// 电话
	private String email;// 邮箱
	private Long department_id;// 部门
	private Long status;// 状态 0 正常 ，-1离职
	private Date minTime;//录入最小时间
	private Date maxTime;//录入最大时间
//	private Role role_id;// 角色
	
	private String q;//在combogrid查询条件

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getKeyword() {
		if (StringUtils.isNotBlank(super.getKeyword())) {
			return "%" + super.getKeyword() + "%";
		}else if(StringUtils.isNotBlank(q)){
			return "%"+q+"%";
		}
		return null;
	}
	public String getUsername() {
		if (username != null) {
			return "%" + username + "%";
		}
		return null;
	}

	public Date getMinTime() {
		return minTime;
	}

	public void setMinTime(Date minTime) {
		this.minTime = minTime;
	}

	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		if (truename != null) {
			return "%" + truename + "%";
		}
		return null;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPassword() {
		if (password != null) {
			return "%" + password + "%";
		}
		return null;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		if (tel != null) {
			return "%" + tel + "%";
		}
		return null;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		if (email != null) {
			return "%" + email + "%";
		}
		return null;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

//	public Role getRole_id() {
//		return role_id;
//	}

//	public void setRole_id(Role role_id) {
//		this.role_id = role_id;
//	}
}
