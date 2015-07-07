package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.PotentialCustomer;
import org.ssm.crm520.mapper.PotentialCustomerMapper;
import org.ssm.crm520.service.IPotentialCustomerService;

@Service
public class PotentialCustomerServiceImpl extends BaseServiceImpl<PotentialCustomer> implements
IPotentialCustomerService {

	@Autowired
	public void setBaseMapper(PotentialCustomerMapper potentialCustomerMapper) {
		super.setBaseMapper(potentialCustomerMapper);
	}

}
