<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.menuauthorization.hello.text"
	var="helloText" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.editdata.text" var="editData" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.deleteuser.text" var="deleteUser" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.createnews.text" var="createNews" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.editnews.text" var="editNews" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.createorder.text" var="createOrder" />
<fmt:message bundle="${loc}"
	key="locale.menuauthorization.editorder.text" var="editOrder" />
<fmt:message bundle="${loc}" key="locale.menuauthorization.history.text"
	var="history" />
<fmt:message bundle="${loc}" key="locale.menuauthorization.signout.text"
	var="signout" />

<div id="block">
	<div id="login">
		<c:if test="${sessionScope.user.role=='admin' }">
			<b id="hellouser">${helloText}, ${sessionScope.userInfo.name}
				${sessionScope.userInfo.surname}</b>
			<br />
			<br />
			<div id="logintext">
				<a href="#" class="userdata">${editData}</a> <br /> 
				<a href="#"	class="userdata">${deleteUser}</a> <br /> 
				<a href="#" class="news">${createNews}</a> <br /> 
				<a href="#"	class="news">${editNews}</a> <br /> 
				<a href="#"	class="history">${history}</a> <br /> 
				<a href="${pageContext.request.contextPath}/Controller?command=sign_out" class="exit">${signout}</a>	<br />
			</div>
		</c:if>
		<c:if test="${sessionScope.user.role=='editor' }">
			<b id="hellouser">${helloText}, ${sessionScope.userInfo.name}
				${sessionScope.userInfo.surname}</b>
			<br />
			<br />
			<div id="logintext">
				<a href="#" class="userdata">${editData}</a> <br />
				<a href="#"	class="news">${createNews}</a> <br />
				<a href="#"	class="news">${editNews}</a> <br /> 
				<a href="#"	class="history">${history}</a> <br />
				<a href="${pageContext.request.contextPath}/Controller?command=sign_out" class="exit">${signout}</a>	<br />
			</div>
		</c:if>
		<c:if test="${sessionScope.user.role=='user' }">
			<b id="hellouser">${helloText}, ${sessionScope.userInfo.name} ${sessionScope.userInfo.surname}</b>
			<br />
			<br />
			<div id="logintext">
				<a href="${pageContext.request.contextPath}/Controller?command=get_page_to_edit_user" class="userdata">${editData}</a> <br /> 
				<a href="${pageContext.request.contextPath}/Controller?command=get_page_to_create_order" class="createorder">${createOrder}</a> <br /> 
				<a href="#"class="editorder">${editOrder}</a> <br /> 
				<a href="${pageContext.request.contextPath}/Controller?command=get_page_to_history_order" class="history">${history}</a> <br /> 
				<a href="${pageContext.request.contextPath}/Controller?command=sign_out" class="exit">${signout}</a>
				<br />
			</div>
		</c:if>
	</div>
</div>