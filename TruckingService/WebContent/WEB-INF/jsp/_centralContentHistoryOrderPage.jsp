<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.local" var="loc" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.dateCreate.text"
	var="hpDateCreate" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.title.text" var="hpTitle" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.dateDelivery.text"
	var="hpDateDelivery" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.price.text" var="hpPrice" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.weight.text" var="hpWeight" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.comment.text" var="hpComment" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.from.text" var="hpFrom" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.to.text" var="hpTo" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.city.text" var="hpCity" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.address.text" var="hpAddress" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.previous.text" var="hpPrevious" />
<fmt:message bundle="${loc}"
	key="locale.centralcontent.history.page.next.text" var="hpNext" />

<table width="auto" style="color: #174F63" border="1">
	<tr>
		<td rowspan="2" align="center"><b>${hpDateCreate}</b></td>
		<td rowspan="2" align="center"><b>${hpTitle}</b></td>
		<td rowspan="2" align="center"><b>${hpDateDelivery}</b></td>
		<td rowspan="2" align="center"><b>${hpPrice}</b></td>
		<td rowspan="2" align="center"><b>${hpWeight}</b></td>
		<td rowspan="2" align="center"><b>${hpComment}</b></td>
		<td colspan="2" align="center"><b>${hpFrom}</b></td>
		<td colspan="2" align="center"><b>${hpTo}</b></td>
	</tr>
	<tr>
		<td align="center">${hpCity}</td>
		<td align="center">${hpAddress}</td>
		<td align="center">${hpCity}</td>
		<td align="center">${hpAddress}</td>
	</tr>
	<c:forEach items="${currentPageOrders}" var="order">
		<tr>
			<td align="center"><c:out value="${order.dateOfCreating}"></c:out></td>
			<td align="center"><c:out value="${order.orderInfo.title}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.dateOfDelivery}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.priceShipment}"></c:out></td>
			<td align="center"><c:out value="${order.orderInfo.weight}"></c:out></td>
			<td align="center"><c:out value="${order.orderInfo.comment}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.addressFrom.city.city}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.addressFrom.street}, ${order.orderInfo.addressFrom.house}-${order.orderInfo.addressFrom.flat}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.addressTo.city.city}"></c:out></td>
			<td align="center"><c:out
					value="${order.orderInfo.addressTo.street}, ${order.orderInfo.addressTo.house}-${order.orderInfo.addressTo.flat}"></c:out></td>
		</tr>
	</c:forEach>
</table>

<c:if test="${not empty sessionScope.dateFrom and not empty sessionScope.dateTo}">
<table cellpadding="5" cellspacing="5">
	<tr>
		<c:if test="${currentPage != 1}">
			<td><a
				href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${currentPage - 1}">${hpPrevious}</a></td>
		</c:if>
		<!-- Comment above code and Uncomment the below code for displaying page numbers
         in range like (1 2 .. 10) in case if we have more number of pages -->
		<c:choose>
			<c:when test="${numberOfPages < 5}">
				<c:forEach begin="1" end="${numberOfPages}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a
								href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${(currentPage <= 3)}">
						<c:forEach begin="1" end="3" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a
										href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${i}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<td><a>...</a></td>
						<td><a
							href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${numberOfPages}">${numberOfPages}</a></td>
					</c:when>

					<c:when
						test="${(currentPage > 3) and (numberOfPages - currentPage >= 3)}">
						<td><a
							href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=1">1</a></td>
						<td><a>...</a></td>
						<c:forEach begin="${currentPage-2}" end="${currentPage+2}" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a
										href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${i}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<td><a
							href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${numberOfPages}">${numberOfPages}</a></td>
					</c:when>

					<c:otherwise>
						<td><a
							href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=1">1</a></td>
						<td><a>...</a></td>
						<c:forEach begin="${currentPage}" end="${numberOfPages}" var="i">
							<c:choose>
								<c:when test="${currentPage eq i}">
									<td>${i}</td>
								</c:when>
								<c:otherwise>
									<td><a
										href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${i}">${i}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
		<c:if test="${currentPage lt numberOfPages}">
			<td><a
				href="${pageContext.request.contextPath}/Controller?command=history_order&dateFrom=${sessionScope.dateFrom}&dateTo=${sessionScope.dateTo}&page=${currentPage + 1}">${hpNext}</a></td>
		</c:if>
	</tr>
</table>
</c:if>

