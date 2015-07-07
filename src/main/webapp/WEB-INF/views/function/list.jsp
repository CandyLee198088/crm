<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		var functionModelGrid,functionModelDlg,functionModelForm; 
		
		// 2、在页面中检索这些组件，并缓存
		functionModelGrid = $("#functionModelTab");
		functionModelDlg = $("#functionModelDlg");
		functionModelForm = $("#functionModelForm");
		
	    // 3、初始化页面组件
		
		functionModelGrid.datagrid({
			url:"/functionModel/query",
			title:"权限",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbResource",
			columns:[[
			          {field:'name',title:'权限名称',width:5},  
			          {field:'functionModelAddr',title:'权限地址',width:5}
			]]
		});
		
		//对话框
		functionModelDlg.dialog({
			title:"添加/编辑权限",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			functionModelForm.form('submit',{
	 			   				url:'/functionModel/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						functionModelDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							functionModelGrid.datagrid('reload');			 			   							
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
	 			   			functionModelForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							functionModelDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var functionModelObjResource = {
			createResource :function(){
				//得先清空缓存
				functionModelForm.form("clear");
// 				functionModelForm.form('reset');
				$("#functionModelDlg").dialog("open");
			},
			editResource:function(){
				var row = functionModelGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				functionModelForm.form("clear");
				$("#functionModelDlg").dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				functionModelForm.form("load",row);
			},
			deleteResource:function(){
				//得到被选中要删除的行对象
				var row = functionModelGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/functionModel/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									functionModelGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findResource:function(){
				functionModelGrid.datagrid("load",{
					name: $("#Rename").val()
				}
			);
		},
		refreshResourceGrid:function(){
			functionModelGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-functionModel]").on("click",function(){
			var functionModel = $(this).data("functionModel");
			if(functionModel){
				functionModelObjResource[functionModel]();
			}
		});
		
		//6:管理验证框
		$("#functionModelName").validatebox({
			required:true
		});
		$("#functionModelAddr").validatebox({
			required:true
		});
		
	});
	
</script>
	
	<div id="tbResource">
		<c:if test="${usercontextInsession.checkFunction('权限管理')}">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-functionModel='createResource' >添加</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-functionModel="editResource" >编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-functionModel="deleteResource">删除</a>
		</c:if>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-functionModel="refreshResourceGrid">刷新</a><br/>
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>权限名称:</td>
				<td><input id="Rename" name="name" type="text" style="width: 40px"/></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-functionModel="findResource">查询</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="functionModelTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="functionModelDlg" class="easyui-dialog" style="width: 300px;height: 240px;">
		<form id="functionModelForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>权限名称:</td>
					<td><input id="functionModelName" name="name" type="text"/></td>
				</tr>
				<tr>
					<td>权限地址:</td>
					<td><input id="functionModelAddr" name="functionModelAddr" type="text"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>