$(function() {
	window.loginUserId = $("#loginUserId").html();
	window.loginUsername = $("#loginUsername").html();
	window.loginTrueName = $("#loginTrueName").html();
	var outerAccordionUrl = "/menu/findParents";
	/**
	 * 整体布局
	 */
	var indexLayout = $("<div>").appendTo('body');
	indexLayout.layout({
		fit : true
	});
	indexLayout.layout('add', {
		id : "north_layout",
		region : 'north',
		height : 65,
		border : false
	});
	var northLayout = $("#north_layout");
	northLayout.css({
		"background" : "url(/images/6.png)",
	});
	//north布局中添加一个div 和 a
	northLayout.html("<div id='welcome'><a id='logout'></a><select id='theme'></select></div><div id='time'></div>").css({
		"text-align" : "right"
	});
	setInterval(setTime, 1000);
	function setTime(){
		$("#time").css({
			"color":"#fff",
			"margin-right":"80px",
			"font-weight":"bold",
			"font-size":"14px",
			"margin-top":"5px"
		}).html("当前时间:"+new Date().toLocaleString());
	}
	//创建子按钮
	var logoutBtn = $("<div>").html("注销").attr("data-options", "iconCls:'icon-remove'");
	//建立子按钮的放未知;	
	$("<div>").attr("id", "mm").append(logoutBtn).appendTo('body');
	//
	$("#logout").menubutton({
		text : loginTrueName,
		iconCls : "icon-user_gray_cool",
		menu : "#mm",
	});
	$("#welcome").css({
		"margin" : "15px 80px auto",
		"color" : "#fff"
	});
	$("#theme").combobox({
		width:100,
		panelHeight:"auto",
		valueField: 'value',
		textField: 'text',
		data: [{
			value: 'default',
			text: '默认主题',
			selected:true
		},{
			value: 'black',
			text: '黑色主题'
		},{
			value: 'bootstrap',
			text: 'Bootstrap主题'
		},{
			value: 'gray',
			text: '灰色主题'
		},{
			value: 'metro',
			text: 'Metro'
		}],
		onSelect:function(record){
			changeTheme(record.value);
			$("#easyui-link").attr("href","/js/easyui/themes/"+record.value+"/easyui.css");
			$("iframe").each(function(index,item){
				var win = item.contentWindow;
				$(win.document).find("#easyui-link").attr("href","/js/easyui/themes/"+record.value+"/easyui.css");
			});
			$.post("/theme/change",{theme:record.value});
			
		}
	});
	var themeName = $("#themeFromSession").html();
	$("#theme").combobox('setValue',themeName||"default");
	changeTheme(themeName);
	function changeTheme(name){
		if(name=='black'){
			northLayout.css("background","url(/images/black.png)");
		}else{
			northLayout.css("background","url(/images/6.png)");
		}
	}
	
	logoutBtn.on('click', function() {
		location.href = '/auth/logout';
	});
	indexLayout.layout('add', {
		region : 'south',
		height : 30,
		border : false,
		content : "<div align='center'>&copy;All copyright Reserved by CRM2</div>"
	});
	indexLayout.layout('add', {
		region : 'west',
		id : "left_layout",
		width : 180,
		title : "导航菜单",
		iconCls : "icon-tip",
		tools : [ {
			iconCls : "icon-reload",
			handler : function() {
				getDynamicMenuFormServer();
			}
		} ]
	});
	indexLayout.layout('add', {
		id : "center_layout",
		region : 'center'
	});
	/**
	 * 主体部分
	 */
	var dataTabs = $("<div>").appendTo($("#center_layout"));
	dataTabs.tabs({
		fit : true,
		//		tabWidth : 100,
		border : false
	});
	dataTabs.tabs('add', {
		title : "欢迎",
		content : "<p>欢迎来到后台管理!</p>"
	});
	/**
	 * 左侧菜单布局
	 */
	var accordionMenu = $("<div>").appendTo($("#left_layout"));
	accordionMenu.accordion({
		fit : true,
		border : false
	});

	/**
	 * 动态菜单
	 */
	var getDynamicMenuFormServer = function() {
		var size = accordionMenu.children().size();
		for ( var i = 0; i < size; i++) {
			accordionMenu.accordion('remove', 0);
		}
		$.post(outerAccordionUrl, function(datas) {
			$(datas).each(function(i, data) {
				var mId = "tree_menu_" + data.id;
				accordionMenu.accordion('add', {
					id : mId,
					title : data.text,
					iconCls : data.iconCls,
					selected : false,
					onOpen : function() {
						loadTreeForMenu(mId, data.id);
					},
					headerCls : "padding-left:20px"
				});
			});
		});
	};
	getDynamicMenuFormServer();
	setTimeout(showLoginSuccessMsg,3000);
	function showLoginSuccessMsg(){
		$.messager.show({
			title : '登录提示',
			msg : "<strong>"+loginTrueName + '</strong>,欢迎您回来! ',
			timeout : 5000,
			showType : 'slide',
		});
	}
	/**
	 * 将数据封装为tree展示;
	 */
	function loadTreeForMenu(accordionId, treeId) {
		$("#" + accordionId).tree({
			url : "menu/findChild?id=" + treeId,
			onClick : function(node) {
				if (node.attributes) {
					var url = node.attributes.url;
					console.debug(node.attributes);
					if (dataTabs.tabs('exists', node.text)) {
						dataTabs.tabs('select', node.text);
					} else {
						dataTabs.tabs('add', {
							title : node.text,
							closable : true,
//							href : url
							content : '<div style="width:100%;height:100%;overflow:hidden;">'
								+ '<iframe src="'
								+ url
								+ '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>'
							
						});
					}
				}
			}
		});
	}
});