package org.ssm.crm520.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.SystemDictionaryDetail;
import org.ssm.crm520.domain.SystemDictionaryType;
import org.ssm.crm520.mapper.SystemDictionaryTypeMapper;
import org.ssm.crm520.page.SystemDictionaryDetailQuery;
import org.ssm.crm520.page.SystemDictionaryTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemDictionaryDetailDaoTest {
	
	@Autowired
	private SystemDictionaryTypeMapper dao;
	
	@Test
	public void testname() throws Exception {
//		dao.createTable();
		SystemDictionaryDetail detail = new SystemDictionaryDetail();
		detail.setId(2L);
		detail.setIntro("xxxxxx");
		detail.setName("耐克");
		detail.setSn(1);
		SystemDictionaryType type = new SystemDictionaryType();
		type.setId(1L);
		detail.setTypes(type);
//		dao.save(detail);
//		dao.delete(1L);
//		dao.update(detail);
//		System.out.println(dao.get(2L));
//		
//		System.out.println(dao.getAll());
//		
		SystemDictionaryDetailQuery query = new SystemDictionaryDetailQuery();
		query.setSort("sn");
		query.setOrder("desc");
		query.setRows(1);
		query.setKeyword("耐");
//		query.setName("耐");
		System.out.println(dao.findQuery(query));
//		System.out.println(dao.findCount(query));
	}
	@Test
	public void testQuery() throws Exception {
		SystemDictionaryTypeQuery query = new SystemDictionaryTypeQuery();
		List<SystemDictionaryType> findQuery = dao.findQuery(query);
		for (SystemDictionaryType detail : findQuery) {
			System.out.println(detail);
			
		}
	}
}
