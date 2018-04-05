package by.htp.trucking.service.impl;

import by.htp.trucking.dao.DAOFactory;
import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.UserService;
import by.htp.trucking.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	@Override
	public boolean create(User user, UserInfo userInfo) throws ServiceException {
		
		return false;
	}

	@Override
	public User logination(String login, String password) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.logination(login, password);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return user;
	}

	@Override
	public void edit(UserInfo userInfo) throws ServiceException {
		
		
	}
	
	
	
}
