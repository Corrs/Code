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
           <h2>编辑菜单信息</h2>
           <ul class="nav navbar-right panel_toolbox">
             <!-- <li><a class="save-link" title="保存"><i class="fa fa-floppy-o text-success"></i></a></li>
             <li><a class="reset-link" title="重置"><i class="fa fa-undo text-info"></i></a></li> -->
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<form class="form-horizontal form-label-left" id="saveForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/sys/menu/save">
         	   <input type="hidden" name="id" id="modelid" value="<c:out value="${model.id }"></c:out>">
         	   <input type="hidden" name="pcode" id="pcode" value="<c:out value="${model.pcode }"></c:out>">
         	   <input type="hidden" name="code" id="code" value="<c:out value="${model.code }"></c:out>">
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">菜单名称 <i class="required text-danger">*</i>
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="name" name="name" class="form-control col-md-7 col-xs-12" autocomplete="off" placeholder="输入需求类型"  value="<c:out value="${model.name }"></c:out>">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="type">菜单类型 <i class="required text-danger">*</i></label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <div class="radio">
                     <label>
                       <input type="radio" <c:if test="${model.type==0 }">checked=""</c:if> value="0" name="type"> 栏目
                     </label>
                     <label>
                       <input type="radio" <c:if test="${model.type==1 }">checked=""</c:if> value="1" name="type"> 菜单
                     </label>
                   </div>
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="path">页面路径 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="path" name="path" class="form-control col-md-7 col-xs-12"  <c:if test="${model.type==0 }">disabled="disabled"</c:if>  autocomplete="off" placeholder="输入访问路径" value="<c:out value="${model.path }"></c:out>">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="icon">菜单图标 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   	<input type="text" id="icon" name="icon" class="form-control col-md-7 col-xs-12" autocomplete="off" placeholder="输入菜单图标" value="<c:out value="${model.icon }"></c:out>">
                 </div>
               </div>
               <div class="ln_solid"></div>
               <div class="form-group">
                 <div class="col-md-6 col-md-offset-3">
                   <button id="reset" type="reset" class="btn btn-primary">重置</button>
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
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/sys/menu/save.js"></script>