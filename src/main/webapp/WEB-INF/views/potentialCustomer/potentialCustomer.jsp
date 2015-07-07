<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
	<script type="text/javascript">
		function empFormatter(v, r, i) {
			if (r) {
				return r.createMan.truename;
			}
		}
		function sydyFormatter(v, r, i) {
			if (r) {
				return r.source.intro;
			}
		}
	</script>
	<table id="potentialcustomerGrid" class="easyui-datagrid"
		title="潜在客户管理"
		data-options="
		fit:true, 
		url:'/potentialCustomer/query' 
     	,singleSelect:true 
		,pagination:true
 		,fitColumns:true 
 		,rownumbers:true 
		,toolbar:'#tbPcu', 
 		pageList:[5,10,20]  ">
		<thead>
			<tr>
				<th field="source_id" width="1" formatter="sydyFormatter">客户ID</th>
				<th field="name" width="1">客户名称</th>
				<th field="odds" width="1">成功几率</th>
				<th field="description" width="1">客户描述</th>
				<th field="linkman" width="1">联系人</th>
				<th field="tel" width="1">联系电话</th>
				<th field="createMan_id" width="1" formatter="empFormatter">创建人</th>
				<th field="createTime" width="1">创建时间</th>
			</tr>
		</thead>
	</table>
	<div id="tbPcu">
		<a class="easyui-linkbutton" plain="true" iconCls="icon-add"
			style="width: 60px; text-align: center" data-cmd="add">添加</a> <a
			class="easyui-linkbutton" plain="true" iconCls="icon-edit"
			style="width: 60px; text-align: center" data-cmd="edit">编辑</a> <a
			class="easyui-linkbutton" plain="true" iconCls="icon-remove"
			style="width: 60px; text-align: center" data-cmd="del">删除</a> 客户名称:<input
			id="name" type="text" align="right" style="width: 60px" /> 联系人:<input
			id="linkman" type="text" align="right" style="width: 60px" /> 联系电话:<input
			id="tel" type="text" align="right" style="width: 80px" /> 创建人:<input
			id="createMan_id" type="text" align="right" style="width: 80px" />
		创建时间:<input id="minCreateTime" type="text" align="right"
			style="width: 30px" />至<input id="maxCreateTime" type="text"
			align="right" style="width: 30px" /> 成功率:<input id="minOdds"
			type="text" align="right" style="width: 30px" />至<input id="maxOdds"
			type="text" align="right" style="width: 30px" /> <a
			class="easyui-linkbutton" style="width: 80px; text-align: center;"
			plain="true" iconCls="icon-search" data-cmd="deptAdvance">查询</a>
	</div>
	<!-- 编辑页面 -->
	<div id="pcmDlg" class="easyui-dialog"
		style="width: 300px; height: 230px;" title="潜在客户编辑|保存框" closed="true"
		modal="true" buttons="#tbPC">
		<form id="pcmform" method="post">
			<table align="center" style="margin-top: 10px;">
				<tr>
					<td>客户名称：</td>
					<td><input name="name" type="text" class="easyui-validatebox"
						required="true" /></td>
				</tr>
				<tr>
					<td>成功几率：</td>
					<td>
						<input name="odds" class="easyui-numberbox"
						data-options="min:17,max:150,required:true">
					</td>
				</tr>
				<tr>
					<td>成功几率：</td>
					<td>
						<input name="description" class="easyui-numberbox"
						data-options="min:17,max:150,required:true">
					</td>
				</tr>
	</div>
</body>
</html>