package org.ssm.crm520.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.SystemDictionaryDetail;
import org.ssm.crm520.page.CustomerQuery;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.ICustomerService;

public class CustomerServiceImplTest extends BaseServiceTest{
	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void testCreateTable() {
		customerService.createTable();
	}
	@Test
	public void testSave() {
		Customer customer=new Customer();
		customer.setName("namoooo");
		customer.setAge(10);
		customer.setGender(1);
		
		customer.setTel("393");
		customer.setEmail("email");
		customer.setQq("qq");
		customer.setWeChat("wechat");
		customer.setTime(new Date());
		customer.setStatus(0);
		
		SystemDictionaryDetail job=new SystemDictionaryDetail();
		job.setId(2L);
		customer.setJob(job);
		
		SystemDictionaryDetail salaryLevel=new SystemDictionaryDetail();
		salaryLevel.setId(3L);
		customer.setSalaryLevel(salaryLevel);
		
		SystemDictionaryDetail source=new SystemDictionaryDetail();
		source.setId(4L);
		customer.setSource(source);
		
		Employee seller=new Employee();
		seller.setId(2L);
		customer.setSeller(seller);
		
		customerService.save(customer);
	}
	@Test
	public void testDelete() {
		customerService.delete(18L);
	}

	@Test
	public void testUpdate() {
		Customer customer=new Customer();
		
		customer.setId(18L);
		customer.setName("name100");
		customer.setAge(100);
		customer.setGender(10);
		
		customer.setTel("39300");
		customer.setEmail("email00");
		customer.setQq("qq00");
		customer.setWeChat("wechat00");
		customer.setTime(new Date());
		
		SystemDictionaryDetail job=new SystemDictionaryDetail();
		job.setId(3L);
		customer.setJob(job);
		
		SystemDictionaryDetail salaryLevel=new SystemDictionaryDetail();
		salaryLevel.setId(4L);
		customer.setSalaryLevel(salaryLevel);
		
		SystemDictionaryDetail source=new SystemDictionaryDetail();
		source.setId(5L);
		customer.setSource(source);
		
		Employee seller=new Employee();
		seller.setId(6L);
		customer.setSeller(seller);
		
		customerService.update(customer);
	}

	@Test
	public void testGet() {
		Customer customer = customerService.get(1L);
		System.out.println(customer);
	}

	@Test
	public void testGetAll() {
		List<Customer> list = customerService.getAll();
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

	@Test
	public void testFindByQuery() {
		CustomerQuery query=new CustomerQuery();
		query.setKeyword("作废");
		PageResult<Customer> pageResult = customerService.findByQuery(query);
		List<Customer> objs = pageResult.getObjs();
		for (Customer customer : objs) {
			System.out.println(customer);
		}
		System.out.println(pageResult.getTotalCount());
		
	}

}
