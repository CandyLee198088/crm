package org.ssm.crm520.page;

import java.util.Date;

public class ClientDevelopPlanQuery extends BaseQuery {
	private String sn;
	private Date minPlanTime;
	private Date maxPlanTime;
	private String summary;
	private Long execWayId;
	private Long pClientId;
	private Long createStaffId;
	private Date minCreateTime;
	private Date maxCreateTime;

	public String getSn() {
		return likeQuery(sn);
	}

	public Date getMinPlanTime() {
		return minPlanTime;
	}

	public Date getMaxPlanTime() {
		return maxPlanTime;
	}

	public String getSummary() {
		return summary;
	}

	public Long getExecWayId() {
		return execWayId;
	}

	public Long getpClientId() {
		return pClientId;
	}

	public Long getCreateStaffId() {
		return createStaffId;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setMinPlanTime(Date minPlanTime) {
		this.minPlanTime = minPlanTime;
	}

	public void setMaxPlanTime(Date maxPlanTime) {
		this.maxPlanTime = maxPlanTime;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setExecWayId(Long execWayId) {
		this.execWayId = execWayId;
	}

	public void setpClientId(Long pClientId) {
		this.pClientId = pClientId;
	}

	public void setCreateStaffId(Long createStaffId) {
		this.createStaffId = createStaffId;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public Date getMaxCreateTime() {
		return maxCreateTime;
	}

	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}

}
