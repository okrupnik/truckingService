<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.centralcontent.service.text"
	var="serviceText" />

<div id="content">
	<img src="${pageContext.request.contextPath}/img/photo.jpg" alt=""
		width="721" height="142" /><br />
	<c:if test="${sessionScope.local=='en'}">
		<c:if test="${not empty sessionScope.errorMessageEn}">
			<h1 style="color: #ff0000">
				<c:out value="${sessionScope.errorMessageEn}"></c:out>
				<% session.setAttribute("errorMessageEn", ""); %>
			</h1>
		</c:if>
	</c:if>
	<c:if test="${sessionScope.local=='ru'}">
		<c:if test="${not empty sessionScope.errorMessageRu}">
			<h1 style="color: #ff0000">
				<c:out value="${sessionScope.errorMessageRu}"></c:out>
				<% session.setAttribute("errorMessageRu", ""); %>
			</h1>
		</c:if>
	</c:if>
	<c:if test="${sessionScope.local=='en'}">
		<c:if test="${not empty sessionScope.successEn}">
			<h1 style="color: #0000ff">
				<c:out value="${sessionScope.successEn}"></c:out>
				<% session.setAttribute("successEn", ""); %>
			</h1>
		</c:if>
	</c:if>
	<c:if test="${sessionScope.local=='ru'}">
		<c:if test="${not empty sessionScope.successRu}">
			<h1 style="color: #0000ff">
				<c:out value="${sessionScope.successRu}"></c:out>
				<% session.setAttribute("successRu", ""); %>
			</h1>
		</c:if>
	</c:if>
	<div class="bigblock">
		<c:if test="${sessionScope.local=='en'}">
			<img src="${pageContext.request.contextPath}/img/title1.gif"
				width="136" height="33" />
			<br />
		</c:if>
		<c:if test="${sessionScope.local=='ru'}">
			<img src="${pageContext.request.contextPath}/img/title11.gif"
				width="136" height="33" />
			<br />
		</c:if>
		<c:if test="${sessionScope.local=='en'}">
			<p>Promote world trade by erasing the borders between countries,
				providing our Clients with perfect schemes of delivery of goods from
				anywhere in the world in a qualitative, reliable and timely manner.</p>
			<p>The company's business relations with its Employees, Clients
				and Partners are based on values developed in the course of activity
				and declared in corporate standards.</p>
			<p>In the market of high competition in the sphere of logistic
				services, the company daily confirms the image of a reliable
				partner, as evidenced by the numerous gratitudes and recommendations
				of the Clients.</p>
			<p>
			<ul>
				Competitive advantages:
				<li>a reliable partner with extensive experience in the market;</li>
				<li>high-quality service;</li>
				<li>complex logistical service;</li>
			</ul>
			</p>
		</c:if>
		<c:if test="${sessionScope.local=='ru'}">
			<p>Содействовать мировой торговле, стирая границы между странами,
				предоставляя нашим. Клиентам совершенные схемы доставки товаров из
				любой точки мира качественным, надежным и своевременным образом.</p>
			<p>Деловые отношения компании со своими сотрудниками, клиентами и
				партнерами основаны на ценностях, разработанных в ходе деятельности
				и заявленных в корпоративных стандартах.</p>
			<p>На рынке высокой конкуренции в сфере логистических услуг
				компания ежедневно подтверждает образ надежного партнера, о чем
				свидетельствуют многочисленные благодарности и рекомендации
				клиентов.</p>
			<p>
			<ul>
				Конкурентные преимущества:
				<li>надежный партнер с большим опытом работы на рынке;</li>
				<li>высококачественный сервис;</li>
				<li>комплексное логистическое обслуживание;</li>
			</ul>
			</p>
		</c:if>
		<a href="#">${serviceText}</a>
	</div>
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
</div>