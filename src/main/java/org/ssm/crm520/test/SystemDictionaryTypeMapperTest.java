package org.ssm.crm520.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.mapper.SystemDictionaryTypeMapper;
import org.ssm.crm520.page.SystemDictionaryTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemDictionaryTypeMapperTest {
	
	@Autowired
	private SystemDictionaryTypeMapper dao;
	
	@Test
	public void testname() throws Exception {
//		SystemDictionaryType type = new SystemDictionaryType();
//		type.setName("单位3");
//		type.setId(2L);
//		type.setIntro("xxxxxxxxxxxxxx");
//		type.setSn("crm1111");
//		dao.save(type);
//		dao.update(type);
//		System.out.println(dao.get(1L));
		
//		System.out.println(dao.getAll());
		
		SystemDictionaryTypeQuery query = new SystemDictionaryTypeQuery();
		query.setName("xx");
//		System.out.println(dao.findQuery(query));
		System.out.println(dao.findCount(query));
	}
}
