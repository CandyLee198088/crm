(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var objDetail = null;
	var icon = "info";
	var ddvs = [];
	var columnsArrayWarrantyReceipts = [ [ {
		field : 'sn',
		title : '保修单号',
		width : 10,
		sortable : true
	}, {
		field : 'contract',
		title : '合同',
		formatter : warrantyReceiptsFormatter,
		width : 10,
		sortable : true
	}, {
		field : 'client',
		title : '客户',
		formatter : contractClientFormatter,
		width : 10,
		sortable : true
	}, {
		field : 'createTime',
		title : '保修开始时间',
		width : 10,
		sortable : true
	}, {
		field : 'expireTime',
		title : '保修失效时间',
		width : 10,
		sortable : true
	} ] ];
	function warrantyReceiptsFormatter(v, r, i) {
		if (v) {
			return v.sn;
		}
	}
	function contractClientFormatter(v) {
		if (v) {
			return v.name + " (" + v.sn + ")";
		}
	}
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArrayWarrantyReceipts = new Array([ {
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
	var queryTableArrayWarrantyReceipts = new Array([ {
		title : '名称',
		tagName : "input",
		attributes : {
			type : "text",
			name : "text"
		}
	} ]);
	function resovleFormatter(v) {
		if (v == 0) {
			return "<font color='red'>否</font>";
		} else if (v == 1) {
			return "<font color='green'>是</font>";
		}
	}
	/**
	 * 明细对话框;
	 */
	var detailDataArray = new Array([ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "id"
		}
	} ], [ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "receipt.id"
		}
	} ], [ {
		title : '保修单号',
		tagName : "input",
		attributes : {
			readonly : "readonly",
			type : "text",
			name : "receiptSn"
		}
	} ], [ {
		title : '保修内容',
		tagName : "textarea",
		attributes : {
			name : "summary",
			style : "width:160px;height:50px;"
		}
	} ], [ {
		title : '是否解决',
		tagName : [ "input", "input" ],
		label : [ "是", "否" ],
		attributes : {
			type : [ "radio", "radio" ],
			name : [ "status", "status" ],
			value : [ "1", "0" ]
		}
	} ], [ {
		title : '维修时间',
		tagName : "input",
		attributes : {
			type : "text",
			name : "repairTime"
		},
		methods : {
			validate : function(v) {
				v.datetimebox();
			}
		}
	} ]);
	var detailDataButtonArray = [ {
		text : "提交",
		iconCls : "icon-redo",
		handler : function() {
			saveDataExt(objDetail.form, objDetail.dialog, "/warrantyDetail/save");
		}
	}, {
		text : "取消",
		iconCls : "icon-cancel",
		handler : function() {
			cancelDataExt(objDetail.dialog);
		}
	} ];

	function handleDDVs() {
		if (ddvs.length <= 0) {
			$.messager.alert("温馨提示", "没有明细可以编辑", "error");
			return;
		}
		var flag = 0;
		var index = 0;
		for ( var i = 0; i < ddvs.length; i++) {
			var selectedRow = ddvs[i].ddv.datagrid('getSelected');
			if (selectedRow) {
				index = i;
				flag++;
				if (flag > 1) {
					$.messager.alert("温馨提示", "每次只能编辑一条明细,请取消不需要编辑的行!", "error");
					flag = 0;
					return;
				}
			}
		}
		return ddvs[index].ddv;
	}
	var highChartDlg = $("<div>").appendTo($("#warrantyReceipts").parent());

	highChartDlg.dialog({
		id : "wd_dlg",
		width : 800,
		height : 500,
		title : "保修报表",
		iconCls : "icon-sum",
		//		href : "/warrantyDetail/dispatch",
		modal : true,
		closed : true
	});

	/**
	 * 初始化CRUD模板;
	 */
	initCRUD({
		columnsArray : columnsArrayWarrantyReceipts,
		modelName : 'warrantyReceipts',
		chineseName : '保修信息',
		relationAttrs : [ 'contract' ],
		editTableArray : editTableArrayWarrantyReceipts,
		queryTableArray : queryTableArrayWarrantyReceipts,
		queryDialog : {
			title : "保修信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog : {
			height : 280
		},
		topButtons : [
			{
				text : "添加明细",
				iconCls : "icon-note_add",
				width : 80,
				handler : function(newFormDialog, dataTable) {
					objDetail = newFormDialog(detailDataArray, {
						height : 300,
						title : "保修明细信息编辑"
					}, detailDataButtonArray);
					objDetail['dataTable'] = dataTable;
					initNewDialog(objDetail);
				}
			},
			{
				text : "编辑明细",
				iconCls : "icon-note_edit",
				width : 80,
				handler : function(newFormDialog, dataTable) {
					if (!objDetail) {
						objDetail = newFormDialog(detailDataArray, {
							height : 300,
							title : "保修明细信息编辑"
						}, detailDataButtonArray);
						objDetail['dataTable'] = dataTable;
					}
					var _ddv = handleDDVs();
					if (!_ddv) {
						return;
					}
					initNewDialog(objDetail, _ddv);
				}
			},
			{
				text : "删除明细",
				width : 80,
				iconCls : "icon-note_delete",
				handler : function(newFormDialog, dataTable) {
					objFollow = newFormDialog(followClientArray, {
						height : 400,
						title : "客户跟进信息"
					}, followButtonArray);
					objFollow['dataTable'] = dataTable;
					initNewDialog(objFollow);
				}
			},
			{
				text : "查看报表",
				width : 80,
				iconCls : "icon-sum",
				handler : function(newFormDialog, dataTable) {
					highChartDlg
							.dialog({
								content : "<iframe style='width:100%;height:95%;border:none;' src='/warrantyDetail/dispatch'</iframe>"

							});
					highChartDlg.dialog('open');
				}
			} ],
		initDataTable : function(dataTable, divId) {
			dataTable.datagrid({
				title : '保修信息管理',
				singleSelect : true,
				fit : true,
				url : '/warrantyReceipts/query',
				method : 'post',
				fitColumns : true,
				pagination : true,
				rownumbers : true,
				toolbar : "#" + divId,
				remoteSort : true,
				border : false,
				pageList : [ 5, 10, 15, 20, 30, 50 ],
				collapsible : true,
				columns : columnsArrayWarrantyReceipts,
				striped : true,
				view : detailview,
				detailFormatter : function(index, row) {
					return '<div style="padding:2px"><table class="ddv"></table></div>';
				},
				onExpandRow : function(index, row) {
					var ddv = $(this).datagrid('getRowDetail', index).find('table.ddv');
					ddvs.push({
						ddv : ddv,
						row : row
					});
					ddv.datagrid({
						url : '/warrantyDetail/list?id=' + row.id,
						fitColumns : true,
						singleSelect : true,
						rownumbers : true,
						loadMsg : '正在加载,请稍候...',
						height : 'auto',
						remoteSort : false,
						columns : [ [ {
							field : 'summary',
							title : '保修内容',
							width : 100,
							sortable : true
						}, {
							field : 'repairTime',
							title : '保修时间',
							width : 100,
							align : 'right',
							sortable : true
						}, {
							field : 'status',
							title : '是否解决',
							width : 100,
							formatter : resovleFormatter,
							align : 'right',
							sortable : true
						}, {
							field : 'receipt',
							title : '保修单号',
							width : 100,
							formatter : snFormatter,
							align : 'right',
							sortable : true
						} ] ],
						onResize : function() {
							dataTable.datagrid('fixDetailRowHeight', index);
						},
						onLoadSuccess : function() {
							setTimeout(function() {
								dataTable.datagrid('fixDetailRowHeight', index);
							}, 0);
						}
					});

					dataTable.datagrid('fixDetailRowHeight', index);
				},
				onCollapseRow : function(index, row) {
					ddvs.del(row);
				}
			});
		}

	});
	Array.prototype.del = function(ele) {
		for ( var i = 0; i < this.length; i++) {
			if (ele == this[i].row) {
				this.splice(i, 1);
				return;
			}
		}
	};
	/*
	 * 初始化新对话框;
	 */
	function initNewDialog(obj, datagrid) {
		var msg = null;
		if (!datagrid) {
			msg = "请选择保修单(非明细)再进行操作!";
		} else {
			msg = "请选择保修单【明细】再进行操作!";
		}
		obj['form'].form('reset');
		obj['form'].show();
		var rowData = (datagrid || obj['dataTable']).datagrid("getSelected");
		if (rowData) {
			obj['dialog'].dialog('open');
			try {
				rowData['receipt.id'] = rowData.receipt.id;
			} catch (e) {
				rowData['receipt.id'] = rowData.id;
			}
			rowData['receiptSn'] = rowData.sn || rowData.receipt.sn;
			obj['form'].form("load", rowData);
		} else {
			$.messager.alert("温馨提示", msg, "info");
		}
	}
	/**
	 * 保存;
	 */
	function saveDataExt(baseForm, baseDialog, url) {
		baseForm.form('submit', {
			url : url,
			success : function(data) {
				try {
					data = $.parseJSON(data);
					msg = data.message;
				} catch (e) {
					msg = "出错了!!!" + data;
					icon = "error";
				}
				$.messager.alert("温馨提示", msg, icon, function() {
					baseDialog.dialog('close');
					$.each(ddvs, function(i, v) {
						v.ddv.datagrid("reload");
					});
				});
			}
		});
	}
	/**
	 * 取消;
	 */
	function cancelDataExt(baseDialog) {
		baseDialog.dialog('close');
	}
})();
