package org.ssm.crm520.util;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.service.ICustomerService;

@Component
public class ProcessEndListener implements ExecutionListener {

	private static final long serialVersionUID = 1L;
	@Autowired
	public ProcessEndListener(ICustomerService customerService){
		ProcessEndListener.customerService = customerService;
	}
	public ProcessEndListener(){}
	private static ICustomerService customerService;
	
	@Override
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("ProcessEndListener.notify()");
		Long id = (Long) execution.getVariable("objId");
		Customer customer = customerService.get(id);
		customer.setStatus(2);
		customerService.update(customer);
		
	}

}
