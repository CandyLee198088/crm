package org.ssm.crm520.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssm.crm520.domain.WarrantyDetail;
import org.ssm.crm520.mapper.WarrantyDetailMapper;
import org.ssm.crm520.service.IWarrantyDetailService;

@Service
public class WarrantyDetailServiceImpl extends BaseServiceImpl<WarrantyDetail> implements
		IWarrantyDetailService {
	private WarrantyDetailMapper warrantyDetailMapper;
	@Autowired
	public void setBaseMapper(WarrantyDetailMapper warrantyDetailMapper) {
		super.setBaseMapper(warrantyDetailMapper);
		this.warrantyDetailMapper = warrantyDetailMapper;
	}

	public List<WarrantyDetail> findByParent(Long id) {
		return warrantyDetailMapper.findByParent(id);
	}

	@Override
	public List<Object[]> findGroupByMonth() {
		List<Object[]> lists = new ArrayList<>(); 
		 List<Map<String, Object>> list = warrantyDetailMapper.findGroupByMonth();
		 for (Map<String, Object> map : list) {
			Object[] objs = new Object[2];
			objs[0] = map.get("time");
			objs[1] = map.get("number");
			lists.add(objs);
		}
		 return lists;
	}

}
