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
		var resourceGrid,resourceDlg,resourceForm; 
		
		// 2、在页面中检索这些组件，并缓存
		resourceGrid = $("#resourceTab");
		resourceDlg = $("#resourceDlg");
		resourceForm = $("#resourceForm");
		
	    // 3、初始化页面组件
		
		resourceGrid.datagrid({
			url:"/resource/query",
			title:"资源",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbResource",
			columns:[[
			          {field:'name',title:'资源名称',width:5},  
			          {field:'resourceAddr',title:'资源地址',width:5}
			]]
		});
		
		//对话框
		resourceDlg.dialog({
			title:"添加/编辑资源",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			resourceForm.form('submit',{
	 			   				url:'/resource/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						resourceDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							resourceGrid.datagrid('reload');			 			   							
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
	 			   			resourceForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							resourceDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var resourceObjResource = {
			createResource :function(){
				//得先清空缓存
				resourceForm.form("clear");
// 				resourceForm.form('reset');
				$("#resourceDlg").dialog("open");
			},
			editResource:function(){
				var row = resourceGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				resourceForm.form("clear");
				$("#resourceDlg").dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				resourceForm.form("load",row);
			},
			deleteResource:function(){
				//得到被选中要删除的行对象
				var row = resourceGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/resource/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									resourceGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findResource:function(){
				resourceGrid.datagrid("load",{
					name: $("#Rename").val()
				}
			);
		},
		refreshResourceGrid:function(){
			resourceGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-resource]").on("click",function(){
			var resource = $(this).data("resource");
			if(resource){
				resourceObjResource[resource]();
			}
		});
		
		//6:管理验证框
		$("#resourceName").validatebox({
			required:true
		});
		$("#resourceAddr").validatebox({
			required:true
		});
		
	});
	
</script>
	
	<div id="tbResource">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-resource='createResource' >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-resource="editResource" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-resource="deleteResource">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-resource="refreshResourceGrid">刷新</a><br/>
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>资源名称:</td>
				<td><input id="Rename" name="name" type="text" style="width: 100px"/></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-resource="findResource">查询</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="resourceTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="resourceDlg" class="easyui-dialog" style="width: 300px;height: 240px;">
		<form id="resourceForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>资源名称:</td>
					<td><input id="resourceName" name="name" type="text"/></td>
				</tr>
				<tr>
					<td>资源地址:</td>
					<td><input id="resourceAddr" name="resourceAddr" type="text"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>