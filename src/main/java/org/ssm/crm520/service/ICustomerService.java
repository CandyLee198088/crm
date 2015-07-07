package org.ssm.crm520.service;

import org.ssm.crm520.domain.Customer;

public interface ICustomerService extends IBaseService<Customer> {

	void startProcess(Long id);
	
}
