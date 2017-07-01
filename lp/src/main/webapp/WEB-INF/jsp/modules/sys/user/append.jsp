<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/static.jsp" %>
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<!-- PNotify -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/css/global.css" rel="stylesheet">
    <div class="x_panel jrwl_custom_panel">
     <div class="col-md-12 col-sm-12 col-xs-12">
       <div class="x_panel">
         <div class="x_title">
           <h2>新增用户信息</h2>
           <ul class="nav navbar-right panel_toolbox">
             <!-- <li><a class="save-link" title="保存"><i class="fa fa-floppy-o text-success"></i></a></li>
             <li><a class="reset-link" title="重置"><i class="fa fa-undo text-info"></i></a></li> -->
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<div class="" role="tabpanel" data-example-id="togglable-tabs">
              <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">基本信息</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">头像照片</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">更多个人信息</a>
                </li>
              </ul>
              <div id="myTabContent" class="tab-content">
              	<form method="post" action="">
              		<input type="hidden" name="id" id="modelid">
	                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
	                  <div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12 text-right" for="username">用户名 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <input type="text" id="username" name="username" class="form-control col-md-7 col-xs-12" autocomplete="off" placeholder="输入用户名">
		                 </div>
		               </div>
	                </div>
	                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
	                </div>
	                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">
	                </div>
                </form>
              </div>
            </div>
         </div>
       </div>
     </div>
   </div>
   
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery.form/jquery.form.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/bootstrapValidator.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/language/zh_CN.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/sys/user/save.js"></script>