package org.ssm.crm520.util;

import java.util.List;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.service.IFunctionService;

/**
 * 用户上下文,封装操作用户的常用方法;
 * @author 李璨
 * @since2015.04.29
 *
 */
public class UserContext {
	
	private static IFunctionService functionService;
	
	public void setFunctionService(IFunctionService functionService) {
		UserContext.functionService = functionService;
	}
	
	/**
	 *定义全局字段;
	 */
	public static final String USER_IN_SESSION = "userInSession";
	public static final String FUNCTION_IN_SESSION = "functionInsession";
	public static final String USERCONTEXT_IN_SESSION = "usercontextInsession";

	/**
	 * 从session中获取当前session中的用户;
	 * @param session 当前的session
	 * @return
	 */
	public static Employee getUser() {
		//获取全局session
		if(RequestContextHolder.getRequestAttributes()!=null){
			return (Employee)  ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getSession().getAttribute(USER_IN_SESSION);
		}else{
			return new Employee();
		}
	}

	/**
	 * 设置user到当前的session中
	 * @param user 被设置的用户对象
	 * @param session 当前session
	 */
	public static void setUser(Employee user) {
		 ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getSession().setAttribute(USER_IN_SESSION, user);
	}

	public static void removeUser() {
		 ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getSession().removeAttribute(USER_IN_SESSION);
	}
	public static String getIp(){
		if(RequestContextHolder.getRequestAttributes()!=null){
			return ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getRemoteAddr();
		}else{
			return "no ip detected";
		}
	}
	//模块权限控制方法
	public static boolean checkFunction(String functionName){
		
		System.out.println("xxxxxxxxxxxxxxxxx");
		Function function = functionService.getResourceByFunctionName(functionName);
		System.out.println(function);
		
		if(function==null){
			return true;
		}
		return UserContext.checkFunction(function);
	}
	
	
	public static boolean checkFunction(Function function){
		//普通权限
		List<Function> userFunctions = UserContext.getUserFunctions();
		System.out.println(userFunctions);
		if(userFunctions==null||userFunctions.isEmpty()||userFunctions.get(0)==null){
			return false;
		}
		
		for (Function f : userFunctions) {
			System.out.println(f);
			System.out.println(function);
			if(f.getId().equals(function.getId())){
				return true;
			}
		}
		
		System.out.println("普通不等");
		//all权限控制
		String resourceName = function.getResourceAddr().split(":")[0]+":ALL";
		Function allFunction = functionService.getResourceByResourceName(resourceName);
		System.out.println(allFunction);
		if(allFunction==null){
			return false;
		}
		
		for (Function f : userFunctions) {
			if(f.getId().equals(allFunction.getId())){
				return true;
			}
		}
		
		return false;
	}
	
	
	//把用户权限绑定到session中
	public static void setUserFunctions(List<Function> functions){
		 ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getSession().setAttribute(FUNCTION_IN_SESSION, functions);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Function> getUserFunctions(){
		return (List<Function>) ((ServletRequestAttributes) (RequestContextHolder
					.getRequestAttributes())).getRequest().getSession().getAttribute(FUNCTION_IN_SESSION);
	}
	
	//将userContext放入session中
	public static void setUserContextInSession(UserContext userContext){
		((ServletRequestAttributes) (RequestContextHolder
				.getRequestAttributes())).getRequest().getSession().setAttribute(USERCONTEXT_IN_SESSION,userContext);
	}
}
