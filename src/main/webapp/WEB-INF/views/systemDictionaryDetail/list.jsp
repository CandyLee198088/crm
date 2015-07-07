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
	
// 	function detailFormatter(type,row,index){
// 		//显示部门名称而不是对象,使用格式化函数对我们的列改变值
// 		//1:先判断是否有部门,以免空指针
// 		if(type && type.name){
// // 			console.debug(dept);
// 			return type.name;
// 		}
// 	}
	
	//页面加载完毕后
	$(function(){
		// 1、声明页面中需要使用的组件
		var detailGrid,detailDlg,detailForm; 
		
		// 2、在页面中检索这些组件，并缓存
		detailGrid = $("#systemDictionaryDetailTab");
		detailDlg = $("#detailDlg");
		detailForm = $("#systemDictionaryDetailForm");
		
	    // 3、初始化页面组件
		detailGrid.datagrid({
			url:"/systemDictionaryDetail/list",
			title:"数据字典明细",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbDetail",
			columns:[[
			          {field:'sn',title:'序号',width:1},  
			          {field:'name',title:'名称',width:2},  
			          {field:'intro',title:'简介',width:4}
// 			          {field:'types',title:'目录',width:2,formatter:detailFormatter} 
			]]
		});
		
		//对话框
		detailDlg.dialog({
			title:"添加/编辑数据字典明细",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
		 			   		$('#systemDictionaryDetailForm').form('submit',{
	 			   				url:'/systemDictionaryDetail/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						detailDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							detailGrid.datagrid('load');			 			   							
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
	 			   			detailForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							detailDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var detailObjDetail = {
			createDetail :function(){
				//得先清空缓存
				detailForm.form("clear");
				detailDlg.dialog("open");
				//因为此时在type确定下新增,所以不需要填写,但是我们得设置为此时的type
				$("#typeId").val($("#did").val());
			},
			editDetail:function(){
				console.debug("xxxx");
				var row = detailGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				detailForm.form("clear");
				detailDlg.dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				if(row.types){
					row["types.id"] = row.types.id;
				}
				detailForm.form("load",row);
			},
			deleteDetail:function(){
				//得到被选中要删除的行对象
				var row = detailGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/systemDictionaryDetail/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									detailGrid.datagrid('load');			 			   							
			   					});
							}
						});
					}	
				},'json');
			},
			findDetail:function(){
				detailGrid.datagrid("load",{
					id:$("#did").val(),
					name:$("#dname").val(),
					sn:$("#dsn").val(),
					keyword:$("#dkeyword").val()
				}
			);
		},
		refreshDetailGrid:function(){
			detailGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-detail]").on("click",function(){
			var detail = $(this).data("detail");
			if(detail){
				detailObjDetail[detail]();
			}
		});
		
		//6:管理验证框
		$("#dsnForm").numberbox({
			min:0,
			required:true
		});
		$("#dnameForm").validatebox({
			required:true
		});
		
	});
</script>
	
	<div id="tbDetail">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-detail="createDetail">添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-detail="editDetail" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-detail="deleteDetail">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-detail="refreshDetailGrid">刷新</a><br/>
		<!-- 加个隐藏域   传递我们type中传递的id数据  -->
		<input id="did" name="id" type="hidden" />
		<!-- 高级查询   -->
		<table>
			<tr>
				<td>名称:</td>
				<td><input id="dname" name="name" type="text" style="width: 70px"/></td>
				<td>&emsp;序号:</td>
				<td><input id="dsn" name="sn" type="text" style="width: 70px"/><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-detail="findDetail">查询</a></td>
				<td>&emsp;&emsp;关键字:</td>
				<td><input id="dkeyword" name="keyword" align="right" type="text" style="width: 120px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-detail="findDetail">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	 <!-- 通过datagrid拿到的数据会自动通过我们配置的thead 下的th的filed字段的属性名去字段添加tbody,tr,td和数据到我们的table中 -->
	<table id="systemDictionaryDetailTab">  <!-- 拿到的json数据格式是有要求的,按照demo里的来 --> 
	</table>
	<!-- 对话框组件 -->
	<div id="detailDlg" style="width: 300px;height: 240px;">
		<form id="systemDictionaryDetailForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<input id="typeId" type="hidden" name="types.id" />
				<tr>
					<td>序号:</td>
					<td><input id="dsnForm" name="sn" type="text"/></td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input id="dnameForm" name="name" type="text"/></td>
				</tr>
				<tr>
					<td>简介:</td>
					<td><textarea name="intro" rows="2" cols="16"/></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>目录:</td> -->
<!-- 					<td><select class="easyui-combobox" name="types.id" style="width:155px;"  -->
<!-- 								data-options=" -->
<!-- 								              url:'/systemDictionaryDetail/allTypes', -->
<!-- 											  valueField:'id', -->
<!-- 											  textField:'name', -->
<!-- 											  panelHeight:'auto' -->
<!-- 											   "> -->
<!-- 					</select></td> -->
<!-- 				</tr> -->
			</table>
		</form>
	</div>
</body>
</html>