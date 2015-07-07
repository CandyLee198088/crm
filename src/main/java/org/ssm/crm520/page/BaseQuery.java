package org.ssm.crm520.page;

import org.apache.commons.lang.StringUtils;
/**
 * 查询对象的基类
 * @author 李璨
 *
 */
public class BaseQuery implements IBaseQuery {
	private Integer page = 1;//当页的页码
	private Integer rows = 10;//每页显示的记录条数
	private String keyword;//关键字搜索
	private String sort;//排序字段
	private String order;//排序规则,desc asc

	/**
	 * limit分页的第一个参数;start
	 * @return
	 */
	public Integer getStart() {
		return (page - 1) * rows;
	}
	/**
	 * 模糊查询方法封装
	 * @param str
	 * @return
	 */
	protected String likeQuery(String str) {
		if (StringUtils.isNotBlank(str)) {
			return "%" + str + "%";
		}
		return null;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	//除去两边空格
	public String getKeyword() {
		if (StringUtils.isNotBlank(keyword)) {
			return "%" + keyword.trim() + "%";
		}
		return null;
	}
	
	public Integer getBeginPage() {
		return (page - 1) * rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getPage() {
		return page >= 1 ? page : 1;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows >= 1 ? rows : 1;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "BaseQuery [page=" + page + ", rows=" + rows + ", keyword="
				+ keyword + ", sort=" + sort + ", order=" + order + "]";
	}

}
