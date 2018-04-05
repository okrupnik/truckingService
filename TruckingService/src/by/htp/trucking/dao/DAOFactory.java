package by.htp.trucking.dao;

import by.htp.trucking.dao.impl.UserDAOImpl;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private static final UserDAO userDAO = new UserDAOImpl();

	private DAOFactory() {	
	}
	
	public static DAOFactory getInstance() {		
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
}
