package org.ssm.crm520.domain;

import java.util.Date;

/**
 * 潜在客户类
 * @author luoyuhai
 *
 */
public class PotentialCustomer extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SystemDictionaryDetail source;//来源
	private String  name;//潜在客户名称
	private Integer odds;//成功几率
	private String description;//客户描述
	private String linkman;//联系人
	private String tel;//联系人电话
	private Employee createMan;//创建人
	private Date createTime = new Date();//创建时间
	public SystemDictionaryDetail getSource() {
		return source;
	}
	public void setSource(SystemDictionaryDetail source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOdds() {
		return odds;
	}
	public void setOdds(Integer odds) {
		this.odds = odds;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
		return "PotentialCustomer [source=" + source + ", name=" + name
				+ ", odds=" + odds + ", description=" + description
				+ ", linkman=" + linkman + ", tel=" + tel + ", createMan="
				+ createMan + ", createTime=" + createTime + ", id=" + id + "]";
	}

}
