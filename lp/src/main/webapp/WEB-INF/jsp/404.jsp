<%@ page language="java" contentType="text/html; charset=UTF-8"
  isErrorPage="true"  pageEncoding="UTF-8"%>
<%@ include file="static.jsp" %>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="text-align: center;">
				<img style="margin-top: 8%;" alt="页面未找到" src="<c:out value="${sessionScope.basePath }" />/img/404.jpg">
			</div>
		</div>	
	</div>
</body>
</html>