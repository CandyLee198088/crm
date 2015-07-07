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
		var typeGrid,typeDlg,typeForm; 
		
		// 2、在页面中检索这些组件，并缓存
		typeGrid = $("#systemDictionaryTypeTab");
		typeDlg = $("#typeDlg");
		typeForm = $("#systemDictionaryTypeForm");
		
	    // 3、初始化页面组件
		function statusFormatter(v,r,i){
			switch (v) {
			case -1:
				return "<font color='red'>停用</font>";
				break;

			default:
				return "<font color='green'>正常</font>";
				break;
			}
		}
		
		typeGrid.datagrid({
			url:"/systemDictionaryType/list",
			title:"数据字典目录",
			fit:true,
			border:false,
			fitColumns:true,
			singleSelect:true,
			rownumbers:true,
			pagination:true,
			toolbar:"#tbType",
			columns:[[
			          {field:'sn',title:'序号',width:1},  
			          {field:'name',title:'名称',width:2},  
			          {field:'intro',title:'简介',width:4},
			          {field:'status',title:'状态',width:2,formatter:statusFormatter} 
			]],
			onClickRow:function(rowIndex, rowData){
				$('#did').val(rowData.id);
// 				$.post('/systemDictionaryType/transf',{id:rowData.id},function(detailData){
// 					$('#systemDictionaryDetailTab').datagrid({data:detailData}); 
					$('#systemDictionaryDetailTab').datagrid('load',{id:rowData.id});
// 			    },'json');
			}
		});
		
		//对话框
		typeDlg.dialog({
			title:"添加/编辑数据字典目录",
			resizable:false,
			closed:true, 
		    modal:true, 
		    buttons:[
		   			{
	 			   		text:'保存',
	 			   		iconCls:'icon-save',
	 			   		handler:function(){
	 			   			typeForm.form('submit',{
	 			   				url:'/systemDictionaryType/save',
	 			   				success:function(data){
	 			   					var data = eval('('+data+')');
	 			   					if(data.success){
	 			   						//保存后,1:关闭对话框
	 			   						typeDlg.dialog('close');
	 			   						$.messager.alert('警示信息',data.message,'info',function(){
	 			   							typeGrid.datagrid('reload');			 			   							
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
	 			   			typeForm.form('reset');
	 			   		}
	 			   	},
	 			   	{
	 			   		text:'取消',
	 			   		iconCls:'icon-cancel',
	 			   		handler:function(){
							typeDlg.dialog('close');
	 			   		}
	 			   	}
		  		 ]
		});
		
		// 4、创建一个事件操作对象，管理所有的事件响应函数
		var typeObjType = {
			createType :function(){
				//得先清空缓存
// 				typeForm.form("clear");
				typeForm.form('reset');
				$("#typeDlg").dialog("open");
			},
			editType:function(){
				var row = typeGrid.datagrid('getSelected');
				if(!row){
					$.messager.alert("警示信息","请选择要编辑的记录","info");
					return;
				}
				//存在就加载回显,先清除缓存的
				typeForm.form("clear");
				$("#typeDlg").dialog("open");
				//特殊对象处理,因为这里加载显示值都是根据属性直接设置过去    所以我们得添加一个新的属性来传值
				if(row.types){
					row["types.id"] = row.types.id;
				}
				typeForm.form("load",row);
			},
			deleteType:function(){
				//得到被选中要删除的行对象
				var row = typeGrid.datagrid('getSelected');
				//可能没有选中行,我们得提示
				if(!row){
					$.messager.alert("警示信息","请选择要删除的记录",'info');
					return;
				}
				$.messager.confirm("警示信息","你确定要删除这一行吗",function(yes){
					if(yes){
						//发删除请求
						$.get("/systemDictionaryType/delete?id="+row.id,function(data){
							if(data.success){
								$.messager.alert('警示信息',data.message,'info',function(){
									typeGrid.datagrid('reload');			 			   							
			   					});
							}
						},'json');
					}	
				});
			},
			findType:function(){
				console.debug($("#tstatus").text());
				typeGrid.datagrid("load",{
					name: $("#tname").val(),
					sn:$("#tsn").val(),
					status:$("#tstatus").val(),
					keyword:$("#tkeyword").val()
				}
			);
		},
		refreshTypeGrid:function(){
			typeGrid.datagrid('reload');
		}
		};
		
		// 5、对页面所有的按钮，进行“统一”的事件监听
		$("a[data-type]").on("click",function(){
			var type = $(this).data("type");
			if(type){
				typeObjType[type]();
			}
		});
		
		//6:管理验证框
		$("#tsnForm").validatebox({
			required:true,
		});
		$("#tnameForm").validatebox({
			required:true
		});
		
		//下拉框
		$("#tstatusForm").combobox({
			panelHeight:'auto',
			valueField:'tsid',    
			textField:'text',
			value:1,
			data:[{
				tsid:1,
				text:'正常',
			},{
				tsid:-1,
				text:'停用'
			}]
		});
	});
	
</script>
	
	<div id="tbType">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" data-type='createType' >添加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-type="editType" >编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" data-type="deleteType">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-type="refreshTypeGrid">刷新</a><br/>
		<!-- 高级查询 -->
		<table>
			<tr>
				<td>名称:</td>
				<td><input id="tname" name="name" type="text" style="width: 40px"/></td>
				<td>序号:</td>
				<td><input id="tsn" name="sn" type="text" style="width: 40px"/></td>
				<td>状态:</td>
				<td><select id="tstatus" name="status" class="easyui-combox" panelHeight="auto" style="width: 65px" >
					<option value="" selected="selected">请选择</option>
					<option value="1">正常</option>
					<option value="-1">停用</option>
				</select><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-type="findType">查询</a></td>
				<td>&emsp;关键字:</td>
				<td><input id="tkeyword" name="keyword" align="right" type="text" style="width: 70px"  /></td>
				<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-type="findType">搜索</a></td>
			</tr>
		</table>
	</div>
	
	<!-- 表格组件 -->
	<table id="systemDictionaryTypeTab"></table> 
	
	<!-- 对话框组件 -->
	<div id="typeDlg" class="easyui-dialog" style="width: 300px;height: 240px;">
		<form id="systemDictionaryTypeForm" method="post">
			<table align="center" style="margin-top: 10px">
				<input type="hidden" name="id" />
				<tr>
					<td>序号:</td>
					<td><input id="tsnForm" name="sn" type="text"/></td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input id="tnameForm" name="name" type="text"/></td>
				</tr>
				<tr>
					<td>简介:</td>
					<td><textarea name="intro" rows="2" cols="16"/></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td>
						<input id="tstatusForm" name="status"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>