<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
    <!-- iCheck -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- bootstrap-progressbar -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
    <!-- Bootstrap Colorpicker -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/mjolnic-bootstrap-colorpicker/dist/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/cropper/dist/cropper.min.css" rel="stylesheet">
    <!-- Dropzone.js -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/dropzone/dist/min/dropzone.min.css" rel="stylesheet">
    <!-- 自定义样式 -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/css/custom.min.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/css/global.css" rel="stylesheet">
  </head>

  <body class="nav-md">
  	<div id="loading"><i class="fa fa-spinner fa-spin fa-3x fa-fw"></i></div>
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <span class="site_title"><i class="fa fa-paw"></i> <span><c:out value="${sessionScope.webTitle }"></c:out></span></span>
            </div>
            <div class="clearfix"></div>
            <!-- 侧边栏菜单 -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <!-- <h3>General</h3> -->
                <ul class="nav side-menu" id="side-menu">
                </ul>
              </div>
            </div>
            <!-- /侧边栏菜单 -->
          </div>
        </div>

        <!-- 顶部导航 -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
              	<li><a href="<c:out value="${sessionScope.basePath }" />/login/logoutUser"><i class="glyphicon glyphicon-off"></i> 退出登录</a></li>
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                                         欢迎您：<c:out value="${userinfor.truename }" />
                  </a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /顶部导航 -->

        <!-- 页面内容 -->
        <div class="right_col" role="main" id="pageContent">
        	<div class="x_panel jrwl_custom_panel"></div>
        </div>
        <!-- /页面内容 -->
		
        <!-- 页脚内容 -->
        <footer>
          <div class="pull-right">
            <p>Copyright &copy; 2005-2017 潍坊匠人网络科技有限公司 (Weifang chinsesjr Network Technology Co., Ltd.) All Rights Reserved)</p>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /页脚内容 -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/nprogress/nprogress.js"></script>
    <!-- Chart.js -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Chart.js/dist/Chart.min.js"></script>
    <!-- gauge.js -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/gauge.js/dist/gauge.min.js"></script>
    <!-- bootstrap-progressbar -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- iCheck -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/iCheck/icheck.min.js"></script>
    <!-- Skycons -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/skycons/skycons.js"></script>
    <!-- Flot -->
    <%-- <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Flot/jquery.flot.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Flot/jquery.flot.pie.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Flot/jquery.flot.time.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Flot/jquery.flot.stack.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/Flot/jquery.flot.resize.js"></script> --%>
    <!-- Flot plugins -->
    <%-- <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/flot.orderbars/js/jquery.flot.orderBars.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/flot-spline/js/jquery.flot.spline.min.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/flot.curvedlines/curvedLines.js"></script> --%>
    <!-- DateJS -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/DateJS/build/date.js"></script>
    <!-- JQVMap -->
    <%-- <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jqvmap/dist/jquery.vmap.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jqvmap/dist/maps/jquery.vmap.world.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jqvmap/examples/js/jquery.vmap.sampledata.js"></script> --%>
    <!-- PNotify -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.js"></script>
    <!-- alert confirm等弹窗 -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootbox.min.js"></script>
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/respond.min.js"></script>
	<script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/json2.min.js"></script>
	<![endif]-->
    <!-- 自定义js -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/modules/js/global.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/modules/index.js"></script>
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/custom.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  </body>
</html>
