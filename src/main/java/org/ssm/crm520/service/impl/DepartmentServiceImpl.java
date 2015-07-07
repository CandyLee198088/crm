package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Department;
import org.ssm.crm520.mapper.DepartmentMapper;
import org.ssm.crm520.service.IDepartmentService;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements
		IDepartmentService {

	@Autowired
	public void setBaseMapper(DepartmentMapper departmentMapper) {
		super.setBaseMapper(departmentMapper);
	}

}
