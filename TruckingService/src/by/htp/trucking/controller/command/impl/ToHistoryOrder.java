package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.trucking.controller.command.Command;

public class ToHistoryOrder implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.HISTORY_ORDER);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {			
			//e.printStackTrace();   !!!!!!!!
		}
		
	}

}
