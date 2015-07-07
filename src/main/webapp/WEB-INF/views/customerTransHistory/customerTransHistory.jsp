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
  //列表的处理
function customerTrans(v,r,i){
	if(v){
		return v.name;
	}
	return null;
}

function transUser(v,r,i){
	if(v){
		return v.truename;
	}
	return null;
}

$(function(){
// 	表格的初始化
	$('#customerTransHistoryGrid').datagrid({    
	    url:'/customerTransHistory/json', 
	    fitColumns:true,
	    title:"客户移交记录",
	    fit:true,
	    border:false,
	    pagination:true,
	    pageList:[5,10,20],
	    rownumbers:true,
	    singleSelect:true,
	    toolbar:"#customerTransHistoryTb",
	    columns:[[    
	        {field:'customer',title:'客户',width:1,formatter:customerTrans}    
	        ,{field:'transUser',title:'移交人员',width:1,formatter:transUser}    
	        ,{field:'transTime',title:'移交时间',width:1}    
	        ,{field:'oldSeller',title:'原营销人员',width:1,formatter:transUser}    
	        ,{field:'newSeller',title:'新营销人员',width:1,formatter:transUser}    
	        ,{field:'reason',title:'移交原因',width:1}    
	      
	    ]]
	}); 
	//按钮的处理
	var customerTransHistoryButtonDeal={
			 deptAdvance:function(){
					$("#customerTransHistoryGrid").datagrid("load",{
							keyword: $("#customerTransHistoryKeyword").val()
						}
					);
				},
			 
			add:function(){
				$("#customerTransHistoryForm").form("clear");
				$("#customerTransHistoryDlg").dialog("open");
			},
			refresh:function(){
				$("#customerTransHistoryGrid").datagrid("reload");
			},
			edit:function(){
				var row=$("#customerTransHistoryGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}
		 		$("#customerTransHistoryForm").form("clear");
				$("#customerTransHistoryDlg").dialog("open");
				
				if(row.customer){
				row["customer.id"]=row.customer.id;
				}
				if(row.transUser){
				row["transUser.id"]=row.transUser.id;
				}
				if(row.oldSeller){
				row["oldSeller.id"]=row.oldSeller.id;
				}
				if(row.newSeller){
				row["newSeller.id"]=row.newSeller.id;
				}
				
				$("#customerTransHistoryForm").form("load",row);
				
			},
			del:function(){
				var row=$("#customerTransHistoryGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}

				$.messager.confirm("提示:","是否删除?",function(yes){
					console.debug(row);
					if(yes){
		 				$.get("/customerTransHistory/delete?id="+row.id,function(data){
		 					$.messager.alert("温馨提示",data.message,"info"); 					
		 					$("#customerTransHistoryGrid").datagrid("reload");
						},"json");
					}
				});
				
			},
			save:function(){
				
				$('#customerTransHistoryForm').form('submit', {    
				    url:"/customerTransHistory/save",    
				    success:function(data){    
				        data=$.parseJSON(data);   
				        if(data.success){
				        	$("#customerTransHistoryDlg").dialog("close");
				        	$.messager.alert("提示信息",data.message,"info",function(){
								$("#customerTransHistoryGrid").datagrid("reload");
				        	});
				        }
				    }    
				});  
				
			},  
			cancel:function(){
				$("#customerTransHistoryDlg").dialog("close");
			}
			
		};
		//对按钮的事件进行统一的处理
		$("a[data-cmd4]").on("click",function(){
			var funName=$(this).data("cmd4");
			if(funName){
				customerTransHistoryButtonDeal[funName]();
			}
		});
	
});

</script>
<!-- 	表格组件 -->
<table id="customerTransHistoryGrid"></table>
<!-- 	表格的工具栏 -->
<div id="customerTransHistoryTb">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-add" style="width: 60px;text-align: center"  data-cmd4="add">添加</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" style="width: 60px;text-align: center"  data-cmd4="edit">编辑</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-remove" style="width: 60px;text-align: center"  data-cmd4="del">删除</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-reload" style="width: 60px;text-align: center"  data-cmd4="refresh">刷新</a>
	
	&emsp;&emsp;&emsp;&emsp;
	关键字:&emsp;<input id="customerTransHistoryKeyword" type="text" style="width: 80px"  />
	<a class="easyui-linkbutton"  style="width: 80px;text-align: center;" plain="true" iconCls="icon-search" data-cmd4="deptAdvance">查询</a>
	
</div>

<!-- 对话框 -->
<div id="customerTransHistoryDlg" class="easyui-dialog" style="width:300px;height:auto;" title="添加/编辑客户移交记录面板" closed="true" modal="true" buttons="#customerTransHistoryTb2">
<form  id="customerTransHistoryForm" method="post">
	<input  type="hidden" name="id" />
	<table align="center" style="margin-top: 10px">
		<tr>
			<td><b>客户:</b></td>
			<td>
			<select  class="easyui-combogrid" name="customer.id" 
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'name',    
            url:'/customer/json',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            required:true,
            editable:false,
            
            columns:[[    
                {field:'name',title:'客户姓名',width:105},    
                {field:'age',title:'客户年龄',width:105},    
                {field:'tel',title:'客户电话',width:105},    
                {field:'email',title:'客户邮箱',width:105}  
            	]]    
        	">
        </select>
			</td>
		</tr>
		<tr>
		<td><b>移交人员:</b></td>
		<td>
		<select  class="easyui-combogrid" name="transUser.id"   
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'truename',    
            url:'/employee/query',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            required:true,
            editable:false,
            
            columns:[[    
                {field:'username',title:'用户名',width:105},    
                {field:'truename',title:'真名',width:105},    
                {field:'tel',title:'电话',width:105},  
                {field:'email',title:'邮箱',width:105}    
            	]]    
        	">
        </select>
			</td>
	</tr>
	<tr>
		<td><b>移交时间:</b></td>
		<td>
		<input name="transTime"  class="easyui-datebox" style="width:159px;" type="text"   
		data-options="    
            required:true,
            editable:false
            "/>
		</td>
	</tr>	
	<tr>
		<td><b>原营销人员:</b></td>
		<td>
		<select  class="easyui-combogrid" name="oldSeller.id"   
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'truename',    
            url:'/employee/query',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            required:true,
            editable:false,
            
            columns:[[    
                {field:'username',title:'用户名',width:105},    
                {field:'truename',title:'真名',width:105},    
                {field:'tel',title:'电话',width:105},  
                {field:'email',title:'邮箱',width:105}    
            	]]    
        	">
        </select>
			</td>
	</tr>
	<tr>
		<td><b>新营销人员:</b></td>
		<td>
		<select  class="easyui-combogrid" name="newSeller.id"   
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'truename',    
            url:'/employee/query',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            editable:false,
            
            columns:[[    
                {field:'username',title:'用户名',width:105},    
                {field:'truename',title:'真名',width:105},    
                {field:'tel',title:'电话',width:105},  
                {field:'email',title:'邮箱',width:105}    
            	]]    
        	">
        </select>
			</td>
	</tr>
	<tr>
		<td><b>移交原因:</b></td>
		<td>
		<textarea name="reason" rows="4" cols="22"></textarea>
		</td>
	</tr>
		
	</table>
</form>
</div>
<div id="customerTransHistoryTb2">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-save"  data-cmd4="save">保存</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel"  data-cmd4="cancel">取消</a>
</div>
</body>
</html>