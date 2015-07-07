(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArraySystemLog = [ [  {
		field : 'opUser',
		title : '操作人',
		formatter:followClientFormatter,
		width : 10,
		sortable : true
	},{
		field : 'opTime',
		title : '操作时间',
		width : 10,
		sortable : true
	},{
		field : 'opIp',
		title : 'IP',
		width : 8,
		sortable : true
	},{
		field : 'function',
		title : '访问资源',
		width : 30,
		sortable : true
	},{
		field : 'args',
		title : '参数',
		width : 20,
		sortable : true
	} ] ];
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArraySystemLog = new Array([ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "id"
		}
	} ]);
	/**
	 * 定义高级查询对话框中的属性,自定义;
	 */
	var queryTableArraySystemLog = new Array( [ {
		title : '名称',
		tagName : "input",
		attributes : {
			type : "text",
			name : "text"
		}
	} ]);
	/**
	 * 初始化CRUD模板;
	 */
	 initCRUD({
		columnsArray : columnsArraySystemLog,
		modelName : 'systemLog',
		preventDefaultBtn : true,
		chineseName : '系统日志信息',
		relationAttrs : [ 'opUser' ],
		editTableArray : editTableArraySystemLog,
		queryTableArray : queryTableArraySystemLog,
		queryDialog : {
			title : "系统日志信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog:{
			height:280
		},
		topButtons:[{
			text : "删除",
			iconCls : "icon-remove",
			handler : function(newFormDialog, dataTable) {
				var messageTitle = "温馨提示";
				var icon = "info";
				var rowData = dataTable.datagrid("getSelected");
				if (rowData) {
					$.messager.confirm(messageTitle, "确认删除数据?", function(yes) {
						if (yes) {
							$.post("/systemLog/delete", {
								id : rowData.id
							}, function(data) {
								$.messager.alert(messageTitle, data.message, icon, function() {
									dataTable.datagrid("reload");
								});
							});
						}
					});
				} else {
					$.messager.alert(messageTitle, "请选中一行再删除!", icon);
				}
			
			}
		}]
	});
})();
