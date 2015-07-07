package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.CustomerTrace;
import org.ssm.crm520.mapper.CustomerTraceMapper;
import org.ssm.crm520.service.ICustomerTraceService;

@Service
public class CustomerTraceServiceImpl extends BaseServiceImpl<CustomerTrace> implements ICustomerTraceService{

	@Autowired
	public void setBaseMapper(CustomerTraceMapper customerTraceMapper) {
		super.setBaseMapper(customerTraceMapper);
	}

}
