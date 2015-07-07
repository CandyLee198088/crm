package org.ssm.crm520.page;

import java.util.Date;

public class SystemLogQuery extends BaseQuery {
	private Long opUserId;
	private Date minOpTime;
	private Date maxOpTime;
	private String opIp;
	private String function;
	private String args;

	public Long getOpUserId() {
		return opUserId;
	}


	public String getOpIp() {
		return likeQuery(opIp);
	}

	public String getFunction() {
		return likeQuery(function);
	}

	public String getArgs() {
		return likeQuery(args);
	}

	public void setOpUserId(Long opUserId) {
		this.opUserId = opUserId;
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


	public Date getMinOpTime() {
		return minOpTime;
	}


	public Date getMaxOpTime() {
		return maxOpTime;
	}


	public void setMinOpTime(Date minOpTime) {
		this.minOpTime = minOpTime;
	}


	public void setMaxOpTime(Date maxOpTime) {
		this.maxOpTime = maxOpTime;
	}

}
