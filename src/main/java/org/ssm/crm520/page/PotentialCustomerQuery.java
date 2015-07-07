package org.ssm.crm520.page;

import java.util.Date;

/**
 * 潜在客户查询的封装
 * 
 * @author 骆余海
 * 
 */
public class PotentialCustomerQuery extends BaseQuery {
	private Long source_id;// 客户来源ID
	private String name;// 潜在客户名称
	private Integer maxOdds;// 最大成功几率
	private Integer minOdds;// 最小成功几率
	private String linkman;// 联系人
	private String tel;// 联系人电话
	private Long createMan_id;// 创建人
	public Long getSource_id() {
		return source_id;
	}
	public String getName() {
		return likeQuery(name);
	}
	public Integer getMaxOdds() {
		return maxOdds;
	}
	public Integer getMinOdds() {
		return minOdds;
	}
	public String getLinkman() {
		return likeQuery(linkman);
	}
	public String getTel() {
		return likeQuery(tel);
	}
	public Long getCreateMan_id() {
		return createMan_id;
	}
	public Date getMaxCreateTime() {
		return maxCreateTime;
	}
	public Date getMinCreateTime() {
		return minCreateTime;
	}
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMaxOdds(Integer maxOdds) {
		this.maxOdds = maxOdds;
	}
	public void setMinOdds(Integer minOdds) {
		this.minOdds = minOdds;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setCreateMan_id(Long createMan_id) {
		this.createMan_id = createMan_id;
	}
	public void setMaxCreateTime(Date maxCreateTime) {
		this.maxCreateTime = maxCreateTime;
	}
	public void setMinCreateTime(Date minCreateTime) {
		this.minCreateTime = minCreateTime;
	}
	private Date maxCreateTime;// 最大创建时间
	private Date minCreateTime;// 最小创建时间


}
