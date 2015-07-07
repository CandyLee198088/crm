package org.ssm.crm520.service;

import java.util.List;

import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.page.DepositOrderQuery;

public interface IDepositOrderService extends IBaseService<DepositOrder> {
	
	List<Object[]> getGroupBy(DepositOrderQuery query);
	
	List<DepositOrder> getListBy(DepositOrderQuery query,Object resultValue);
	
}
