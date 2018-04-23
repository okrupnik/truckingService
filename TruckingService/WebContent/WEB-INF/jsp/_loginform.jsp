<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.loginform.button.name"
	var="button" />
<fmt:message bundle="${loc}" key="locale.loginform.button.create.name"
	var="buttonCreate" />
<fmt:message bundle="${loc}" key="locale.loginform.button.forgot.name"
	var="buttonForgot" />

<div id="block">
	<div id="login">
		<form method="post" action="Controller">
			<input type="hidden" name="command" value="sign_in">
			<c:if test="${sessionScope.local=='en'}">
				<img src="${pageContext.request.contextPath}/img/text1.gif" alt=""
					width="56" height="11" />
				<input type="text" class="input" name="login" value="" />
				<img src="${pageContext.request.contextPath}/img/text2.gif" alt=""
					width="56" height="11" />
				<input type="password" class="input" name="password" value="" />
				<br />
				<input type="checkbox" class="checkbox" />
				<img src="${pageContext.request.contextPath}/img/text3.gif" alt=""
					width="70" height="13" />
				<br />
				<input type="submit" class="button" value="${button}" />
				<br />
			</c:if>
			<c:if test="${sessionScope.local=='ru'}">
				<img src="${pageContext.request.contextPath}/img/text11.gif" alt=""
					width="56" height="11" />
				<input type="text" class="input" name="login" value="" />
				<img src="${pageContext.request.contextPath}/img/text22.gif" alt=""
					width="56" height="11" />
				<input type="password" class="input" name="password" value="" />
				<br />
				<input type="checkbox" class="checkbox" />
				<img src="${pageContext.request.contextPath}/img/text33.gif" alt=""
					width="70" height="13" />
				<br />
				<input type="submit" class="button" value="${button}" />
				<br />
			</c:if>

		</form>
		<a
			href="${pageContext.request.contextPath}/Controller?command=get_page_to_create_user"
			class="create">${buttonCreate}</a> <a
			href="${pageContext.request.contextPath}/Controller?command=forgot_password"
			class="forgot">${buttonForgot}?</a>
	</div>
</div>