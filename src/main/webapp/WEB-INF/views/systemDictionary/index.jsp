<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css">   
<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>   
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#right_style {
		border-right: none;
		border-bottom: none;
		border-top: none;
	} 

</style>
</head>
<body>
	<div class="easyui-layout" style="width:700px;height:350px;" border="false" fit="true">
		<div data-options="region:'west'" href="/systemDictionaryType/" border="false" style="width:550px;" fitColumns="true" ></div>
		<div data-options="region:'center'" href="/systemDictionaryDetail/" border="true" id="right_style" fitColumns="true" style="height:150px;" ></div>
	</div>
</body>
</html>