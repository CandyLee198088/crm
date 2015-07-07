package org.ssm.crm520.mapper;

import java.util.List;

import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;

public interface FunctionMapper extends BaseMapper<Function> {

	Function getResourceByResourceName(String resourceName);

	List<Function> getFunctionsByEmployee(Employee employee);

	Function getResourceByFunctionName(String functionName);

}
