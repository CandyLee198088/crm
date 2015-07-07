package org.ssm.crm520.page;

import java.util.Date;

public class PotentialClientQuery extends BaseQuery {
	private String sn;
	private Long sourceId;
	private String name;
	private Integer minSuccessChance;//0到100的数字;
	private Integer maxSuccessChance;//0到100的数字;
	private String tel;
	private Long createStaffId;
	private Date minCreateTime;
	private Date maxCreateTime;

	public String getSn() {
		return likeQuery(sn);
	}

	public Long getSourceId() {
		return sourceId;
	}

	public String getName() {
		return likeQuery(name);
	}

	public Integer getMinSuccessChance() {
		return minSuccessChance;
	}

	public Integer getMaxSuccessChance() {
		return maxSuccessChance;
	}

	public String getTel() {
		return likeQuery(tel);
	}

	public Long getCreateStaffId() {
		return createStaffId;
	}

	public Date getMinCreateTime() {
		return minCreateTime;
	}

	public Date getMaxCreateTime() {
		return maxCreateTime;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMinSuccessChance(Integer minSuccessChance) {
		this.minSuccessChance = minSuccessChance;
	}

	public void setMaxSuccessChance(Integer maxSuccessChance) {
		this.maxSuccessChance = maxSuccessChance;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCreateStaffId(Long createStaffId) {
		this.createStaffId = createStaffId;
	}

	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}

	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}

}
