<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Edit User</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css" />
</head>
<body>
	<% session.setAttribute("dateFrom", ""); %>
	<% session.setAttribute("dateTo", ""); %>
	<jsp:include page="_header.jsp"></jsp:include>
	<div id="wrapper" class="contentbg">
		<div id="sidebar">
			<jsp:include page="_menuauthorizationuser.jsp"></jsp:include>
		</div>
		<jsp:include page="_centralContentEditUser.jsp"></jsp:include>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>