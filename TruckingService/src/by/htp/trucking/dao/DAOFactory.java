package by.htp.trucking.dao;

import by.htp.trucking.dao.impl.SQLOrderDAO;
import by.htp.trucking.dao.impl.SQLUserDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private static final UserDAO userDAO = new SQLUserDAO();
	private static final OrderDAO orderDAO = new SQLOrderDAO();

	private DAOFactory() {	
	}
	
	public static DAOFactory getInstance() {		
		return instance;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
}
