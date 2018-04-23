package by.htp.trucking.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.trucking.controller.command.Command;

public class Localization implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String local = request.getParameter("local");
		request.getSession(true).setAttribute("local", local);

//		RequestDispatcher dispatcher = request.getRequestDispatcher(JSPPagePath.MAIN_PAGE);
//		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getRequestURI());
//		dispatcher.forward(request, response);
//		 response.sendRedirect(request.getHeader("Header"));
//		String s = request.getRequestURI();
		String referer = request.getHeader("Referer");
//		String command = request.getParameter("command");
//		request.getSession(true).setAttribute("referer", referer);
		 response.sendRedirect(referer);
	}

}
