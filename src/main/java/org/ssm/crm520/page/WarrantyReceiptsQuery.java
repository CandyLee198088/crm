package org.ssm.crm520.page;

import java.util.Date;

public class WarrantyReceiptsQuery extends BaseQuery {
	private String sn;
	private Long contractId;
	private Date minCreateTime;
	private Date maxCreateTime;
	private Date minExpireTime;
	private Date maxExpireTime;


	public String getSn() {
		return likeQuery(sn);
	}

	public Long getContractId() {
		return contractId;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public Date getMaxCreateTime() {
		return maxCreateTime;
	}

	public Date getMinExpireTime() {
		return minExpireTime;
	}

	public Date getMaxExpireTime() {
		return maxExpireTime;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}

	public void setMinExpireTime(Date minExpireTime) {
		this.minExpireTime = minExpireTime;
	}

	public void setMaxExpireTime(Date maxExpireTime) {
		this.maxExpireTime = maxExpireTime;
	}

}
