package org.ssm.crm520.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.${domain};
import org.ssm.crm520.page.${domain}Query;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.service.I${domain}Service;
import org.ssm.crm520.test.BaseServiceTest;

public class ${domain}ServiceTest extends BaseServiceTest {
	
	@Autowired
	private I${domain}Service ${domainLower}Service;
	@Test
	public void testCreateTable() {
		${domainLower}Service.createTable();
	}
	@Test
	public void testQuery() throws Exception {
		${domain}Query query = new ${domain}Query();
		PageResult<${domain}> result = ${domainLower}Service.findByQuery(query);
		System.out.println(result.getTotalCount());
		
	}

}
