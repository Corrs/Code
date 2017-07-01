$(document).ready(function() {
	var $FORM = $("#saveForm"),
	 	$SUBMIT = $("#send"),
	 	$RESET = $("#reset"),
	 	$RADIOTYPE = $("[type='radio']"),
	 	$PATH = $("#path"),
	 	$SENDBACK = $("#sendBack"),
	 	$BACKFLAG = false;
	// 初始化验证组件的验证方法
	jr.bootstrapValidator();
	initHistoryGo();
	initValidator();
	
	// 菜单类型切换
	$RADIOTYPE.on("click", function(e){
		var _this = $(this);
		$PATH.val("");
		$PATH.prop("disabled", _this.val() == "0");
		// path可编辑时验证非空
		$FORM.bootstrapValidator('enableFieldValidators', 'path', !(_this.val() == "0"));
	});
	
	// 返回按钮初始化
	function initHistoryGo() {
		$('.return-link').on('click', function() {
			jr.loadPage($HISTORY_URL[0]);
		});
	}
	
	// 初始化表单验证
	function initValidator() {
		$FORM.bootstrapValidator({
			// 隐藏、不可见、不可用的组件不进行验证
			excluded: [':disabled', ':hidden', ':not(:visible)'],
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				name: {
					validators: {
						notEmpty: {
							message: '请输入菜单名称'
						},
						stringLength: {
							max: 45,
							message: '菜单名称不得超过%s个字符'
						}
					}
				},
                path: {
                    enabled: false,
                    validators: {
                        notEmpty: {
                            message: '页面路径不能为空'
                        },
						stringLength: {
							max: 200,
							message: '页面路径不得超过%s个字符'
						}
                    }
                }
			}
		}).on('success.form.bv', function(e, data) {
			e.preventDefault();
			var $form = $(e.target);
			$form.data('bootstrapValidator').disableSubmitButtons(false);
			formSubmit($form);
			if ($BACKFLAG) {
				jr.loadPage($HISTORY_URL[0]);
			}
		}).on('reset', function(e, data){
			// 获得表单
			var $form = $(e.target);
			// 清楚表单验证
			$form.data('bootstrapValidator').resetForm();
			if ($RADIOTYPE.val() == "0") {
				$PATH.val("");
				$PATH.prop("disabled", true);
				// path可编辑时验证非空
				$FORM.bootstrapValidator('enableFieldValidators', 'path', false);
			}
		});
		
		$SENDBACK.on("click", function(e) {
			$BACKFLAG = true;
		});
		
		$SUBMIT.on("click", function(e) {
			$BACKFLAG = false;
		})
	}
	
	
	// 表单提交
    function formSubmit(form) {
    	var _thisForm = $(form);
    	if(_thisForm.data('bootstrapValidator').isValid()) {
    		_thisForm.ajaxSubmit({
//    		resetForm: true, // 提交成功后清除表单数据
    			success: function(responseText, statusText, xhr, $form) { 
    				var result = $.parseJSON(responseText);
//    				alert(result["msg"]);
    				if (result["code"] == "00") {
    					init_PNotify("错误", result["msg"], "error", 2000, {nonblock:true});
    				} else if (result["code"] == "01") {
    					init_PNotify("成功", result["msg"], "success", 2000, {nonblock:true});
    					$("#modelid").val(result["model"]["id"]);
    				}
    			}
    		});
    	}
    }
});