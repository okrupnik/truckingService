<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.login.text" var="nUserLogin" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.password.text" var="nUserPass" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.email.text" var="nUserEmail" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.name.text" var="nUserName" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.surname.text" var="nUserSName" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.pnumber.text" var="nUserPNumb" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.city.text" var="nUserCity" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.address.text" var="nUserAddr" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.newuser.button.text" var="nUserCreate" />

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
	<div class="bigblock">
		<form id="regForm" action="Controller" method="post">
			<input type="hidden" name="command" value="create_user">
			<table style="color: #174F63">
				<tr>
					<br />
					<br />
					<td><label for="newLogin">${nUserLogin}*:</label></td>
					<td>
						<div class="input-container">
							<input name="newLogin" id="newLogin" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="pass">${nUserPass}*:</label></td>
					<td>
						<div class="input-container">
							<input name="pass" id="pass" type="password" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="email">${nUserEmail}*:</label></td>
					<td>
						<div class="input-container">
							<input name="email" id="email" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="fname">${nUserName}:</label></td>
					<td>
						<div class="input-container">
							<input name="firstName" id="fname" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="sname">${nUserSName}:</label></td>
					<td>
						<div class="input-container">
							<input name="surName" id="sname" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="pNumber">${nUserPNumb}*:</label></td>
					<td>
						<div class="input-container">
							<input name="phoneNumber" id="pNumber" type="text" placeholder="+375-29-5554433" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="city">${nUserCity}:</label></td>
					<td>
						<div class="input-container">
							<input name="city" id="city" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="address">${nUserAddr}:</label></td>
					<td>
						<div class="input-container">
							<input name="address" id="address" type="text" />
						</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="button" value="${nUserCreate}" /></td>
				</tr>
			</table>
		</form>
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