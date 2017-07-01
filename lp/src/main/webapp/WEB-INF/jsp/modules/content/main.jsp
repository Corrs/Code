<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/static.jsp" %>
	<!-- PNotify -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    <!-- bootstrap table -->
	<link rel="stylesheet" href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/bootstrap-table.min.css">
	<link rel="stylesheet" href="<c:out value="${sessionScope.basePath }"></c:out>/css/custom_table.css">
    <div class="x_panel jrwl_custom_panel">
     <div class="col-md-12 col-sm-12 col-xs-12">
       <div class="x_panel jrwl_custom_listpanel">
         <div class="x_title">
           <h2>内容管理</h2>
           <ul class="nav navbar-right panel_toolbox">
             <li class="right"><a class="collapse-link" title="伸缩表格面板"><i class="fa fa-chevron-up"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="row">
        	<div class="x_content jrwl_custom_listcontent">
        		<div id="tablePanel" class="col-md-12">
		           <div id="toolbar">
		           		<button id="append" class="btn btn-info" title="新增"><i class="fa fa-plus fa-fw"></i>新增</button>
				        <button id="remove" class="btn btn-danger" title="删除" disabled><i class="fa fa-trash fa-fw"></i>删除</button>
				    </div>
		           	<table id="table" 
		           		data-toolbar="#toolbar"
		           		data-search="false"
		           		data-escape="true"
		           		data-trim-on-search="false"
		           		data-pagination="true"
		           		data-page-list="[15, 25, 50, 100, ALL]"
		           		data-page-size=15
		           		data-show-export="true"
		           		data-show-refresh="true"
		           		data-show-toggle="false"
		           		data-show-columns="true"
		           		data-show-footer="false"
		           		data-id-field="id"
		           		data-unique-id="id"
		           		data-search-on-enter-key=true
		           		data-classes="table table-hover table-condensed table-fixed"
		           	>
				    </table>
        		</div>
	        </div>
         </div>
       </div>
     </div>
   </div>
   <!-- bootstrap table -->
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/bootstrap-table.min.js"></script>
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/locale/bootstrap-table-zh-CN.min.js"></script>
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/extensions/export/bootstrap-table-export.min.js"></script>
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/tableExport.js"></script>
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-table-develop/dist/tableExport.js"></script>
   <script src="<c:out value="${sessionScope.basePath }"></c:out>/modules/content/main.js"></script>
