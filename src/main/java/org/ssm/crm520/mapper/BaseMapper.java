package org.ssm.crm520.mapper;

import java.util.List;

import org.ssm.crm520.page.IBaseQuery;
/**
 * 映射接口的基接口
 * 
 * @author 李璨
 *
 * @param 模型对象;
 */
public interface BaseMapper<T> {

	void createTable();

	void save(T t);

	void delete(Long id);

	void update(T t);

	T get(Long id);

	List<T> getAll();
	/**
	 * 高级查询查询集合
	 * @param query 查询条件
	 * @return 符合条件的集合
	 */
	List<T> findQuery(IBaseQuery query);
	/**
	 * 高级查询查询符合条件的数量
	 * @param query 查询条件
	 * @return 数量
	 */
	Long findCount(IBaseQuery query);
}
