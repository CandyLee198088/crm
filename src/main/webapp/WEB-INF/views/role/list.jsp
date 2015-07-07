<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/base.jsp"%>
</head>
<body>
	<script type="text/javascript">
		//页面加载完毕后
		$(function() {
			// 1、声明页面中需要使用的组件
			var roleGrid, roleDlg, roleForm;
			var roleMenuDlg = $("#roleMenuDlg");
			var roleMenuForm = $("#roleMenuForm");
			var grid3  =$("#grid3");
			var grid4 = $("#grid4");
			// 2、在页面中检索这些组件，并缓存
			roleGrid = $("#roleTab");
			roleDlg = $("#roleDlg");
			roleForm = $("#roleForm");

			// 3、初始化页面组件

			roleGrid.datagrid({
				url : "/role/query",
				title : "角色",
				fit : true,
				border : false,
				fitColumns : true,
				singleSelect : true,
				rownumbers : true,
				pagination : true,
				toolbar : "#tbRole",
				columns : [ [ {
					field : 'sn',
					title : '角色编号',
					width : 5
				}, {
					field : 'name',
					title : '角色名称',
					width : 5
				} ] ]
			});
			function clearForm(){
				$("#roleSn").val("");
				$("#roleName").val("");
				$("#id").val("");
			}
			roleMenuDlg.dialog({
				title : "添加/编辑菜单",
				resizable : false,
				closed : true,
				modal : true,
				buttons : [ {
					text : '保存',
					iconCls : 'icon-save',
					handler : function() {
						roleMenuForm.form('submit', {
							url : '/role/saveMenu',
							onSubmit: function(param){
								var datas = grid4.datagrid('getRows');
								for ( var i = 0; i < datas.length; i++) {
									param['menus['+i+'].id'] = datas[i].id;
								}
// 								return false;
								return roleMenuForm.form('validate');
						    },
							success : function(data) {
								data = eval('(' + data + ')');
								if (data.success) {
									//保存后,1:关闭对话框
									roleMenuDlg.dialog('close');
									$.messager.alert('警示信息', data.message, 'info', function() {
// 										roleGrid.datagrid('reload');
									});
								}
							}
						});
					}
				}, {
					text : '重置',
					iconCls : 'icon-reload',
					handler : function() {
						roleMenuForm.form('reset');
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						roleMenuDlg.dialog('close');
					}
				} ]
				
				
			});
			//对话框
			roleDlg.dialog({
				title : "添加/编辑角色",
				resizable : false,
				closed : true,
				modal : true,
				buttons : [ {
					text : '保存',
					iconCls : 'icon-save',
					handler : function() {
						roleForm.form('submit', {
							url : '/role/save',
							onSubmit: function(param){
								var datas = $("#grid2").datagrid('getRows');
								for ( var i = 0; i < datas.length; i++) {
									param['functions['+i+'].id'] = datas[i].id;
								}
// 								return false;
								return roleForm.form('validate');
						    },
							success : function(data) {
								data = eval('(' + data + ')');
								if (data.success) {
									//保存后,1:关闭对话框
									roleDlg.dialog('close');
									$.messager.alert('警示信息', data.message, 'info', function() {
										roleGrid.datagrid('reload');
									});
								}
							}
						});
					}
				}, {
					text : '重置',
					iconCls : 'icon-reload',
					handler : function() {
						roleForm.form('reset');
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						roleDlg.dialog('close');
					}
				} ]
			});

			// 4、创建一个事件操作对象，管理所有的事件响应函数
			var roleObjRole = {
				createRole : function() {
					//得先清空缓存
					clearForm();
// 					roleForm.form("clear");
					// 				roleForm.form('reset');
					$("#roleDlg").dialog("open");
				},
				editRole : function() {
					var row = roleGrid.datagrid('getSelected');
					if (!row) {
						$.messager.alert("警示信息", "请选择要编辑的记录", "info");
						return;
					}
					//存在就加载回显,先清除缓存的
// 					roleForm.form("clear");
					clearForm();
					$("#roleDlg").dialog("open");
					//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
					$("#grid2").datagrid("loadData",row.functions);
					console.debug(row);
					roleForm.form("load", row);
				},
				deleteRole : function() {
					//得到被选中要删除的行对象
					var row = roleGrid.datagrid('getSelected');
					//可能没有选中行,我们得提示
					if (!row) {
						$.messager.alert("警示信息", "请选择要删除的记录", 'info');
						return;
					}
					$.messager.confirm("警示信息", "你确定要删除这一行吗", function(yes) {
						if (yes) {
							//发删除请求
							$.get("/role/delete?id=" + row.id, function(data) {
								if (data.success) {
									$.messager.alert('警示信息', data.message, 'info', function() {
										roleGrid.datagrid('reload');
									});
								}
							}, 'json');
						}
					});
				},
				findRole : function() {
					roleGrid.datagrid("load", {
						sn : $("#queryRoleSn").val(),
						name : $("#queryRoleName").val()
					});
				},
				refreshRoleGrid : function() {
					roleGrid.datagrid('reload');
				},
				menuSetting:function(){
					var row = roleGrid.datagrid('getSelected');
					if (!row) {
						$.messager.alert("警示信息", "请选择要编辑的记录", "info");
						return;
					}
					//存在就加载回显,先清除缓存的
// 					roleForm.form("clear");
					clearForm();
					//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
					grid4.datagrid("loadData",row.menus);
					roleMenuDlg.dialog('open');
					roleMenuForm.form("load", row);
				}
			};

			// 5、对页面所有的按钮，进行“统一”的事件监听
			$("a[data-role]").on("click", function() {
				var role = $(this).data("role");
				if (role) {
					roleObjRole[role]();
				}
			});

			//6:管理验证框
			$("#roleSn").validatebox({
				required : true
			});
			$("#roleName").validatebox({
				required : true
			});

		});
		$(function() {
			var grid3  =$("#grid3");
			var grid4 = $("#grid4");
			$.extend($.fn.datagrid.defaults, {
				singleSelect : true,
				method : 'post',
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				border : true,
				fit : true,
			});
			var gd1 = $("#grid1");
			var gd2 = $("#grid2");
			gd1.datagrid({
				url : "/functionModel/query",
				title : "所有权限列表",
				columns : [ [ {
					field : 'name',
					title : '权限名称',
					width : 50
				}, {
					field : 'resourceAddr',
					title : '资源地址',
					width : 150
				} ] ],
				onDblClickRow : function(index, row) {
					var gd2Datas = gd2.datagrid('getRows');
					for ( var i = 0; i < gd2Datas.length; i++) {
						if(gd2Datas[i]==row){
							return;
						}
					}
					gd2.datagrid('appendRow', row);
				}
			});
			gd2.datagrid({
				title : "该角色拥有的权限列表",
				pagination : false,
				columns : [ [ {
					field : 'name',
					title : '权限名称',
					width : 50
				}, {
					field : 'resourceAddr',
					title : '资源地址',
					width : 150
				} ] ],
				onDblClickRow:function(index,row){
					gd2.datagrid('deleteRow',index);
				}
			});
			grid3.datagrid({
				url : "/menu/query",
				title : "所有菜单列表",
				pageSize:5,
				pageList:[5,10,15],
				columns : [ [ {
					field : 'text',
					title : '菜单名称',
					width : 50
				}, {
					field : 'url',
					title : '资源地址',
					width : 150
				} ] ],
				onDblClickRow : function(index, row) {
					var gd4Datas = grid4.datagrid('getRows');
					for ( var i = 0; i < gd4Datas.length; i++) {
						if(gd4Datas[i]==row){
							return;
						}
					}
					grid4.datagrid('appendRow', row);
				}
			});
			grid4.datagrid({
				title : "该角色拥有的权限列表",
				pagination : false,
				columns : [ [ {
					field : 'text',
					title : '菜单名称',
					width : 50
				}, {
					field : 'url',
					title : '资源地址',
					width : 150
				} ] ],
				onDblClickRow:function(index,row){
					grid4.datagrid('deleteRow',index);
				}
			});
		});
	</script>

	<div id="tbRole">
	
		<c:if test="${usercontextInsession.checkFunction('角色管理')}">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-role='createRole'>添加</a> 
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-role="editRole">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-role="deleteRole">删除</a> 
		</c:if>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-role="refreshRoleGrid">刷新</a>
		<a class="easyui-linkbutton" iconCls="icon-2012080404391" plain="true" data-role="menuSetting">菜单维护</a><br />
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>角色编号:</td>
				<td><input id="queryRoleSn" name="sn" type="text" style="width: 40px" /></td>
				<td>角色名称:</td>
				<td><input id="queryRoleName" name="name" type="text" style="width: 40px" /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-role="findRole">查询</a></td>
			</tr>
		</table>
	</div>

	<!-- 表格组件 -->
	<table id="roleTab"></table>

	<!-- 对话框组件 -->
	<div id="roleDlg" class="easyui-dialog" style="width: 950px; height: 400px;">
		<form id="roleForm" method="post">
			<table align="center" style="margin-top: 10px; width: 100%">
				<input type="hidden" name="id" id="id"/>
				<tr>
					<td>角色编号:</td>
					<td><input id="roleSn" name="sn" type="text" /></td>
					<td>角色名称:</td>
					<td><input id="roleName" name="name" type="text" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div style="float: left; width: 450px; height: 270px;">
							<table id="grid1"></table>
						</div>
						<div style="float: right; width: 450px; height: 270px;">
							<table id="grid2" style="float: right;"></table>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
<!-- 	菜单权限 -->
	<div id="roleMenuDlg" class="easyui-dialog" style="width: 950px; height: 400px;">
		<form id="roleMenuForm" method="post">
			<table align="center" style="margin-top: 10px; width: 100%">
				<input type="hidden" name="id" id="id"/>
				<tr>
					<td>角色编号:</td>
					<td><input id="roleMenuSn" name="sn" type="text" /></td>
					<td>角色名称:</td>
					<td><input id="roleMenuName" name="name" type="text" /></td>
				</tr>
				<tr>
					<td colspan="4">
						<div style="float: left; width: 450px; height: 270px;">
							<table id="grid3"></table>
						</div>
						<div style="float: right; width: 450px; height: 270px;">
							<table id="grid4" style="float: right;"></table>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>