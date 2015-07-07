/**
 * 封装前台easyui CRUD,高级查询的框架;
 * @author 李璨
 * 2015.04.25
 */
var initCRUD =
		function(options) {
			if (!options) {
				throw new Error("options必须设置;");
			}
			/**
			 * 公共属性申明及form,table创建;
			 */
			var modelName = options.modelName;
			var editDialog = options.editDialog || {};
			var queryDialog = options.queryDialog || {};
			var rootEle = $("#" + modelName).parent();
			/**
			 * url相关属性申明;
			 */
			var urlSeparator = options.urlSeparator || "/";
			var urlSuffix = options.urlSuffix || '';
			var urlList = options.urlList || getUrl('query');
			var urlDelete = options.urlDelete || getUrl('delete');
			var urlSave = options.urlSave || getUrl('save');
			/**
			 * 属性设置;
			 */
			var chineseName = options.chineseName || modelName;
			var messageTitle = "温馨提示", icon = 'info';
			var isShowadvancedBtn = options.isShowadvancedBtn = true;

			/**
			 * [编辑]对话框底部按钮;
			 */
			var editButtonArray = handleDialogButton("提交", "icon-ok", saveData);
			/**
			 * [高级查询]对话框底部按钮;
			 */
			var advancedQueryButtonArray = handleDialogButton("查询", "icon-search", queryData);
			/**
			 * 创建form,table
			 */
			var dataObj = newFormDialog(options.editTableArray, editDialog, editButtonArray);
			var dataDialog = dataObj.dialog;
			var dataForm = dataObj.form;
			var advancedQueryObj =
					newFormDialog(options.queryTableArray, queryDialog, advancedQueryButtonArray);
			var advancedQueryDialog = advancedQueryObj.dialog;
			var queryForm = advancedQueryObj.form;
			var dataTable = createElement("table", rootEle);
			/**
			 * 创建jQuery元素
			 * @param tagName dom元素名称
			 * @param parentEle 父元素
			 * @returns 创建好的新元素
			 */
			function createElement(tagName, parentEle, name) {
				return $("<" + tagName + " name=" + name + ">").appendTo(parentEle);
			}
			/**
			 * 产生一个新的对话框并且可以自由扩展;
			 */
			function newFormDialog(tableDataArray, editDialog, editButtonArray) {
				var _dialog = createElement("div", rootEle, "myDialog");
				var _form = createElement("form", _dialog).css("display", "none");
				_form.attr('method', 'post');
				var _table = createElement("table", _form).css("margin", "10px auto");
				generateTwoColumnsForm(tableDataArray, _table);
				generateDialog(_dialog, editDialog, editButtonArray);
				return {
					dialog : _dialog,
					form : _form,
					table : _table
				};
			}

			/**
			 * 创建url
			 * 必须写"/"
			 * @param param 访问的方法;
			 * @returns 返回url
			 */
			function getUrl(param) {
				return "/" + modelName + urlSeparator + param + urlSuffix;
			}

			/**
			 * 数据表格toolBar表格
			 */
			var divId = "toolbar" + modelName;
			var toolbarDiv = createElement("div", rootEle).attr("id", divId);
			var toolbarElement = createElement("table", toolbarDiv).css("width", "100%");
			var tb_tr = createElement("tr", toolbarElement);
			var preventDefaultBtn = options.preventDefaultBtn ;
			if (options.topButtons) {
				if (!preventDefaultBtn) {
					userDefaultBtn();
				}
				$.each(options.topButtons, function(i, v) {
					generateButtonForToolBar(v.text, v.iconCls, v.handler, v.width);
				});
				/**
				 * 删除按钮的设置
				 */
				generateButtonForToolBar("刷新", "icon-reload", function() {
					dataTable.datagrid();
					dataTable.datagrid('reload');
				});

			} else {
				userDefaultBtn();
				/**
				 * 删除按钮的设置
				 */
				generateButtonForToolBar("刷新", "icon-reload", function() {
					dataTable.datagrid();
					dataTable.datagrid('reload');
				});
			}
			
			function userDefaultBtn(){
				/**
				 * 添加按钮的设置
				 */
				generateButtonForToolBar("添加", "icon-add", createData);
				/**
				 * 编辑按钮的设置
				 */
				generateButtonForToolBar("编辑", "icon-edit", updateData);
				/**
				 * 删除按钮的设置
				 */
				generateButtonForToolBar("删除", "icon-remove", removeData);
			}
			/**
			 * 搜索框的设置;
			 */
			var searchBox = $("<input>");
			($("<td>")).css({
				"text-align" : "right",
				"padding" : "5px 10px 0"
			}).append(searchBox).appendTo(tb_tr);
			searchBox.searchbox({
				prompt : '请输入关键字....',
				searcher : function(value) {
					queryByKeyword(value);
				}
			});
			/**
			 * 高级查询按钮的设置
			 */
			if (isShowadvancedBtn) {
				generateButtonForToolBar("高级查询", "icon-search", openQueryDialog, "100");
			}

			/**
			 * 用于编辑数据的对话框;
			 */
			//		generateDialog(dataDialog, editDialog, editButtonArray);
			/**
			 * 用于高级查询的对话框;
			 */
			//	generateDialog(advancedQueryDialog, queryDialog, advancedQueryButtonArray);
			/**
			 * 处理对话框下面两个按钮对象;
			 * 
			 * @param dialog 目标对话框;
			 * @param text1 按钮1的文本;
			 * @param iconCls1  按钮1的样式;
			 * @param method1 按钮1点击的方法;
			 * @param text2 按钮2的文本;
			 * @param iconCls2 按钮2的样式;
			 * @param method2 按钮2点击的方法;
			 * @returns {Array} 该按钮数组;
			 */

			function handleDialogButton(text1, iconCls1, method1, text2, iconCls2) {
				var _buttonArr = [ {
					text : text1,
					iconCls : iconCls1,
					handler : function() {
						method1();
					}
				}, {
					text : text2 || "取消",
					iconCls : iconCls2 || "icon-cancel",
					handler : function() {
						cancelData($(this).closest("div[name=myDialog]"));
					}
				} ];
				return _buttonArr;
			}

			/**
			 * 数据表格初始化,可以外界传入
			 */
			if (!options.initDataTable) {
				dataTable.datagrid({
					title : chineseName + '管理',
					singleSelect : true,
					fit : true,
					url : urlList,
					method : 'post',
					fitColumns : true,
					pagination : true,
					rownumbers : true,
					toolbar : "#" + divId,
					remoteSort : true,
					border : false,
					pageList : [ 5, 10, 15, 20, 30, 50 ],
					collapsible : true,
					columns : options.columnsArray || [],
					striped : true
				});
			} else {
				options.initDataTable(dataTable, divId);
			}
			/**
			 * 由div生成dialog方法
			 * 
			 * @param tempDiv  div的jQuery对象,用于生成dialog
			 * @param waitEditDialog  从外面传入的自定义dialog属性;
			 * @param buttons  对话框底部按钮
			 */
			function generateDialog(tempDiv, waitEditDialog, buttons) {
				tempDiv.dialog({
					width : waitEditDialog.width || 300,
					height : waitEditDialog.height || 220,
					buttons : buttons,
					title : waitEditDialog.title || (chineseName + '信息编辑'),
					iconCls : waitEditDialog.iconCls || 'icon-tip',
					closed : true,
					modal : true
				});
			}
			/**
			 * 打开高级查询对话框;
			 */
			function openQueryDialog() {
				openDialog(queryForm, advancedQueryDialog);
			}
			/**
			 * 打开对话框
			 * @param formObj
			 * @param dialogObj
			 */
			function openDialog(formObj, dialogObj) {
				formObj.show();
				dialogObj.dialog('open');
			}
			/**
			 * 新增数据,弹出对话框;
			 */
			function createData() {
				dataForm.form('clear');
				openDialog(dataForm, dataDialog);
			}
			/**
			 * 更新数据,将数据回显到表单内; relationAttrs为关联属性,比如department为employee的关联属性;
			 */
			function updateData() {
				dataForm.show();
				var relationAttrs = options.relationAttrs;
				dataForm.form('clear');
				var rowData = dataTable.datagrid("getSelected");
				if (rowData) {
					dataDialog.dialog('open');
					if (relationAttrs) {
						for ( var i = 0; i < relationAttrs.length; i++) {
							var _data = rowData[relationAttrs[i]];
							if (_data) {
								try {
									rowData[relationAttrs[i] + '.id'] = _data.id;
									rowData[relationAttrs[i] + '.truename'] =
											_data.truename + "(" + _data.username + ")";
								} catch (e) {
									rowData[relationAttrs[i] + '.id'] = _data.id;
								}
							}
						}
					}
					dataForm.form("load", rowData);
				} else {
					$.messager.alert(messageTitle, "请选中一行再编辑!", icon);
				}
			}
			/**
			 * 删除数据; 利用Ajax异步删除数据;
			 */
			function removeData() {
				var rowData = dataTable.datagrid("getSelected");
				if (rowData) {
					$.messager.confirm(messageTitle, "确认删除数据?", function(yes) {
						if (yes) {
							$.post(urlDelete, {
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
			/**
			 * 保存数据;
			 */
			function saveData() {
				save(dataForm, dataDialog, dataTable, urlSave);
			}
			/**
			 * 通过搜索框查询keyword
			 */
			function queryByKeyword(value) {
				queryForm.form('clear');
				dataTable.datagrid("load", {
					keyword : value
				});
			}
			/**
			 * 高级查询,让dataTable从后台加载数据;
			 */
			function queryData() {
				var params = {};
				queryForm.find("input,select").each(function(index, obj) {
					obj = $(obj);
					var name = obj.attr("name");
					if (name) {
						params[name] = obj.val();
					}
				});
				advancedQueryDialog.dialog('close');
				dataTable.datagrid("load", params);
			}
			/**
			 * 保存;
			 */
			function save(formObj, dialogObj, dataTableObj, url, flag) {
				formObj.form('submit', {
					url : url,
					success : function(data) {
						try {
							data = $.parseJSON(data);
							msg = data.message;
						} catch (e) {
							msg = "出错了!!!" + data;
							icon = "error";
						}
						$.messager.alert(messageTitle, msg, icon, function() {
							dialogObj.dialog('close');
							dataTableObj.datagrid("reload");
						});
					}
				});
			}
			/**
			 * 关闭对话框;
			 */
			function cancelData(baseDialog) {
				baseDialog.dialog('close');
			}
			/**
			 * 用于生成N列N行表单的方法;
			 * 
			 * @param tableDataArray 表单的格式数据;
			 * @param table  表单中唯一表格;
			 */
			function generateTwoColumnsForm(tableDataArray, table) {
				if (table.find('tr')) {
					table.find('tr').remove();
				}
				var attributes = methods = null;
				$.each(tableDataArray, function(index, rowArr) {
					var tr = createElement("tr", table);
					$(rowArr).each(
							function(i, columnsObj) {
								if (columnsObj && columnsObj.attributes
										&& columnsObj.attributes.type != 'hidden') {
									$('<td>').html(columnsObj.title + "：").css("text-align",
											"right").appendTo(tr);
								}
								if ($.type(columnsObj.tagName) == 'array') {
									var td2 = $('<td>').css("text-align", "center");
									$(columnsObj.tagName).each(
											function(i, v) {
												var baseForm = $('<' + v + '>');// input标签等
												attributes = columnsObj.attributes;
												methods = columnsObj.methods;
												if (attributes) {
													$.each(attributes, function(name, value) {
														if ($.type(value[i]) == 'string') {
															baseForm.attr(name, value[i]);// 表单项目attributes设置;
														}
													});
												}
												if (columnsObj.colspan) {
													td2.attr("colspan", columnsObj.colspan);
												}
												if (columnsObj.label) {
													td2.append($("<label>").append(
															"&emsp;" + columnsObj.label[i]));
													baseForm.css("width", "auto");
												}
												td2.append(baseForm).appendTo(tr);
												if (methods) {
													$.each(methods, function(name, value) {
														if ($.type(value) == 'function') {
															methods[name](baseForm);// 运行methods中的方法;
														}
													});
												}
											});
								} else if ($.type(columnsObj.tagName) == 'string') {
									var baseForm = $('<' + columnsObj.tagName + '>');// input标签等
									attributes = columnsObj.attributes;
									methods = columnsObj.methods;
									if (attributes) {
										$.each(attributes, function(name, value) {
											if ($.type(value) == 'string') {
												baseForm.attr(name, value);// 表单项目attributes设置;
											}
										});
									}
									var td2 = $('<td>');
									if (columnsObj.colspan) {
										td2.attr("colspan", columnsObj.colspan);
									}
									if (columnsObj.label) {
										td2.append($("<label>").append(columnsObj.label));
										baseForm.css("width", "auto");
									}
									td2.append(baseForm).appendTo(tr);
									if (methods) {
										$.each(methods, function(name, value) {
											if ($.type(value) == 'function') {
												methods[name](baseForm);// 运行methods中的方法;
											}
										});
									}
								}
							});
				});
			}
			/**
			 * 生成表格上toolBar的按钮
			 * 
			 * @param text 按钮文本
			 * @param iconCls  按钮样式
			 * @param method 点击触发的方法
			 * @returns 按钮对象
			 */
			function generateButtonForToolBar(text, iconCls, method, width) {
				var srcBtn = $("<a>");
				$("<td>").css("width", (width || "58") + "px").append(srcBtn).appendTo(tb_tr);
				srcBtn.linkbutton({
					text : text,
					iconCls : iconCls,
					plain : true
				});
				srcBtn.on('click', function() {
					method(newFormDialog, dataTable);
				});
				return srcBtn;
			}
		};
function findEmployee(obj) {

	obj.combogrid({
		panelWidth : 440,
		idField : 'id',
		textField : 'truename',
		url : '/employee/list',
		//		pagination : true,
		//		pageList : [ 5, 10, 20 ],
		//		pageSize : 5,

		columns : [ [ {
			field : 'username',
			title : '用户名',
			width : 100
		}, {
			field : 'truename',
			title : '真名',
			width : 100
		}, {
			field : 'tel',
			title : '电话',
			width : 100
		}, {
			field : 'email',
			title : '邮箱',
			width : 100
		} ] ]
	});
	var page = obj.combogrid('grid').datagrid('getPager');
	page.pagination({
		showPageList : false,
		diaplayMsg : ''
	});
}
function findClient(obj) {

	obj.combogrid({
		panelWidth : 440,
		idField : 'id',
		textField : 'name',
		url : '/client/list',
		//		pagination : true,
		//		pageList : [ 5, 10, 20 ],
		//		pageSize : 5,

		columns : [ [ {
			field : 'name',
			title : '客户',
			width : 100
		}, {
			field : 'sn',
			title : '编号',
			width : 100
		}, {
			field : 'tel',
			title : '电话',
			width : 100
		}, {
			field : 'gender',
			title : '性别',
			width : 100,
			formatter : genderFormatter
		} ] ]
	});
	var page = obj.combogrid('grid').datagrid('getPager');
	page.pagination({
		showPageList : false,
		diaplayMsg : ''
	});
}
function genderFormatter(v, r, i) {
	switch (v) {
	case 0:
		return '女';
		break;
	case 1:
		return '男';
		break;
	default:
		return '未知';
		break;
	}
}
function clientFormatter(v) {
	if (v) {
		return v.name + "(" + v.sn + ")";
	}
}
function followClientFormatter(v, r, i) {
	if (v) {
		return v.name || v.truename + "(" + v.username + ")";
	}
}
function nameFormatter(v,r,i){
	if(v){
		return v.name;
	}
}
function snFormatter(v, r, i) {
	if (v) {
		return v.sn;
	}
}