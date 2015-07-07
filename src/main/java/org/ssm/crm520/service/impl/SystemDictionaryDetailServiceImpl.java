package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.SystemDictionaryDetail;
import org.ssm.crm520.mapper.SystemDictionaryDetailMapper;
import org.ssm.crm520.service.ISystemDictionaryDetailService;

@Service
public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail>
		implements ISystemDictionaryDetailService {


	@Autowired
	public void setBaseMapper(SystemDictionaryDetailMapper systemDictionaryDetailMapper) {
		super.setBaseMapper(systemDictionaryDetailMapper);
	}

}
