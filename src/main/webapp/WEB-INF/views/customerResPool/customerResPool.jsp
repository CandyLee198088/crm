<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理</title>
<%@include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<!-- 1.引入外部资源 -->
<script type="text/javascript" >
$(function(){
	// 2.声明页面常用的组件 
	var customerResPoolGrid;
	// 3.在页面加载完毕后,缓存这些组件 
	customerResPoolGrid = $("#customerResPoolGrid");
	
	// 4.声明一个"命令管理对象",声明需要提供的函数支持 
	var cmdObj = {
		
		refresh : function(){
			customerResPoolGrid.datagrid("reload");
		},
		claim : function(){
			//获取行数据
			var rowData =customerResPoolGrid.datagrid("getSelected");
			if(!rowData){
				$.messager.alert("温馨提示","请选中一行数据","info");
				return;
			}
			$.messager.confirm('温馨提示', '是否确定接手客户-【<font color="red"><b>'+rowData.name+'</b></font>】？', function(ok){
				 if (ok){
					// 获取需要删除数据的唯一标示
						var id = rowData.id;
						// 发送ajax请求
						$.get("/customerResPool/claim",{"id":id},function(data){
							if(data.success){
								$.messager.alert("温馨提示","已成功接手客户【"+rowData.name+"】，赶快跟进吧!!","info",function(){
									customerResPoolGrid.datagrid("load");
								});
							}
						},"json");
				 }
			});
		},
	};
	
	//5. 初始化组件
	function statusFormatter(val, row, index){
		switch (val) {
		case -1:
			return "<font color='red'>作废</font>";
			break;

		default:
			return "<font color='green'>正常</font>";
			break;
		}
	}
	function genderFormatter(val, row, index){
		switch (val) {
		case 1:
			return "男";
			break;
		case 2:
			return "女";
			break;

		default:
			return "未知";
			break;
		}
	}
	///////////////////////////
	function poolsource(v,r,i){
		if(v&&v.name){
			return v.name;
		}
		return "";
	}
	
	function poolseller(v,r,i){
		if(v&&v.truename){
			return v.truename;
		}
		return "";
	}
	
	//new Ext.grid({
	customerResPoolGrid.datagrid({
			title:"客户资源池" ,
			fit:true ,
			border:false ,
			url:"/customerResPool/json",
			toolbar:[
			      {
			    	  text:"刷新",
			    	  iconCls:"icon-reload",
			    	  handler : cmdObj['refresh']
			      },
			      "-",
			      {
			    	  text:"接手",
			    	  iconCls:"icon-undo",
			    	  handler : cmdObj['claim']
			      }
            ],
			pagination:true,
			pageSize:"10" , 
			pageList:[5,10,20],
			fitColumns:true ,
			singleSelect:true,
			rownumbers:true,
			//默认传参
			queryParams: {
				"status": -1
			},
			
		    columns:[[    
		            {field:'name',title:'客户名称',width:1},
		            {field:'age',title:'年龄',width:1},   
		            {field:'gender',title:'性别',formatter:genderFormatter,width:1},   
		            {field:'tel',title:'电话',width:1},
		            {field:'seller',title:'营销人员',formatter:poolseller,width:1},
		            {field:'source',title:'客户来源',formatter:poolsource,width:1},
		            {field:'status',title:'客户状态',formatter:statusFormatter,width:1}
		        ]]    

	});
	
});
</script>
	<!-- 1. 数据表格 -->
	<table id="customerResPoolGrid"></table>
</body>
</html>