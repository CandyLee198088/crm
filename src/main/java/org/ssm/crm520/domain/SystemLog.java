package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SystemLog extends IdEntity {

	private static final long serialVersionUID = 1L;
	private Employee opUser;
	private Date opTime;
	private String opIp;
	private String function;
	private String args;

	public Employee getOpUser() {
		return opUser;
	}

	public Date getOpTime() {
		return opTime;
	}

	public String getOpIp() {
		return opIp;
	}

	public String getFunction() {
		return function;
	}

	public String getArgs() {
		return args;
	}

	public void setOpUser(Employee opUser) {
		this.opUser = opUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	@Override
	public String toString() {
		return "SystemLog [opUser=" + opUser + ", opTime=" + opTime + ", opIp=" + opIp
				+ ", function=" + function + ", args=" + args + ", id=" + id + "]";
	}

}
