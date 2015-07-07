package org.ssm.crm520.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ssm.crm520.domain.Employee;
import org.ssm.crm520.domain.Function;
import org.ssm.crm520.service.IEmployeeService;
import org.ssm.crm520.service.IFunctionService;
import org.ssm.crm520.util.AjaxResult;
import org.ssm.crm520.util.UserContext;

/**
 * 登录控制器
 * @author 李璨
 * @since 2015.04.29
 *
 */
@Controller
@RequestMapping("/auth")
public class LoginAndLogoutController {
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IFunctionService functionService;
	
	@Autowired
	private UserContext userContext;
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResult checkLogin(String username, String password, boolean savePwd,
			boolean autoLogin, HttpSession session) {
		AjaxResult ajaxResult = new AjaxResult();
		Employee user = employeeService.checkLogin(username, password);
		if (user != null) {
			UserContext.setUser(user);
			List<Function> functions = functionService.getFunctionsByEmployee(user);
			//把用户所有权限放入userContext
			UserContext.setUserFunctions(functions);
			//把userContext放入session
			UserContext.setUserContextInSession(userContext);
		} else {
			ajaxResult.setMessage("用户名或者密码错误!");
			ajaxResult.setSuccess(false);
		}
		return ajaxResult;
	}
	@RequestMapping("/logout")
	public void logout(HttpSession session,HttpServletResponse response) throws IOException{
		UserContext.removeUser();
		response.sendRedirect("/login.jsp");
	}
}
