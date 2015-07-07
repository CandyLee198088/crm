<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
<%@include file="/WEB-INF/views/base.jsp"%>
<script type="text/javascript" src="/js/custom/employee.js"></script>
</head>
<body>
		<div id="employee"></div>
	<!-- 对话框组件 -->
		<div id="roleEmployeeDlg" style="width: 800px; height: 400px;">
			<form id="employeeForm" method="post" style="display: none;">
				<table align="center" style="margin-top: 10px; width: 100%">
					<tr>
						<td><input type="hidden" name="id" id="id" /></td>
						<td>员工账号:</td>
						<td><input id="username" name="username" type="text"
							readonly="readonly" /></td>
						<td>员工姓名:</td>
						<td><input id="truename" name="truename" type="text"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td colspan="5">
							<div style="float: left; width: 380px; height: 270px;">
								<table id="employee_grid1"></table>
							</div>
							<div style="float: right; width: 380px; height: 270px;">
								<table id="employee_grid2" style="float: right;"></table>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
<!-- 	</div> -->
</body>
</html>