<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="static.jsp" %>    
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <%@ include file="header.jsp" %>
    <!-- Bootstrap -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/nprogress/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/animate.css/animate.min.css" rel="stylesheet">
    <!-- bootstrapvalidator -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/css/custom.min.css" rel="stylesheet">
    <style type="text/css">
    	.nomargin {
			margin: 0 !important;
		}
		.error-span {
			text-align: left !important;
			font-size: 16px;
		}
    </style>
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form id="loginForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/login/loginUser">
              <input id="loginMobile" type="hidden" name="mobile" />
              <input id="loginEmail" type="hidden" name="email" />
              <h1>用户登录</h1>
              <div>
	              <div class="input-group">
					  <label class="input-group-addon" for="username"><i class="fa fa-user fa-fw"></i></label>
					  <input type="text" class="form-control nomargin" id="username" name="username" placeholder="请输入用户名"  />
				  </div>
				  <span class="error-span" id="error-username"></span>
              </div>
              <div>
				  <div class="input-group">
					  <label class="input-group-addon" for="password"><i class="fa fa-key fa-fw"></i></label>
					  <input type="password" class="form-control nomargin" id="password" name="password" placeholder="请输入密码"  />
				  </div>
				  <span class="error-span" id="error-password"></span>
              </div>
              <div>
				  <div class="input-group">
					  <input type="text" class="form-control nomargin" id="validCode" name="validCode" autocomplete="off" placeholder="请输入验证码"  />
					  <label class="input-group-addon" for="validCode" style="background: transparent; border: 0; padding: 0; cursor: pointer;"><a title="点击刷新"><img alt="点击刷新验证码" style="margin-right: -15px;height: 34px;border-top-right-radius: 3px;border-bottom-right-radius: 3px;" class="validCodeImg"></a></label>
				  </div>
				  <span class="error-span" id="error-validCode"></span>
              </div>
              <div>
                <input type="submit" class="btn btn-default submit" value="登录">
                <input type="checkbox" style="margin-top: 13px;" id="cookie" > 10天内免登录
                <!-- <a class="reset_pass" href="#">忘记密码？</a> -->
              </div>

              <div class="clearfix"></div>
            </form>
          </section>
        </div>
      </div>
    </div>
     <!-- jQuery -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery.form/jquery.form.min.js"></script>
    <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/language/zh_CN.js"></script>
    <!-- alert confirm等弹窗 -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootbox.min.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/modules/js/global.js"></script>
    <script type="text/javascript">
	    $(function(){
	    	var $VALIDCODE = $(".validCodeImg"),
	    		$CODE = "",
	    		$LOGINFORM = $("#loginForm"),
	    		$COOKIE = $("#cookie");
	    	initValidCodeImg();
	    	initLoginFormValid();
	    	checkCookie();
	    	function initValidCodeImg() {
	    		$.post(basePath + "/login/validCode", function(data, status, jqXHR) {
	    			var result = $.parseJSON(data);
	    			if (result["success"]) {
		    			$CODE = result["code"];
		    			$VALIDCODE.prop("src", "data:image/png;base64," + result["validCode"]);
		    			//console.log($CODE);
	    			}
	    		});
	    	}
	    	
	    	$VALIDCODE.on("click", function(){
	    		initValidCodeImg();
	    	});
	    	
	    	
	    	function initLoginFormValid() {
	    		$LOGINFORM.bootstrapValidator({
	    			excluded: [':disabled', ':hidden', ':not(:visible)'],
	    			fields: {
		            	username: {
		            		container: "#error-username",
		                    validators: {
		                        notEmpty: {
		                            message: '请输入用户名'
		                        }
		                    }
		                },
		                password: {
		                	container: "#error-password",
		                    validators: {
		                        notEmpty: {
		                            message: '请输入密码'
		                        }
		                    }
		                },
		                validCode: {
		                	container: "#error-validCode",
		                	validators: {
		                		callback: {
		                			message: "请输入正确的验证码",
		                			callback: function(value, validator, $field) {
		                				return value.toUpperCase() == $CODE;
		                			}
		                		}
		                	}
		                }
		            }
	    		}).on('success.form.bv', function(e, data) {
					e.preventDefault();
					var $form = $(e.target);
					$form.data('bootstrapValidator').disableSubmitButtons(false);
					
					if($COOKIE.prop("checked")) {
						jr.setCookie("username", $("#username").val(), 10);
					} else {
						jr.setCookie("username", "", 0);
					}
					formSubmit($form);
				});
	    		
	    		$("#username").on("input", function(e) {
	    			$("#loginMobile").val(this.value);
	    			$("#loginEmail").val(this.value);
		    	});
	    	}
	    	   	
	    	// 表单提交
	        function formSubmit(form) {
	        	var _thisForm = $(form);
	        	if(_thisForm.data('bootstrapValidator').isValid()) {
	        		_thisForm.ajaxSubmit({
//	        		resetForm: true, // 提交成功后清除表单数据
	        			success: function(responseText, statusText, xhr, $form) { 
	        				var result = $.parseJSON(responseText);
//	        				alert(result["msg"]);
	        				if (result["success"]) {
	        					window.location.href = basePath + "/home";
	        				} else {
	        					jr.alert({msg: result["msg"]});
	        				}
	        			}
	        		});
	        	}
	        }
	    	
	    	
	        function checkCookie() {
	        	var username = jr.getCookie('username');
	        	
	        	if (username != null && username != "" && username != "null") {
	        		$("#username").val(username);
	        		$("#loginMobile").val(username);
	        		$("#loginEmail").val(username);
	        		$COOKIE.prop("checked", true);
	        	}
	        }
	    	
	    });
    </script>
  </body>
</html>
