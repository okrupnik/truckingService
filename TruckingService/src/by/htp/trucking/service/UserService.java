package by.htp.trucking.service;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.exception.ServiceException;

public interface UserService {
	boolean create(User user, UserInfo userInfo) throws ServiceException;
	User logination(String login, String password) throws ServiceException;
	void edit(UserInfo userInfo) throws ServiceException;
}
