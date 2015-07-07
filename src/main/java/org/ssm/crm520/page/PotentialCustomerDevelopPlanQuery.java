
package org.ssm.crm520.page;

import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.SystemDictionaryDetail;
/**
 * 潜在客户根据计划封装
 * @author 骆余海
 *
 */
public class PotentialCustomerDevelopPlanQuery extends BaseQuery {
	private SystemDictionaryDetail source_id;// 客户来源ID
	private String name;//潜在客户名称
	private Integer odds;//成功几率
	/**
	 * @return the odds
	 */
	public Integer getOdds() {
		return odds;
	}
	private String linkman;//联系人
	private String tel;//联系人电话
	private Employee createMan_id;//创建人
	//根据客户来源查询
	public String getSource_id() {
		if (source_id != null) {
			return "%" + source_id + "%";
		}
		return null;
	}
	//根据客户名称查询
	public String getName() {
		if (name != null) {
			return "%" + name + "%";
		}
		return null;
	}
	//根据联系人查询
	public String getLinkman() {
		if (linkman != null) {
			return "%" + linkman + "%";
		}
		return null;
	}
	//根据客户电话查询
	public String getTel() {
		if (tel != null) {
			return "%" + tel + "%";
		}
		return null;
	}
	//根据创建人查询
	public String getCreateMan_id() {
		if (createMan_id != null) {
			return "%" + createMan_id + "%";
		}
		return null;
	}
	public void setSource_id(SystemDictionaryDetail source_id) {
		this.source_id = source_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOdds(Integer odds) {
		this.odds = odds;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
