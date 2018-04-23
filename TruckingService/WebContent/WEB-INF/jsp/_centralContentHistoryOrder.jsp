<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.order.text" var="hSelect" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.order.from.text" var="hFrom" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.order.to.text" var="hTo" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.button.text" var="hPerform" />

<div id="content">
	<img src="${pageContext.request.contextPath}/img/photo.jpg" alt=""
		width="721" height="142" /><br />
	<c:if test="${sessionScope.local=='en'}">
		<c:if test="${not empty sessionScope.errorMessageEn}">
			<h1 style="color: #ff0000">
				<c:out value="${sessionScope.errorMessageEn}"></c:out>
				<%
					session.setAttribute("errorMessageEn", "");
				%>
			</h1>
		</c:if>
	</c:if>
	<c:if test="${sessionScope.local=='ru'}">
		<c:if test="${not empty sessionScope.errorMessageRu}">
			<h1 style="color: #ff0000">
				<c:out value="${sessionScope.errorMessageRu}"></c:out>
				<%
					session.setAttribute("errorMessageRu", "");
				%>
			</h1>
		</c:if>
	</c:if>
	<div class="bigblock">
		<form id="regForm" action="Controller" method="get">
			<input type="hidden" name="command" value="history_order">
			<table style="color: #174F63">
				<tr>
					<td colspan="4" align="center"><label for="description">
							<b>${hSelect}</b>
					</label></td>
				</tr>
				<tr>
					<td><label for="dateFrom">${hFrom}:</label></td>
					<td>
						<div class="input-container">							
							<input name="dateFrom" type="date" style="width: 130px;" value="${sessionScope.dateFrom}" />						
						</div>
					</td>

					<td><label for="dateTo">${hTo}:</label></td>
					<td>
						<div class="input-container">						
							<input name="dateTo" type="date" style="width: 130px;" value="${sessionScope.dateTo}"/>						
						</div>
					</td>
					<td colspan="4" align="center">
						<div class="input-container">
							<input type="submit" class="button" value="${hPerform}" />							
						</div>
					</td>				
				</tr>
			</table>
		</form>
		<jsp:include page="_centralContentHistoryOrderPage.jsp"></jsp:include>
	</div>
	<c:if test="${empty requestScope.orderList}">
	<div class="news">
		<c:if test="${sessionScope.local=='en'}">
			<img src="${pageContext.request.contextPath}/img/title2.gif" alt=""
				width="83" height="33" />
			<br />
		</c:if>
		<c:if test="${sessionScope.local=='ru'}">
			<img src="${pageContext.request.contextPath}/img/title22.gif" alt=""
				width="83" height="33" />
			<br />
		</c:if>
		<div class="newsblock">
			<p class="date">03 April, 2018</p>
			<p></p>
			<p>
				<a href="#">The weather is good.</a>
			</p>
		</div>
		<div class="newsblock">
			<p class="date">09 April, 2018</p>
			<p></p>
			<p>
				<a href="#">The weather likely will be good.</a>
			</p>
		</div>
	</div>
	</c:if>
</div>