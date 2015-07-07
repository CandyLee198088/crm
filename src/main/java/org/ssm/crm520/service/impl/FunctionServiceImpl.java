package org.ssm.crm520.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.mapper.FunctionMapper;
import org.ssm.crm520.service.IFunctionService;

@Service
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements
		IFunctionService {
	
	private FunctionMapper functionMapper;
	
	@Autowired
	public void setBaseMapper(FunctionMapper functionMapper) {
		super.setBaseMapper(functionMapper);
		this.functionMapper = functionMapper;
	}

	@Override
	public Function getResourceByResourceName(String resourceName) {
		return functionMapper.getResourceByResourceName(resourceName);
	}

	@Override
	public List<Function> getFunctionsByEmployee(Employee employee) {
		return functionMapper.getFunctionsByEmployee(employee);
	}

	@Override
	public Function getResourceByFunctionName(String functionName) {
		return functionMapper.getResourceByFunctionName(functionName);
	}

}
