package org.ssm.crm520.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.Contract;
import org.ssm.crm520.domain.ContractPayItem;
import org.ssm.crm520.page.ContractPayItemQuery;
import org.ssm.crm520.service.IContractPayItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ContractPayItemServiceTest {

	@Autowired
	private IContractPayItemService service;
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		ContractPayItem item = new ContractPayItem();
//		item.setId(3L);
		item.setPayAmount(new BigDecimal(10000));
		item.setPayDate(new Date());
		item.setPayProcent(new BigDecimal(0.2));
		item.setStatus(-1);
		Contract contract = new Contract();
		contract.setId(2L);
		item.setContract(contract);
		service.save(item);
//		service.update(item);
	}
	
	@Test
	public void testDelete() throws Exception {
		service.delete(3L);
	}
	
	@Test
	public void testGet() throws Exception {
//		System.out.println(service.get(1L));
		System.out.println(service.getAll());
	}
	
	@Test
	public void testQuery() throws Exception {
		ContractPayItemQuery query = new ContractPayItemQuery();
		query.setStatus(-1);
		System.out.println(service.findByQuery(query));
	}	
}
