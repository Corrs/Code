$(document).ready(function() {
	var addPageUrl = basePath + "/sys/catalog/append",
		$table = $('#table'),
		$remove = $('#remove'),
		$append = $('#append'),
		$TABLEPANEL = $('#tablePanel'),
		$TOOLBAR = $('#toolbar');
//		selections = [];
	
	initTable();
	// 收缩内容面板
	jr.initFade();
	
	function getQueryParams(params) {
		var columns = this.columns[0],
			sortArr = [],
			orderArr = [];

		for(var i=0; i<columns.length; i++) {
			var c = columns[i];
			if (c.searchable && params.search) {
				params[c.field] = params.search;
			} 
			
			if (c.sortable) {
				sortArr.push(c.field);
				orderArr.push(c.order);
			}
			
		}
		
		// 如果没有排序字段  可以赋初始值 如下 
		if (!params.sort) {
			params.sort = sortArr.join(",");
			params.order = orderArr.join(",");
		}
		
		return params;
		
	}
	
	// 初始化列表
	function initTable() {
		$table.bootstrapTable({
			url: basePath+'/sys/catalog/query',
			method: 'post',
			sidePagination: 'server',
			contentType: "application/x-www-form-urlencoded", // 使用queryParams 必须配置此选项 否则后台接收不到数据
			queryParams: getQueryParams,
			columns: [
				{
					checkbox: true,
					width: "30px",
					searchable: false,
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',	// 值对其方式  left right center
				},
				{
					title: "操作",
					width: "59px",
					searchable: false,
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',	// 值对其方式  left right center
					formatter: function(value, row, index) {
						var html = "<ul class='list-inline' style='position:relative; top:25%;'>";
						html += jr.formatterEdit(value, row, index);
						/*if(row.code != "001") {
							html += jr.formatterRemove(value, row, index);
						}*/
						html += "</ul>";
						return html;
					}
				},
				{
					title: "序号",
					formatter: jr.formatterNo, 
					width: "40px",
					searchable: false,
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center'	// 值对其方式  left right center
				}, {
					field: 'name',
					title: '栏目名称',
					width: '250px',
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'left',	// 值对其方式  left right center
					formatter: jr.formatterLongText
				},
				{
					field: "code",
					title: "栏目编码",
					searchable: false,
					sortable: true,
					width: "100px",
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center'	// 值对其方式  left right center
				},
				{
					field: "remark",
					title: "备注",
					searchable: false,
					sortable: true,
					width: "500px",
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'left'	// 值对其方式  left right center
				}
				],
				rowStyle: function (row, index) {
					//这里有5个取值代表5中颜色['active', 'success', 'info', 'warning', 'danger'];
					var strclass = "";
					/*if (row.ORDER_STATUS == "待排产") {
		                strclass = 'success';//还有一个active
		            }
		            else if (row.ORDER_STATUS == "已删除") {
		                strclass = 'danger';
		            }
		            else {
		                return {};
		            }*/
					return { classes: strclass }
				}
		});
		
		
		$table.on('check.bs.table uncheck.bs.table ' +
				'check-all.bs.table uncheck-all.bs.table', function () {
			$remove.prop('disabled', !jr.getSelectionIds($table).length);
		});
		// 视图切换事件
		$table.on('toggle.bs.table', function(){
			$table.bootstrapTable('refresh');
		});
		
		$table.on('load-success.bs.table', function() {
			$TABLEPANEL.find('.search').children().attr("placeholder","按菜单名称搜索");
			$('[modelid]').on('click', function(e){
				e.preventDefault();
				jr.loadPage(basePath+"/sys/catalog/edit/" + $(this).attr('modelid'));
			});
			
			$('[delete]').on('click', function(e) {
				e.preventDefault();
				var id = $(this).attr('delete'),
				title = "<h4 class=\"modal-title text-danger\" >删除提示</h4>",
				msg = "<h5 class=\"text-danger\">数据删除后，不能恢复，是否继续删除？</h5>",
				obj = {
					"title":title, 
					"msg":msg, 
					"callback":callback
				};
				
				jr.confirm(obj);
				
				function callback(result) {
					if(result) {
						jr.operate($table, basePath+"/sys/catalog/delete/"+id, null);
					}
			    }
			});
		});
	}
	
	
	function refreshTree(data) {
		var result = $.parseJSON(data);
		if (result["success"]) {
			initTree();
		}
	}
	
	
	//  新增 打开新增页面
	$append.on('click', function() {
//		debugger;
		jr.loadPage(addPageUrl);
    });
	
	// 删除
	$remove.on('click', function() {
		var ids = jr.getSelectionIds($table);
		if(ids.length <= 0) {
			init_PNotify("提示", "请选择要删除的数据", "info", 1000);
			return;
		}
		var title = "<h4 class=\"modal-title text-danger\" >删除提示</h4>",
			msg = "<h5 class=\"text-danger\">数据删除后，不能恢复，是否继续删除？</h5>",
			obj = {
				"title":title, 
				"msg":msg, 
				"callback":callback
			}
			
		jr.confirm(obj);
		
		function callback(result) {
			if(result) {
				jr.deleteDatas($table, basePath+"/sys/catalog/delete");
			}
	    }
    });
	
});