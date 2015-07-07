package org.ssm.crm520.mapper;

import java.util.List;
import java.util.Map;

import org.ssm.crm520.domain.Menu;

/**
 * 直接继承BaseMapper
 * @author Administrator
 *
 */
public interface MenuMapper extends BaseMapper<Menu> {
	List<Menu> getAllParentMenus();
	List<Menu> getAllParentMenusByUser(Long id);

	List<Menu> getChildrenMenus(Long id);
	List<Menu> getChildrenMenusByUser(Map<String,Long> map);
}
