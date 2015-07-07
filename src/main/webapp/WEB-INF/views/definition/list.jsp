<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="/WEB-INF/views/base.jsp"%>
</head>
<script type="text/javascript">
	$(function() {
		var deployeDlg = $("#deployeDlg"); 
		var pngDlg = $("#pngDlg"); 
		deployeDlg.dialog({
			width : 300,
			height : 150,
			modal:true,
			title : "流程部署",
			iconCls:"icon-ok",
			closed : true,
			buttons : [ {
				text : "上传",
				iconCls : "icon-ok",
				handler : function() {
					deployeDlg.dialog('close');
					$("#deployForm").form('submit', {
						url : "/definition/upload",
						method:"post",
						success : function(data) {
							data = $.parseJSON(data);
							$.messager.alert("温馨提示", data.message,'info',function(){
								listTable.datagrid("reload");
							});
						}
					})
				}
			}, {
				text : "取消",
				iconCls : "icon-cancel",
				handler:function(){
					deployeDlg.dialog('close');
				}
			} ]
		});
	
		pngDlg.dialog({
			width : 1000,
			height : 500,
			iconCls:"icon-ok",
			modal:true,
			title : "流程图",
			closed : true,
		});
		var listTable = $("#deployeList");
		listTable.datagrid({
			title : '流程部署管理',
			singleSelect : true,
			fit : true,
			url : "/definition/list",
			method : 'post',
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				text:"部署流程",
				iconCls:"icon-ok",
				handler:function(){
					deployeDlg.dialog('open');
				}
			},{
				text:"删除流程",
				iconCls:"icon-remove",
				handler:function(){
					var rowData = listTable.datagrid("getSelected");
					if(rowData){
						$.messager.confirm("温馨提示","是否确定删除这条流程?",function(yes){
							if(yes){
								$.post('/definition/delete',{deploymentId:rowData.deploymentId},function(data){
									$.messager.alert("温馨提示",data.message,"info",function(){
										listTable.datagrid("reload");
									});
								});
							}
						});
					}else{
						$.messager.alert("温馨提示","请选中后再删除!","info");
					}
				}
			}, {
				text:"查看流程图",
				iconCls:"icon-search",
				handler:function(){
					var rowData = listTable.datagrid('getSelected');
					if(rowData ){
						pngDlg.dialog('open');
						viewPng(rowData.deploymentId,rowData.diagramResourceName);
						//$.post("/definition/viewPng",{deploymentId:rowData.deploymentId,resourceName:rowData.diagramResourceName});
					}else{
						$.messager.alert("温馨提示","请选中一行后再查看",'info');
					}
				}
			}, {
				text:"刷新",
				iconCls:"icon-reload",
				handler:function(){
					listTable.datagrid("load");
				}
			} ],
			border : false,
			pageList : [ 5, 10, 15, 20, 30, 50 ],
			collapsible : true,
			columns : [ [ {
				title:"流程ID",
				field:"id",
				width:10
			},{
				title:"流程名字",
				field:"name",
				width:10
			}, {
				title:"流程key",
				field:"key",
				width:10
			},{
				title:"流程版本",
				field:"version",
				width:10
			},{
				title:"资源名称",
				field:"resourceName",
				width:10
			},{
				title:"描述",
				field:"description",
				width:10
			}
			] ],
			striped : true
		});
		function viewPng(did,name){
			$("#pngImg").attr("src","/definition/viewPng?deploymentId="+did+"&resourceName="+name);
		}
	});
</script>
<body>
	<table id="deployeList"></table>
	<div id="deployeDlg">
		<form id="deployForm" enctype="multipart/form-data" method="post">
			<table style="margin: 10px auto;">
				<tr>
					<td style="text-align: right">流程部署名称:</td>
					<td><input type="text" name="zipFileName"></td>
				</tr>
				<tr>
					<td style="text-align: right">流程图ZIP文件:</td>
					<td><input type="file" name="zipFile"
						class="easyui-validatebox" data-options="required:true"></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="pngDlg">
		<img alt="流程图" id="pngImg" >
	</div>
</body>
</html>