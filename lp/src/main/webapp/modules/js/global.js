var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
	$BODY = $('body'), 
	$MENU_TOGGLE = $('#menu_toggle'), 
	$SIDEBAR_MENU = $('#sidebar-menu'), 
	$SIDEBAR_FOOTER = $('.sidebar-footer'), 
	$LEFT_COL = $('.left_col'), 
	$RIGHT_COL = $('.right_col'), 
	$NAV_MENU = $('.nav_menu'), 
	$FOOTER = $('footer'), 
	$HISTORY_URL = [],
	$PAGECONTENT = $("#pageContent");
function Chinesejr() {
}

Chinesejr.prototype.loadPage = function(pageUrl) {
	$PAGECONTENT.load(pageUrl);
};

/**
 * bootstrap validator 自定义验证方法
 */
Chinesejr.prototype.bootstrapValidator = function() {
	$.fn.bootstrapValidator.validators.isQQ = {
		validate : function(validator, $field, options) {
			var value = $field.val(), regexp = /^[1-9]\d{4,9}$/;
			if (value === '') {
				return true;
			}

			return regexp.test(value);
		}
	};

	// 验证 中英文 不含空格
	$.fn.bootstrapValidator.validators.isName = {
		validate : function(validator, $field, options) {
			var value = $field.val(),
			// regexp = /^([\u4e00-\u9fa5]+|([a-z]+\s?)+)$/;
			regexp = /^[\u0391-\uFFE5a-zA-Z·.。;&\\s]+$/;
			if (value === '') {
				return true;
			}

			return regexp.test(value);
		}
	};

	$.fn.bootstrapValidator.validators.isWechat = {
		validate : function(validator, $field, options) {
			var value = $field.val(), regexp = /^[a-zA-Z\d_]{5,}$/;
			if (value === '') {
				return true;
			}

			return regexp.test(value);
		}
	};

	$.fn.bootstrapValidator.validators.isEmail = {
		validate : function(validator, $field, options) {
			var value = $field.val(), regexp = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
			if (value === '') {
				return true;
			}

			return regexp.test(value);
		}
	};

	$.fn.bootstrapValidator.validators.isTowDec = {
		validate : function(validator, $field, options) {
			var value = $field.val(), regexp = /^[1-9]\d+(\.\d{1,2})?$/;
			if (value === '') {
				return true;
			}

			return regexp.test(value);
		}
	};

};

/*
 * jquery validator
 */
Chinesejr.prototype.validatorAddMethod = function() {
	if (typeof ($.validator) === 'undefined') {
		return;
	}
	// 验证QQ
	$.validator.addMethod("isQQ", function(value, element) {
		var regexp = /^[1-9]\d{4,9}$/;
		return this.optional(element) || (regexp.test(value));
	}, "请输入正确的QQ号码");

	// 验证微信号
	$.validator.addMethod("isWechat", function(value, element) {
		var regexp = /^[a-zA-Z\d_]{5,}$/;
		return this.optional(element) || (regexp.test(value));
	}, "请输入正确的微信号");

	// 验证手机号
	$.validator.addMethod("isMobile", function(value, element) {
		var regexp = /^1[3|4|5|8][0-9]\d{4,8}$/;
		return this.optional(element) || (regexp.test(value));
	}, "请输入正确的手机号码");

	// 验证固话
	$.validator.addMethod("isTelphone", function(value, element) {
		var regexp = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/;
		return this.optional(element) || (regexp.test(value));
	}, "请输入正确的电话号码");

	// 同时验证手机和固话
	$.validator
			.addMethod(
					"isTel",
					function(value, element) {
						var mobile = /^1[3|4|5|8][0-9]\d{4,8}$/, phone = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/;
						return this.optional(element)
								|| (mobile.test(value) || phone.test(value));
					}, "请输入正确的电话/手机");

	// 验证邮箱
	$.validator
			.addMethod(
					"isEmail",
					function(value, element) {
						var regexp = /^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/;
						return this.optional(element) || (regexp.test(value));
					}, "请输入正确的邮箱");

	// 验证两位小数 /^\d+(\.\d{2})?$/
	$.validator.addMethod("isTowDec", function(value, element) {
		var regexp = /^\d+(\.\d{1,2})?$/;
		return this.optional(element) || (regexp.test(value));
	}, "最多两位小数");

	$.validator.setDefaults({
		showErrors : function(map, list) {
			var focussed = document.activeElement;
			if (focussed && $(focussed).is("input, select, textarea")) {
				$(this.currentForm).tooltip("close", {
					currentTarget : focussed
				}, true)
			}
			this.currentElements.removeAttr("title").removeClass(
					"ui-state-error");
			$.each(list, function(index, error) {
				$(error.element).attr("title", error.message).addClass(
						"ui-state-error");
			});
			if (focussed && $(focussed).is("input, select, textarea")) {
				$(this.currentForm).tooltip("open", {
					target : focussed
				});
			}
		}
	});
};

/**
 * datepicker默认属性
 */
