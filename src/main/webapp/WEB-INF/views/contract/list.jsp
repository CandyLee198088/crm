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
		var contractGrid,contractDlg,contractForm,ddv; 
		
		// 2、在页面中检索这些组件，并缓存
		contractGrid = $("#contractTab");
		contractDlg = $("#contractDlg");
		contractForm = $("#contractForm");
		//使用一个数组来封装我们每次打开的子表
		var subgridArray = [];		
		
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
	    
	    function payStatusFormatter(v,r,i){
			switch (v) {
			case -1:
				return "<font color='red'>否</font>";
				break;
			case 0:
				return "<font color='red'>正在支付</font>";
				break;

			default:
				return "<font color='green'>是</font>";
				break;
			}
		}
	    
	    function contractFormatter(v){
	    	if(v){
	    		return v.sn;
	    	}
	    }
	    
		contractGrid.datagrid({
			url:"/contract/list",
			title:"合同",
			fit:true,
			border:false,
			fitColumns:true,
			view: detailview,
			detailFormatter:function(index,row){
				 return '<div style="padding:2px"><table class="ddv"></table></div>';
			},
			onExpandRow: function(index,row){   //index,点击行的索引,从0开始   row点击的这行对象
// 				console.debug($(this).datagrid('getRowDetail',index)); //拿到的是上面的div 
				 var ddvAndRow = {};
				 $("#contractRowId").val(row.id);
				 ddv = $(this).datagrid('getRowDetail',index).find('table.ddv'); //拿到table对象 
				 ddvAndRow.subDdv = ddv;
				 ddvAndRow.parentRow = row;
				 subgridArray.push(ddvAndRow);
				 ddv.datagrid({
					 url:'/contractPayItem/list?id='+row.id,
					 fitColumns:true,
					 singleSelect:true,
					 rownumbers:true,
					 loadMsg:'',
					 height:'auto',
					 columns:[[
			             {field:'payDate',title:'付款时间',width:2},  
				         {field:'payAmount',title:'付款金额',width:2},  
				         {field:'payProcent',title:'付款百分比',width:2},
				         {field:'status',title:'是否付款 ',width:1,formatter:payStatusFormatter},
				         {field:'contract',title:'所属合同',width:1,formatter:contractFormatter}
					 ]],
					 onResize:function(){
						 contractGrid.datagrid('fixDetailRowHeight',index);
					 },
					 onLoadSuccess:function(){
						 setTimeout(function(){
							contractGrid.datagrid('fixDetailRowHeight',index);
						 },0);
					 }
				 });
				 contractGrid.datagrid('fixDetailRowHeight',index);
			},
			onCollapseRow: function(index,row){
				$.each(subgridArray,function(v,r){
					if(r.parentRow.id ==row.id){
						subgridArray.splice(v, 1);
					}
				});
			},
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbContract",
			columns:[[
			          {field:'sn',title:'合同号',width:2},  
			          {field:'sginDate',title:'签订时间',width:2},  
			          {field:'contractAmount',title:'合同金额',width:2},
			          {field:'saler',title:'销售员',width:1,formatter:salerFormatter},
			          {field:'customer',title:'客户名',width:1,formatter:customerFormatter},
			          {field:'intro',title:'合同摘要',width:2}
			]],
		});
		
		//对话框
		contractDlg.dialog({
			title:"添加/编辑合同",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			contractForm.form('submit',{
	 			   				url:'/contract/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						contractDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							contractGrid.datagrid('reload');			 			   							
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
	 			   			contractForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							contractDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var contractObj = {
			createContract :function(){
				//得先清空缓存
				contractForm.form("clear");
// 				contractForm.form('reset');
				contractDlg.dialog("open");
			},
			editContract:function(){
				var row = contractGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				contractForm.form("clear");
				contractDlg.dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				if(row.saler){
					row["saler.id"] = row.saler.id;
				}
				if(row.customer){
					row["customer.id"] = row.customer.id;
				}
				contractForm.form("load",row);
			},
			deleteContract:function(){
				//得到被选中要删除的行对象
				var row = contractGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/contract/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									contractGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findContract:function(){
				contractGrid.datagrid("load",{
					sn:$("#contractAdvanceSn").val(),
					minDate:$("#minConSginDate").datebox('getValue'),
					maxDate:$("#maxConSginDate").datebox('getValue'),
					saler:$("#contractSalerCon").combo('getText'),
					customer:$("#contractCustomerAdvance").combo('getText'),
					keyword:$("#contractKeyword").val()
				}
			);
		},
		refreshContractGrid:function(){
			contractGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-contract]").on("click",function(){
			var contract = $(this).data("contract");
			if(contract){
				contractObj[contract]();
			}
		});
		
		//6:管理表单
		$("#contractSginDate").datebox({
			required:true
		});
		
		$("#contractAmount").validatebox({
			required:true
		});
		
		//员工数据表格下拉框
		$("#contractSaler").combogrid({
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
		var congrid = $("#contractSaler").combogrid('grid');
		var conpager = congrid.datagrid('getPager');
		conpager.pagination({
// 			showPageList:false,
			showRefresh:false,
			displayMsg:''
		});
		
		//客户数据表格下拉框
		$("#contractCustomer").combogrid({
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
		var customerGrid = $("#contractCustomer").combogrid('grid');
		var customerPager = customerGrid.datagrid('getPager');
		customerPager.pagination({
// 			showPageList:false,
			showRefresh:false,
			displayMsg:''
		});
		
	

// 	 子表格操作     
	//页面加载完毕后
		// 1、声明页面中需要使用的组件
		var contractPayItemDlg,contractPayItemForm; 
		
		// 2、在页面中检索这些组件，并缓存
		contractPayItemDlg = $("#contractPayItemDlg");
		contractPayItemForm = $("#contractPayItemForm");
		
	    // 3、初始化页面组件
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
	 			   							ddv.datagrid('reload');			 			   							
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
				//当点击合同表的某行才能打开子表新增页面
// 				var contractRowId = $("#contractRowId").val();
// 				if(!contractRowId){
// 					$.messager.alert("警示信息","请选择合同项 ","info");
// 					return;
// 				}
				var row = contractGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选中合同项","info");
					return;
				}
				//得先清空缓存
				contractPayItemForm.form('reset');
				contractPayItemDlg.dialog("open");
				$("#contractId").val(row.id);
				$("#contractCode").val(row.sn);
			},
			editContractPayItem:function(){
// 				var contractRowId = $("#contractRowId").val();
// 				if(!contractRowId){
// 					$.messager.alert("警示信息","请选择合同项 ","info");
// 					return;
// 				}
				var i = 0;
				$.each(subgridArray,function(v,r){
					var row = r.subDdv.datagrid('getSelected');
					if(row){
						i++;
					}
				});
				if(i==0){
					$.messager.alert("警示信息","请选择要编辑的付款明细记录","info");
					return;
				}
				if(i>1){
					$.messager.alert("警示信息","不能同时编辑"+i+"个明细项,请选择一项","error");
					return;
				}
				//正常编辑一行
				var subRow;
				var parentRow;
				$.each(subgridArray,function(v,r){
					subRow = r.subDdv.datagrid('getSelected');
					ddv = r.subDdv;
					parentRow = r.parentRow;
				});
				//存在就加载回显,先清除缓存的
				contractPayItemForm.form("clear");
				contractPayItemDlg.dialog("open");
				$("#contractId").val(parentRow.id);
				$("#contractCode").val(parentRow.sn);
				contractPayItemForm.form("load",subRow);
			},
			deleteContractPayItem:function(){
// 				var contractRowId = $("#contractRowId").val();
// 				if(!contractRowId){
// 					$.messager.alert("警示信息","请选择合同项 ","info");
// 					return;
// 				}
// 				//得到被选中要删除的行对象
// 				var row = ddv.datagrid('getSelected');
// 				//可能没有选中行,我们得提示
// 				if(!row){
// 					$.messager.alert("警示信息","请选择要删除的付款明细记录",'info');
// 					return;
// 				}
				var i = 0;
				$.each(subgridArray,function(v,r){
					var row = r.subDdv.datagrid('getSelected');
					if(row){
						i++;
					}
				});
				if(i==0){
					$.messager.alert("警示信息","请选择要删除的付款明细记录","info");
					return;
				}
				if(i>1){
					$.messager.alert("警示信息","不能同时删除"+i+"个明细项,请选择一项","error");
					return;
				}
				var subRow;
				$.each(subgridArray,function(v,r){
					subRow = r.subDdv.datagrid('getSelected');
					ddv = r.subDdv;
				});
				//存在就加载回显,先清除缓存的
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/contractPayItem/delete?id="+subRow.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									ddv.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
// 			findContractPayItem:function(){
// 				ddv.datagrid("load",{
// 					minPayDate:$("#minPayDate").datebox('getValue'),
// 					maxPayDate:$("#maxPayDate").datebox('getValue'),
// // 					status:$("#status").datebox('getValue'), //付款状态搜索
// 					keyword:$("#contractPayItemKeyword").val()
// 				}
// 			);
// 		},
// 		refreshContractPayItemGrid:function(){
// 			var contractRowId = $("#contractRowId").val();
// 			if(!contractRowId){
// 				$.messager.alert("警示信息","请选择合同项 ","info");
// 				return;
// 			}
// 			ddv.datagrid('reload');
// 		}
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
		
		//下拉框
		$("#contractPayItemStatus").combobox({
			panelHeight:'auto',
			valueField:'tsid',    
			textField:'text',
			value:-1,
			data:[{
				tsid:1,
				text:'是',
			},{
				tsid:0,
				text:'正在支付',
			},{
				tsid:-1,
				text:'否'
			}]
		});
		
	});
</script>
	
	<div id="tbContract">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-contract='createContract' >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-contract="editContract" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-contract="deleteContract">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-contract="refreshContractGrid">刷新</a>&emsp;&emsp;&emsp;
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-contractPayItem='createContractPayItem' >添加明细</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-contractPayItem="editContractPayItem" >编辑明细</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-contractPayItem="deleteContractPayItem">删除明细</a>
<!-- 		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-contractPayItem="refreshContractPayItemGrid">刷新明细</a><br/> -->
		<!-- 高级查询 -->
		<input id="contractRowId" type="hidden" name="id"/>  <!-- 用来点击合同表的某行id -->
		<table>
			<tr>
				<td>订单号:</td>
				<td><input id="contractAdvanceSn" name="sn" style="width: 40px"/></td>
				<td>&emsp;签订时间:</td>
				<td>从:<input id="minConSginDate" class="easyui-datebox" name="minDate" style="width: 100px;height: 25px" /></td>
				<td>&emsp;到:<input id="maxConSginDate" class="easyui-datebox" name="maxDate" style="width: 100px;height: 25px"/></td>
				<td>&emsp;销售员:</td>
				<td><select id="contractSalerCon" name="saler" class="easyui-combogrid" data-options="
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
				<td>&emsp;客户:</td>
				<td><select id="contractCustomerAdvance" name="customer" class="easyui-combogrid" data-options="
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
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-contract="findContract">查询</a></td>
				<td>&emsp;关键字:</td>
				<td><input id="contractKeyword" name="keyword" align="right" contract="text" style="width: 70px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-contract="findContract">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="contractTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="contractDlg" class="easyui-dialog" style="width: 300px;height: 260px;">
		<form id="contractForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>签订日期:</td>
					<td><input id="contractSginDate" name="sginDate" /></td>
				</tr>
				<tr>
					<td>合同金额:</td>
					<td><input id="contractAmount" name="contractAmount" type="text"/></td>
				</tr>
				<tr>
					<td>销售员:</td>
					<td><select id="contractSaler" name="saler.id"></select></td>
				</tr>
				<tr>
					<td>客户:</td>
					<td><input id="contractCustomer" name="customer.id" type="text"/></td>
				</tr>
				<tr>
					<td>摘要:</td>
					<td><textarea name="intro" rows="2" cols="16"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 子表格toolbar -->
	<!-- <div id="tbContractPayItem">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-contractPayItem='createContractPayItem' >添加明细</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-contractPayItem="editContractPayItem" >编辑明细</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-contractPayItem="deleteContractPayItem">删除明细</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-contractPayItem="refreshContractPayItemGrid">刷新明细</a><br/>
		高级查询
		<input id="itemAdvanceId" type="hidden" name="id"/>
		<table>
			<tr>
				<td>&emsp;付款日期:</td>
				<td>从:<input id="minPayDate" class="easyui-datebox" name="minPayDate" style="width: 100px;height: 25px" /></td>
				<td>&emsp;到:<input id="maxPayDate" class="easyui-datebox" name="maxPayDate" style="width: 100px;height: 25px"/></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-contractPayItem="findContractPayItem">查询</a></td>
			</tr>
		</table>
	</div> -->
	
	<!-- 子表格对话框组件 -->
	<div id="contractPayItemDlg" class="easyui-dialog" style="width: 300px;height: 300px;">
		<form id="contractPayItemForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<input id="contractId" type="hidden" name="contract.id" />
				<tr>
					<td>所属合同:</td>
					<td><input id="contractCode" disabled="disabled" /></td>
				</tr>
				<tr>
					<td>付款日期:</td>
					<td><input id="contractPayItemPayDate" name="payDate" /></td>
				</tr>
				<tr>
					<td>付款金额:</td>
					<td><input id="contractPayItemAmount" name="payAmount" /></td>
				</tr>
				<tr>
					<td>付款百分比:</td>
					<td><input id="contractPayItemPayProcent" name="payProcent" /></td>
				</tr>
				<tr>
					<td>付款状态:</td>
					<td><input id="contractPayItemStatus" name="status" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>