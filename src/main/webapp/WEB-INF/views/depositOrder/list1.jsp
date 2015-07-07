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
		var depositGrid; 
		
		// 2、在页面中检索这些组件，并缓存
		depositGrid = $("#depositOrderTab");
		
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
			]]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var depositObj = {
			findDepositOrder:function(){
				depositGrid.datagrid("load",{
					sn:$("#depositAdvanceSn").val(),
					minDate:$("#minSginDate").datebox('getValue'),
					maxDate:$("#maxSginDate").datebox('getValue'),
					saler:$("#depositSalerCon").combo('getText'),
					customer:$("#depositCustomerCon").combo('getText'),
					groupBy : $("#despositGroupBy").val(),
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
				<td><select id="despositGroupBy" name="groupBy" >
						<option value="emp.truename">按销售员分类</option> 
				    </select></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-deposit="findDepositOrder">查询</a></td>
				<td>&emsp;关键字:</td>
				<td><input id="despositKeyword" name="keyword" align="right" type="text" style="width: 70px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-deposit="findDepositOrder">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="depositOrderTab"></table> 
	
</body>
</html>