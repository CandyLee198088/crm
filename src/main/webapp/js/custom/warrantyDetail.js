(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArrayWarrantyDetail = [ [  {
		field : 'name',
		title : '姓名',
		width : 10,
		sortable : true
	} ] ];
	function warrantyDetailFormatter(v,r,i){
		if(v){
			return v.name||v.truename;
		}
		
	}
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArrayWarrantyDetail = new Array([ {
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
	var queryTableArrayWarrantyDetail = new Array( [ {
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
		columnsArray : columnsArrayWarrantyDetail,
		modelName : 'warrantyDetail',
		chineseName : '保修明细信息',
		relationAttrs : [ 'parent' ],
		editTableArray : editTableArrayWarrantyDetail,
		queryTableArray : queryTableArrayWarrantyDetail,
		queryDialog : {
			title : "保修明细信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog:{
			height:280
		},
	});
})();
