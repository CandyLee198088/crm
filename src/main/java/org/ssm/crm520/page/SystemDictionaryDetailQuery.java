package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author 严玉
 *
 */
public class SystemDictionaryDetailQuery extends BaseQuery {
	
	private String id;  //封装的是目录的id
	private String name; //字典明细名称  
	private Integer sn; //字典明细序号

	public String getName() {
		if (StringUtils.isNotBlank(name)) {
			return "%" + name + "%";
		}
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
