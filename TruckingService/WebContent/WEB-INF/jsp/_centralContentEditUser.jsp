<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.login.text" var="eUserLogin" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.password.text" var="eUserPass" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.email.text" var="eUserEmail" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.name.text" var="eUserName" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.surname.text" var="eUserSName" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.pnumber.text" var="eUserPNumb" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.city.text" var="eUserCity" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.address.text" var="eUserAddr" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.edituser.button.text" var="eUserChange" />

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
			<input type="hidden" name="command" value="edit_user">
			<table style="color: #174F63">
				<!-- <tr>
					<br />
					<br />
					<td><label for="newLogin">${eUserLogin}*:</label></td>
					<td>
						<div class="input-container">
							<input name="newLogin" id="newLogin" type="text" value="${sessionScope.user.login}"/>
						</div>
					</td>
				</tr> -->
				<tr>
					<td><label for="pass">${eUserPass}*:</label></td>
					<td>
						<div class="input-container">
							<input name="pass" id="pass" type="password" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="email">${eUserEmail}*:</label></td>
					<td>
						<div class="input-container">
							<input name="email" id="email" type="text" value="${sessionScope.userInfo.email}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="fname">${eUserName}:</label></td>
					<td>
						<div class="input-container">
							<input name="firstName" id="fname" type="text" value="${sessionScope.userInfo.name}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="sname">${eUserSName}:</label></td>
					<td>
						<div class="input-container">
							<input name="surName" id="sname" type="text" value="${sessionScope.userInfo.surname}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="pNumber">${eUserPNumb}*:</label></td>
					<td>
						<div class="input-container">
							<input name="phoneNumber" id="pNumber" type="text" value="${sessionScope.userInfo.phoneNumber}" />
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="city">${eUserCity}:</label></td>
					<td>
						<div class="input-container">
							<input name="city" id="city" type="text" value="${sessionScope.userInfo.city}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="address">${eUserAddr}:</label></td>
					<td>
						<div class="input-container">
							<input name="address" id="address" type="text" value="${sessionScope.userInfo.address}"/>
						</div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" class="button" value="${eUserChange}" /></td>
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