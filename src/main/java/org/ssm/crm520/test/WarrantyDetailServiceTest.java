package org.ssm.crm520.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.WarrantyDetail;
import org.ssm.crm520.domain.WarrantyReceipts;
import org.ssm.crm520.mapper.WarrantyDetailMapper;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.WarrantyDetailQuery;
import org.ssm.crm520.service.IWarrantyDetailService;
import org.ssm.crm520.service.IWarrantyReceiptsService;

public class WarrantyDetailServiceTest extends BaseServiceTest {

	@Autowired
	private IWarrantyDetailService warrantyDetailService;
	@Autowired
	private IWarrantyReceiptsService warrantyReceiptsService;
	@Autowired
	private WarrantyDetailMapper warrantyDetailMapper;

	@Test
	public void testCreateTable() {
		warrantyDetailService.createTable();
	}

	@Test
	public void testQuery() throws Exception {
		WarrantyDetailQuery query = new WarrantyDetailQuery();
		PageResult<WarrantyDetail> result = warrantyDetailService.findByQuery(query);
		System.out.println(result.getTotalCount());

	}

	@Test
	public void testSave() throws Exception {
		List<WarrantyReceipts> receipts = warrantyReceiptsService.getAll();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			WarrantyDetail wd = new WarrantyDetail();
			wd.setReceipt(receipts.get(r.nextInt(receipts.size())));
			wd.setRepairTime(new Date());
			wd.setSummary("保修内容" + i);
			warrantyDetailService.save(wd);
		}
	}

	@Test
	public void testUpdate() throws Exception {
		WarrantyDetail detail = warrantyDetailService.get(1L);
		detail.setRepairTime(new Date());
		warrantyDetailService.update(detail);
	}

	@Test
	public void testDelete() throws Exception {
		warrantyDetailService.delete(1L);
	}

	@Test
	public void testDetail() throws Exception {
		 List<Object[]>  list = warrantyDetailService.findGroupByMonth();
		for (Object[] objects : list) {
			System.out.println(Arrays.asList(objects));
		}
	}
	@Test
	public void testDetail2() throws Exception {
		Object  list = warrantyDetailMapper.findGroupByMonth();
		System.out.println(list);
//		for (Object[] objects : list) {
//			System.out.println(Arrays.asList(objects));
//		}
	}
}
