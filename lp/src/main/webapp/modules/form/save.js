$(document).ready(function() {
	var $FORM = $("#saveForm"), 
		$SUBMIT = $("#send"),
	 	$SENDBACK = $("#sendBack"),
	 	$BACKFLAG = false;
	initValidator();
	initHistoryGo();
	
	
	// 初始化表单验证
	function initValidator() {
		var id = $.trim($("input[name='id']").val()) == "" ? 0 : $.trim($("input[name='id']").val());
		$FORM.bootstrapValidator({
			// 隐藏、不可见、不可用的组件不进行验证
			excluded: [':disabled', ':hidden', ':not(:visible)'],
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				fieldname: {
					validators: {
						notEmpty: {
							message: '请输入字段名称'
						},
						stringLength: {
							max: 20,
							message: '字段名称不得超过%s个字符'
						},
						regexp: {
	                        regexp: /^[a-z\s]+$/i,
	                        message: '字段名称必须为英文字符'
	                    },
	                    remote: {
	                        message: '字段名称已存在，请重新输入',
	                        url: basePath + '/form/validFieldname/' + id
	                    },
	                    callback: {
	                    	message: "您输入了默认字段名称，请更换其他字段名称",
	                    	callback: function(value, validator, $field){
	                    		
	                    		if(value.toUpperCase() == "ID" || value.toUpperCase() == "CREATEDATE") {
	                    			return false;
	                    		}
	                    		
	                    		return true;
	                    	}
	                    }
					}
				},
				fieldtype: {
                    validators: {
                        notEmpty: {
                            message: '字段类型不能为空'
                        },
                        integer: {
                            message: '请勿仔细修改页面信息'
                        },
                        stringLength: {
							max: 1,
							message: '请勿仔细修改页面信息'
						}
                    }
                },
                label: {
                	validators: {
                        notEmpty: {
                            message: '标签名不能为空'
                        },
                        stringLength: {
							max: 20,
							message: '标签名不得超过%s个字符'
						}
                    }
                },
                placeholder: {
                	validators: {
                        stringLength: {
							max: 50,
							message: '提示语不得超过%s个字符'
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
    					$("input[name='id']").val(result["model"]["id"]);
    					$("input[name='sn']").val(result["model"]["sn"]);
    					$("input[name='createdate']").val(result["model"]["createdate"]);
    					$("input[name='oldFieldName']").val(result["model"]["fieldname"]);
    				}
    			}
    		});
    	}
    }
});