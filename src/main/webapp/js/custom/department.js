(function() {
	var columnsArrayDept = [ [ {
		field : 'name',
		title : '名称',
		width : 10,
		sortable : true
	}, {
		field : 'address',
		title : '地址',
		width : 10,
		sortable : true
	} ] ];

	var editTableArrayDepartment = new Array([ {
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
			name : "name"
		}
	} ], [ {
		title : '地址',
		tagName : "input",
		attributes : {
			type : "text",
			name : "address"
		}
	} ]);

	/**
	 * 初始化department的crud组件;
	 */
	initCRUD({
		columnsArray : columnsArrayDept,
		modelName : 'department',
		chineseName : '部门',
		editTableArray : editTableArrayDepartment,
		queryTableArray : editTableArrayDepartment,
		editDialog : {
			height : 160
		},
		queryDialog:{
			title:"部门信息高级查询",
			iconCls:"icon-search",
			height:160
		}
	});

})();