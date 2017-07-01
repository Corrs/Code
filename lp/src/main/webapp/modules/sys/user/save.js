$(document).ready(function() {
	var $BASICINFOFORM = $("#basicInfoForm"),
		$MOREINFOFORM = $("#moreInfoForm"),
		$IMGFORM = $("#imgForm"),
	 	$BACKFLAG = false,
	 	now = new Date(),
	 	$SAVE_COUNT = 0,
	 	$IMG = "";
	// 初始化日期控件默认属性
	jr.datepickerDefaults();
	// 初始化验证组件的验证方法
	jr.bootstrapValidator();
	initHistoryGo();
	initValidator();
	initDatepicker();
	initUpload();
	selectImg();
	initHiddenButtons();
	
	function initHiddenButtons() {
		if(window.personInfoFlag == 1) {
			$("button[name='sendBack']").hide();
			$('.return-link').hide();
		}
	}
	
	function initDatepicker() {
		$("#birthday").datepicker({
			format: "yyyy-mm-dd",
			//startDate: new Date(now.getTime() - 7 * 24 * 3600 * 1000),
			endDate: now
		});
	}
	
	
	// 返回按钮初始化
	function initHistoryGo() {
		$('.return-link').on('click', function() {
			jr.loadPage($HISTORY_URL[0]);
		});
	}
	
	// 初始化表单验证
	function initValidator() {
		$BASICINFOFORM.bootstrapValidator({
			// 隐藏、不可见、不可用的组件不进行验证
			excluded: [':disabled', ':hidden', ':not(:visible)'],
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				sex: {
					validators: {
						notEmpty: {
							message: '请选择性别'
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
		});
		
		// 更多个人信息
		$MOREINFOFORM.bootstrapValidator({
			// 隐藏、不可见、不可用的组件不进行验证
			excluded: [':disabled', ':hidden', ':not(:visible)'],
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				personcode: {
					validators: {
						callback: {
							message: "",
							callback: function(value, validator, $field){
								var reg = null; //判断是否为数字的正则表达式
								//判断是否为空
								if(value==null || value==""){
									return true;
								}else if(value.length==15){
									reg=/^[0-9]+$/;
									if(!reg.test(value)){
										return {
		                                    valid: false,
		                                    message: "请输入正确的身份证号码！15位居民身份证号码应由纯数字构成"
		                                };
									}
								}else if(value.length==18){
									reg=/^[0-9]+[xX]{0,1}$/;
									if(reg.test(value)){
										value=value.replace("x","X");
										$field.val(value);
									}else{
										return {
		                                    valid: false,
		                                    message: "请输入正确的身份证号码！18位居民身份证号码应由数字或X构成"
		                                };
									}
								}else{
									return {
	                                    valid: false,
	                                    message: "请输入正确的身份证号码！居民身份证号码为15或18位"
	                                };
								}
							    return true;
							}
						}
					}
				}
			}
		}).on('success.form.bv', function(e, data) {
			e.preventDefault();
			if($SAVE_COUNT == 1) {
				$MOREINFOFORM.data('bootstrapValidator').disableSubmitButtons(false);
				formSubmit($MOREINFOFORM);
				if ($BACKFLAG) {
					jr.loadPage($HISTORY_URL[0]);
				}
				$SAVE_COUNT++;
			}
		}).on('reset', function(e, data){
			// 获得表单
			var $MOREINFOFORM = $(e.target);
			// 清楚表单验证
			$MOREINFOFORM.data('bootstrapValidator').resetForm();
		});
		
		$("button[name='sendBack']").on("click", function(e) {
			$BACKFLAG = true;
			$SAVE_COUNT = 1;
		});
		
		$("button[name='send']").on("click", function(e) {
			$BACKFLAG = false;
			$SAVE_COUNT = 1;
		});
		
		// 头像照片
		$IMGFORM.bootstrapValidator().on("success.form.bv", function(e){
			e.preventDefault();
			$IMGFORM.data('bootstrapValidator').disableSubmitButtons(false);
			if($IMG == "") {
				jr.alert({msg: "请选择推荐头像或自定义头像"});
				return;
			} 
			$IMGFORM.find("[name='image']").val($IMG.substring(1));
			formSubmit($IMGFORM);
			if ($BACKFLAG) {
				jr.loadPage($HISTORY_URL[0]);
			}
		})
	}
	
	function selectImg() {
		var $LI = $("#defaultImg>li, #customImg>li");
		$LI.on("click", function(){
			$LI.removeClass("selected");
			$(this).addClass("selected");
			$IMG = $(this).attr("value");
		});
		
		$LI.removeClass("selected").each(function(index, item){
			if ($(item).attr("value").substring(1) === $IMGFORM.find("[name='image']").val().replace(/\\/g, "/")) {
				$(item).addClass("selected");
				$IMG = $(item).attr("value");
			}
		});
	}
	
	function initUpload() {
		try {
			$("#dropzone").dropzone({
				url: basePath + "/sys/user/upload",
				maxFilesize: 4, // 4Mb
				//paramName: "uploadfile",
				parallelUploads: 1,
				autoProcessQueue: true,
				maxFiles: 1, // 最多上传一个文件
				acceptedFiles: 'image/*',
				addRemoveLinks: true,
				dictDefaultMessage: "点击或拖拽图片进行上传，每次只能上传一个图片文件，图片大小不超过4M。",
				dictMaxFilesExceeded: "只能上传一个文件",
				dictRemoveFile: "移除",
				dictFileTooBig: "无法上传，因为您的图片过大，请上传大小不大于{{maxFilesize}}M的图片。",
				dictFallbackText: "浏览器版本低。",
				dictFallbackMessage: "您的浏览器不支持拖放文件上传。",
				dictInvalidFileType: "您不能上传这种类型的文件。",
				dictCancelUpload: "取消",
				dictResponseError: "服务器响应{{statusCode}}代码。",
				init: function() {
				    this.on("success", function(file, data) {
				    	var img = data.model.image.replace(/\\/g, "/");
				    	$IMGFORM.find("[name='image']").val(img);
				    	$("#customImg").append("<li value='/"+img+"'>" +
				    			"<img alt='' src='"+basePath+"/"+img+"' width='50px' height='50px'>" +
				    			"<b></b>" +
				    			"</li>");
				    	selectImg();
				    });
				}
			});
		} catch(e) {
			console.log(e);
		}
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