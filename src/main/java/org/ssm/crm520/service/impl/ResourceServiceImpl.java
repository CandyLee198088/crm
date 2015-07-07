package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.Resource;
import org.ssm.crm520.mapper.ResourceMapper;
import org.ssm.crm520.service.IResourceService;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements
		IResourceService {

	@Autowired
	public void setBaseMapper(ResourceMapper resourceMapper) {
		super.setBaseMapper(resourceMapper);
	}

}
