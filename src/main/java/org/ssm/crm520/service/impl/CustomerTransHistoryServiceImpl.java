package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.CustomerTransHistory;
import org.ssm.crm520.mapper.CustomerTransHistoryMapper;
import org.ssm.crm520.service.ICustomerTransHistoryService;

@Service
public class CustomerTransHistoryServiceImpl extends BaseServiceImpl<CustomerTransHistory> implements ICustomerTransHistoryService{

	@Autowired
	public void setBaseMapper(CustomerTransHistoryMapper customerTransHistoryMapper) {
		super.setBaseMapper(customerTransHistoryMapper);
	}

}
