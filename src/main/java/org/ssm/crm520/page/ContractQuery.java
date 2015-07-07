package org.ssm.crm520.page;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * 合同高级查询条件的封装
 * @author 严玉
 *
 */
public class ContractQuery extends BaseQuery {
	private String sn;// 合同单号
	private Date minDate;// 最早签订时间
	private Date maxDate;// 最晚签订时间
	private String saler;//销售员姓名
	private String customer;// 客户姓名
	public String getSn() {
		if(StringUtils.isNotBlank(sn)){
			return "%"+sn+"%";
		}
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}
	public String getSaler() {
		if(StringUtils.isNotBlank(saler)){
			return "%"+saler+"%";
		}
		return saler;
	}
	public void setSaler(String saler) {
		this.saler = saler;
	}
	public String getCustomer() {
		if(StringUtils.isNotBlank(customer)){
			return "%"+customer+"%";
		}
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "DepositOrderQuery [sn=" + sn + ", minDate=" + minDate
				+ ", maxDate=" + maxDate + ", saler=" + saler + ", customer="
				+ customer + "]";
	}
	
}
