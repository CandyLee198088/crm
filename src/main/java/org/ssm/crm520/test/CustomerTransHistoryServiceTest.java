package org.ssm.crm520.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.CustomerTransHistory;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.page.CustomerTransHistoryQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerTransHistoryService;

public class CustomerTransHistoryServiceTest extends BaseServiceTest{
	@Autowired
	private ICustomerTransHistoryService  customerTransHistoryService;
	
	@Test
	public void testCreateTable() {
		customerTransHistoryService.createTable();
	}
	@Test
	public void testSave() {
		for (int i = 0; i < 6; i++) {
			CustomerTransHistory t=new CustomerTransHistory();
			t.setReason("reason");
			t.setTransTime(new Date());
			
			Customer customer=new Customer();
			customer.setId(1L);
			t.setCustomer(customer);
			
			Employee transUser=new Employee();
			transUser.setId(2L);
			t.setTransUser(transUser);
			
			Employee oldSeller=new Employee();
			oldSeller.setId(3L);
			t.setOldSeller(oldSeller);
			
			Employee newSeller=new Employee();
			newSeller.setId(6L);
			t.setNewSeller(newSeller);
			
			customerTransHistoryService.save(t);
			
		}
	}
	@Test
	public void testDelete() {
		customerTransHistoryService.delete(12L);
	}

	@Test
	public void testUpdate() {
		CustomerTransHistory t=new CustomerTransHistory();
		t.setId(11L);
		t.setReason("reason00");
		t.setTransTime(new Date());
		
		Customer customer=new Customer();
		customer.setId(3L);
		t.setCustomer(customer);
		
		Employee transUser=new Employee();
		transUser.setId(7L);
		t.setTransUser(transUser);
		
		Employee oldSeller=new Employee();
		oldSeller.setId(8L);
		t.setOldSeller(oldSeller);
		
		Employee newSeller=new Employee();
		newSeller.setId(9L);
		t.setNewSeller(newSeller);
		
		customerTransHistoryService.update(t);
		
	}

	@Test
	public void testGet() {
		CustomerTransHistory customerTransHistory = customerTransHistoryService.get(1L);
		System.out.println(customerTransHistory);
	}

	@Test
	public void testGetAll() {
		List<CustomerTransHistory> list = customerTransHistoryService.getAll();
		for (CustomerTransHistory customerTransHistory : list) {
			System.out.println(customerTransHistory);
		}
	}

	@Test
	public void testFindByQuery() {
		CustomerTransHistoryQuery query=new CustomerTransHistoryQuery();
//		query.setKeyword("reason1");
//		query.setKeyword("李书记");
		query.setKeyword("骆余海");
		PageResult<CustomerTransHistory> pageResult = customerTransHistoryService.findByQuery(query);
		List<CustomerTransHistory> list = pageResult.getObjs();
		for (CustomerTransHistory customerTransHistory : list) {
			System.out.println(customerTransHistory);
		}
		System.out.println(pageResult.getTotalCount());
		
	}
	
}
