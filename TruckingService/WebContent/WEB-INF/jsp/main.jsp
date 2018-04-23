<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Trucking Service</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main.css" type="text/css" />
</head>
<body>
	<% session.setAttribute("dateFrom", ""); %>
	<% session.setAttribute("dateTo", ""); %>
	<jsp:include page="_header.jsp"></jsp:include>
	<div id="wrapper" class="contentbg">
		<div id="sidebar">
			<c:if test="${empty sessionScope.user}">
				<jsp:include page="_loginform.jsp"></jsp:include>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				<jsp:include page="_menuauthorizationuser.jsp"></jsp:include>
			</c:if>			
		</div>
		<jsp:include page="_centralContent.jsp"></jsp:include>
	</div>
	<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>