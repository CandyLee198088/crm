package org.ssm.crm520.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.SystemLog;
import org.ssm.crm520.page.PageResult;
import org.ssm.crm520.page.SystemLogQuery;
import org.ssm.crm520.service.ISystemLogService;
import org.ssm.crm520.test.BaseServiceTest;

public class SystemLogServiceTest extends BaseServiceTest {
	
	@Autowired
	private ISystemLogService systemLogService;
	@Test
	public void testCreateTable() {
		systemLogService.createTable();
	}
	@Test
	public void testQuery() throws Exception {
		SystemLogQuery query = new SystemLogQuery();
		PageResult<SystemLog> result = systemLogService.findByQuery(query);
		System.out.println(result.getTotalCount());
	}
	@Test
	public void testGet() throws Exception {
		systemLogService.createTable();
	}
	@Test
	public void testSave() throws Exception {
		Employee e = new Employee();
		e.setId(3L);
		SystemLog sl = new SystemLog();
		sl.setOpIp("192.168.1.12");
		sl.setArgs("safasd");
		sl.setOpTime(new Date());
		sl.setOpUser(e);
		systemLogService.save(sl);
	}
	@Test
	public void testUpdate() throws Exception {
		Employee e = new Employee();
		e.setId(3L);
		SystemLog sl = new SystemLog();
		sl.setOpIp("192.168.2.12");
		sl.setArgs("safasd");
		sl.setOpTime(new Date());
		sl.setOpUser(e);
		sl.setId(1L);
		systemLogService.update(sl);
	}

}
