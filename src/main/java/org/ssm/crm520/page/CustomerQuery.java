package org.ssm.crm520.page;

public class CustomerQuery extends BaseQuery{

	private Integer status;//按照状态查询
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
}
