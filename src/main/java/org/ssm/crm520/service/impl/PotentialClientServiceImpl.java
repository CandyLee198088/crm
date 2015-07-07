package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.PotentialClient;
import org.ssm.crm520.mapper.PotentialClientMapper;
import org.ssm.crm520.service.IPotentialClientService;

@Service
public class PotentialClientServiceImpl extends BaseServiceImpl<PotentialClient> implements
		IPotentialClientService {

	@Autowired
	public void setBaseMapper(PotentialClientMapper potentialClientMapper) {
		super.setBaseMapper(potentialClientMapper);
	}

}
