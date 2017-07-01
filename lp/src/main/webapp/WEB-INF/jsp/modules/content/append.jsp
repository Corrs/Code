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
           <h2>新增内容管理</h2>
           <ul class="nav navbar-right panel_toolbox">
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<form class="dropzone" dropzone="" id="dropzone">
                <input type="hidden" name="id">
                <input type="hidden" name="catalog">
                <input type="hidden" name="remark">
                <input type="hidden" name="createdate">
                <div class="dz-default dz-message fallback"></div>
       		</form>
         	<form class="form-horizontal form-label-left" id="saveForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/content/save">
         	   <input type="hidden" name="id">
         	   <input type="hidden" name="image">
         	   <input type="hidden" name="createdate">
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="catalog">栏目名称 <i class="required text-danger">*</i>
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <select class="form-control col-md-7 col-xs-12" name="catalog" >
                   	 <option value="banner" selected="">顶部图片</option>
                     <option value="carousel">轮播图片</option>
                     <option value="sub">附属图片</option>
                     <c:forEach items="${catalogList }" var="catalog">
                     <option value="<c:out value="${catalog.code }"></c:out>"><c:out value="${catalog.name }"></c:out></option>
                     </c:forEach>
                   </select>
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="remark">图片名称 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   	<input class="form-control col-md-7 col-xs-12" id="remark" type="text" name="remark">
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
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/content/save.js"></script>