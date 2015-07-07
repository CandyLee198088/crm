package org.ssm.crm520.mapper;

import java.util.List;
import java.util.Map;

import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.page.DepositOrderQuery;

public interface DepositOrderMapper extends BaseMapper<DepositOrder>{

	List<Map<String, Object>> getGroupBy(DepositOrderQuery query);
	
	List<DepositOrder> getListBy(Map<String, Object> map);
}
