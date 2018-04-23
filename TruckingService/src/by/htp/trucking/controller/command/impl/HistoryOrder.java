package by.htp.trucking.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.User;
import by.htp.trucking.service.OrderService;
import by.htp.trucking.service.ServiceFactory;
import by.htp.trucking.service.exception.ServiceException;

public class HistoryOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int page = 1;
		int recordsPerPage = 3;
		int numberOfRecords = 0;
		int numberOfPages = 0;

		List<Order> currentPageOrders = null;

		String historyOrderDateFrom = request.getParameter(ParamAndAttribute.DATE_HISTORY_ORDER_FROM_PARAM_NAME);
		String historyOrderDateTo = request.getParameter(ParamAndAttribute.DATE_HISTORY_ORDER_TO_PARAM_NAME);
		String locale = (String) request.getSession().getAttribute("local");
		HttpSession session = request.getSession();

		User user = (User) request.getSession().getAttribute(ParamAndAttribute.ATTRIBUTE_USER);

		List<Order> orderList = null;

		ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
		OrderService orderService = serviceFactory.getOrderService();

		try {
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			orderList = orderService.showOrderHistory(user, historyOrderDateFrom, historyOrderDateTo, locale);
			numberOfRecords = orderList.size();
			numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
			currentPageOrders = getCurrentPageOrders(orderList, page, recordsPerPage);
			if (currentPageOrders != null) {
				request.setAttribute(ParamAndAttribute.ATTRIBUTE_ORDER_NUMBER_OF_PAGES, numberOfPages);
				request.setAttribute(ParamAndAttribute.ATTRIBUTE_CURRENT_PAGE, page);
				request.setAttribute(ParamAndAttribute.ATTRIBUTE_CURRENT_PAGE_ORDERS, currentPageOrders);
				request.setAttribute(ParamAndAttribute.ATTRIBUTE_ORDER_LIST, orderList);
				session.setAttribute(ParamAndAttribute.DATE_HISTORY_ORDER_FROM_PARAM_NAME, historyOrderDateFrom);
				session.setAttribute(ParamAndAttribute.DATE_HISTORY_ORDER_TO_PARAM_NAME, historyOrderDateTo);
				RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.HISTORY_ORDER);
				dispatcher.forward(request, response);
			} else {
				session.setAttribute("errorMessageEn", "The error requesting order history");
				session.setAttribute("errorMessageRu", "Ошибка запроса истории заказов");
				response.sendRedirect(request.getHeader("Referer"));
			}
		} catch (ServiceException e) {
			String errorMessage = e.getMessage();
			if (locale.equals("ru")) {
				session.setAttribute("errorMessageRu", errorMessage);
				response.sendRedirect(request.getHeader("Referer"));
			} else {
				session.setAttribute("errorMessageEn", errorMessage);
				response.sendRedirect(request.getHeader("Referer"));
			}
		}

	}

	public List<Order> getCurrentPageOrders(List<Order> totalList, int currentPageNo, int recordsPerPage) {
		if (totalList == null) {
			return null;
		}
		int startIndex = (currentPageNo - 1) * recordsPerPage;
		int endIndex = (startIndex + recordsPerPage > totalList.size() ? totalList.size() : startIndex + recordsPerPage);
		return totalList.subList(startIndex, endIndex);
	}

}
