<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/static.jsp" %>
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<!-- PNotify -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    <div class="x_panel jrwl_custom_panel">
     <div class="col-md-12 col-sm-12 col-xs-12">
       <div class="x_panel">
         <div class="x_title">
           <h2>编辑内容管理</h2>
           <ul class="nav navbar-right panel_toolbox">
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<form class="form-horizontal form-label-left" id="saveForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/contact/save">
         	   <input type="hidden" name="id" value="<c:out value="${model.id }"></c:out>">
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">联系类型 <i class="required text-danger">*</i>
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <select class="form-control col-md-7 col-xs-12" name="type" >
                   	 <option <c:if test="${model.type eq '1' }">selected=""</c:if> value="1">QQ</option>
                     <option <c:if test="${model.type eq '2' }">selected=""</c:if> value="2">联系电话</option>
                     <option <c:if test="${model.type eq '3' }">selected=""</c:if> value="3">微信</option>
                   </select>
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contact">联系方式 <i class="required text-danger">*</i>
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   	<input class="form-control col-md-7 col-xs-12" id="contact" type="text"  value="<c:out value="${model.contact }"></c:out>" name="contact">
                 </div>
               </div>
               <div class="ln_solid"></div>
               <div class="form-group">
                 <div class="col-md-6 col-md-offset-3">
                   <button id="send" type="submit" class="btn btn-success">保存</button>
                   <button id="sendBack" type="submit" class="btn btn-success">保存并返回</button>
                 </div>
               </div>
             </form>
         </div>
       </div>
     </div>
   </div>
   
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery.form/jquery.form.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/bootstrapValidator.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/language/zh_CN.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
   <!-- Dropzone.js -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/dropzone/dist/min/dropzone.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/contact/save.js"></script>