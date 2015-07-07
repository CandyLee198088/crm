package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.PotentialCustomerDevelopPlan;
import org.ssm.crm520.mapper.PotentialCustomerDevelopPlanMapper;
import org.ssm.crm520.service.IPotentialCustomerDevelopPlanService;

@Service
public class PotentialCustomerDevelopPlanServiceImpl extends BaseServiceImpl<PotentialCustomerDevelopPlan> implements
IPotentialCustomerDevelopPlanService {

	@Autowired
	public void setBaseMapper(PotentialCustomerDevelopPlanMapper potentialCustomerDevelopPlanMapper) {
		super.setBaseMapper(potentialCustomerDevelopPlanMapper);
	}

	
}
