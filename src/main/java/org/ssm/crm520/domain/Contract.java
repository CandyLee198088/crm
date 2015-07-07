package org.ssm.crm520.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 合同
 * @author 严玉
 *
 */
public class Contract extends IdEntity{
	private static final long serialVersionUID = 1L;
	private String sn = "c"+new Date().getTime(); //合同单号
	private Date sginDate;//签订时间
	private BigDecimal contractAmount; //合同金额
	private String intro; //合同摘要
	private Employee saler;//营销人员
	private Customer customer;//合同客户
	private List<ContractPayItem> items = new ArrayList<ContractPayItem>(); //合同付款明细
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getSginDate() {
		return sginDate;
	}
	public void setSginDate(Date sginDate) {
		this.sginDate = sginDate;
	}
	public Employee getSaler() {
		return saler;
	}
	public void setSaler(Employee saler) {
		this.saler = saler;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public BigDecimal getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
	public List<ContractPayItem> getItems() {
		return items;
	}
	public void setItems(List<ContractPayItem> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Contract [sn=" + sn + ", sginDate=" + sginDate
				+ ", contractAmount=" + contractAmount + ", intro=" + intro
				+ ", saler=" + saler + ", customer=" + customer + ", items="
				+ items + "]";
	}
	
}
