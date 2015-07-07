package org.ssm.crm520.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 合同付款明细
 * @author 严玉
 *
 */
public class ContractPayItem extends IdEntity{
	private static final long serialVersionUID = 1L;
	private Date payDate;//付款时间
	private BigDecimal payAmount; //付款金额
	private BigDecimal payProcent;//付款百分比   
	private Integer status;//付款状态  1:付款完成   0:正在付款   -1:未付款    
	private Contract contract;//所属合同
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getPayProcent() {
		return payProcent;
	}
	public void setPayProcent(BigDecimal payProcent) {
		this.payProcent = payProcent;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	@Override
	public String toString() {
		return "ContractPayItem [payDate=" + payDate + ", payAmount="
				+ payAmount + ", payProcent=" + payProcent + ", status="
				+ status + ", contract=" + contract + "]";
	}
}
