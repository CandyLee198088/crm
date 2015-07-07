<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/base.jsp" %>
<style type="text/css">
	a{
		text-decoration: none;
	}
</style>
</head>
<body>
<script type="text/javascript">
//状态的处理
function customerStatus(value,row,index){
		if(value==0){
			return "<font color='orange'>待报备</font>";
		}else if(value==-1){
			return "<font color='red'>作废</font>";
		}else if(value==1){
			return "<font color='gray'>报备中</font>";
		}else if(value==2){
			return "<font color='green'>审批通过</font>";
		}
	}
// 性别的处理
function customerGender(v,r,i){
	switch(v){
		case 1:return "男";break;
		case 2:return "女";break;
		default: return "未知";
	}
}
//职业的处理
function customerJob(v,r,i){
	if(v&&v.name){
		return v.name;
	}
	return "";
}
function customerSelaryLevel(v,r,i){
	if(v&&v.name){
		return v.name;
	}
	return "";
}
function customerSource(v,r,i){
	if(v&&v.name){
		return v.name;
	}
	return "";
}
//创建人的处理
function customerSeller(v,r,i){
	if(v&&v.truename){
		return v.truename;
	}
	return "";
}
function flowStatusFormatter(v,r,i){
	if(r.status==0){
		return "<a href='javascript:void(0)' onclick='startFlow("+r.id+");'>启动流程</a>";
	}
}
function startFlow(id){
	$.messager.confirm("温馨提示","确定启动客户审批流程?",function (yes){
		if(yes){
			$.post("/customer/startProcess",{id:id},function(data){
				$.messager.alert("温馨提示",data.message,'info',function(){
					$('#customerGrid').datagrid('reload');    
				});
			},'json');
		}
	});
}
$(function(){
// 	表格的初始化
	$('#customerGrid').datagrid({    
	    url:'/customer/json', 
	    fitColumns:true,
	    title:"客户信息管理",
	    fit:true,
	    border:false,
	    pagination:true,
	    pageList:[5,10,20],
	    rownumbers:true,
	    singleSelect:true,
	    toolbar:"#customeTb",
	    columns:[[    
	        {field:'name',title:'姓名',width:1},    
	        {field:'age',title:'年龄',width:1},    
	        {field:'gender',title:'性别',width:1,formatter:customerGender},   
	        
	        {field:'tel',title:'电话',width:1},  
	        {field:'email',title:'邮箱',width:1},  
	        {field:'qq',title:'QQ',width:1},  
	        {field:'weChat',title:'微信',width:1}, 
	        {field:'job',title:'职业',width:1,formatter:customerJob}, 
	        {field:'salaryLevel',title:'收入水平',width:1,formatter:customerSelaryLevel}, 
	        {field:'source',title:'客户来源',width:1,formatter:customerSource}, 
	        {field:'seller',title:'创建人',width:1,formatter:customerSeller}, 
	        {field:'time',title:'创建时间',width:1},
	        {field:'status',title:'状态',width:1,formatter:customerStatus},
	        {field:'startflow',title:'流程操作',width:1,formatter:flowStatusFormatter}
	    ]]
	}); 
	//按钮的处理
	var customerButtonDeal={
		customertrace:function(){
			var row=$('#customerGrid').datagrid("getSelected");
			if(!row){
				$.messager.alert("温馨提示","请选一行数据","info");return;
			}
	 		$("#customerTraceForm00").form("clear");
			$("#customerTraceDlg00").dialog("open");
			
			$("#customerTraceForm00").form("load",row);
			
		
// 			if(row.traceType){
// 			row["traceType.id"]=row.traceType.id;
// 			}
			

		},
		customertrans:function(){
// 				alert("customertrans");
				
		},
		customerpool:function(){
			//获取行数据
			var rowData =$("#customerGrid").datagrid("getSelected");
			if(!rowData){
				$.messager.alert("温馨提示","请选中一行数据","info");
				return;
			}
			$.messager.confirm('温馨提示', '确定将客户【<font color="red"><b>'+rowData.name+'</b></font>】放入资源池？', function(ok){
				 if (ok){
					// 获取需要删除数据的唯一标示
						var id = rowData.id;
						// 发送ajax请求
						$.get("/customerResPool/putpool",{"id":id},function(data){
							if(data.success){
								$.messager.alert("温馨提示","放入成功!!","info",function(){
									$("#customerGrid").datagrid("load");
								});
							}
						},"json");
				 }
			});	
				
				
		},
			 deptAdvance:function(){
				 console.debug($("#deptStatus1").val());
					$("#customerGrid").datagrid("load",{
							keyword: $("#customerKeyword").val()
						}
					);
				},
			 
			add:function(){
				$("#customerForm").form("clear");
				$("#customerDlg").dialog("open");
			},
			refresh:function(){
				$("#customerGrid").datagrid("reload");
			},
			edit:function(){
				var row=$("#customerGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}
		 		$("#customerForm").form("clear");
// 				$("#customerForm").form("reset");
				$("#customerDlg").dialog("open");
				
				if(row.job){
				row["job.id"]=row.job.id;
				}
				if(row.gender==3){
					row.gender='';
				}
				
				if(row.source){
				row["source.id"]=row.source.id;
				}
				
				if(row.salaryLevel){
				row["salaryLevel.id"]=row.salaryLevel.id;
				}
				
				$("#customerForm").form("load",row);
				
			},
			del:function(){
				var row=$("#customerGrid").datagrid("getSelected");
				if(!row){
					$.messager.alert("温馨提示","请选一行数据","info");return;
				}

				$.messager.confirm("提示:","是否删除?",function(yes){
					console.debug(row);
					if(yes){
		 				$.get("/customer/delete?id="+row.id,function(data){
		 					$.messager.alert("温馨提示",data.message,"info"); 					
		 					$("#customerGrid").datagrid("reload");
						},"json");
					}
				});
				
			},
			save:function(){
				
				$('#customerForm').form('submit', {    
				    url:"/customer/save",    
				    success:function(data){    
				        data=$.parseJSON(data);   
				        if(data.success){
				        	$("#customerDlg").dialog("close");
				        	$.messager.alert("提示信息",data.message,"info",function(){
								$("#customerGrid").datagrid("reload");
				        	});
				        }
				    }    
				});  
				
			},  
			cancel:function(){
				$("#customerDlg").dialog("close");
			},
			
			/////////////////////////////////////////////客户跟进的的保存和取消
			tracesave:function(){
				
// 				$('#customerTraceForm00').form('submit', {    
// 				    url:"/customerTrace/save",    
// 				    success:function(data){    
// 				        data=$.parseJSON(data);   
// 				        if(data.success){
// 				        	$("#customerTraceDlg00").dialog("close");
// 				        	$.messager.alert("提示信息",data.message,"info",function(){
// 				        	});
// 				        }
// 				    }    
// 				});  
				
			},  
			tracecancel:function(){
				$("#customerTraceDlg00").dialog("close");
			}
			
			
			
			
		};
		//对按钮的事件进行统一的处理
		$("a[data-cmd2]").on("click",function(){
			var funName=$(this).data("cmd2");
			if(funName){
				customerButtonDeal[funName]();
			}
		});
	
});

</script>
<!-- 	表格组件 -->
<table id="customerGrid"></table>
<!-- 	表格的工具栏 -->
<div id="customeTb">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-add"  data-cmd2="add">添加</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-edit"  data-cmd2="edit">编辑</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-remove"  data-cmd2="del">删除</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-reload"  data-cmd2="refresh">刷新</a>
	
	<a class="easyui-linkbutton" plain="true" iconCls="icon-tip"  data-cmd2="customertrace">跟进</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-redo"  data-cmd2="customertrans">移交</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel"  data-cmd2="customerpool">放入资源池</a>
	&emsp;&emsp;&emsp;&emsp;
	关键字:&emsp;<input id="customerKeyword" type="text" style="width: 80px"  />
	<a class="easyui-linkbutton"   plain="true" iconCls="icon-search" data-cmd2="deptAdvance">查询</a>
	
</div>

<!-- 对话框 -->
<div id="customerDlg" class="easyui-dialog" style="width: 300px;height:auto;" title="添加/编辑客户面板" closed="true" modal="true" buttons="#customerTb2">
<form  id="customerForm" method="post" style="display: none;">
	<input  type="hidden" name="id" />
	<table align="center" style="margin-top: 10px">
		<tr>
			<td><b>姓名:</b></td>
			<td><input  name="name" class="easyui-validatebox" required="true"/></td>
		</tr>
		<tr>
			<td><b>年龄:</b></td>
			<td><input  name="age" class="easyui-numberbox" required="true" min="1" max="100"/></td>
		</tr>
		<tr>
			<td><b>性别:</b></td>
			<td>
				<select class="easyui-combobox" name="gender"  style="width: 167px" panelHeight="auto">
					<option value="1" >男</option>
					<option value="2">女</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td><b>电话:</b></td>
			<td><input  name="tel" class="easyui-validatebox" required="true"/></td>
		</tr>
		<tr>
			<td><b>邮箱:</b></td>
			<td><input  name="email" class="easyui-validatebox" validType="email"/></td>
		</tr>
		<tr>
			<td><b>QQ:</b></td>
			<td><input  name="qq" class="easyui-validatebox" /></td>
		</tr>
		<tr>
			<td><b>微信:</b></td>
			<td><input  name="weChat" class="easyui-validatebox" /></td>
		</tr>
		
		<tr>
			<td><b>客户来源:</b></td>
			<td>
			<select  class="easyui-combogrid" name="source.id"  style="width: 167px" 
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'name',    
            url:'/systemDictionaryDetail/list?id=2',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            
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
			<td><b>客户职业:</b></td>
			<td>
			<select  class="easyui-combogrid" name="job.id"  style="width: 167px" 
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'name',    
            url:'/systemDictionaryDetail/list?id=1',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            
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
			<td><b>客户收入:</b></td>
			<td>
			<select  class="easyui-combogrid" name="salaryLevel.id"  style="width: 167px" 
        	data-options="    
            panelWidth:428,        
            idField:'id',    
            textField:'name',    
            url:'/systemDictionaryDetail/list?id=3',    
            pagination:true,
            pageList:[5,10,20],
            pageSize:5,
            
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
		
	</table>
</form>
</div>
<div id="customerTb2">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-save"  data-cmd2="save">保存</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel"  data-cmd2="cancel">取消</a>
</div>

<!-- 客户跟进的对话框 ----------------------------------------------->
<div id="customerTraceDlg00" class="easyui-dialog" style="width:300px;height:auto;" title="添加/编辑客户跟进面板" closed="true" modal="true" buttons="#customerTraceTb200">
<form  id="customerTraceForm00" method="post">
<!-- 	<input  type="hidden" name="id" /> -->
	<table align="center" style="margin-top: 10px">
		<tr>
			<td><b>跟进客户:</b></td>
			<td>
			<input name="name" disabled="disabled" class="easyui-validatebox" style="width:154px;" />
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
<div id="customerTraceTb200">
	<a class="easyui-linkbutton" plain="true" iconCls="icon-save"  data-cmd2="tracesave">保存</a>
	<a class="easyui-linkbutton" plain="true" iconCls="icon-cancel"  data-cmd2="tracecancel">取消</a>
</div>
</body>
</html>