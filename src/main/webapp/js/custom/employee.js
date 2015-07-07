$(function() {
	var roleEmployeeDlg = $("#roleEmployeeDlg");
	var roleEmployeeForm = $("#employeeForm");
	var employeeGrid1 = $("#employee_grid1");
	var employeeGrid2 = $("#employee_grid2");
	roleEmployeeDlg.dialog({
		title : "添加/编辑员工角色",
		resizable : false,
		closed : true,
		modal : true,
		buttons : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				roleEmployeeDlg.dialog('close');
				roleEmployeeForm.form('submit', {
					url : '/employee/saveRole',
					onSubmit : function(param) {
						var datas = employeeGrid2.datagrid('getRows');
						console.debug(datas);
						for ( var i = 0; i < datas.length; i++) {
							param['roles[' + i + '].id'] = datas[i].id;
						}
						//						return false;
						return roleEmployeeForm.form('validate');
					},
					success : function(data) {
						data = eval('(' + data + ')');
						if (data.success) {
							//保存后,1:关闭对话框
							$.messager.alert('警示信息', data.message, 'info', function() {
								//								roleGrid.datagrid('reload');
							});
						}
					}
				});
			}
		}, {
			text : '重置',
			iconCls : 'icon-reload',
			handler : function() {
				roleEmployeeForm.form('reset');
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				roleEmployeeDlg.dialog('close');
			}
		} ]
	});
	/**
	 * 两个表格
	 */
	$.extend($.fn.datagrid.defaults, {
		singleSelect : true,
		method : 'post',
		fitColumns : true,
		pagination : true,
		rownumbers : true,
		border : true,
		fit : true,
	});
	employeeGrid1.datagrid({
		url : "/role/query",
		title : "所有角色列表",
		columns : [ [ {
			field : 'sn',
			title : '角色编号',
			width : 50
		}, {
			field : 'name',
			title : '角色名称',
			width : 50
		} ] ],
		onDblClickRow : function(index, row) {
			var gd2Datas = employeeGrid2.datagrid('getRows');
			for ( var i = 0; i < gd2Datas.length; i++) {
				if (gd2Datas[i] == row) {
					return;
				}
			}
			employeeGrid2.datagrid('appendRow', row);
		}
	});
	employeeGrid2.datagrid({
		title : "该用户具有的角色",
		pagination : false,
		columns : [ [ {
			field : 'sn',
			title : '角色编号',
			width : 50
		}, {
			field : 'name',
			title : '角色名称',
			width : 50
		} ] ],
		onDblClickRow : function(index, row) {
			employeeGrid2.datagrid('deleteRow', index);
		}
	});

	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArrayEmployee = [ [ {
		field : 'id',
		title : '编号',
		width : 10,
		sortable : true
	}, {
		field : 'username',
		title : '员工账号',
		width : 10,
		sortable : true
	}, {
		field : 'truename',
		title : '真名',
		width : 10,
		sortable : true
	}, {
		field : 'tel',
		title : '电话',
		width : 10,
		sortable : true
	}, {
		field : 'email',
		title : '邮箱',
		width : 10,
		sortable : true
	}, {
		field : 'inTime',
		title : '录入时间',
		width : 10,
		sortable : true
	}, {
		field : 'status',
		title : '状态',
		width : 10,
		formatter : statusFormatter,
		sortable : true
	}, {
		field : 'department',
		title : '部门',
		width : 10,
		formatter : deptFormatter,
		sortable : true
	} ] ];
	/**
	 * 格式化部门,显示部门名字
	 */
	function deptFormatter(v, r, i) {
		if (v) {
			return v.name;
		}
	}
	/**
	 * 显示中文状态
	 */
	function statusFormatter(v, r, i) {
		if (v == 0) {
			return "正常";
		} else {
			return "离职";
		}
	}
	/**
	 * 定义表单编辑中的属性,自定义; 外层数组代表行数, 内层数组代表列数
	 */
	var editTableArrayEmployee = new Array([ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "id"
		}
	} ], [ {
		title : '员工账号',
		tagName : "input",
		attributes : {
			type : "text",
			name : "username"
		},
		methods : {
			validate : function(v) {
				v.validatebox({
					required : true,
					validType : 'length[2,10]'
				});
			}
		}
	}, {
		title : '真实姓名',
		tagName : "input",
		attributes : {
			type : "text",
			name : "truename"
		},
		methods : {
			validate : function(v) {
				v.validatebox({
					required : true,
					validType : 'length[2,10]'
				});
			}
		}
	} ], [ {
		title : '密码',
		tagName : "input",
		attributes : {
			// readonly:"readonly",
			type : "text",
			name : "password"
		},
	}, {
		title : '电话',
		tagName : "input",
		attributes : {
			type : "text",
			name : "tel"
		}
	} ], [ {
		title : '邮箱',
		tagName : "input",
		attributes : {
			type : "text",
			name : "email"
		},
		methods : {
			validate : function(v) {
				v.validatebox({
					required : true,
					validType : 'email'
				});
			}
		}
	}, {
		title : '部门',
		tagName : "select",
		attributes : {
			id : "dept",
			name : "department.id"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					panelHeight : "auto",
					url : '/department/list',
					valueField : 'id',
					textField : 'name'
				});
			}
		}
	} ], [ {
		title : '录入时间',
		tagName : "input",
		attributes : {
			type : "text",
			name : "inTime"
		},
		methods : {
			handler : function(f) {
				f.datebox({
					required : true
				});
			}
		}
	} ]);
	/**
	 * 定义高级查询对话框中的属性,自定义;
	 */
	var queryTableArrayEmployee = new Array([ {
		title : '员工账号',
		tagName : "input",
		colspan : 3,
		attributes : {
			type : "text",
			name : "username"
		}
	} ], [ {
		title : '真名',
		tagName : "input",
		colspan : 3,
		attributes : {
			type : "text",
			name : "truename"
		}
	} ], [ {
		title : '状态',
		tagName : "select",
		colspan : 3,
		attributes : {
			type : "text",
			name : "status"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					valueField : 'label',
					textField : 'value',
					panelHeight : "auto",
					data : [ {
						label : '0',
						value : '正常'
					}, {
						label : '1',
						value : '离职'
					} ]
				});
			}
		}
	} ], [ {
		title : '录入时间',
		tagName : "input",
		attributes : {
			type : "text",
			name : "minTime",
			style : "width:60px"
		},
		methods : {
			handler : function(f) {
				f.datebox({
					required : false
				});
			}
		}
	}, {
		title : '至',
		tagName : "input",
		attributes : {
			type : "text",
			name : "maxTime",
			style : "width:60px"
		},
		methods : {
			handler : function(f) {
				f.datebox({
					required : false
				});
			}
		}
	} ], [ {
		title : '邮件',
		tagName : "input",
		colspan : 3,
		attributes : {
			type : "text",
			name : "email"
		}
	} ], [ {
		title : '部门',
		tagName : "select",
		colspan : 3,
		attributes : {
			id : "dept",
			name : "department_id"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					//					panelHeight : "auto",
					url : '/department/list',
					valueField : 'id',
					textField : 'name'
				});
			}
		}

	} ]);
	/**
	 * 初始化CRUD模板;
	 */
	initCRUD({
		columnsArray : columnsArrayEmployee,
		modelName : 'employee',
		chineseName : '员工',
		relationAttrs : [ 'department' ],
		editTableArray : editTableArrayEmployee,
		queryTableArray : queryTableArrayEmployee,
		queryDialog : {
			title : "员工信息高级查询",
			iconCls : "icon-search",
			height : 300
		},
		editDialog : {
			height : 220,
			width : 500
		},
		topButtons : [ {
			text : "角色维护",
			width : 80,
			iconCls : "icon-status_online",
			handler : function(newDialogRow, dataTable) {
				var rowData = dataTable.datagrid('getSelected');
				if (rowData) {
					employeeGrid2.datagrid('loadData', rowData.roles);
					roleEmployeeForm.show();
					roleEmployeeForm.form('load', rowData);
					roleEmployeeDlg.dialog('open');
				} else {
					$.messager.alert("温馨提示", "请选择一行再编辑", "info");
				}
			}
		} ]
	});
});
