<%@page import="com.chinesejr.model.sys.SysContentModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/static.jsp" %>
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<!-- PNotify -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    <!-- Bootstrap Colorpicker -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/mjolnic-bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <div class="x_panel jrwl_custom_panel">
     <div class="col-md-12 col-sm-12 col-xs-12">
       <div class="x_panel">
         <div class="x_title">
           <h2>编辑基础设置信息</h2>
           <ul class="nav navbar-right panel_toolbox">
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<form class="form-horizontal form-label-left" id="saveForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/sys/content/save">
         	   <input type="hidden" name="id" id="modelid" value="<c:out value="${model.id }"></c:out>">
         	   <input type="hidden" name="prop1" id="prop1" value="<c:out value="${model.prop1 }"></c:out>">
         	   <input type="hidden" name="prop3" id="prop3" value="<c:out value="${model.prop3 }"></c:out>">
         	   <input type="hidden" name="prop4" id="prop4" value="<c:out value="${model.prop4 }"></c:out>">
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title">网站标题 <i class="required text-danger">*</i>
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="title" name="title" class="form-control col-md-7 col-xs-12" autocomplete="off" value="<c:out value="${model.title }"></c:out>" placeholder="输入网站标题" >
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bgcolor">网站主题 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="bgcolor" readonly="readonly" name="bgcolor" class="colorpicker form-control col-md-7 col-xs-12 " autocomplete="off" value="<c:out value="${model.bgcolor }"></c:out>" placeholder="选择主题颜色">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="offer">优惠信息 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="offer" name="offer" class="form-control col-md-7 col-xs-12" autocomplete="off" value="<c:out value="${model.offer }"></c:out>" placeholder="输入优惠信息，如：恭喜{手机号}获得优惠信息">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="buy">购买信息 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="buy" name="buy" class="form-control col-md-7 col-xs-12" autocomplete="off" value="<c:out value="${model.buy }"></c:out>" placeholder="输入购买信息，如：用户{手机号}在9分钟前订购了（两件99元）">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="prop2">表单提交提示信息 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="prop2" name="prop2" class="form-control col-md-7 col-xs-12" autocomplete="off" value="<c:out value="${model.prop2 }"></c:out>" placeholder="输入表单提交提示信息">
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="inuse">是否启用 <i class="required text-danger">*</i></label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <div class="radio">
                     <label>
                       <input type="radio" <c:if test="${model.inuse==0 }">checked=""</c:if> value="0" name="inuse"> 未启用
                     </label>
                     <label>
                       <input type="radio" <c:if test="${model.inuse==1 }">checked=""</c:if> value="1" name="inuse"> 启用
                     </label>
                   </div>
                 </div>
               </div>
               <div class="item form-group">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="footerbgcolor">底部颜色 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <input type="text" id="footerbgcolor" readonly="readonly" name="footerbgcolor" class="colorpicker form-control col-md-7 col-xs-12 " autocomplete="off" value="<c:out value="${model.footerbgcolor }"></c:out>" placeholder="选择主题颜色">
                 </div>
               </div>
               <div class="item form-group" style="margin-top: 30px;">
                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="footercontent">底部信息 &nbsp;
                 </label>
                 <div class="col-md-6 col-sm-6 col-xs-12">
                   <!-- <input type="text" id="footercontent" name="footercontent" class="form-control col-md-7 col-xs-12" autocomplete="off" placeholder="输入底部信息"> -->
                     <div class="btn-toolbar editor" data-role="editor-toolbar" data-target="#editor-one">
	                    <div class="btn-group">
	                      <a class="btn dropdown-toggle" id="Font" data-toggle="dropdown" title="字体"><i class="fa fa-font"></i><b class="caret"></b></a>
	                      <ul class="dropdown-menu">
	                      </ul>
	                    </div>
	                    <div class="btn-group">
	                      <a class="btn dropdown-toggle" data-toggle="dropdown" title="字体大小"><i class="fa fa-text-height"></i>&nbsp;<b class="caret"></b></a>
	                      <ul class="dropdown-menu">
	                        <li>
	                          <a data-edit="fontSize 5">
	                            <p style="font-size:17px">大</p>
	                          </a>
	                        </li>
	                        <li>
	                          <a data-edit="fontSize 3">
	                            <p style="font-size:14px">中</p>
	                          </a>
	                        </li>
	                        <li>
	                          <a data-edit="fontSize 1">
	                            <p style="font-size:11px">小</p>
	                          </a>
	                        </li>
	                      </ul>
	                    </div>
	
	                    <div class="btn-group">
	                      <a class="btn" data-edit="bold" title="加粗 (Ctrl/Cmd+B)"><i class="fa fa-bold"></i></a>
	                      <a class="btn" data-edit="italic" title="倾斜 (Ctrl/Cmd+I)"><i class="fa fa-italic"></i></a>
	                      <a class="btn" data-edit="strikethrough" title="删除线"><i class="fa fa-strikethrough"></i></a>
	                      <a class="btn" data-edit="underline" title="下划线 (Ctrl/Cmd+U)"><i class="fa fa-underline"></i></a>
	                    </div>
	
	                    <div class="btn-group">
	                      <a class="btn" data-edit="insertunorderedlist" title="符号列表"><i class="fa fa-list-ul"></i></a>
	                      <a class="btn" data-edit="insertorderedlist" title="数字列表"><i class="fa fa-list-ol"></i></a>
	                      <a class="btn" data-edit="outdent" title="减少缩进 (Shift+Tab)"><i class="fa fa-dedent"></i></a>
	                      <a class="btn" data-edit="indent" title="缩进 (Tab)"><i class="fa fa-indent"></i></a>
	                    </div>
	
	                    <div class="btn-group">
	                      <a class="btn" data-edit="justifyleft" title="左对齐 (Ctrl/Cmd+L)"><i class="fa fa-align-left"></i></a>
	                      <a class="btn" data-edit="justifycenter" title="居中 (Ctrl/Cmd+E)"><i class="fa fa-align-center"></i></a>
	                      <a class="btn" data-edit="justifyright" title="右对齐 (Ctrl/Cmd+R)"><i class="fa fa-align-right"></i></a>
	                      <a class="btn" data-edit="justifyfull" title="两端对齐 (Ctrl/Cmd+J)"><i class="fa fa-align-justify"></i></a>
	                    </div>
	
	                    <div class="btn-group">
	                      <a class="btn dropdown-toggle" data-toggle="dropdown" title="超链接"><i class="fa fa-link"></i></a>
	                      <div class="dropdown-menu input-append">
	                        <input class="span2" placeholder="URL" type="text" data-edit="createLink" />
	                        <button class="btn" type="button">添加</button>
	                      </div>
	                      <a class="btn" data-edit="unlink" title="移除超链接"><i class="fa fa-cut"></i></a>
	                    </div>
	
	                    <div class="btn-group">
	                      <a class="btn" data-edit="undo" title="撤销 (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
	                      <a class="btn" data-edit="redo" title="重做 (Ctrl/Cmd+Y)"><i class="fa fa-repeat"></i></a>
	                    </div>
	                    <!-- <div class="btn-group" style="position:relative;left: 0">
	                      <a class="btn" title="上传图片" id="pictureBtn"><i class="fa fa-picture-o"></i></a>
	                      <input  style="cursor: pointer;" type="file" title="上传图片" data-role="magic-overlay" data-target="#pictureBtn" data-edit="insertImage" />
	                    </div> -->
	                  </div>
	
	                  <div id="editor-one" class="editor-wrapper" style="overflow: auto"><%=((SysContentModel)request.getAttribute("model")).getFootercontent() %></div>
	                  <%-- <div id="editor-one" class="editor-wrapper" style="overflow: auto"><c:out value="${model.footercontent }"></c:out></div> --%>
	                  <textarea name="footercontent" id="footercontent" style="display:none;"></textarea>
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
   <!-- Bootstrap Colorpicker -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/mjolnic-bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
    <!-- bootstrap-wysiwyg -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery.hotkeys/jquery.hotkeys.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/google-code-prettify/src/prettify.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/sys/content/save.js"></script>