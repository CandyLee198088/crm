package org.ssm.crm520.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 定金订单
 * @author 严玉
 *
 */
public class DepositOrder extends IdEntity{
	private static final long serialVersionUID = 1L;
	private String sn = "d"+new Date().getTime(); //定金单号
	private Date sginDate;//签订时间
	private BigDecimal depositAmount; //定金金额
	private String intro; //摘要
	private Employee saler;//营销人员
	private Customer customer;//定金客户
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
	public BigDecimal getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
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
	@Override
	public String toString() {
		return "DepositOrder [sn=" + sn + ", sginDate=" + sginDate
				+ ", depositAmount=" + depositAmount + ", intro=" + intro
				+ ", saler=" + saler + ", customer=" + customer + "]";
	}
}
