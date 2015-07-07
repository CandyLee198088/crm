package org.ssm.crm520.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.mapper.FunctionMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FunctionMapperTest {
	@Autowired
	private FunctionMapper dao;
	
	@Test
	public void testname() throws Exception {
		String resourceName = "xxx";
		System.out.println(dao.getResourceByResourceName(resourceName));
	}
}
