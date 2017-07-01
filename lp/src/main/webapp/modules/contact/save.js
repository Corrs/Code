$(document).ready(function() {
	var $FORM = $("#saveForm"), 
		$SUBMIT = $("#send"),
	 	$SENDBACK = $("#sendBack"),
	 	$BACKFLAG = false;
	initValidator();
	initHistoryGo();
	
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
				contact: {
					validators: {
						notEmpty: {
							message: '请输入联系方式'
						},
						stringLength: {
							max: 45,
							message: '联系方式不得超过%s个字符'
						},
	                    callback: {
	                    	message: "",
	                    	callback: function(value, validator, $field){
	                    		var $type = $("[name='type']").val();
	                    		switch($type) {
	                    		case "1": 
	                    			if(!(/^[1-9]\d{4,10}$/.test(value))) {
	                    				return {
	                                        valid: false,
	                                        message: "请输入正确的QQ号，QQ号由5-10位数字组成，并不能以0开头！"
	                                    };
	                    			}
	                    			break;
	                    		case "2": 
	                    			if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(value) || /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,8}$/.test(value))) {
	                    				return {
	                                        valid: false,
	                                        message: "请输入正确的联系电话！如：15951101124或010-84954157"
	                                    };
	                    			}
	                    			break;
	                    		}
	                    		
	                    		return true;
	                    	}
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
		});
		
		$SENDBACK.on("click", function(e) {
			$BACKFLAG = true;
		});
		
		$SUBMIT.on("click", function(e) {
			$BACKFLAG = false;
		});
	}
	
	
	// 返回按钮初始化
	function initHistoryGo() {
		$('.return-link').on('click', function() {
			jr.loadPage($HISTORY_URL[0]);
		});
	}
	
	// 表单提交
    function formSubmit(form) {
    	var _thisForm = $(form);
    	if(_thisForm.data('bootstrapValidator').isValid()) {
    		_thisForm.ajaxSubmit({
    			success: function(responseText, statusText, xhr, $form) { 
    				var result = $.parseJSON(responseText);
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