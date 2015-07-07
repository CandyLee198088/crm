package org.ssm.crm520.page;
/**
 * 查询对象封装的接口
 * @author 李璨
 *
 */
public interface IBaseQuery {
	Integer getPage();

	void setPage(Integer page);

	Integer getRows();

	void setRows(Integer rows);
}
