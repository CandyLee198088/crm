package org.ssm.crm520.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.Contract;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.page.ContractQuery;
import org.ssm.crm520.service.IContractService;
import org.ssm.crm520.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ContractServiceTest {

	@Autowired
	private IContractService service;
	
	@Autowired
	private IEmployeeService eservice;
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		Contract contract = new Contract(); 
//		contract.setId(1L);
		contract.setSginDate(new Date());
		contract.setIntro("xxx");
		Employee saler = new Employee();
		saler.setId(2L);
		Customer customer = new Customer();
		customer.setId(2L);
		contract.setSaler(saler);
		contract.setCustomer(customer);
		contract.setContractAmount(new BigDecimal("3311223"));
//		ContractPayItem item = new ContractPayItem();
//		List<ContractPayItem> items = new ArrayList<ContractPayItem>();
//		items.add(e)
		service.save(contract);
//		service.update(contract);
	}
	
	@Test
	public void testDelete() throws Exception {
		service.delete(1L);
	}
	
	@Test
	public void testGet() throws Exception {
//		System.out.println(service.get(2L));
		System.out.println(service.getAll());
	}
	
	@Test
	public void testQuery() throws Exception {
		ContractQuery query = new ContractQuery();
		query.setSn("c2");
		Employee saler = eservice.get(2L);
		query.setSaler(saler.getTruename());
		System.out.println(service.findByQuery(query));
	}
}
