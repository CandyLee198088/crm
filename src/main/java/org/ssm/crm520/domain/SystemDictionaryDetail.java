package org.ssm.crm520.domain;

/**
 * 数据字典明细对象;
 * @author 严玉
 *
 */
public class SystemDictionaryDetail extends IdEntity {
	private static final long serialVersionUID = 1L;
	private String name; //字典明细名称  
	private Integer sn; //字典明细序号
	private String intro; //字典明细简介
	private SystemDictionaryType types;

	public String getName() {
		return name;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public SystemDictionaryType getTypes() {
		return types;
	}

	public void setTypes(SystemDictionaryType types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "SystemDictionaryDetail [name=" + name + ", sn=" + sn + ", intro=" + intro
				+ ", types=" + types + "]";
	}
}
