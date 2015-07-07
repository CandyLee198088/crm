<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link id="easyui-link" rel="stylesheet" type="text/css" href="/js/easyui/themes/${not empty sessionScope.theme?sessionScope.theme:'default'}/easyui.css" />
<link rel="stylesheet" type="text/css" href="/css/IconExtension.css" />
<link rel="stylesheet" type="text/css" href="/js/easyui/themes/icon.css" />
<script type="text/javascript" src="/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui/datagrid-detailview.js"></script>
<script type="text/javascript" src="/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/custom/crud_common.js"></script>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

input,select {
	width: 160px;
}
li{
	margin: 5px auto;
}
textarea{
	font-size: 12px;
}
input[readonly]{
	border:none;
	background-color: #fff;
}
</style>