package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.dao.impl.UserDAOImpl;
import by.htp.trucking.entity.User;
import by.htp.trucking.service.ServiceFactory;
import by.htp.trucking.service.UserService;
import by.htp.trucking.service.exception.ServiceException;

public class LoginPage implements Command {

	private static final String LOGIN_PAGE_JSP_PATH = "WEB-INF/jsp/loginUser.jsp";
	private static final String ATTRIBUTE_MODEL_TO_VIEW = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter("userlogin");
		String password = request.getParameter("userpassword");

		
		// UserDAO userDAO = new DBUserDAOImpl();
		// try {
		// User user = userDAO.logination(login, password);
		// System.out.println(user.getLogin() + " " + user.getPassword() + " " +
		// user.getStatus() + " " + user.getRole() + " ");
		// } catch (DAOException e) {
		// e.printStackTrace();
		// }

		if (!login.isEmpty() && !password.isEmpty())
			try {
//				UserDAO userDAO = new UserDAOImpl();
//				User user = userDAO.logination(login, password);
				ServiceFactory serviceFactory = ServiceFactory.getInstatnce();
				UserService userService = serviceFactory.getUserService();
				User user = userService.logination(login, password);
				request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, user);
				RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_PAGE_JSP_PATH);
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// e.printStackTrace(); !!!!!!!!
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
