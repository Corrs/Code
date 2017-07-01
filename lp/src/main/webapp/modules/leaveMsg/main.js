$(document).ready(function() {
	var $table = $('#table'),
		$TABLEPANEL = $('#tablePanel'),
		$remove = $('#remove');
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
		$.getJSON(basePath + "/form/query", function(result) {
			if(result && result.success) {
				var columns = [{
						checkbox: true,
						width: "30px",
						searchable: false,
						valign: "middle", // 值的竖向对齐方式 top middle bottom
						halign: 'center',	// 表头对其方式  left right center
						align: 'center'	// 值对其方式  left right center
					},
					{
						title: "序号",
						formatter: jr.formatterNo, 
						width: "50px",
						searchable: false,
						valign: "middle", // 值的竖向对齐方式 top middle bottom
						halign: 'center',	// 表头对其方式  left right center
						align: 'center'	// 值对其方式  left right center
					},
					{
						field: 'CREATEDATE',
						title: '留言时间',
						sortable: true,		// 此列可点击排序
						order: 'desc',
						width: '150px',
						valign: "middle", // 值的竖向对齐方式 top middle bottom
						halign: 'center',	// 表头对其方式  left right center
						align: 'center',
						formatter: jr.formatterLongText
					}];
				for(var i=0; i<result.rows.length; i++) {
					var row = result.rows[i];
					var col = {
						field: row.fieldname,
						title: row.label,
						width: '100px',
						valign: "middle", // 值的竖向对齐方式 top middle bottom
						halign: 'center',	// 表头对其方式  left right center
						align: 'center',
						formatter: jr.formatterLongText
					};
					columns.push(col);
				}
				console.log(columns);

				$table.bootstrapTable({
					url: basePath+'/msg/queryMap',
					method: 'post',
					exportDataType:'all',
					exportTypes: ['excel'],
					sidePagination: 'server',
					contentType: "application/x-www-form-urlencoded", // 使用queryParams 必须配置此选项 否则后台接收不到数据
					queryParams: getQueryParams,
					columns: columns,
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
				
				// 视图切换事件
				$table.on('toggle.bs.table', function(){
					$table.bootstrapTable('refresh');
				});
				
				/*$table.on('check.bs.table uncheck.bs.table ' +
						'check-all.bs.table uncheck-all.bs.table', function () {
					$remove.prop('disabled', !jr.getSelectionIds($table).length);
				});*/
			}
			
		});
	}
	
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
				jr.deleteDatas($table, basePath+"/msg/deleteMap");
			}
	    }
    });
});