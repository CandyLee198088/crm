(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArrayPotentialClient = [ [ {
		field : 'sn',
		title : '编号',
		width : 10,
		sortable : true
	}, {
		field : 'source',
		title : '客户来源',
		width : 10,
		formatter : nameFormatter,
		sortable : true
	}, {
		field : 'name',
		title : '姓名',
		width : 10,
		sortable : true
	}, {
		field : 'successChance',
		title : '成功几率',
		width : 10,
		sortable : true
	}, {
		field : 'description',
		title : '描述',
		width : 10,
		sortable : true
	}, {
		field : 'tel',
		title : '联系电话',
		width : 10,
		sortable : true
	}, {
		field : 'createStaff',
		title : '创建人',
		formatter : followClientFormatter,
		width : 10,
		sortable : true
	}, {
		field : 'createTime',
		title : '创建时间',
		width : 10,
		sortable : true
	} ] ];
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArrayPotentialClient = new Array([ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "id"
		}
	} ], [ {
		title : '编号',
		tagName : "input",
		attributes : {
			type : "text",
			name : "sn"
		}
	} ], [ {
		title : '客户来源',
		tagName : "select",
		attributes : {
			name : "source.id"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					panelHeight : "auto",
					url : '/systemDictionaryType/transf?id=2',
					valueField : 'id',
					textField : 'name'
				});
			}
		}

	} ], [ {
		title : '成功几率',
		tagName : "input",
		attributes : {
			name : "successChance"
		},
		methods : {
			handler : function(f) {
				f.numberbox({
					min:0,
					max:100
				});
			}
		}
	} ],[ {
		title : '电话',
		tagName : "input",
		attributes : {
			name : "tel"
		}
	} ],[ {
		title : '描述',
		tagName : "textarea",
		attributes : {
			name : "description",
			style : "width:160px;height:100px;"
		}
	} ]);
	/**
	 * 定义高级查询对话框中的属性,自定义;
	 */
	var queryTableArrayPotentialClient = new Array([ {
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
		columnsArray : columnsArrayPotentialClient,
		modelName : 'potentialClient',
		chineseName : '潜在客户信息',
		relationAttrs : [ 'source' ],
		editTableArray : editTableArrayPotentialClient,
		queryTableArray : queryTableArrayPotentialClient,
		queryDialog : {
			title : "潜在客户信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog : {
			height : 350
		},
	});
})();
