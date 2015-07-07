package org.ssm.crm520.page;

import java.util.Date;
/**
 * 
 * 合同付款明细高级查询条件的封装
 * @author 严玉
 *
 */
public class ContractPayItemQuery extends BaseQuery {
	private Long id;// 明细所依赖的合同id号
	private Date minPayDate;// 最早付款时间
	private Date maxPayDate;// 最晚付款时间
	private Integer status; //付款状态
	public Date getMinPayDate() {
		return minPayDate;
	}
	public void setMinPayDate(Date minPayDate) {
		this.minPayDate = minPayDate;
	}
	public Date getMaxPayDate() {
		return maxPayDate;
	}
	public void setMaxPayDate(Date maxPayDate) {
		this.maxPayDate = maxPayDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ContractPayItemQuery [minPayDate=" + minPayDate
				+ ", maxPayDate=" + maxPayDate + ", status=" + status + "]";
	}
}
