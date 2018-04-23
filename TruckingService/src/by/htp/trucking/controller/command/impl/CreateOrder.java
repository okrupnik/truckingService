package by.htp.trucking.controller.command.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.entity.AddressFrom;
import by.htp.trucking.entity.AddressTo;
import by.htp.trucking.entity.City;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.OrderInfo;
import by.htp.trucking.entity.User;
import by.htp.trucking.service.OrderService;
import by.htp.trucking.service.ServiceFactory;
import by.htp.trucking.service.exception.ServiceException;

public class CreateOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter(ParamAndAttribute.TITLE_ORDER_PARAM_NAME);
		String dateDeliverySt = request.getParameter(ParamAndAttribute.DATE_DELIVERY_ORDER_PARAM_NAME);
		String weight = request.getParameter(ParamAndAttribute.WEIGHT_ORDER_PARAM_NAME);
		String price = request.getParameter(ParamAndAttribute.PRICE_ORDER_PARAM_NAME);
		String comment = request.getParameter(ParamAndAttribute.COMMENT_ORDER_PARAM_NAME);
		String cityFrom = request.getParameter(ParamAndAttribute.CITY_FROM_ORDER_PARAM_NAME);
		String streetFrom = request.getParameter(ParamAndAttribute.STREET_FROM_ORDER_PARAM_NAME);
		String houseFrom = request.getParameter(ParamAndAttribute.HOUSE_FROM_ORDER_PARAM_NAME);
		String flatFrom = request.getParameter(ParamAndAttribute.FLAT_FROM_ORDER_PARAM_NAME);
		String cityTo = request.getParameter(ParamAndAttribute.CITY_TO_ORDER_PARAM_NAME);
		String streetTo = request.getParameter(ParamAndAttribute.STREET_TO_ORDER_PARAM_NAME);
		String houseTo = request.getParameter(ParamAndAttribute.HOUSE_TO_ORDER_PARAM_NAME);
		String flatTo = request.getParameter(ParamAndAttribute.FLAT_TO_ORDER_PARAM_NAME);

		ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
		OrderService orderService = serviceFactory.getOrderService();
		User user = null;
		Order order = null;
		boolean orderCreating = false;
		OrderInfo orderInfo = null;
		AddressFrom addressFrom = null;
		AddressTo addressTo = null;
		City cityFromOb = null;
		City cityToOb = null;
		String goToPage = null;
		HttpSession session = request.getSession();
		String locale = (String) request.getSession().getAttribute("local");

		cityFromOb = new City(cityFrom);
		addressFrom = new AddressFrom(streetFrom, houseFrom, flatFrom, cityFromOb);

		cityToOb = new City(cityTo);
		addressTo = new AddressTo(streetTo, houseTo, flatTo, cityToOb);

		if (dateDeliverySt != null && !dateDeliverySt.isEmpty()) {
			if (weight != null && !weight.isEmpty()) {
				if (price != null && !price.isEmpty()) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate dateDelivery = LocalDate.parse(dateDeliverySt, formatter);
					orderInfo = new OrderInfo(title, dateDelivery, Double.valueOf(price), Integer.valueOf(weight),
							comment, "true", addressFrom, addressTo);

					LocalDate currentDate = LocalDate.now();
					user = (User) request.getSession().getAttribute(ParamAndAttribute.ATTRIBUTE_USER);
					order = new Order(currentDate, user, orderInfo);

					try {
						orderCreating = orderService.create(order, locale);
						if (orderCreating) {
							session.setAttribute(ParamAndAttribute.ATTRIBUTE_ORDER, order);
							session.setAttribute("successEn", "Order successfully is created!");
							session.setAttribute("successRu", "Заказ успешно создан!");
							response.sendRedirect("Controller?command=main_page");							
						} else {
							session.setAttribute("errorMessageEn", "Error of creating order");
							session.setAttribute("errorMessageRu", "Ошибка создания заказа");
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
				} else {
					session.setAttribute("errorMessageEn", "You didn't click the button Calculate");
					session.setAttribute("errorMessageRu", "Вы не нажали кнопку Рассчитать");
					response.sendRedirect(request.getHeader("Referer"));
				}
			} else {
				session.setAttribute("errorMessageEn", "You didn't enter the estimated cargo weight");
				session.setAttribute("errorMessageRu", "Вы не ввели ориентировочный вес груза");
				response.sendRedirect(request.getHeader("Referer"));
			}
		} else {
			session.setAttribute("errorMessageEn", "You didn't select a delivery date");
			session.setAttribute("errorMessageRu", "Вы не выбрали дату доставки");
			response.sendRedirect(request.getHeader("Referer"));
		}

		
	}

}
