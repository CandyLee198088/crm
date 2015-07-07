package org.ssm.crm520.service;

import java.util.List;

import org.ssm.crm520.page.IBaseQuery;
import org.ssm.crm520.page.PageResult;

/**
 * Service的父类接口
 * @author 李璨
 *
 * @param <T>
 */
public interface IBaseService<T> {
	/**
	 * 创建表
	 */
	void createTable();

	/**
	 * 保存实体
	 * @param department
	 */
	void save(T t);

	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * @param t
	 */
	void update(T t);

	/**
	 * 根据id获取唯一一个实体
	 * @param id id
	 * @return 查询结果
	 */
	T get(Long id);

	/**
	 * 查询所有实体
	 * @return 所有实体的结合
	 */
	List<T> getAll();

	/**
	 * 封装高级查询和分页
	 * @param query 封装查询的对应对象
	 * @return PageResult对象,封装了List集合和查询结果的数量;
	 */
	PageResult<T> findByQuery(IBaseQuery query);
}
