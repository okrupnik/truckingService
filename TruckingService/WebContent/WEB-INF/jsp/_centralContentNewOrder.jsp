<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.description.text" var="descript" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.fromWhere.text" var="fromWhere" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.where.text" var="where" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.titleLab.text" var="title" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.cityLab.text" var="city" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.deliveryLab.text" var="deliveryDate" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.streetLab.text" var="street" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.weightLab.text" var="weight" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.weightKgLab.text" var="weightKg" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.houseLab.text" var="house" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.priceLab.text" var="price" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.flatLab.text" var="flat" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.commentLab.text" var="comment" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.buttonCalc.text" var="calcOrder" />
<fmt:message bundle="${loc}" key="locale.centralcontent.neworder.buttonCreate.text" var="createOrder" />


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
			<input type="hidden" name="command" value="create_order">
			<table style="color: #174F63">
				<tr>
					<td>					
					</td>
					<td align="center">
						<label for="description"><b>${descript}</b></label>
					</td>
					<td>					
					</td>
					<td align="center">
						<label for="fromWhere"><b>${fromWhere}</b></label>
					</td>
					<td>					
					</td>
					<td align="center">
						<label for="where"><b>${where}</b></label>
					</td>
				</tr>
				<tr>
					<td><label for="titleLab">${title}:</label></td>
					<td>
						<div class="input-container">
							<input name="title" id="title" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="cityLab">${city}:</label></td>
					<td>
						<div class="input-container">
							<input name="cityFrom" id="cityFrom" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="cityLab">${city}:</label></td>
					<td>
						<div class="input-container">
							<input name="cityTo" id="cityTo" type="text" style="width: 100px;"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="deliveryLab">${deliveryDate}:</label></td>
					<td>
						<div class="input-container">
							<input name="deliveryDate" id="title" type="date" style="width: 100px;" />
						</div>
					</td>
					<td><label for="streetLab">${street}:</label></td>
					<td>
						<div class="input-container">
							<input name="streetFrom" id="streetFrom" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="streetLab">${street}:</label></td>
					<td>
						<div class="input-container">
							<input name="streetTo" id="streetFrom" type="text" style="width: 100px;"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="weightLab">${weight}, ${weightKg}:</label></td>
					<td>
						<div class="input-container">
							<input name="weight" id="weight" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="houseLab">${house}:</label></td>
					<td>
						<div class="input-container">
							<input name="houseFrom" id="houseFrom" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="houseLab">${house}:</label></td>
					<td>
						<div class="input-container">
							<input name="houseTo" id="houseTo" type="text" style="width: 100px;"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="priceLab">${price}, BYN:</label></td>
					<td>
						<div class="input-container">
							<input name="price" id="price" type="text" style="width: 100px;" readonly />
						</div>
					</td>
					<td><label for="flatLab">${flat}:</label></td>
					<td>
						<div class="input-container">
							<input name="flatFrom" id="flatFrom" type="text" style="width: 100px;"/>
						</div>
					</td>
					<td><label for="flatLab">${flat}:</label></td>
					<td>
						<div class="input-container">
							<input name="flatTo" id="flatTo" type="text" style="width: 100px;"/>
						</div>
					</td>
				</tr>
				<tr>
					<td><label for="commentLab">${comment}:</label></td>
					<td colspan="5"><textarea name="comment" rows="5" cols="65"> </textarea>
					</td>
				</tr>
				<tr>
					<td align="center"></td>
					<td colspan="3" align="center"><input type="submit"
						onclick="document.getElementById('price').value=Number(document.getElementById('weight').value*1.15).toFixed(2); document.getElementById('createbutton').disabled=false; return false;"
						class="button" value="${calcOrder}" /></td>
					<td colspan="2" align="center"><input type="submit" id="createbutton" class="buttondis" disabled value="${createOrder}" /></td>
				</tr>
			</table>
		</form>
	</div>	
</div>