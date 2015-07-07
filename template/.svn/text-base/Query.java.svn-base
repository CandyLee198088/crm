package org.ssm.crm520.page;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ${domain}Query extends BaseQuery {
	private String name;

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

}
