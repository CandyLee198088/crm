package org.ssm.crm520.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.page.DepositOrderQuery;
import org.ssm.crm520.service.IDepositOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepositeOrderServiceTest {
	
	@Autowired
	private IDepositOrderService service;
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		DepositOrder depositOrder = new DepositOrder();
		depositOrder.setId(5L);
		depositOrder.setSginDate(new Date());
		depositOrder.setDepositAmount(new BigDecimal("1111"));
		depositOrder.setIntro("intro");
		Employee saler = new Employee();
		saler.setId(2L);
		Customer customer = new Customer();
		customer.setId(2L);
		depositOrder.setSaler(saler);
		depositOrder.setCustomer(customer);
//		service.save(depositOrder);
		service.update(depositOrder);
	}
	
	@Test
	public void testDelete() throws Exception {
		service.delete(6L);
	}
	
	@Test
	public void testGet() throws Exception {
		System.out.println(service.get(1L));
//		System.out.println(service.getAll());
	}
	
	@Test
	public void testQuery() throws Exception {
		DepositOrderQuery query = new DepositOrderQuery();
//		query.setSn("d");
//		query.setSaler("严玉");
//		query.setCustomer("习大大");
//		System.out.println(dao.findQuery(query));
		query.setGroupBy("emp.truename");
//		System.out.println(service.findByQuery(query));
		
		List<Object[]> groupBy = service.getGroupBy(query);
		for (Object[] objects : groupBy) {
			List<DepositOrder> listBy = service.getListBy(query,objects[0]);
			for (DepositOrder depositOrder : listBy) {
				System.out.println(objects[0]+":"+depositOrder);
			}
		}
	}
	
}