Chinesejr.prototype.datepickerDefaults = function() {
	if (typeof ($.fn.datepicker) === 'undefined') {
		return;
	}
	$.fn.datepicker.defaults.language = "zh-CN";
	$.fn.datepicker.defaults.todayBtn = "linked";
	$.fn.datepicker.defaults.autoclose = true;
	$.fn.datepicker.defaults.todayHighlight = true;
};

/**
 * bootstrap table的相关列渲染 渲染超链接
 */
Chinesejr.prototype.formatterHref = function(value, row, index) {
	// value 当前字段值 row 当前行数据 index 行索引
	return "<a href='#' style='display:block;margin:-8px auto;padding:0 2px;' class='modelid text-info' modelid='"
			+ row.id + "'>" + value + "</a>";
};

/**
 * bootstrap table的相关列渲染 渲染金额
 */
Chinesejr.prototype.formatterMoney = function(value, row, index) {
	return "<label style='display:block;margin:-8px auto;padding:0 2px;text-align:right;font-weight:normal;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;   '>￥"
			+ value + "</label>";
};

/**
 * bootstrap table的相关列渲染 渲染长文本
 */
Chinesejr.prototype.formatterLongText = function(value, row, index) {
	return "<label title='"
			+ value
			+ "'  style='padding:0 5px;margin:-8px auto;overflow:hidden;white-space:nowrap;text-overflow:ellipsis; font-weight:normal; display:block; text-align: left; vertical-align: middle;'>"
			+ value + "</label>";
};

/**
 * bootstrap table的相关列渲染 渲染数字
 */
Chinesejr.prototype.formatterNumber = function(value, row, index) {
	return "<label style='padding:0 2px;margin:-8px auto;overflow:hidden;white-space:nowrap;text-overflow:ellipsis; font-weight:normal; display:block; text-align: right; vertical-align: middle;'>"
			+ value + "</label>";
};

/**
 * bootstrap table的相关列渲染 渲染序号列
 */
Chinesejr.prototype.formatterNo = function(value, row, index) {
	return "<label style='padding:0 2px;margin:-8px auto;overflow:hidden;white-space:nowrap;text-overflow:ellipsis; font-weight:normal; display:block; text-align: center; vertical-align: middle;'>"
			+ (index + 1) + "</label>";
};

/**
 * bootstrap渲染列 添加关注图标 数据库必须含有关注（attention）字段
 */
Chinesejr.prototype.fomatterHeart = function(value, row, index) {
	var html = "<li style='display: inline-block; padding-right: 3px; padding-left: 4px;'><a href='#' title='关注' class='heartRow' heart='"
			+ row.id + "'>";
	if (row.attention == 1) {
		html += "<i class='fa fa-heart fa-fw text-danger'></i>";
	} else {
		html += "<i class='fa fa-heart-o fa-fw'></i>";
	}
	html += "</a></li>";
	return html;
};

/**
 * bootstrap渲染列 添加编辑图标
 */
Chinesejr.prototype.formatterEdit = function(value, row, index) {
	return "<li style='display: inline-block; padding-right: 3px; padding-left: 4px;'><a href='#' title='编辑'  class='modelid' modelid='"
			+ row.id
			+ "'><i class='fa fa-pencil-square fa-fw text-primary'></i></a></li>";
};

/**
 * bootstrap渲染列 添加删除图标
 */
Chinesejr.prototype.formatterRemove = function(value, row, index) {
	return "<li style='display: inline-block; padding-right: 3px; padding-left: 4px;'><a href='#' title='删除'  class='removeRow' delete='"
			+ row.id
			+ "'><i class='fa fa-times fa-fw text-danger'></i></a></li>";
};

/**
 * bootstrap table的相关列渲染 渲染操作列 只包含关注、编辑 和 删除
 */
Chinesejr.prototype.formatterOperate = function(value, row, index) {
	var html = "<ul class='list-inline' style='position:relative; top:25%;'>";
	html += jr.fomatterHeart(value, row, index);
	html += jr.formatterEdit(value, row, index);
	html += jr.formatterRemove(value, row, index);
	html += "</ul>";
	return html;
};

/**
 * bootstrap table的物理删除数据功能 $table： bootstrap table 对象 url：地址 id：主键
 */
Chinesejr.prototype.operate = function($table, url, data, callback) {
	var _this = this;
	$.post(url, data, function(_data, status, jqXHR) {
		_this.ajaxReturnOperate($table, _data);
		if(typeof callback == "function") {
			callback(_data);
		}
	});
};

/**
 * 根据id获得行数据
 */
Chinesejr.prototype.getRowByUniqueId = function($table, id) {
	return $table.bootstrapTable('getRowByUniqueId', id);
}

/**
 * bootstrap table 异步请求后台后 进行的相关操作 $table： bootstrap table 对象 data： 后台返回的数据
 */
Chinesejr.prototype.ajaxReturnOperate = function($table, data) {
	var result = $.parseJSON(data);
	if (result["code"] == "00") {
		// 提示框 title标题 text提示内容
		// type提示框类型（success、info、error、notice、dark） delay提示框显示时常（毫秒）
		init_PNotify("错误", result["msg"], "error", 2000);
	} else if (result["code"] == "01") {
		init_PNotify("成功", result["msg"], "success", 2000);
		$table.bootstrapTable('refresh');
	}
};

