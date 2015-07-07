<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css" />
<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>Alert Messager - jQuery EasyUI Demo</title>
</head>
<body>
	<script>
		$(function() {
			$.messager.alert('温馨提示', '您没有访问的权限!', 'info',function(){
				location.href = "/index";
			});
		});
	</script>
</body>
</html>