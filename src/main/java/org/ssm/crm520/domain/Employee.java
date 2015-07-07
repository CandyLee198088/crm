package org.ssm.crm520.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 任何domain请继承IdEntity获取id属性
 * @author 骆余海;
 * 请一定记得写注释!
 *
 */
public class Employee extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String username;//员工账号
	private String truename;//真名
	private String password;//密码
	private String tel;//电话
	private String email;//邮箱
	private Department department;//部门
	private Date inTime;//录入时间
	private Long status=0L;//状态 0 正常 ，-1离职
	private List<Role> roles = new ArrayList<Role>();//角色

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [id= "+id+" + username=" + username + ", truename=" + truename
				+ ", password=" + password + ", tel=" + tel + ", email="
				+ email + ", department=" + department + ", inTime=" + inTime
				+ ", status=" + status + ", roles=" + roles + "]";
	}

}
