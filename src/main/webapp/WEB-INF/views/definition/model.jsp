<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@include file="/WEB-INF/views/base.jsp"%>
<style type="text/css">
	a{
		text-decoration: none;
	}
</style>
</head>
<body>
</body>
<script type="text/javascript">
$(function(){
	var modelTable = $("#modelTable");
	var modelInfoDlg = $("#modelInfoDlg");
	var modelForm = $("#modelForm");
	modelInfoDlg.dialog({
		title:"模型新增",
		width:300,
		height:250,
		closed:true,
		buttons:[
		         {text:"新建模型",iconCls:"icon-save",handler:function(){
		        	 modelForm.submit();
		         }},{
		        	 text:"取消",iconCls:"icon-cancel",handler:function(){
		        		 modelInfoDlg.dialog('close');
			         }
		         }
		         
		         ]
	});
	modelTable.datagrid({
		title : '任务模型管理',
		singleSelect : true,
		fit : true,
		url : "/myModel/list",
		method : 'post',
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		border : false,
		pageList : [ 5, 10, 15, 20, 30, 50 ],
		collapsible : true,
		toolbar : [ {
			text:"刷新",
			iconCls:"icon-reload",
			handler:function(){
				modelTable.datagrid('load');
			}
		},{
			text:"添加模型",
			iconCls:"icon-add",
			handler:function(){
				modelInfoDlg.dialog('open');
			}
		},{
			text:"编辑模型",
			iconCls:"icon-edit",
			handler:function(){
				var row= modelTable.datagrid('getSelected');
				if(row){
					window.open("/myModel/editor?id="+row.id);
				}
			}
		},{
			text:"删除模型",
			iconCls:"icon-cancel",
			handler:function(){
				var row= modelTable.datagrid('getSelected');
				if(row){
					$.messager.confirm("温馨提示","确认删除这个模型?",function(yes){
						if(yes){
							$.post("/myModel/delete",{modelId:row.id},function(data){
								$.messager.alert("温馨提示",data.message,'info',function(){
									modelTable.datagrid('reload');
								});
							},'json');
						}
					})
				}
			}
		},{
			text:"部署模型",
			iconCls:"icon-ok",
			handler:function(){
				var row= modelTable.datagrid('getSelected');
				if(row){
					$.post("/myModel/deploy",{modelId:row.id},function(data){
						$.messager.alert("温馨提示",data.message,'info');
					},'json');
				}
			}
		}],
		columns : [ [ 
		    {title:"模型ID",field:"id",width:10}
		   ,{title:"模型名称",field:"name",width:10}
		   ,{title:"创建时间",field:"createTime",formatter:timeFormatter,width:10}
		   ,{title:"key",field:"key",width:10}
		   ,{title:"版本",field:"version",width:10}
		] ]
	});
	function timeFormatter(v,r,i){
		if(v){
			return new Date(v).toLocaleString();
		}
	}
});

</script>
<table id="modelTable"></table>
<div id="modelInfoDlg">
	<form action="/myModel/create" id="modelForm" method="post">
		<table style="margin: 20px auto;" >
			<tr>
				<td>模型名称:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>模型key:</td>
				<td><input type="text" name="key"></td>
			</tr>
			<tr>
				<td>模型描述:</td>
				<td><textarea cols="22" rows="2" name="description"></textarea></td>
			</tr>
		</table>
	</form>
</div>
</html>