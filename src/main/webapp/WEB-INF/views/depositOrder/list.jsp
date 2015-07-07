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
		var depositGrid,depositDlg,depositForm; 
		
		// 2、在页面中检索这些组件，并缓存
		depositGrid = $("#depositOrderTab");
		depositDlg = $("#depositOrderDlg");
		depositForm = $("#depositOrderForm");
		
	    // 3、初始化页面组件
	    function salerFormatter(v,row,index){
		if(v && v.truename){
			return v.truename;
		}
		}
	    
	    function customerFormatter(v,row,index){
		if(v && v.name){
			return v.name;
		}
		}
	    
	    
		depositGrid.datagrid({
			url:"/depositOrder/list",
			title:"定金订单",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbDeposit",
			columns:[[
			          {field:'sn',title:'定金单号',width:2},  
			          {field:'sginDate',title:'签订时间',width:2},  
			          {field:'depositAmount',title:'定金金额',width:2},
			          {field:'saler',title:'销售员',width:1,formatter:salerFormatter},
			          {field:'customer',title:'客户名',width:1,formatter:customerFormatter},
			          {field:'intro',title:'摘要',width:2}
			]],
		});
		
		//对话框
		depositDlg.dialog({
			title:"添加/编辑定金订单",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			depositForm.form('submit',{
	 			   				url:'/depositOrder/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						depositDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							depositGrid.datagrid('reload');			 			   							
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
	 			   			depositForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							depositDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var depositObj = {
			createDepositOrder :function(){
				//得先清空缓存
				depositForm.form("clear");
// 				depositForm.form('reset');
				depositDlg.dialog("open");
			},
			editDepositOrder:function(){
				var row = depositGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				depositForm.form("clear");
				depositDlg.dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				if(row.saler){
					row["saler.id"] = row.saler.id;
				}
				if(row.customer){
					row["customer.id"] = row.customer.id;
				}
				depositForm.form("load",row);
			},
			deleteDepositOrder:function(){
				//得到被选中要删除的行对象
				var row = depositGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/depositOrder/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									depositGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findDepositOrder:function(){
				depositGrid.datagrid("load",{
					sn:$("#depositAdvanceSn").val(),
					minDate:$("#minSginDate").datebox('getValue'),
					maxDate:$("#maxSginDate").datebox('getValue'),
					saler:$("#depositSalerCon").combo('getText'),
					customer:$("#depositCustomerCon").combo('getText'),
					keyword:$("#despositKeyword").val()
				}
			);
		},
		refreshDepositGrid:function(){
			depositGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-deposit]").on("click",function(){
			var deposit = $(this).data("deposit");
			console.debug(deposit);
			if(deposit){
				depositObj[deposit]();
			}
		});
		
		//6:管理表单
		$("#depositSginDate").datebox({
			required:true
		});
		
		$("#depositAmount").validatebox({
			required:true
		});
		
		//员工数据表格下拉框
		$("#depositSaler").combogrid({
			//combogrid  上半段的属性都是combo的
			panelWidth:380,        
            idField:'id',    
            textField:'truename',    
            url:'/employee/query',    
            method:'post',
            mode:'remote', //会把我们的数据封装在q中,然后发送到服务器去检索
            //下面的都是datagrid的属性
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            columns:[[    
                {field:'id',title:'ID',width:1},    
                {field:'username',title:'用户名',width:2},    
                {field:'truename',title:'真名',width:2},    
                {field:'tel',title:'电话',width:2},  
                {field:'email',title:'邮箱',width:3}    
            ]],
            fitColumns:true
		});
		var grid = $("#depositSaler").combogrid('grid');
		var pager = grid.datagrid('getPager');
		pager.pagination({
// 			showPageList:false,
			showRefresh:false,
			displayMsg:''
		});
		
		
		//客户数据表格下拉框
		$("#depositCustomer").combogrid({
			//combogrid  上半段的属性都是combo的
			panelWidth:380,        
            idField:'id',    
            textField:'name',    
            url:'/customer/list',    
            method:'post',
            mode:'remote', //会把我们的数据封装在q中,然后发送到服务器去检索
            //下面的都是datagrid的属性
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            columns:[[    
                {field:'id',title:'ID',width:1},    
                {field:'name',title:'名称',width:2},    
                {field:'age',title:'年龄',width:2},    
                {field:'gender',title:'性别',width:2},    
                {field:'tel',title:'电话',width:2}   
            ]],
            fitColumns:true
		});
		var customerGrid = $("#depositCustomer").combogrid('grid');
		var customerPager = customerGrid.datagrid('getPager');
		customerPager.pagination({
// 			showPageList:false,
			showRefresh:false,
			displayMsg:''
		});
		
	});
	
</script>
	
	<div id="tbDeposit">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-deposit='createDepositOrder' >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-deposit="editDepositOrder" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-deposit="deleteDepositOrder">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-deposit="refreshDepositGrid">刷新</a><br/>
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>订单号:</td>
				<td><input id="depositAdvanceSn" name="sn" style="width: 40px"/></td>
				<td>&emsp;签订时间:</td>
				<td>从:<input id="minSginDate" class="easyui-datebox" name="minDate" style="width: 100px;height: 25px" /></td>
				<td>&emsp;到:<input id="maxSginDate" class="easyui-datebox" name="maxDate" style="width: 100px;height: 25px"/></td>
				<td>&emsp;销售员:</td>
				<td><select id="depositSalerCon" style="width: 100px" name="saler" class="easyui-combogrid" data-options="
						panelWidth:480,        
			            idField:'id',    
			            textField:'truename',    
			            url:'/employee/query',  
			            pagination:true,
			            pageList:[5,10,20],
			            pageSize:5,
			            columns:[[    
			                {field:'id',title:'ID',width:50},    
			                {field:'username',title:'用户名',width:100},    
			                {field:'truename',title:'真名',width:100},    
			                {field:'tel',title:'电话',width:100},  
			                {field:'email',title:'邮箱',width:100}    
			            ]]
						">
				</select></td>
				<td>客户:</td>
				<td><select id="depositCustomerCon" style="width: 100px" name="customer" class="easyui-combogrid" data-options="
						panelWidth:380,        
			            idField:'id',    
			            textField:'name',    
			            url:'/customer/list',    
			            method:'post',
			            mode:'remote', 
			            pagination:true,
			            pageList:[5,10,20],
			            pageSize:5,
			            columns:[[    
			                {field:'id',title:'ID',width:1},    
			                {field:'name',title:'名称',width:2},    
			                {field:'age',title:'年龄',width:2},    
			                {field:'gender',title:'性别',width:2},    
			                {field:'tel',title:'电话',width:2}   
			            ]],
			            fitColumns:true
						">
				</select></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-deposit="findDepositOrder">查询</a></td>
				<td>&emsp;关键字:</td>
				<td><input id="despositKeyword" name="keyword" align="right" deposit="text" style="width: 70px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-deposit="findDepositOrder">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="depositOrderTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="depositOrderDlg" class="easyui-dialog" style="width: 300px;height: 260px;">
		<form id="depositOrderForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>签订日期:</td>
					<td><input id="depositSginDate" name="sginDate" /></td>
				</tr>
				<tr>
					<td>定金金额:</td>
					<td><input id="depositAmount" name="depositAmount" type="text"/></td>
				</tr>
				<tr>
					<td>销售员:</td>
					<td><select id="depositSaler" name="saler.id"></select></td>
				</tr>
				<tr>
					<td>客户:</td>
					<td><input id="depositCustomer" name="customer.id" type="text"/></td>
				</tr>
				<tr>
					<td>摘要:</td>
					<td><textarea name="intro" rows="2" cols="16"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>