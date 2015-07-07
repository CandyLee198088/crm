package org.ssm.crm520.service.impl;

import java.util.List;

import org.ssm.crm520.mapper.BaseMapper;
import org.ssm.crm520.page.IBaseQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IBaseService;
/**
 * serviceImpl的父类实现类,所有serviceImpl应该继承此类
 * @author 李璨
 *
 * @param 对应的实体模型;
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	private BaseMapper<T> baseMapper;
	
	public void setBaseMapper(BaseMapper<T> baseMapper){
		this.baseMapper = baseMapper;
	};
	
	@Override
	public void createTable() {
		baseMapper.createTable();

	}

	@Override
	public void save(T t) {
			baseMapper.save(t);
	}

	@Override
	public void delete(Long id) {
		baseMapper.delete(id);

	}

	@Override
	public void update(T t) {
		baseMapper.update(t);

	}

	@Override
	public T get(Long id) {
		return baseMapper.get(id);
	}

	@Override
	public List<T> getAll() {
		return baseMapper.getAll();
	}
	@Override
	public PageResult<T> findByQuery(IBaseQuery query){
		List<T> objs = baseMapper.findQuery(query);
		Long totalCount = baseMapper.findCount(query);
		return new PageResult<>(objs, totalCount);
	}

	

}
