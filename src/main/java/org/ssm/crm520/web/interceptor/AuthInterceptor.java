package org.ssm.crm520.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.service.IFunctionService;
import org.ssm.crm520.util.UserContext;

/**
 * 权限拦截器,用于拦截登录和用户的其他权限
 * @author 李璨
 * @since 2015.04.29
 */
public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private IFunctionService functionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//登陆权限检查
		String uri = request.getRequestURI();
		Employee user = UserContext.getUser();
		if(user==null){
			if(!"/index".equals(uri)){
				request.getRequestDispatcher("/WEB-INF/views/login_alert.jsp").forward(request, response);
			}else{
				response.sendRedirect("/login.jsp");
			}
			return false;
		}
		//针对不同用户的具体权限验证
		
//	     1、 获取用户请求的资源(什么格式)  
		if(handler instanceof DefaultServletHttpRequestHandler){
			response.sendRedirect("/error.jsp");
			return false;
		}
		HandlerMethod hm = (HandlerMethod)handler;
		String controllerName = hm.getBean().getClass().getName();
		String resourceMethod = hm.getMethod().getName();
		String resourceName = controllerName+":"+resourceMethod;
//	     2、 检查这个资源是否需要权限控制
		Function function = functionService.getResourceByResourceName(resourceName);
		if(function==null){
			return true;
		}
//	     3、 如果需要权限控制，然后再检查当前登录用户上是否有这些权限
//	         1） 普通权限检查
//	         2） ALL 权限检查
		if(!UserContext.checkFunction(function)){
			if(request.getMethod().equals("GET")){
				request.getRequestDispatcher("/WEB-INF/views/noPermission.jsp").forward(request, response);
			}else{
				response.sendRedirect("/data/noFunction.json");
			}
			System.out.println("没有权限");
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {

	}

}
