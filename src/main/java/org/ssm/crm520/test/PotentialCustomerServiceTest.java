package org.ssm.crm520.test;

/**
 * 客户管理类测试
 */
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.PotentialCustomer;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.PotentialCustomerQuery;
import org.ssm.crm520.service.IEmployeeService;
import org.ssm.crm520.service.IPotentialCustomerService;

public class PotentialCustomerServiceTest extends BaseServiceTest {
	@Autowired
	private IPotentialCustomerService pcs;
	@Autowired
	private IEmployeeService employeeService ;
	@Test
	public void save() {
		Employee employee = employeeService.get(3L);
		PotentialCustomer client = new PotentialCustomer();
		client.setName("client");
		client.setOdds(100);
		client.setDescription("sdsdfsdf");
		client.setLinkman("potential");
		client.setTel("212331");
		client.setCreateMan(employee);
		client.setCreateTime(new Date());
		pcs.save(client);
	}
	@Test
	public void testname() throws Exception {
		pcs.createTable();
	}
	@Test
	public void delete() {
		pcs.delete(1L);
	}

	@Test
	public void update() {
		Employee employee = employeeService.get(3L);
		PotentialCustomer client = new PotentialCustomer();
		client.setId(1L);
		client.setName("client11111");
		client.setOdds(100);
		client.setDescription("sssss");
		client.setLinkman("ssss");
		client.setTel("11111");
		client.setCreateMan(employee);
		client.setCreateTime(new Date());
		pcs.update(client);
	}

	@Test
	public void get() {
		System.out.println(pcs.get(1L));;
	}
	@Test
	public void getAll() {
		List<PotentialCustomer> all = pcs.getAll();
		for (PotentialCustomer potentialCustomer : all) {
			System.out.println(potentialCustomer);
		}
	}
	@Test
	public void testQuery() throws Exception {
		PotentialCustomerQuery query = new PotentialCustomerQuery();
		PageResult<PotentialCustomer> result = pcs.findByQuery(query);
		for (PotentialCustomer customer : result.getObjs()) {
			System.out.println(customer);
		}
	}
}
