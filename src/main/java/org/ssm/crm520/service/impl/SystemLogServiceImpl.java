package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.SystemLog;
import org.ssm.crm520.mapper.SystemLogMapper;
import org.ssm.crm520.service.ISystemLogService;

@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements
		ISystemLogService {

	@Autowired
	public void setBaseMapper(SystemLogMapper systemLogMapper) {
		super.setBaseMapper(systemLogMapper);
	}

}
