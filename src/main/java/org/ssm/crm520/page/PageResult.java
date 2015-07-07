package org.ssm.crm520.page;

import java.util.ArrayList;
import java.util.List;
/**
 * 高级查询结果集的对象封装
 * @author 李璨
 *
 * @param 对应的持久化对象Employee,Department等....
 */
public class PageResult<T> {
	/**
	 * 高级查询的对象集合,如employee的集合,department集合等;
	 */
	private List<T> objs = new ArrayList<T>();
	/**
	 * 高级查询符合条件的记录条数;
	 */
	private Long totalCount;

	public PageResult(List<T> objs, Long totalCount) {
		this.objs = objs;
		this.totalCount = totalCount;
	}

	public List<T> getObjs() {
		return objs;
	}

	public void setObjs(List<T> objs) {
		this.objs = objs;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
