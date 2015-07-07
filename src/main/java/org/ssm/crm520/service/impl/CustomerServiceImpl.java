package org.ssm.crm520.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.mapper.CustomerMapper;
import org.ssm.crm520.service.ICustomerService;
import org.ssm.crm520.service.IWorkFlowService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService{
	@Autowired
	private IWorkFlowService workFlowService;
	
	@Autowired
	public void setBaseMapper(CustomerMapper customerMapper) {
		super.setBaseMapper(customerMapper);
	}

	@Override
	public void startProcess(Long id) {
		Customer customer = get(id);
		customer.setStatus(1);
		update(customer);
		//处理流程
		String className = customer.getClass().getSimpleName();
		//设置流程变量
		Map<String,Object> varsMap = new HashMap<String,Object>();
		varsMap.put("seller", customer.getSeller().getUsername());
		varsMap.put("classType", className);
		varsMap.put("objId", id);
		workFlowService.startProcess(className+"Flow",varsMap);
	}

}
