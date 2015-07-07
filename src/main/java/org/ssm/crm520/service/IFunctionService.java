package org.ssm.crm520.service;

import java.util.List;

import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;

public interface IFunctionService extends IBaseService<Function> {
	
	Function getResourceByFunctionName(String functionName);
	
	Function getResourceByResourceName(String resourceName);
	
	List<Function> getFunctionsByEmployee(Employee employee);
}
