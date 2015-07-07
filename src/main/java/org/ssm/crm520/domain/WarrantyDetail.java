package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WarrantyDetail extends IdEntity {

	private static final long serialVersionUID = 1L;
	private Date repairTime;
	private String summary;
	private Integer status = 0;//0未解决,1已经解决;
	private WarrantyReceipts receipt;

	public Date getRepairTime() {
		return repairTime;
	}

	public String getSummary() {
		return summary;
	}
	
	public Integer getStatus() {
		return status;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public WarrantyReceipts getReceipt() {
		return receipt;
	}

	public void setReceipt(WarrantyReceipts receipt) {
		this.receipt = receipt;
	}

	@Override
	public String toString() {
		return "WarrantyDetail [repairTime=" + repairTime + ", summary=" + summary + ", status="
				+ status + ", receipt=" + receipt + ", id=" + id + "]";
	}
}
