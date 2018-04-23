<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.header.button.ru.text"	var="buttonRu" />
<fmt:message bundle="${loc}" key="locale.header.button.en.text"	var="buttonEn" />
<fmt:message bundle="${loc}" key="locale.header.button.home.text" var="buttonHome" />
<fmt:message bundle="${loc}" key="locale.header.button.search.text"	var="buttonSearch" />
<fmt:message bundle="${loc}" key="locale.header.button.contacts.text" var="buttonContact" />
<fmt:message bundle="${loc}" key="locale.header.button.services.text" var="buttonService" />
<fmt:message bundle="${loc}" key="locale.header.button.about.text" var="buttonAbout" />
<fmt:message bundle="${loc}" key="locale.header.button.faq.text" var="buttonFaq" />
<fmt:message bundle="${loc}" key="locale.header.button.forum.text" var="buttonForum" />

<div id="header">
	<a href="${pageContext.request.contextPath}/Controller?command=main_page"> <img src="${pageContext.request.contextPath}/img/logo.gif" alt="" width="322" height="127" class="logo" /></a>
	<div id="meta">
		<a href="${pageContext.request.contextPath}/Controller?command=main_page" class="meta1">${buttonHome}</a> 
		<a href="#" class="meta2">${buttonSearch}</a>
		<a href="#" class="meta3">${buttonContact}</a> 
		<a href="${pageContext.request.contextPath}/Controller?command=localization&local=ru"><img
			src="${pageContext.request.contextPath}/img/ru.gif" border="0" alt="">${buttonRu}</a>
		<a href="${pageContext.request.contextPath}/Controller?command=localization&local=en"><img
			src="${pageContext.request.contextPath}/img/en.gif" border="0" alt="">${buttonEn}</a>
	</div>
	<div id="menu">
		<ul>
			<li id="active"><a href="${pageContext.request.contextPath}/jsp/main.jsp">${buttonHome}</a></li>
			<li><a href="#">${buttonService}</a></li>
			<li><a href="#">${buttonAbout}</a></li>
			<li><a href="#">${buttonFaq}</a></li>
			<li><a href="#">${buttonForum}</a></li>
		</ul>
	</div>
	<div id="submenu">
		<!-- <ul class="first">
            <li><a href="#" id="over">Mission</a></li>
            <li><a href="#">Company</a></li>
            <li><a href="#">History</a></li>
            <li><a href="#">News</a></li>
        </ul> -->
	</div>
</div>