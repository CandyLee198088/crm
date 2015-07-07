package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClientDevelopPlan extends IdEntity {

	
	private static final long serialVersionUID = 1L;
	private String sn;
	private Date planTime;
	private String summary;
	private String content;
	private SystemDictionaryDetail execWay;
	private PotentialClient pClient;
	private Employee createStaff;
	private Date createTime = new Date();
	public String getSn() {
		return sn;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
	public Date getPlanTime() {
		return planTime;
	}
	public String getSummary() {
		return summary;
	}
	public String getContent() {
		return content;
	}
	public SystemDictionaryDetail getExecWay() {
		return execWay;
	}
	public PotentialClient getpClient() {
		return pClient;
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
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setExecWay(SystemDictionaryDetail execWay) {
		this.execWay = execWay;
	}
	public void setpClient(PotentialClient pClient) {
		this.pClient = pClient;
	}
	public void setCreateStaff(Employee createStaff) {
		this.createStaff = createStaff;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ClientDevelopPlan [sn=" + sn + ", planTime=" + planTime + ", summary=" + summary
				+ ", content=" + content + ", execWay=" + execWay + ", pClient=" + pClient
				+ ", createStaff=" + createStaff + ", createTime=" + createTime + ", id=" + id
				+ "]";
	}
}
