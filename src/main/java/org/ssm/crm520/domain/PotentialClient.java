package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PotentialClient extends IdEntity {

	private static final long serialVersionUID = 1L;
	private String sn;
	private SystemDictionaryDetail source;
	private String name;
	private Integer successChance;//0到100的数字;
	private String description;
	private String tel;
	private Employee createStaff;
	private Date createTime = new Date();

	public String getSn() {
		return sn;
	}

	public SystemDictionaryDetail getSource() {
		return source;
	}

	public String getName() {
		return name;
	}

	public Integer getSuccessChance() {
		return successChance;
	}

	public String getDescription() {
		return description;
	}

	public String getTel() {
		return tel;
	}

	public Employee getCreateStaff() {
		return createStaff;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public void setSource(SystemDictionaryDetail source) {
		this.source = source;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSuccessChance(Integer successChance) {
		this.successChance = successChance;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCreateStaff(Employee createStaff) {
		this.createStaff = createStaff;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PotentialClient [sn=" + sn + ", source=" + source + ", name=" + name
				+ ", successChance=" + successChance + ", description=" + description + ", tel="
				+ tel + ", createStaff=" + createStaff + ", createTime=" + createTime + ", id="
				+ id + "]";
	}
}
