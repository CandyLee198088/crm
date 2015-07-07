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
	//页面加载完毕后
	$(function(){
		// 1、声明页面中需要使用的组件
		var contractPayItemGrid,contractPayItemDlg,contractPayItemForm; 
		
		// 2、在页面中检索这些组件，并缓存
		contractPayItemGrid = $("#contractPayItemTab");
		contractPayItemDlg = $("#contractPayItemDlg");
		contractPayItemForm = $("#contractPayItemForm");
		
	    // 3、初始化页面组件
	    function salerFormatter(v,row,index){
		if(v && v.truename){
			return v.truename;
		}
		}
	    
	    function clientFormatter(v,row,index){
		if(v && v.name){
			return v.name;
		}
		}
	    
	    
		contractPayItemGrid.datagrid({
			url:"/contractPayItem/list",
			title:"合同付款明细",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbContractPayItem",
			columns:[[
			          {field:'payDate',title:'付款时间',width:2},  
			          {field:'payAmount',title:'付款金额',width:2},  
			          {field:'payProcent',title:'付款百分比',width:2},
			          {field:'status',title:'付款状态 ',width:1}
			]],
		});
		
		//对话框
		contractPayItemDlg.dialog({
			title:"添加/编辑合同付款明细",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			contractPayItemForm.form('submit',{
	 			   				url:'/contractPayItem/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						contractPayItemDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							contractPayItemGrid.datagrid('reload');			 			   							
	 			   						});
	 			   					}
	 			   				}
	 			   			});
	 			   		}
	 			   	},
				   		{
	 			   		text:'重置',
	 			   		iconCls:'icon-reload',
	 			   		handler:function(){
	 			   			contractPayItemForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							contractPayItemDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var contractPayItemObj = {
			createContractPayItem :function(){
				//得先清空缓存
				contractPayItemForm.form("clear");
// 				contractPayItemForm.form('reset');
				contractPayItemDlg.dialog("open");
			},
			editContractPayItem:function(){
				var row = contractPayItemGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				contractPayItemForm.form("clear");
				contractPayItemDlg.dialog("open");
				contractPayItemForm.form("load",row);
			},
			deleteContractPayItem:function(){
				//得到被选中要删除的行对象
				var row = contractPayItemGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/contractPayItem/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									contractPayItemGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findContractPayItem:function(){
				contractPayItemGrid.datagrid("load",{
					minPayDate:$("#minPayDate").datebox('getValue'),
					maxPayDate:$("#maxPayDate").datebox('getValue'),
// 					status:$("#status").datebox('getValue'), //付款状态搜索
					keyword:$("#contractPayItemKeyword").val()
				}
			);
		},
		refreshContractPayItemGrid:function(){
			contractPayItemGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-contractPayItem]").on("click",function(){
			var contractPayItem = $(this).attr("data-contractPayItem");
			if(contractPayItem){
				contractPayItemObj[contractPayItem]();
			}
		});
		
		//6:管理表单
		$("#contractPayItemPayDate").datebox({
			required:true
		});
		
		$("#contractPayItemAmount").validatebox({
			required:true
		});
		
	});
	
</script>
	
	<div id="tbContractPayItem">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-contractPayItem='createContractPayItem' >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-contractPayItem="editContractPayItem" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-contractPayItem="deleteContractPayItem">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-contractPayItem="refreshContractPayItemGrid">刷新</a><br/>
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>&emsp;付款日期:</td>
				<td>从:<input id="minPayDate" class="easyui-datebox" name="minPayDate" style="width: 100px;height: 25px" /></td>
				<td>&emsp;到:<input id="maxPayDate" class="easyui-datebox" name="maxPayDate" style="width: 100px;height: 25px"/></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-contractPayItem="findContractPayItem">查询</a></td>
				<td>&emsp;关键字:</td>
				<td><input id="contractPayItemKeyword" name="keyword" align="right" contractPayItem="text" style="width: 70px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-contractPayItem="findContractPayItem">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="contractPayItemTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="contractPayItemDlg" class="easyui-dialog" style="width: 300px;height: 260px;">
		<form id="contractPayItemForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>付款日期:</td>
					<td><input id="contractPayItemPayDate" name="payDate" /></td>
				</tr>
				<tr>
					<td>付款金额:</td>
					<td><input id="contractPayItemAmount" name="payAmount" type="text"/></td>
				</tr>
				<tr>
					<td>付款百分比:</td>
					<td><input id="contractPayItemPayProcent" name="payProcent" /></td>
				</tr>
				<tr>
					<td>付款状态:</td>
					<td><input id="contractPayItemStatus" name="status" type="text"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>