package org.ssm.crm520.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.activiti.engine.RepositoryService;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.crm520.service.IWorkFlowService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class WorkFlowServiceTest {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Test
	public void testname() throws Exception {
		repositoryService.deleteDeployment("customer:1:4", true);
	}
	@Test
	public void test123() throws Exception {
		workFlowService.viewProcessPngCordinates(2316+"");
	}
	@Test
	public void test124() throws Exception {
		InputStream viewProcessPng = workFlowService.viewProcessPng("1401","CustomerFlow.png");
		IOUtils.copy(viewProcessPng, new FileOutputStream(new File("C:/124.PNG")));
	}
	@Test
	public void testTask() throws Exception {
		workFlowService.viewProcessPngCordinates(2308+"");
//		IOUtils.copy(viewProcessPng, new FileOutputStream(new File("C:/124.PNG")));
	}
}
