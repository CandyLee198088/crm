package org.ssm.crm520.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.mapper.DepositOrderMapper;
import org.ssm.crm520.page.DepositOrderQuery;
import org.ssm.crm520.service.IDepositOrderService;

@Service
public class DepositOrderServiceImpl extends BaseServiceImpl<DepositOrder> implements IDepositOrderService{
	
	private DepositOrderMapper depositOrderMapper;
	
	@Autowired
	public void setBaseMapper(DepositOrderMapper depositOrderMapper) {
		super.setBaseMapper(depositOrderMapper);
		
		this.depositOrderMapper = depositOrderMapper;
	}

	@Override
	public List<Object[]> getGroupBy(DepositOrderQuery query) {
		String groupBy = query.getGroupBy();
		Assert.notNull(groupBy, "分组条件不能为空");
		//此处有无查询条件都一样,由xml去判断
		List<Object[]> list = new ArrayList<Object[]>();
		List<Map<String, Object>> resultMap = depositOrderMapper.getGroupBy(query);
		for (Map<String, Object> map : resultMap) {
			Object[] objects = new Object[2];
			objects[0] = map.get("groupName");
			objects[1] = map.get("num");
			list.add(objects);
		}
		return list;
	}

	@Override
	public List<DepositOrder> getListBy(DepositOrderQuery query,
			Object resultValue) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		map.put("resultValue", resultValue);
		
		return depositOrderMapper.getListBy(map);
	}
}
