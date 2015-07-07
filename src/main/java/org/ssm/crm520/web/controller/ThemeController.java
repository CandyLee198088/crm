package org.ssm.crm520.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主题控制器
 * @author 李璨
 *
 */
@Controller
@RequestMapping("/theme")
public class ThemeController {
	@RequestMapping("/change")
	@ResponseBody
	public void change(String theme,HttpSession session) {
		session.setAttribute("theme", theme);
	}
}
