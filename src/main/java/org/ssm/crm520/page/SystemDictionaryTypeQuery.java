package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;

/**
 * page为当前页,start为limit的第一个参数,见父类
 * @author 严玉
 *
 */
public class SystemDictionaryTypeQuery extends BaseQuery {

	private String name; //字典目录名称  
	private String sn; //字典目录编号
	private Integer status;//状态   1正常  -1停用

	public String getName() {
		if (StringUtils.isNotBlank(name)) {
			return "%" + name + "%";
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		if (StringUtils.isNotBlank(sn)) {
			return "%" + sn + "%";
		}
		return null;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SystemDictionaryTypeQuery [name=" + name + ", sn=" + sn
				+ ", status=" + status + "]";
	}
}
