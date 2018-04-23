package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

public class CreateUser implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ParamAndAttribute.NEW_LOGIN_PARAM_NAME);
		String password = request.getParameter(ParamAndAttribute.NEW_PASSWORD_PARAM_NAME);
		String email = request.getParameter(ParamAndAttribute.EMAIL_PARAM_NAME);
		String firstName = request.getParameter(ParamAndAttribute.FIRST_NAME_PARAM_NAME);
		String surName = request.getParameter(ParamAndAttribute.SURNAME_PARAM_NAME);
		String phoneNumber = request.getParameter(ParamAndAttribute.PNUMBER_PARAM_NAME);
		String city = request.getParameter(ParamAndAttribute.CITY_PARAM_NAME);
		String address = request.getParameter(ParamAndAttribute.ADDRESS_PARAM_NAME);

		ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
		UserService userService = serviceFactory.getUserService();
		User user = null;
		UserInfo userInfo = null;
		String goToPage = null;
		HttpSession session = request.getSession();
		String locale = (String)request.getSession().getAttribute("local");

		try {
			user = userService.checkLoginUser(login);
			if (user != null) {
				session.setAttribute("errorMessageEn", "This user already exists");
				session.setAttribute("errorMessageRu", "Пользователь с таким логином уже существует");
			} else {				
				user = new User(login, password, "true", "3");
				userInfo = new UserInfo(firstName, surName, email, phoneNumber, address, city);
				user = userService.create(user, userInfo, locale);
				if (user != null) {
					session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER, user);
					session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER_INFO, userInfo);
					session.setAttribute("successEn", "User successfully is created!");
					session.setAttribute("successRu", "Пользователь успешно создан!");
					response.sendRedirect("Controller?command=main_page");
				} else {
					session.setAttribute("errorMessageEn", "Error of creating user");
					session.setAttribute("errorMessageRu", "Ошибка создания пользователя");
					response.sendRedirect(request.getHeader("Referer"));
				}
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
