(function() {
	/**
	 * 定义需要显示的列属性,easyui集成;
	 */
	var columnsArrayClientDevelopPlan = [ [  {
		field : 'sn',
		title : '编号',
		width : 10,
		sortable : true
	},{
		field : 'planTime',
		title : '计划时间',
		width : 10,
		sortable : true
	},{
		field : 'summary',
		title : '主题',
		width : 10,
		sortable : true
	},{
		field : 'content',
		title : '内容',
		width : 10,
		sortable : true
	},{
		field : 'execWay',
		title : '执行方式',
		formatter:nameFormatter,
		width : 10,
		sortable : true
	},{
		field : 'pClient',
		title : '潜在客户',
		formatter:clientFormatter,
		width : 10,
		sortable : true
	},{
		field : 'createStaff',
		title : '创建人',
		formatter:followClientFormatter,
		width : 10,
		sortable : true
	},{
		field : 'createTime',
		title : '创建时间',
		width : 10,
		sortable : true
	} ] ];
	function clientDevelopPlanFormatter(v,r,i){
		if(v){
			return v.name||v.truename;
		}
		
	}
	/**
	 * 定义表单编辑中的属性,自定义;
	 */
	var editTableArrayClientDevelopPlan = new Array([ {
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
	var queryTableArrayClientDevelopPlan = new Array( [ {
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
		columnsArray : columnsArrayClientDevelopPlan,
		modelName : 'clientDevelopPlan',
		chineseName : '潜在客户开发计划信息',
		relationAttrs : [ 'parent' ],
		editTableArray : editTableArrayClientDevelopPlan,
		queryTableArray : queryTableArrayClientDevelopPlan,
		queryDialog : {
			title : "潜在客户开发计划信息高级查询",
			iconCls : "icon-search",
			height : 280
		},
		editDialog:{
			height:280
		},
	});
})();
