package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;

public class FunctionQuery extends BaseQuery {
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
