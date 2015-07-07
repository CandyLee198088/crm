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
function customer(v,r,i){
	if(v){
		return v.name;
	}
	return null;
}
function traceResult(v,r,i){
	switch(v){
		case 1:return "<font color='green'>好</font>";break;
		case 0:return "中";break;
		case -1:return "<font color='red'>差</font>";break;
		default: return "";
	}
}
function traceUser(v,r,i){
	if(v){
		return v.truename;
	}
	return null;
}


$(function(){
// 	表格的初始化
	$('#customerTraceGrid').datagrid({    
	    url:'/customerTrace/json', 
	    fitColumns:true,
	    title:"客户跟进管理",
	    fit:true,
	    border:false,
	    pagination:true,
	    pageList:[5,10,20],
	    rownumbers:true,
	    singleSelect:true,
	    toolbar:"#customeTraceTb",
	    columns:[[    
	        {field:'customer',title:'跟进客户',width:1,formatter:customer}    
	        ,{field:'theme',title:'跟进主题',width:1}
	        ,{field:'traceType',title:'跟进方式',width:1,formatter:customer}
	        ,{field:'traceResult',title:'跟进效果',width:1,formatter:traceResult}
	        
	        ,{field:'traceUser',title:'跟进人',width:1,formatter:traceUser}
	        ,{field:'date',title:'跟进时间',width:1}
	        ,{field:'remark',title:'备注',width:1}
	        
	    ]]
	}); 
	//按钮的处理
	var customerTraceButtonDeal={
			 deptAdvance:function(){
					$("#customerTraceGrid").datagrid("load",{
							keyword: $("#customerTraceKeyword").val()
						}
					);
				},
			 
			add:function(){
				$("#customerTraceForm").form("clear");
				$("#customerTraceDlg").dialog("open");
			},
			refresh:function(){
				$("#customerTraceGrid").datagrid("reload");
			},
			edit:function(){
				var row=$("#customerTraceGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}
		 		$("#customerTraceForm").form("clear");
				$("#customerTraceDlg").dialog("open");
				
				if(row.customer){
				row["customer.id"]=row.customer.id;
				}
				if(row.traceUser){
				row["traceUser.id"]=row.traceUser.id;
				}
				if(row.traceType){
				row["traceType.id"]=row.traceType.id;
				}
				
				$("#customerTraceForm").form("load",row);
				
			},
			del:function(){
				var row=$("#customerTraceGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}

				$.messager.confirm("提示:","是否删除?",function(yes){
					console.debug(row);
					if(yes){
		 				$.get("/customerTrace/delete?id="+row.id,function(data){
		 					$.messager.alert("温馨提示",data.message,"info"); 					
		 					$("#customerTraceGrid").datagrid("reload");
						},"json");
					}
				});
				
			},
			save:function(){
				
				$('#customerTraceForm').form('submit', {    
				    url:"/customerTrace/save",    
				    success:function(data){    
				        data=$.parseJSON(data);   
				        if(data.success){
				        	$("#customerTraceDlg").dialog("close");
				        	$.messager.alert("提示信息",data.message,"info",function(){
								$("#customerTraceGrid").datagrid("reload");
				        	});
				        }
				    }    
				});  
				
			},  
			cancel:function(){
				$("#customerTraceDlg").dialog("close");
			}
			
		};
		//对按钮的事件进行统一的处理
		$("a[data-cmd3]").on("click",function(){
			var funName=$(this).data("cmd3");
			if(funName){
				customerTraceButtonDeal[funName]();
			}
		});
	
});

</script>
<!-- 	表格组件 -->
<table id="customerTraceGrid"></table>
<!-- 	表格的工具栏 -->
<div id="customeTraceTb">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-add" style="width: 60px;text-align: center"  data-cmd3="add">添加</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" style="width: 60px;text-align: center"  data-cmd3="edit">编辑</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-remove" style="width: 60px;text-align: center"  data-cmd3="del">删除</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-reload" style="width: 60px;text-align: center"  data-cmd3="refresh">刷新</a>
	
	&emsp;&emsp;&emsp;&emsp;
	关键字:&emsp;<input id="customerTraceKeyword" type="text" style="width: 80px"  />
	<a class="easyui-linkbutton"  style="width: 80px;text-align: center;" plain="true" iconCls="icon-search" data-cmd3="deptAdvance">查询</a>
	
</div>

<!-- 对话框 -->
<div id="customerTraceDlg" class="easyui-dialog" style="width:300px;height:auto;" title="添加/编辑客户跟进面板" closed="true" modal="true" buttons="#customerTraceTb2">
<form  id="customerTraceForm" method="post">
	<input  type="hidden" name="id" />
	<table align="center" style="margin-top: 10px">
		<tr>
			<td><b>跟进客户:</b></td>
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
			<td ><b>跟进主题:</b></td>
			<td>
			<input name="theme"  class="easyui-validatebox" style="width:154px;"/>
			</td>
		</tr>
		<tr>
			<td><b>跟进方式:</b></td>
			<td>
			<select  class="easyui-combogrid" name="traceType.id"   
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'name',    
            url:'/systemDictionaryDetail/list?id=4',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            required:true,
            editable:false,
            
            columns:[[    
                {field:'id',title:'ID',width:105},    
                {field:'name',title:'字典明细名称',width:105},    
                {field:'sn',title:'字典明细序号',width:105},    
                {field:'intro',title:'字典明细简介',width:105},  
            	]]    
        	">
        </select>
			</td>
		</tr>
		
	<tr>
		<td><b>跟进效果:</b></td>
		<td>&emsp;好<input name="traceResult" type="radio" value="1" style="width:25px;" checked="checked">
			中<input name="traceResult" type="radio" value="0" style="width:25px;">
			差<input name="traceResult" type="radio"value="-1" style="width:25px;">
		</td>
	</tr>
	<tr>
		<td><b>跟进人:</b></td>
		<td>
		<select  class="easyui-combogrid" name="traceUser.id"   
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
		<td><b>跟进时间:</b></td>
		<td>
		<input name="date"  class="easyui-datebox" style="width:159px;" type="text"   
		data-options="    
            required:true,
            editable:false
            "/>
		</td>
	</tr>
	<tr>
		<td><b>备注:</b></td>
		<td>
		<textarea name="remark" rows="4" cols="22"></textarea>
		</td>
	</tr>
		
	</table>
</form>
</div>
<div id="customerTraceTb2">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-save"  data-cmd3="save">保存</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel"  data-cmd3="cancel">取消</a>
</div>
</body>
</html>