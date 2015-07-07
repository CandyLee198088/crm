package org.ssm.crm520.service;

import java.util.List;

import org.ssm.crm520.domain.WarrantyDetail;

public interface IWarrantyDetailService extends IBaseService<WarrantyDetail> {

	List<WarrantyDetail> findByParent(Long id);
	List<Object[]> findGroupByMonth();
}
