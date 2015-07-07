package org.ssm.crm520.util;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.SystemLog;
import org.ssm.crm520.service.ISystemLogService;

@Component
public class SystemLogUtil {
	@Autowired
	private ISystemLogService systemLogService;

	public void addLog(JoinPoint jp) {
		Object clz = jp.getTarget();
		if(clz instanceof ISystemLogService){
			return;
		}
		
		String clzName = clz.getClass().getName();
		String method = jp.getStaticPart().getSignature().getName();
		SystemLog sl = new SystemLog();
		Employee currentUser  = UserContext.getUser();
		sl.setOpUser(currentUser);
		sl.setOpIp(UserContext.getIp());
		sl.setOpTime(new Date());
		sl.setFunction(clzName+":"+method);
//		sl.setArgs(Arrays.asList(jp.getArgs())+"");
		systemLogService.save(sl);
		
	}
}
