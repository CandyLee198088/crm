package org.ssm.crm520.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.service.ISystemDictionaryDetailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemDictionaryDetailServiceTest {
	
	@Autowired
	private ISystemDictionaryDetailService service;
	
	@Test
	public void testname() throws Exception {
//		service.createTable();
//		SystemDictionaryDetail detail = new SystemDictionaryDetail();
//		detail.setIntro("sssss");
//		detail.setName("阿迪达斯");
//		detail.setSn(1);
//		SystemDictionaryType type = new SystemDictionaryType();
//		type.setId(1L);
//		detail.setTypes(type);
////		detail.setTypes(types);
		System.out.println(service.get(3L));
//		
////		System.out.println(dao.getAll());
//		
//		SystemDictionaryDetailQuery query = new SystemDictionaryDetailQuery();
//		query.setName("阿");
////		System.out.println(dao.findQuery(query));
//		System.out.println(service.findCount(query));
	}
}
