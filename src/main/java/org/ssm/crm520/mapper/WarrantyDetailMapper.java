package org.ssm.crm520.mapper;

import java.util.List;
import java.util.Map;

import org.ssm.crm520.domain.WarrantyDetail;

public interface WarrantyDetailMapper extends BaseMapper<WarrantyDetail> {

	List<WarrantyDetail> findByParent(Long id);

	List<Map<String,Object>> findGroupByMonth();

}
