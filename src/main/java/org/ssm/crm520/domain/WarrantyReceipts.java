package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WarrantyReceipts extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String sn;
	private Contract contract;
	private Date createTime;
	private Date expireTime;
	private Customer customer;

	public String getSn() {
		return sn;
	}

	public Contract getContract() {
		return contract;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getExpireTime() {
		return expireTime;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "WarrantyReceipts [sn=" + sn + ", contract=" + contract + ", createTime="
				+ createTime + ", expireTime=" + expireTime + ", customer=" + customer + ", id="
				+ id + "]";
	}




}
