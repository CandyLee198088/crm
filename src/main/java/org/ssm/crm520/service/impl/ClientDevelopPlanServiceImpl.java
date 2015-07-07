package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.ClientDevelopPlan;
import org.ssm.crm520.mapper.ClientDevelopPlanMapper;
import org.ssm.crm520.service.IClientDevelopPlanService;

@Service
public class ClientDevelopPlanServiceImpl extends BaseServiceImpl<ClientDevelopPlan> implements
		IClientDevelopPlanService {

	@Autowired
	public void setBaseMapper(ClientDevelopPlanMapper clientDevelopPlanMapper) {
		super.setBaseMapper(clientDevelopPlanMapper);
	}

}
