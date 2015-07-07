package org.ssm.crm520.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.SystemDictionaryType;
import org.ssm.crm520.mapper.SystemDictionaryTypeMapper;
import org.ssm.crm520.page.SystemDictionaryTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemDictionaryTypeServiceTest {
	
	@Autowired
	private SystemDictionaryTypeMapper dao;
	
	@Test
	public void testname() throws Exception {
		SystemDictionaryType type = new SystemDictionaryType();
		type.setName("尺寸1");
		type.setId(3L);
		type.setIntro("xxxxxxxxxxxxxx");
		type.setSn("crm1111");
//		dao.update(type);
//		dao.delete(4L);
//		System.out.println(dao.get(1L));
		
//		System.out.println(dao.getAll());
		
		SystemDictionaryTypeQuery query = new SystemDictionaryTypeQuery();
		query.setName("单");
//		query.setSn("c");
//		query.setPage(1);
//		query.setRows(2);
//		query.setKeyword("xx");
		query.setKeyword("单");
		query.setSn("cr");
		System.out.println(dao.findQuery(query));
//		System.out.println(dao.findCount(query));
	}
}
