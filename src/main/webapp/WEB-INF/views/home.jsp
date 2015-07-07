<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="/images/favicon.gif">
<title>LAZY客户关系管理系统</title>
	<%@include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
	<div style="display: none" id="loginUsername">${sessionScope.userInSession.username}</div>
	<div style="display: none" id="loginTrueName">${sessionScope.userInSession.truename}</div>
	<div style="display: none" id="loginUserId">${sessionScope.userInSession.id}</div>
	<div style="display: none" id="themeFromSession">${sessionScope.theme}</div>
	<script type="text/javascript" src="/js/custom/index.js"></script>
</body>
</html>