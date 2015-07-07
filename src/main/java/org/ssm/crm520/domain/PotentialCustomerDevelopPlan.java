package org.ssm.crm520.domain;

import java.util.Date;

/**
 * 潜在客户开发计划类
 * @author Administrator
 *
 */
public class PotentialCustomerDevelopPlan extends IdEntity{
	private static final long serialVersionUID = 1L;
	private Date planTime;//计划时间
	private String theme;//计划主题
	private String content;//计划内容
	private SystemDictionaryType implementWay;//计划实施方式
	private PotentialCustomer probabilityCustomer;//机会客户
	private Employee createMan;//创建人
	private Date createTime = new Date();//创建时间
	
	public Date getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SystemDictionaryType getImplementWay() {
		return implementWay;
	}
	public void setImplementWay(SystemDictionaryType implementWay) {
		this.implementWay = implementWay;
	}
	public PotentialCustomer getProbabilityCustomer() {
		return probabilityCustomer;
	}
	public void setProbabilityCustomer(PotentialCustomer probabilityCustomer) {
		this.probabilityCustomer = probabilityCustomer;
	}
	public Employee getCreateMan() {
		return createMan;
	}
	public void setCreateMan(Employee createMan) {
		this.createMan = createMan;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "PotentialCustomerDevelopPlan [planTime=" + planTime
				+ ", theme=" + theme + ", content=" + content
				+ ", implementWay=" + implementWay + ", probabilityCustomer="
				+ probabilityCustomer + ", createMan=" + createMan
				+ ", createTime=" + createTime + ", id=" + id + "]";
	}
}
