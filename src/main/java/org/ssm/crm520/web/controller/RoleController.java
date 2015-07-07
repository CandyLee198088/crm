package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Menu;
import org.ssm.crm520.domain.Role;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.RoleQuery;
import org.ssm.crm520.service.IRoleService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(RoleQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Role> results = roleService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "/role/list";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Role role) {
		Assert.notNull(role, "role不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (role.getId() == null) {
				roleService.save(role);  //此时角色接收前台传入包含权限属性的对象
			} else {
				roleService.update(role); //编辑角色时应该干掉以前中间表所关联的权限
			}
		} catch (Exception e) {
			e.printStackTrace();
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}
	@RequestMapping("/saveMenu")
	@ResponseBody
	public AjaxResult saveMenu(Role role) {
		Assert.notNull(role, "role不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (role.getId() == null) {
				roleService.save(role);  //此时角色接收前台传入包含权限属性的对象
			} else {
				List<Menu> menus = role.getMenus();
				role = roleService.get(role.getId());
				role.setMenus(menus);
				roleService.updateMenu(role); //编辑角色时应该干掉以前中间表所关联的权限
			}
		} catch (Exception e) {
			e.printStackTrace();
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		AjaxResult as = new AjaxResult();
		try {
			roleService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Role> list() {
		return roleService.getAll();
	}

}
