package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.ServiceFactory;
import by.htp.trucking.service.UserService;
import by.htp.trucking.service.exception.ServiceException;

public class EditUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter(ParamAndAttribute.NEW_PASSWORD_PARAM_NAME);
		String email = request.getParameter(ParamAndAttribute.EMAIL_PARAM_NAME);
		String firstName = request.getParameter(ParamAndAttribute.FIRST_NAME_PARAM_NAME);
		String surName = request.getParameter(ParamAndAttribute.SURNAME_PARAM_NAME);
		String phoneNumber = request.getParameter(ParamAndAttribute.PNUMBER_PARAM_NAME);
		String city = request.getParameter(ParamAndAttribute.CITY_PARAM_NAME);
		String address = request.getParameter(ParamAndAttribute.ADDRESS_PARAM_NAME);

		ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
		UserService userService = serviceFactory.getUserService();
		User user = (User)request.getSession().getAttribute("user");
		User userNew = null;
		UserInfo userInfo = null;
		String goToPage = null;
		boolean editUser = false;
		HttpSession session = request.getSession();
		String locale = (String) request.getSession().getAttribute("local");

		try {
			userNew = new User(user.getLogin(), password, user.getStatus(), user.getRole());
			userInfo = new UserInfo(firstName, surName, email, phoneNumber, address, city);
			editUser = userService.edit(userNew, userInfo, locale);
			if (editUser) {
				session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER, userNew);
				session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER_INFO, userInfo);
				session.setAttribute("successEn", "The data is changed successfully!");
				session.setAttribute("successRu", "Данные успешно изменены!");
				response.sendRedirect("Controller?command=main_page");
			} else {
				session.setAttribute("errorMessageEn", "The error of changing data");
				session.setAttribute("errorMessageRu", "Ошибка изменения данных");
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

}
