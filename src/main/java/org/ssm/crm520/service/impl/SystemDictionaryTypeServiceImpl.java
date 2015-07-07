package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.SystemDictionaryType;
import org.ssm.crm520.mapper.SystemDictionaryTypeMapper;
import org.ssm.crm520.service.ISystemDictionaryTypeService;

@Service
public class SystemDictionaryTypeServiceImpl extends BaseServiceImpl<SystemDictionaryType>
		implements ISystemDictionaryTypeService {

	@Autowired
	public void setBaseMapper(SystemDictionaryTypeMapper systemDictionaryTypeMapper) {
		super.setBaseMapper(systemDictionaryTypeMapper);
	}

}
