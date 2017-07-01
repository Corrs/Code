$(document).ready(function() {
	var $FORM = $("#saveForm"), 
		$SUBMIT = $("#send"),
	 	$SENDBACK = $("#sendBack"),
	 	$BACKFLAG = false;
	initValidator();
	initHistoryGo();
	initUpload();
	initFormAttr();
	
	function initFormAttr() {
		$("#dropzone [name='catalog']").val($("#saveForm [name='catalog']").val());
		$("#saveForm [name='catalog']").on("change", function(e){
			$("#dropzone [name='catalog']").val($(this).val());
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
			}
		}).on('success.form.bv', function(e, data) {
			e.preventDefault();
			var $form = $(e.target);
			
			if(!$form.find("[name='image']").val()) {
				init_PNotify("提示", "请上传图片", "info", 2000, {nonblock:false});
				return false;
			}
			
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
	
	function initUpload() {
		try {
			$("#dropzone").dropzone({
				url: basePath + "/content/upload",
				maxFilesize: 10, // 10Mb
				//paramName: "uploadfile",
				parallelUploads: 1,
				autoProcessQueue: true,
				maxFiles: 1, // 最多上传一个文件
				acceptedFiles: 'image/*',
				addRemoveLinks: true,
				dictDefaultMessage: "点击或拖拽图片进行图片上传，每次只能上传一个图片文件，图片大小不超过10M。",
				dictMaxFilesExceeded: "只能上传一个文件",
				dictRemoveFile: "移除",
				dictFileTooBig: "无法上传，因为您的图片过大，请上传大小不大于{{maxFilesize}}M的图片。",
				dictFallbackText: "浏览器版本低。",
				dictFallbackMessage: "您的浏览器不支持拖放文件上传。",
				dictInvalidFileType: "您不能上传这种类型的文件。",
				dictCancelUpload: "取消",
				dictResponseError: "服务器响应{{statusCode}}代码。",
				init: function() {
					if ($("#saveForm [name='image']").val()) {
						var mockFile = { name: $("#saveForm [name='remark']").val(), accepted:true };
						this.emit("addedfile", mockFile);
						this.emit("thumbnail", mockFile, basePath + "/api/static" + $("#saveForm [name='image']").val().substring($("#saveForm [name='image']").val().lastIndexOf("/")));
						this.emit("complete", mockFile);
//						this.options.maxFiles = 1;
						this.files.push(mockFile);
					}
					
					
				    this.on("success", function(file, data) {
				    	if(data.success) {
				    		$FORM.data('bootstrapValidator').disableSubmitButtons(false);
				    		init_PNotify("成功", "图片上传成功", "success", 2000, {nonblock:false});
				    		$("form [name='id']").val(data.model.id);
				    		$("form [name='createdate']").val(data.model.createdate);
				    		$("#saveForm [name='image']").val(data.model.image);
				    		$("form [name='remark']").val(file.name);
				    	} else {
				    		init_PNotify("错误", "图片上传失败", "error", 2000, {nonblock:false});
				    	}
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