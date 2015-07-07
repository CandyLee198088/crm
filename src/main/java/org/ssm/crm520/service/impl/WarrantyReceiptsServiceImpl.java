package org.ssm.crm520.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.WarrantyReceipts;
import org.ssm.crm520.mapper.WarrantyReceiptsMapper;
import org.ssm.crm520.service.IWarrantyReceiptsService;

@Service
public class WarrantyReceiptsServiceImpl extends BaseServiceImpl<WarrantyReceipts> implements
		IWarrantyReceiptsService {

	@Autowired
	public void setBaseMapper(WarrantyReceiptsMapper warrantyReceiptsMapper) {
		super.setBaseMapper(warrantyReceiptsMapper);
	}

}
