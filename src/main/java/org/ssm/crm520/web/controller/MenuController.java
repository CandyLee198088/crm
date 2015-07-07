package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Menu;
import org.ssm.crm520.page.MenuQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IMenuService;
import org.ssm.crm520.util.AjaxResult;
import org.ssm.crm520.util.UserContext;

/**
 * 菜单的控制器
 * @author 李璨
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	private IMenuService menuService;

	/**
	 * 用于导航;
	 * @return 地址;menu.jsp
	 */
	@RequestMapping("/")
	public String index() {
		return "menu/menu";
	}

	/**
	 * 查询符合条件的menu;
	 * @param query 查询条件
	 * @return 结果拼成的map集合;
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> list(MenuQuery query) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Menu> results = menuService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;

	}

	/**
	 * 查询所有的父类菜单;
	 * @return 父类菜单;
	 */
	@RequestMapping("/findParents")
	@ResponseBody
	public List<Menu> findParents() {
		Employee user  =UserContext.getUser();
		if("admin".equals(user.getUsername())){
			return menuService.getAllParentMenus();
		}
		return menuService.getAllParentMenus(user);
	}

	/**
	 * 根据父类菜单的id查询此id下面所有的子菜单;
	 * @param id 父类菜单的id;
	 * @return 所有子类菜单的集合;
	 */
	@RequestMapping("/findChild")
	@ResponseBody
	public List<Menu> findById(Long id) {
		Employee user  =UserContext.getUser();
		if("admin".equals(user.getUsername())){
			return menuService.getChildrenMenus(id);
		}
		return menuService.getChildrenMenus(id,user);
	}

	/**
	 * 保存menu
	 * @param menu 被保存的实体
	 * @return 封装保存提示信息的集合,保存/更新成功或者失败!
	 */
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Menu menu) {
		Assert.notNull(menu, "role不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (menu.getId() == null) {
				menuService.save(menu);
			} else {
				menuService.update(menu);
			}
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	/**
	 * 删除menu
	 * @param id 被删除menu 的id;
	 * @return 删除成功或者失败的信息集合,最后返回到前台成功json;
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult as = new AjaxResult();
		try {
			menuService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

}
