package org.ssm.crm520.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.CustomerTrace;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.SystemDictionaryDetail;
import org.ssm.crm520.page.CustomerTraceQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerTraceService;

public class CustomerTraceServiceTest extends BaseServiceTest{
	@Autowired
	private ICustomerTraceService customerTraceService;
	
	@Test
	public void testCreateTable() {
		customerTraceService.createTable();
	}
	@Test
	public void testSave() {
		for (int i = 0; i < 2; i++) {
			CustomerTrace t=new CustomerTrace();
			t.setDate(new Date());
			t.setRemark("remark");
			t.setTheme("theme");
			t.setTraceResult(0);
			
			Customer customer=new Customer();
			customer.setId(1L);
			t.setCustomer(customer);
			
			SystemDictionaryDetail traceType=new SystemDictionaryDetail();
			traceType.setId(2L);
			t.setTraceType(traceType);
			
			Employee traceUser=new Employee();
			traceUser.setId(2L);
			t.setTraceUser(traceUser);
			
			customerTraceService.save(t);
			
		}
	}
	@Test
	public void testDelete() {
		customerTraceService.delete(8L);
	}

	@Test
	public void testUpdate() {
		CustomerTrace t=new CustomerTrace();
		t.setId(8L);
		t.setDate(new Date());
		t.setRemark("remark00");
		t.setTheme("theme00");
		t.setTraceResult(1);
		
		Customer customer=new Customer();
		customer.setId(3L);
		t.setCustomer(customer);
		
		SystemDictionaryDetail traceType=new SystemDictionaryDetail();
		traceType.setId(3L);
		t.setTraceType(traceType);
		
		Employee traceUser=new Employee();
		traceUser.setId(3L);
		t.setTraceUser(traceUser);
		
		customerTraceService.update(t);
		
	}

	@Test
	public void testGet() {
		CustomerTrace customerTrace = customerTraceService.get(1L);
		System.out.println(customerTrace);
	}

	@Test
	public void testGetAll() {
		List<CustomerTrace> list = customerTraceService.getAll();
		for (CustomerTrace customerTrace : list) {
			System.out.println(customerTrace);
		}
	}

	@Test
	public void testFindByQuery() {
		CustomerTraceQuery query=new CustomerTraceQuery();
//		query.setKeyword("remark1");
//		query.setKeyword("王科长");
		query.setKeyword("短信");
		PageResult<CustomerTrace> pageResult = customerTraceService.findByQuery(query);
		List<CustomerTrace> list = pageResult.getObjs();
		for (CustomerTrace customerTrace : list) {
			System.out.println(customerTrace);
		}
		System.out.println(pageResult.getTotalCount());
		
	}
	
}
