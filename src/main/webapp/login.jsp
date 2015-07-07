<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="images/favicon.gif">
<title>LAZY客户关系管理系统-登录</title>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/css/IconExtension.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<style type="text/css">
tr {
	height: 40px;
}

td {
	color: #fff;
	font-family: "微软雅黑","Times New Roman"
}

tr td:FIRST-CHILD {
	text-align: right;
}
</style>
<script type="text/javascript">
	if(window!=window.top){
		window.top.location.href =window.location.href;
	}
</script>
</head>
<body style="background: url('/images/1.jpg');">

	<div align="center" style="margin-top: 150px;">
		<form id="loginForm">
			<table align="center">
				<tr>
					<td colspan="2" style="text-align: center;"><h2>CRM用户登录
							<h2></td>
				</tr>
				<tr>
					<td>账号:</td>
					<td><input type="text" name="username" id="username" style="font-family: Arial"></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="password" name="password" id="password"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input id="savePsw" type="checkbox" name="savePsw"><label
						for="savePsw">记住密码</label>&emsp; <input id="autoLogin" type="checkbox" name="autoLogin"><label
						for="autoLogin">自动登录</lable></td>
				</tr>
				<tr>
					<td></td>
					<td style="text-align: center;"><a class="easyui-linkbutton" iconCls="icon-ok" id="loginBtn">登录</a> <a
						class="easyui-linkbutton" iconCls="icon-reload" id="resetBtn">重置</a></td>
				</tr>
			</table>
		</form>
	</div>

	</div>
	<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#username").focus();
			$("#loginBtn").on('click', function() {
				checkLogin();
			});
			$("#password").on('keydown', function(e) {
				if (e.keyCode == 13) {
					checkLogin();
				}
			})
			function checkLogin() {
				var username = $("#username").val();
				var password = $("#password").val();
				var savePwd = $("#savePsw")[0].checked;
				var autoLogin = $("#autoLogin")[0].checked;
				// 				console.debug(username,password,savePwd,autoLogin);
				$.post("/auth/login", {
					username : username,
					password : password,
					savePwd : savePwd,
					autoLogin : autoLogin
				}, function(data) {
					if (data.success) {
						location.href = "/index";
					} else {
						$.messager.alert("温馨提示", data.message, 'error');
					}

				}, 'json');
			}
		});
	</script>
</body>
</html>

