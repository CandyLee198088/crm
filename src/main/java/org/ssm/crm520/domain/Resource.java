package org.ssm.crm520.domain;

public class Resource extends IdEntity{
	private static final long serialVersionUID = 1L;
	private String name; //资源名称
	private String resourceAddr;//资源地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResourceAddr() {
		return resourceAddr;
	}
	public void setResourceAddr(String resourceAddr) {
		this.resourceAddr = resourceAddr;
	}
	@Override
	public String toString() {
		return "Resource [name=" + name + ", resourceAddr=" + resourceAddr
				+ "]";
	}
}
