package by.htp.trucking.dao;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.dao.impl.UserDAOImpl;
import by.htp.trucking.entity.User;

public class DAOTest {

	public static void main(String[] args) {
		UserDAO userDAO = new UserDAOImpl();
		try {
			User user = userDAO.logination("admin", "admin");
			System.out.println(user.getLogin() + " " + user.getPassword() + " " + user.getStatus() + " " + user.getRole() + " ");
		} catch (DAOException e) {
			e.printStackTrace();
		}
		

	}

}
