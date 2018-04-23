<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.loginform.button.name"
	var="button" />

<fmt:message bundle="${loc}" key="locale.header.button.home.text"
	var="buttonHome" />
<fmt:message bundle="${loc}" key="locale.header.button.contacts.text"
	var="buttonContact" />
<fmt:message bundle="${loc}" key="locale.header.button.services.text"
	var="buttonService" />
<fmt:message bundle="${loc}" key="locale.header.button.about.text"
	var="buttonAbout" />
<fmt:message bundle="${loc}" key="locale.header.button.forum.text"
	var="buttonForum" />

<div id="footer" class="mainbg">
	<div id="navigation">
		<ul>
			<li><a href="#">${buttonHome}</a> |</li>
			<li><a href="#">${buttonService}</a> |</li>
			<li><a href="#">${buttonForum}</a> |</li>
			<li><a href="#">${buttonAbout}</a> |</li>
			<li><a href="#">${buttonContact}</a></li>
		</ul>
	</div>
	<p>
		Copyright &copy;. All rights reserved. Design by <a href="#"
			class="bft" title="" target="_blank">Fisher</a>
	</p>	
</div>