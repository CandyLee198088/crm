package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerTransHistory extends IdEntity{
	private static final long serialVersionUID = 1L;
	private Customer customer;//客户
	private Employee transUser;//移交人员
	
	private Date transTime;//移交时间
	private Employee oldSeller;//原营销人员
	private Employee newSeller;//新营销人员
	private String reason;//移交原因
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getTransUser() {
		return transUser;
	}
	public void setTransUser(Employee transUser) {
		this.transUser = transUser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getTransTime() {
		return transTime;
	}
	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}
	public Employee getOldSeller() {
		return oldSeller;
	}
	public void setOldSeller(Employee oldSeller) {
		this.oldSeller = oldSeller;
	}
	public Employee getNewSeller() {
		return newSeller;
	}
	public void setNewSeller(Employee newSeller) {
		this.newSeller = newSeller;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "CustomerTransHistory [customer=" + customer + ", transUser="
				+ transUser + ", transTime=" + transTime + ", oldSeller="
				+ oldSeller + ", newSeller=" + newSeller + ", reason=" + reason
				+ ", id=" + id + "]";
	}
	
	

}
