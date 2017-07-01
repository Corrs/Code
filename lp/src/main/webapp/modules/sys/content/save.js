$(document).ready(function() {
	var $FORM = $("#saveForm"),
	 	$SUBMIT = $("#send"),
	 	$SENDBACK = $("#sendBack"),
	 	$BACKFLAG = false;
	// 初始化验证组件的验证方法
	jr.bootstrapValidator();
	initHistoryGo();
	initValidator();
	initColorPicker();
	initWysiwyg();
	
	function initColorPicker() {
		if( typeof ($.fn.colorpicker) === 'undefined'){ return; }
		console.log('init_ColorPicker');
		$('.colorpicker').colorpicker();
	}; 
	
	function initWysiwyg() {
		
		if( typeof ($.fn.wysiwyg) === 'undefined'){ return; }
		console.log('init_wysiwyg');	
		init_ToolbarBootstrapBindings();
			
        function init_ToolbarBootstrapBindings() {
          var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
              'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
              'Times New Roman', 'Verdana'
            ],
            fontTarget = $('#Font').siblings('.dropdown-menu');
          $.each(fonts, function(idx, fontName) {
            fontTarget.append($('<li><a data-edit="fontName ' + fontName + '" style="font-family:\'' + fontName + '\'">' + fontName + '</a></li>'));
          });
          $('a[title]').tooltip({
            container: 'body'
          });
          $('.dropdown-menu input').click(function() {
              return false;
            })
            .change(function() {
              $(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');
            })
            .keydown('esc', function() {
              this.value = '';
              $(this).change();
            });

          $('[data-role=magic-overlay]').each(function() {
            var overlay = $(this),
              target = $(overlay.data('target'));
            overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
          });

          if ("onwebkitspeechchange" in document.createElement("input")) {
            var editorOffset = $('#editor').offset();

            $('.voiceBtn').css('position', 'absolute').offset({
              top: editorOffset.top,
              left: editorOffset.left + $('#editor').innerWidth() - 35
            });
          } else {
            $('.voiceBtn').hide();
          }
        }

        function showErrorAlert(reason, detail) {
          var msg = '';
          if (reason === 'unsupported-file-type') {
            msg = "Unsupported format " + detail;
          } else {
            console.log("error uploading file", reason, detail);
          }
          $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>' +
            '<strong>File upload error</strong> ' + msg + ' </div>').prependTo('#alerts');
        }

       $('.editor-wrapper').each(function(){
			var id = $(this).attr('id');	//editor-one
			
			$(this).wysiwyg({
				toolbarSelector: '[data-target="#' + id + '"]',
				fileUploadError: showErrorAlert
			});	
		});
 
		
        window.prettyPrint;
        prettyPrint();
	
    };
	
	
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
				title: {
					validators: {
						notEmpty: {
							message: '请输入网站标题'
						},
						stringLength: {
							max: 50,
							message: '菜单名称不得超过%s个字符'
						}
					}
				},
				inuse: {
					validators: {
						notEmpty: {
							message: '请选择是否启用该基础设置'
						}
					}
				},
                offer: {
                    validators: {
                    	stringLength: {
							max: 200,
							message: '优惠信息不得超过%s个字符'
						}
                    }
                },
                buy: {
                    validators: {
                    	stringLength: {
							max: 200,
							message: '购买信息不得超过%s个字符'
						}
                    }
                },
                prop2: {
                	validators: {
                		stringLength: {
							max: 100,
							message: '表单提交提示信息不得超过%s个字符'
						}
                	}
                }
			}
		}).on('success.form.bv', function(e, data) {
			e.preventDefault();
			var $form = $(e.target);
			$form.data('bootstrapValidator').disableSubmitButtons(false);
			$("#footercontent").val($("#editor-one").html());
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