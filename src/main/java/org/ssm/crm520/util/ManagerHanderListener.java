package org.ssm.crm520.util;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.service.IEmployeeService;
/**
 * 动态分配经理;
 * @author 李璨
 * @since 2015-5-16 下午2:49:16
 */
@Component
public class ManagerHanderListener implements TaskListener {

	
	private static final long serialVersionUID = 1L;
	
	private static  IEmployeeService employeeService; 
	public ManagerHanderListener(){}
	@Autowired
	public ManagerHanderListener(IEmployeeService employeeService) {
		ManagerHanderListener.employeeService = employeeService;
	}

	@Override
	public void notify(DelegateTask delegateTask) {
		Employee user  = UserContext.getUser();
		System.out.println(employeeService);
		Employee manager = employeeService.getManager(user.getId());
		delegateTask.setAssignee(manager.getUsername());
	}

}
