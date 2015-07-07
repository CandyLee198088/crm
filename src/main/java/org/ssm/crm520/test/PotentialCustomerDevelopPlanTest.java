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
import org.ssm.crm520.domain.PotentialCustomerDevelopPlan;
import org.ssm.crm520.domain.SystemDictionaryType;
import org.ssm.crm520.service.IEmployeeService;
import org.ssm.crm520.service.IPotentialCustomerDevelopPlanService;
import org.ssm.crm520.service.IPotentialCustomerService;
import org.ssm.crm520.service.ISystemDictionaryTypeService;

public class PotentialCustomerDevelopPlanTest extends BaseServiceTest {
	@Autowired
	private IPotentialCustomerDevelopPlanService pcs;
	@Autowired
	private IEmployeeService employeeService ;
	@Autowired
	private ISystemDictionaryTypeService dictionaryTypeService;
	@Autowired
	private IPotentialCustomerService customerService;
	
	@Test
	public void save() {
		Employee employee = employeeService.get(3L);
		SystemDictionaryType dictionaryType = dictionaryTypeService.get(2L);
		PotentialCustomer customer = customerService.get(2L);
		PotentialCustomerDevelopPlan client = new PotentialCustomerDevelopPlan();
		client.setPlanTime(new Date());
		client.setCreateMan(employee);
		client.setContent("asdasdasd");
		client.setTheme("asdasd");
		client.setProbabilityCustomer(customer);
		client.setImplementWay(dictionaryType);
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
		Employee employee = employeeService.get(2L);
		SystemDictionaryType dictionaryType = dictionaryTypeService.get(3L);
		PotentialCustomer customer = customerService.get(3L);
		PotentialCustomerDevelopPlan client = new PotentialCustomerDevelopPlan();
		client.setPlanTime(new Date());
		client.setCreateMan(employee);
		client.setProbabilityCustomer(customer);
		client.setImplementWay(dictionaryType);
		client.setCreateTime(new Date());
		client.setId(2L);
		pcs.update(client);
	}

	@Test
	public void get() {
		System.out.println(pcs.get(2L));;
	}
	@Test
	public void getAll() {
		List<PotentialCustomerDevelopPlan> all = pcs.getAll();
		for (PotentialCustomerDevelopPlan potentialCustomerDevelopPlan : all) {
			System.out.println(potentialCustomerDevelopPlan);
		}
	}

}