/**
 * bootstrap table的批量物理删除数据功能 table： bootstrap table 对象 url：地址
 */
Chinesejr.prototype.deleteDatas = function($table, url, callback) {
	var ids = this.getSelectionIds($table), _this = this;
	if (ids.length <= 0) {
		init_PNotify("提示", "请选择要删除的数据", "info", 1000);
		return;
	}

	$.post(url, {
		ids : ids.join(",")
	}, function(_data, status, jqXHR) {
		_this.ajaxReturnOperate($table, _data);
		if(typeof callback == "function") {
			callback(_data);
		}
	});
};

/**
 * bootstrap table的获得选中行数据功能
 */
Chinesejr.prototype.getSelections = function($table) {
	return $.map($table.bootstrapTable('getSelections'), function(row) {
		return row;
	});
};

/**
 * bootstrap table的获得选中行id功能
 */
Chinesejr.prototype.getSelectionIds = function($table) {
	return $.map($table.bootstrapTable('getSelections'), function(row) {
		return row.id || row.ID;
	});
};

/**
 * bootbox 弹窗 confirm
 */
Chinesejr.prototype.confirm = function(obj) {
	if (typeof (bootbox) === 'undefined') {
		return;
	}
	bootbox.confirm({
		title : obj.title,
		message : obj.msg,
		size : obj.size ? obj.size : "small",
		buttons : {
			cancel : {
				label : '<i class="fa fa-times"></i> 取消'
			},
			confirm : {
				label : '<i class="fa fa-check"></i> 确定'
			}
		},
		callback : obj.callback
	});
};

/**
 * bootbox 弹窗 alert
 */
Chinesejr.prototype.alert = function(obj) {
	if (typeof (bootbox) === 'undefined') {
		return;
	}
	bootbox.alert({
		title : obj.title ? obj.title : "提示",
		message : obj.msg,
		size : obj.size ? obj.size : "small",
		callback : obj.callback
	});
};

/**
 * 浏览器全屏
 */
Chinesejr.prototype.launchFullScreen = function(element) {
	var el = element;
	var rfs = el.requestFullScreen || el.webkitRequestFullScreen
			|| el.mozRequestFullScreen || el.msRequestFullscreen;
	if (typeof rfs != "undefined" && rfs) {
		rfs.call(el);
	} else if (typeof window.ActiveXObject != "undefined") {
		// for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏
		try {
			var wscript = new ActiveXObject("WScript.Shell");
			if (wscript != null) {
				wscript.SendKeys("{F11}");
			}
		} catch(e) {
			this.alert({msg: "浏览器版本较低，请按F11切换全屏显示。"});
		}
	}
};

/**
 * 收缩内容面板
 */
Chinesejr.prototype.initFade = function() {
	$('.collapse-link').on(
			'click',
			function() {
				var $BOX_PANEL = $(this).closest('.x_panel'), $ICON = $(this)
						.find('i'), $BOX_CONTENT = $BOX_PANEL
						.find('.x_content');

				// fix for some div with hardcoded fix class
				if ($BOX_PANEL.attr('style')) {
					$BOX_CONTENT.slideToggle(200, function() {
						$BOX_PANEL.removeAttr('style');
					});
				} else {
					$BOX_CONTENT.slideToggle(200);
					$BOX_PANEL.css('height', 'auto');
				}

				$ICON.toggleClass('fa-chevron-up fa-chevron-down');
			});
};

/**
 * 获得cookie
 */
Chinesejr.prototype.getCookie = function(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1)
				c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return ""
};

/**
 * 设置cookie
 */
Chinesejr.prototype.setCookie = function(c_name, value, expiredays) {
	var exdate = new Date()
	exdate.setDate(exdate.getDate() + expiredays)
	document.cookie = c_name + "=" + escape(value)
			+ ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString())
};

/**
 * 检测浏览器
 */
Chinesejr.prototype.checkBrowser = function(){
	var ua = navigator.userAgent;     
    ua = ua.toLowerCase();     
    var match = /(webkit)[ \/]([\w.]+)/.exec(ua) ||     
    /(opera)(?:.*version)?[ \/]([\w.]+)/.exec(ua) ||     
    /(msie) ([\w.]+)/.exec(ua) ||     
    !/compatible/.test(ua) && /(mozilla)(?:.*? rv:([\w.]+))?/.exec(ua) || [];     
  
   //如果需要获取浏览器版本号：match[2]     
    switch(match[1]){                 
    case "msie":      //ie             
	    if (parseInt(match[2]) === 6 || parseInt(match[2]) === 7 || parseInt(match[2]) === 8){   //ie6                 
	            this.alert({msg:"暂时不支持IE8.0及以下版本浏览器，请升级您的浏览器版本！"});  
	    }  
      break;             
     case "webkit":     //safari or chrome                 
      break;             
     case "opera":      //opera                 
      break;                 
     case "mozilla":    //Firefox                 
      break;             
     default:                     
      break;                 
    } 
};
var jr = (function() {
	return new Chinesejr();
}());
