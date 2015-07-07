package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Contract;
import org.ssm.crm520.mapper.ContractMapper;
import org.ssm.crm520.service.IContractService;

@Service
public class ContractServiceImpl extends BaseServiceImpl<Contract> implements IContractService{
	
	@Autowired
	public void setBaseMapper(ContractMapper contractMapper) {
		super.setBaseMapper(contractMapper);
	}
}
