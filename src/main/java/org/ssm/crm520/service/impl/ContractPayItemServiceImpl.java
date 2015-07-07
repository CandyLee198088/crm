package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.ContractPayItem;
import org.ssm.crm520.mapper.ContractPayItemMapper;
import org.ssm.crm520.service.IContractPayItemService;

@Service
public class ContractPayItemServiceImpl extends BaseServiceImpl<ContractPayItem> implements IContractPayItemService{
	
	@Autowired
	public void setBaseMapper(ContractPayItemMapper contractPayItemMapper) {
		super.setBaseMapper(contractPayItemMapper);
	}
}
