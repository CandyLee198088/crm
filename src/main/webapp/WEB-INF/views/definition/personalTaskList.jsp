<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="/WEB-INF/views/base.jsp"%>
<style type="text/css">
	a{
		text-decoration: none;
	}
</style>
</head>
<body>
</body>
<script type="text/javascript">
$(function(){
	var taskTable = $("#personalTaskTable");
	var currentPngDlg  = $("#currentPngDlg");
	currentPngDlg.dialog({
		width : 1000,
		height : 500,
		modal:true,
		title : "当前活动流程图",
		closed : true,
	});
	taskTable.datagrid({
		title : '私有任务管理',
		singleSelect : true,
		fit : true,
		url : "/definition/personalTaskList",
		method : 'post',
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		border : false,
		pageList : [ 5, 10, 15, 20, 30, 50 ],
		collapsible : true,
		toolbar : [ {
			text:"刷新",
			iconCls:"icon-reload",
			handler:function(){
				taskTable.datagrid('load');
			}
		},{
			text:"查看当前流程图",
			iconCls:"icon-search",
			handler:function(){
				var rowData = taskTable.datagrid('getSelected');
				if(rowData ){
					currentPngDlg.dialog('open');
					viewCurrentPng(rowData.id);
					//$.post("/definition/viewPng",{deploymentId:rowData.deploymentId,resourceName:rowData.diagramResourceName});
				}else{
					$.messager.alert("温馨提示","请选中一行后再查看",'info');
				}
			}
		}],
		columns : [ [ {
			title:"任务ID",
			field:"id",
			width:10
		},{
			title:"任务名称",
			field:"name",
			width:10
		}, {
			title:"创建时间",
			field:"createTime",
			formatter:ctFormatter,
			width:10
		},{
			title:"描述",
			field:"description",
			width:10
		},{
			title:"指定办理人",
			field:"assignee",
			width:10
		},{
			title:"操作",
			field:"op",
			formatter:opFormatter,
			width:10
		}
		] ],
		striped : true
	});
});
function viewCurrentPng(id){
	var currentPng = $("#currentPng");
	currentPng.attr("src","/definition/viewCoordinates?_="+new Date()+"&taskId="+id);
	$.post("/definition/locationInfo",{taskId:id},function(data){
		console.debug(data);
		$("<div>").appendTo($("#dlgTemp")).css({
			"position": "absolute",
			"border-color": "red",
			"width":data.width+"px",
			"height":data.height+"px",
			"top": data.y+32+"px",
			"left": data.x+7+"px",
			"border-style": "solid"
		});
	})
}

function viewTaskDetail(id){
	var detailTaskDlg= $("#detailTaskDlg");
	$.post("/definition/getTaskInfo",{taskId:id},function(data){
		detailTaskDlg.dialog({
			href:"/definition/"+data.formKey,
			title:"任务详情",
			width:500,
			height:250,
			closed:true,
			modal:true,
			buttons:[{
				text:"办理任务",
				iconCls:"icon-tip",
				handler:function(){
					$.post("/definition/completeTask",{id:id},function(data){
						$("#personalTaskTable").datagrid('reload');
						$("#detailTaskDlg").dialog('close');
						$.messager.alert("温馨提示",data.message,'info');
					});
				}
			},{
				text:"取消",
				iconCls:"icon-cancel",
				handler:function(){
					detailTaskDlg.dialog('close');			
				}
			}],
			onLoad:function(){
				var className = data.className;
				$.post("/"+className+"/get",{id:data.objId},function(customerData){
					customerData['gender']=(customerData.gender==1?'男':'女');
					customerData['sourceName'] = customerData.source.name;
					customerData['jobName'] = customerData.job.name;
					customerData['salaryLevelName'] = customerData.salaryLevel.name;
					var form = detailTaskDlg.find("form");
					form.find("input").attr("readOnly","readOnly");
					form.form('load',customerData);
					form.show();
				});
			}
		});
		detailTaskDlg.dialog('open');
	},'json');
}

function opFormatter(v,r,i){
		return "<a href='javascript:void(0);' onclick='viewTaskDetail("+r.id+");'>办理任务</a>";
}
function ctFormatter(v,r,i){
	if(v){
		return (new Date(v)).toLocaleString();
	}
}
</script>
<table id="personalTaskTable"></table>
<div id="detailTaskDlg"></div>
<div id="currentPngDlg" >
	<div id="dlgTemp">
	<img id="currentPng" alt="" style="margin: 0px;top: 35px;left: 10px;position:absolute;">
	</div>
</div>
</html>