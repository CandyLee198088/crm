package org.ssm.crm520.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.Customer;
import org.ssm.crm520.domain.DepositOrder;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.mapper.DepositOrderMapper;
import org.ssm.crm520.mapper.EmployeeMapper;
import org.ssm.crm520.page.DepositOrderQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepositeOrderMapperTest {
	
	@Autowired
	private DepositOrderMapper dao;
	
	@Autowired
	private EmployeeMapper edao;
	@Test
	public void testSaveOrUpdate() throws Exception {
		DepositOrder depositOrder = new DepositOrder();
		depositOrder.setId(4L);
		depositOrder.setSginDate(new Date());
		depositOrder.setDepositAmount(new BigDecimal("3333333"));
		depositOrder.setIntro("introssssssssss");
		Employee saler = edao.get(3L);
		Customer customer = new Customer();
		customer.setId(2L);
		depositOrder.setSaler(saler);
		depositOrder.setCustomer(customer);
//		dao.save(depositOrder);
		dao.update(depositOrder);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(4L);
	}
	
	@Test
	public void testGet() throws Exception {
		System.out.println(dao.get(1L));
//		System.out.println(dao.getAll());
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		DepositOrderQuery query = new DepositOrderQuery();
		query.setGroupBy("emp.truename");
//		query.setSn("d");
//		query.setSaler("严玉");
//		query.setCustomer("习大大");
//		System.out.println(dao.findQuery(query));
		
//		System.out.println(dao.findCount(query));
		List<Map<String, Object>> groupBy = dao.getGroupBy(query);
		System.out.println(groupBy);
		Map<String, Object> map = new HashMap<>();
		map.put("query", query);
		map.put("resultValue", groupBy.get(2).get("groupName"));
		
		List<DepositOrder> lists = dao.getListBy(map);
		for (DepositOrder depositOrder : lists) {
			System.out.println(depositOrder);
		}
		
	}
}
