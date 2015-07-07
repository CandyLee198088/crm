<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table id="departmentGrid" class="easyui-datagrid" 
	title="部门管理"
	data-options="
		fit:true,
		url:'/department/json'
    	,singleSelect:true
		,pagination:true
		,fitColumns:true
		,rownumbers:true
		,toolbar:'#tb'

">
	<thead>
		<tr>
			<th field="deptno" width="1">部门编号</th>
			<th field="name"  width="1">部门名称</th>
			<th field="parent" formatter="deptParent"  width="1">上级部门</th>
			<th field="manager" formatter="deptTrueName"  width="1">部门经理</th>
			<th field="status"  width="1" formatter="deptStatus">状态</th>
		</tr>
	</thead>
</table>

<div id="tb">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-add" style="width: 60px;text-align: center" onclick="add()">添加</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-edit" style="width: 60px;text-align: center" onclick="edit()">编辑</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-remove" style="width: 60px;text-align: center" onclick="del()">删除</a>
	
	&emsp;&emsp;&emsp;&emsp;
	编号:<input id="deptno" type="text" style="width: 80px" align="right" />&nbsp;
	名称:<input id="deptname" type="text" style="width: 80px" align="right" />&nbsp;
	状态:<select id="deptStatus1"   panelHeight="auto" style="width: 100px">
					<option value="-2">--请选择--</option>
					<option value="0">正常</option>
					<option value="-1">停用</option>
		</select>&nbsp;
	<a class="easyui-linkbutton"  style="width: 80px;text-align: center;" plain="true" iconCls="icon-search" onclick="deptAdvance()">查询</a>
	
	
</div>

<!-- 对话框 -->
<div id="mydlg" class="easyui-dialog" style="width: 300px;height: 230px;" title="部门编辑|保存框" closed="true" modal="true" buttons="#tb2">
<form  id="myform" method="post">
	<input  type="hidden" name="id" />
	<table align="center" style="margin-top: 10px">
		<tr>
			<td><b>部门编号:</b></td>
			<td><input  name="deptno" class="easyui-validatebox" required="true"/></td>
		</tr>
		<tr>
			<td><b>部门名称:</b></td>
			<td><input  name="name" class="easyui-validatebox" required="true"/></td>
		</tr>
		<tr>
			<td><b>上级部门:</b></td>
			<td>
				<select class="easyui-combobox" name="parent.id" data-options="
					url:'/department/list',
					valueField:'id',
					textField:'name'
				" 
				 style="width: 167px">
					
				</select>
			</td>
		</tr>
		
		<tr>
			<td><b>部门经理:</b></td>
			<td>
				<select  class="easyui-combogrid" name="manager.id"  style="width: 167px" 
        	data-options="    
            panelWidth:480,        
            idField:'id',    
            textField:'truename',    
            url:'/employee/list',    
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
        </select>
			</td>
		</tr>
		
		<tr>
			<td><b>部门状态:</b></td>
			<td>
				<select class="easyui-combobox" name="status" panelHeight="auto" style="width: 167px">
					<option value="0" >正常</option>
					<option value="-1">停用</option>
				</select>
			</td>
		</tr>
	
	</table>
</form>
</div>
<div id="tb2">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="save()">保存</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="cancel()">取消</a>
</div>
 
 
 <script type="text/javascript">
 function deptAdvance(){
	 console.debug($("#deptStatus1").val());
		$("#departmentGrid").datagrid("load",{
				name: $("#deptname").val(),
				deptno: $("#deptno").val(),
				status:$("#deptStatus1").val()
			}
		);
	}
 
	function deptParent(value,row,index){
		if(value&&value.name){
			return value.name;
		}else{
			return null;
		}
	}
	
	function deptStatus(value,row,index){
		if(value==0){
			return "<font color='green'>正常</font>";
		}else if(value==-1){
			return "<font color='red'>停用</font>";
		}
	}
	
	function deptTrueName(value,row,index){
// 		console.debug(value);
// 		console.debug(row);
		if(value&&value.truename){
			return value.truename;
		}else{
			return null;
		}
	}
	
	function add(){
		$("#myform").form("clear");
		$("#mydlg").dialog("open");
	}
	function edit(){
		var row=$("#departmentGrid").datagrid("getSelected");
		if(!row){
			$.messager.alert("温馨提示","请选一行数据","info");return;
		}
// 		$("#myform").form("clear");
		$("#myform").form("reset");
		$("#mydlg").dialog("open");
		
		if(row.parent){
		row["parent.id"]=row.parent.id;
		}
		
		if(row.manager){
		row["manager.id"]=row.manager.id;
		}
		$("#myform").form("load",row);
		
		
	}
	function del(){
		var row=$("#departmentGrid").datagrid("getSelected");
		if(!row){
			$.messager.alert("温馨提示","请选一行数据","info");return;
		}

		$.messager.confirm("提示:","是否删除?",function(yes){
			console.debug(row.id);
			if(yes){
 				$.get("/department/delete?id="+row.id,function(data){
//  					console.debug(data);
//  					if(!data.success){
//  						$.messager.alert("温馨提示",data.message,"info");return;
//  					}
 					$.messager.alert("温馨提示",data.message,"info"); 					
 					$("#departmentGrid").datagrid("reload");
				},"json");
			}
		});
		
	}
	function save(){
		
		$('#myform').form('submit', {    
		    url:"/department/save",    
		    success:function(data){    
		        data=$.parseJSON(data);   
		        if(data.success){
		        	$("#mydlg").dialog("close");
		        	$.messager.alert("提示信息",data.message,"info",function(){
						$("#departmentGrid").datagrid("reload");
		        	});
		        }
		    }    
		});  
		
	}  
	function cancel(){
		$("#mydlg").dialog("close");
	}
</script>
</body>
</html>