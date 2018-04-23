package by.htp.trucking.controller.command.impl;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.trucking.controller.command.Command;
import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.dao.impl.SQLUserDAO;
import by.htp.trucking.entity.User;

public class MainPage implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String local = null;
		local = (String)request.getSession().getAttribute("local");
		if (local == null) {
		local = Locale.getDefault().getLanguage();
		}
		request.getSession(true).setAttribute("local", local);
				
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.MAIN_PAGE);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {			
			//e.printStackTrace();   !!!!!!!!
		}
		
	}

}
