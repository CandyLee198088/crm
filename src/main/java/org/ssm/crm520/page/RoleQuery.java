package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;

public class RoleQuery extends BaseQuery {
	private String name;
	private String sn;
	public String likeQuery(String str) {
		if (StringUtils.isNotBlank(str)) {
			return "%" + str + "%";
		}
		return null;
	}

	public String getName() {
		return likeQuery(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return likeQuery(sn);
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
}
