<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="/WEB-INF/views/base.jsp"%>
<style type="text/css">
</style>
</head>
<body>
	<form id="taskDetailForm" method="post" >
		<table align="center" style="margin-top: 10px">
			<tr>
				<td><b>姓名:</b></td>
				<td><input name="name" /></td>
				<td><b>年龄:</b></td>
				<td><input name="age" /></td>
			</tr>
			<tr>
				<td><b>性别:</b></td>
				<td><input name="gender">
					</td>
				<td><b>电话:</b></td>
				<td><input name="tel"/></td>
			</tr>
			<tr>
				<td><b>邮箱:</b></td>
				<td><input name="email" /></td>
				<td><b>QQ:</b></td>
				<td><input name="qq"  /></td>
			</tr>
			<tr>
				<td><b>微信:</b></td>
				<td><input name="weChat"/></td>
				<td><b>客户来源:</b></td>
				<td><input  name="sourceName"
					style="width: 167px">
				</td>
			</tr>
			<tr>
				<td><b>客户职业:</b></td>
				<td><input  name="jobName"
					style="width: 167px">
				</td>
				<td><b>客户收入:</b></td>
				<td><input  name="salaryLevelName"
					style="width: 167px">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>