package org.ssm.crm520.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典类型对象;
 * @author 严玉
 *
 */
public class SystemDictionaryType extends IdEntity {
	private static final long serialVersionUID = 1L;
	private String sn; //字典目录编号
	private String name; //字典目录名称
	private String intro; //字典目录简介
	private Integer status = 1;//状态   1正常  -1停用
	private List<SystemDictionaryDetail> details = new ArrayList<SystemDictionaryDetail>();//一对多  一个目录有多个明细
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	public List<SystemDictionaryDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SystemDictionaryDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "SystemDictionaryType [sn=" + sn + ", name=" + name + ", intro="
				+ intro + ", status=" + status + ", details=" + details + "]";
	}
}
