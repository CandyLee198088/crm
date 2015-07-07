package org.ssm.crm520.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.domain.Resource;
import org.ssm.crm520.service.IResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ResourceServiceTest {
//	@Autowired
//	private ResourceMapper dao;
//	
	@Autowired
	private IResourceService service;
	
	@Test
	public void testSave() throws Exception {
		Resource resource = new Resource();
//		resource.setId(2L);
		resource.setName("员工增加");
		resource.setResourceAddr("org.ssm.crm520.web.controller.ResourceController.list");
//		System.out.println(service);
//		service.save(resource);
		service.getAll();
//		dao.save(resource);
//		service.update(resource);
	}
}
