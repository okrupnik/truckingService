package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.dao.impl.SQLUserDAO;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.ServiceFactory;
import by.htp.trucking.service.UserService;
import by.htp.trucking.service.exception.ServiceException;

public class SignIn implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ParamAndAttribute.LOGIN_PARAM_NAME);
		String password = request.getParameter(ParamAndAttribute.PASSWORD_PARAM_NAME);

		ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
		UserService userService = serviceFactory.getUserService();
		User user = null;
		UserInfo userInfo = null;
		String goToPage = null;
		HttpSession session = request.getSession();
		String locale = (String) request.getSession().getAttribute("local");

		try {
			user = userService.logination(login, password, locale);
			if (user != null) {
				session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER, user);
				goToPage = JSPPagePath.USER_MAIN;
				userInfo = userService.getuserInfo(login);
				if (userInfo != null) {
					session.setAttribute(ParamAndAttribute.ATTRIBUTE_USER_INFO, userInfo);					
				}
				response.sendRedirect("Controller?command=main_page");
			} else {
				session.setAttribute("errorMessageEn", "Incorrect login or password");
				session.setAttribute("errorMessageRu", "Неправильный логин или пароль");
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
