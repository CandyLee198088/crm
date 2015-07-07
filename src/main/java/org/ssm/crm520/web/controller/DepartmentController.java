package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Department;
import org.ssm.crm520.page.DepartmentQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IDepartmentService;
import org.ssm.crm520.util.AjaxResult;
/**
 * 部门的控制器
 * @author 张高强
 *
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private IDepartmentService   departmentService;
	
	@RequestMapping("/json")
	@ResponseBody
	public Map<String,Object> json(DepartmentQuery query) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<Department> results = departmentService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index(){
		return "department/department";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Department department){
		if(department!=null&&department.getId()==null){
			departmentService.save(department);
			return new AjaxResult("保存成功!");
		}else if(department!=null&&department.getId()!=null){
			departmentService.update(department);
		}
		return new AjaxResult("更新成功!");
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long id){
		AjaxResult ajaxResult=new AjaxResult();
		//有关联对象时不能删除
		try {
			departmentService.delete(id);
		} catch (Exception e) {
			ajaxResult.setSuccess(false);
			ajaxResult.setMessage("有关联对象,不能删除!");
			return ajaxResult;
		}
		ajaxResult.setMessage("删除成功!");
		return ajaxResult;
	}


	@RequestMapping("/list")
	@ResponseBody
	public List<Department> list(){
		return departmentService.getAll();
	}

}
