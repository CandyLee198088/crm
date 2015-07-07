(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArrayMenu = [ [ {
		field : 'text',
		title : '名称',
		width : 10,
		sortable : true
	}, {
		field : 'url',
		title : '地址',
		width : 10,
		sortable : true
	}, {
		field : 'iconCls',
		title : '图标样式',
		width : 10,
		sortable : true
	}, {
		field : 'description',
		title : '描述',
		width : 10,
		sortable : true
	}, {
		field : 'parent',
		title : '父级菜单',
		width : 10,
		formatter : menuFormatter,
		sortable : true
	} ] ];
	function menuFormatter(v, r, i) {
		if (v) {
			return v.text;
		}
	}
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArrayMenu = new Array([ {
		title : '',
		tagName : "input",
		attributes : {
			type : "hidden",
			name : "id"
		}
	} ], [ {
		title : '名称',
		tagName : "input",
		attributes : {
			type : "text",
			name : "text"
		},
		methods : {
			validate : function(v) {
				v.validatebox({
					required : true,
					validType : 'length[3,10]'
				});
			}
		}
	} ], [ {
		title : '地址',
		tagName : "input",
		attributes : {
			type : "text",
			name : "url"
		}
	} ], [ {
		title : '样式',
		tagName : "input",
		attributes : {
			type : "text",
			name : "iconCls"
		}
	} ],[ {
		title : '描述',
		tagName : "textarea",
		attributes : {
			name : "description",
			rows : 3,
			style:"width:160px;font-size:12px;"
		}
	} ], [ {
		title : '父菜单',
		tagName : "select",
		attributes : {
			name : "parent.id"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					panelHeight : "auto",
					url : '/menu/findParents',
					valueField : 'id',
					textField : 'text'
				});
			}
		}

	} ]);
	/**
	 * 定义高级查询对话框中的属性,自定义;
	 */
	var queryTableArrayEmployee = new Array( [ {
		title : '名称',
		tagName : "input",
		attributes : {
			type : "text",
			name : "text"
		}
	} ], [ {
		title : '地址',
		tagName : "input",
		attributes : {
			type : "text",
			name : "url"
		}
	} ], [ {
		title : '样式',
		tagName : "input",
		attributes : {
			type : "text",
			name : "iconCls"
		}
	} ], [ {
		title : '父菜单',
		tagName : "select",
		attributes : {
			name : "parentId"
		},
		methods : {
			handler : function(f) {
				f.combobox({
					panelHeight : "auto",
					url : '/menu/findParents',
					valueField : 'id',
					textField : 'text'
				});
			}
		}

	} ]);
	/**
	 * 初始化CRUD模板;
	 */
	initCRUD({
		columnsArray : columnsArrayMenu,
		modelName : 'menu',
		chineseName : '菜单',
		relationAttrs : [ 'parent' ],
		editTableArray : editTableArrayMenu,
		queryTableArray : queryTableArrayEmployee,
		queryDialog : {
			title : "菜单信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog:{
			height:280
		},
	});
})();
