package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.WarrantyDetail;
import org.ssm.crm520.page.WarrantyDetailQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IWarrantyDetailService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/warrantyDetail")
public class WarrantyDetailController {

	@Autowired
	private IWarrantyDetailService warrantyDetailService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(WarrantyDetailQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<WarrantyDetail> results = warrantyDetailService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "warrantyDetail/warrantyDetail";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(WarrantyDetail warrantyDetail) {
		Assert.notNull(warrantyDetail, "warrantyDetail不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (warrantyDetail.getId() == null) {
				warrantyDetailService.save(warrantyDetail);
			} else {
				warrantyDetailService.update(warrantyDetail);
			}
		} catch (Exception e) {
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
			warrantyDetailService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<WarrantyDetail> list(Long id) {
		if(id==null){
			return warrantyDetailService.getAll();
		}else{
			return warrantyDetailService.findByParent(id);
		}
	}
	@RequestMapping("/charts")
	@ResponseBody
	public List<Object[]> charts() {
		return warrantyDetailService.findGroupByMonth();
	}
	@RequestMapping("/dispatch")
	public String dispatch() {
		return "warrantyDetail/charts2";
	}
	

}
