package org.ssm.crm520.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class CustomerTrace extends  IdEntity{
	
	/**
	 * 客户跟进
	 */
	private static final long serialVersionUID = 1L;
	
	private Customer customer;//跟进的客户
	private Employee traceUser;//跟进人
	private Date date;//跟进时间
	
	private SystemDictionaryDetail traceType;//跟进方式   邀约上门、电话、短信、邮件等
	private Integer traceResult;//跟进的效果   1为好  0为中  -1为差 
	private String theme;//跟进的主题
	private String remark;//备注      跟进的细节，如：QQ聊天记录等
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getTraceUser() {
		return traceUser;
	}
	public void setTraceUser(Employee traceUser) {
		this.traceUser = traceUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SystemDictionaryDetail getTraceType() {
		return traceType;
	}
	public void setTraceType(SystemDictionaryDetail traceType) {
		this.traceType = traceType;
	}
	public Integer getTraceResult() {
		return traceResult;
	}
	public void setTraceResult(Integer traceResult) {
		this.traceResult = traceResult;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "CustomerTrace [customer=" + customer + ", traceUser="
				+ traceUser + ", date=" + date + ", traceType=" + traceType
				+ ", traceResult=" + traceResult + ", theme=" + theme
				+ ", remark=" + remark + ", id=" + id + "]";
	}

	
	
}
