$(document).ready(function() {
	var addPageUrl = basePath + "/form/append",
		$table = $('#table'),
//		$remove = $('#remove'),
		$append = $('#append'),
		$TABLEPANEL = $('#tablePanel');
//		selections = [];
	
	initTable();
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
			url: basePath+'/form/query',
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
					width: "60px",
					searchable: false,
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',	// 值对其方式  left right center
					formatter: function(value, row, index) {
						var html = "<ul class='list-inline' style='position:relative; top:25%;'>";
						html += jr.formatterEdit(value, row, index);
						html += jr.formatterRemove(value, row, index);
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
				}, 
				{
					field: 'sn',
					title: '顺序号',
					width: '60px',
					sortable: true,
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'right'
				},
				{
					field: 'fieldname',
					title: '字段名称',
					width: '120px',
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',
					formatter: jr.formatterLongText
				},
				{
					field: "fieldtype",
					title: "字段类型",
					width: "80px",
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'left',	// 值对其方式  left right center
					formatter: function(value, row, index) {
						switch(value) {
						case 1: 
							return "字符串";
						case 2: 
							return "数字";
						case 3: 
							return "时间";
						case 4: 
							return "文本域";
						}
					}
				},
				{
					field: 'label',
					title: '标签名',
					width: '150px',
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',	// 值对其方式  left right center
					formatter: jr.formatterLongText
				},
				{
					field: 'placeholder',
					title: '提示语',
					width: '200px',
					valign: "middle", // 值的竖向对齐方式 top middle bottom
					halign: 'center',	// 表头对其方式  left right center
					align: 'center',	// 值对其方式  left right center
					formatter: jr.formatterLongText
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
		
		
		/*$table.on('check.bs.table uncheck.bs.table ' +
				'check-all.bs.table uncheck-all.bs.table', function () {
			$remove.prop('disabled', !jr.getSelectionIds($table).length);
		});*/
		// 视图切换事件
		$table.on('toggle.bs.table', function(){
			$table.bootstrapTable('refresh');
		});
		
		$table.on('load-success.bs.table', function() {
			$TABLEPANEL.find('.search').children().attr("placeholder","按菜单名称搜索");
			$('[modelid]').on('click', function(e){
				e.preventDefault();
				jr.loadPage(basePath+"/form/edit/" + $(this).attr('modelid'));
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
						jr.operate($table, basePath+"/form/delete/"+id, null);
					}
			    }
			});
		});
	}
	
	
	//  新增 打开新增页面
	$append.on('click', function() {
		jr.loadPage(addPageUrl);
    });
	
	// 删除
	/*$remove.on('click', function() {
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
				jr.deleteDatas($table, basePath+"/form/delete");
			}
	    }
    });*/
});