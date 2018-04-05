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

public class MainPage implements Command {
	
	private static final String MAIN_PAGE_JSP_PATH = "WEB-INF/jsp/main.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE_JSP_PATH);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {			
			//e.printStackTrace();   !!!!!!!!
		}
		
//		UserDAO userDAO = new DBUserDAOImpl();
//		try {
//			User user = userDAO.logination("admin", "admin1");
//			System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getStatus() + " " + user.getRole() + " ");
//		} catch (DAOException e) {
//			System.out.println("error in query");
//		}
		
	}

}
