package org.ssm.crm520.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.WarrantyReceipts;
import org.ssm.crm520.page.WarrantyReceiptsQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.IWarrantyReceiptsService;
import org.ssm.crm520.util.AjaxResult;

@Controller
@RequestMapping("/warrantyReceipts")
public class WarrantyReceiptsController {

	@Autowired
	private IWarrantyReceiptsService warrantyReceiptsService;

	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> json(WarrantyReceiptsQuery query) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<WarrantyReceipts> results = warrantyReceiptsService.findByQuery(query);
		map.put("rows", results.getObjs());
		map.put("total", results.getTotalCount());
		return map;
	}

	@RequestMapping("/")
	public String index() {
		return "warrantyReceipts/warrantyReceipts";
	}

	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(WarrantyReceipts warrantyReceipts) {
		Assert.notNull(warrantyReceipts, "warrantyReceipts不能为空");
		AjaxResult as = new AjaxResult();
		try {
			if (warrantyReceipts.getId() == null) {
				warrantyReceiptsService.save(warrantyReceipts);
			} else {
				warrantyReceiptsService.update(warrantyReceipts);
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
			warrantyReceiptsService.delete(id);
		} catch (Exception e) {
			as.setSuccess(false);
			as.setMessage("操作失败!");
		}
		return as;
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<WarrantyReceipts> list() {
		return warrantyReceiptsService.getAll();
	}

}
