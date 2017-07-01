$(function(){
	// 浏览器全屏 按F11 不再扩展功能
	$("#full-screen").on("click", function(e){
		e.preventDefault();
		jr.launchFullScreen(document.documentElement);
	});
	
	$("#personInfo").on("click", function(){
		$.post(basePath + "/home/queryCurrentUser", function(_data, status, jqXHR){
			var data = $.parseJSON(_data);
			if(data.success) {
				if(uri.indexOf("/sys/user/edit/") != -1) {
					jr.loadPage($HISTORY_URL[0]);
				}
				//jr.loadPage(basePath+"/sys/user/edit/" + data.model.id);
				window.personInfoFlag = 1;
				$.get(basePath+"/sys/user/edit/" + data.model.id, function($data, status, jqXHR){
					try {
						bootbox.dialog({ 
							message: $data,
							size: "large"
						});
					} catch(e) {
						console.log(e);
					} finally {
						delete window.personInfoFlag;
					}
				});
			}
		});
	});
	
	var $MENUS = $("#side-menu");
	initMenus();
	
	function initMenus() {
		
		if (menus.success) {
			var ms = menus.model.nodes,
				html = "";
			for (var i=0; i<ms.length; i++) {
				var m = ms[i];
				html += initMenuNodes(m);
			}
			
			$MENUS.html(html);
		} else {
			init_PNotify("错误", "菜单初始化错误", "error", 3000);
		}
	}
	
	
	function initMenuNodes(node) {
		var html = "";
		if (node.nodes.length == 0) {
			html += "<li><a";
			if (node.url) {
				html += " href='" + basePath + node.url + "'";
			}
			html += "><i class='" + node.menuIcon + "'></i> " + node.text + "</a> <span class='fa'></span></li>";
		} else {
			html += "<li><a><i class='" + node.menuIcon + "'></i> " + node.text + " <span class='fa fa-chevron-down'></span></a>";
			html += "<ul class='nav child_menu'>";
			for (var i=0; i<node.nodes.length; i++) {
				var m = node.nodes[i];
				html += initMenuNodes(m);
			}
			html += "</ul>";
			html += "</li>";
		}
		
		return html;
	}
	
});