<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="/WEB-INF/jsp/tags/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/jsp/tags/fmt.tld" %>
<%@ taglib prefix="sql" uri="/WEB-INF/jsp/tags/sql.tld" %>
<%@ taglib prefix="x" uri="/WEB-INF/jsp/tags/x.tld" %> 
<%@ taglib prefix="fn" uri="/WEB-INF/jsp/tags/fn.tld" %>   
<%
	// 项目路径名
	String contextPath = request.getContextPath();
	// 全路径
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
	session.setAttribute("basePath", basePath);
	session.setAttribute("contextPath", contextPath);
	boolean isLogin = (session.getAttribute("sessiontimeout")==null?false:Boolean.getBoolean((String) session.getAttribute("sessiontimeout")));
	String uri = (session.getAttribute("uri")==null?"":(String) session.getAttribute("uri"));
	String menus = (String) request.getAttribute("menus");
%> 

<script type="text/javascript">
	var basePath = "<%=basePath %>",
		menus = JSON.parse('<%=menus%>'),
		isLogin = <%=isLogin %>,
		uri = "<%=uri %>";
		
	/* if(!isLogin && window.location.href.indexOf("/login") == -1) {
		top.window.location.href = basePath + "/login";	
	}  */
	
	window.onload = function() {
		$(document).ajaxComplete(function(event, xhr, settings) { 
			//debugger;
			hideLoading();
			if(isLogin && window.location.href.indexOf("/login") == -1) {
				top.window.location.href = basePath + "/login";	
			}
		}).ajaxStart(function(){
			showLoading();
	    }).ajaxStop(function(){
	    	hideLoading();
	    });
		
		
		function showLoading() {
			if ($(top.document).find("div#loading").length > 0) {
				$('#loading').show();
			}
		}
		
		function hideLoading() {
			if ($(top.document).find("div#loading").length > 0) {
				$('#loading').hide();
			}
		}
	};
	
</script>   
