package org.ssm.crm520.page;

import java.util.Date;

public class WarrantyDetailQuery extends BaseQuery {

	private Date minRepairTime;
	private Date maxRepairTime;
	private String summary;
	private Integer status;//0未解决,1已经解决;
	private Long receiptId;


	public Date getMinRepairTime() {
		return minRepairTime;
	}

	public Date getMaxRepairTime() {
		return maxRepairTime;
	}

	public String getSummary() {
		return likeQuery(summary);
	}

	public Integer getStatus() {
		return status;
	}

	public Long getReceiptId() {
		return receiptId;
	}

	public void setMinRepairTime(Date minRepairTime) {
		this.minRepairTime = minRepairTime;
	}

	public void setMaxRepairTime(Date maxRepairTime) {
		this.maxRepairTime = maxRepairTime;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

}
