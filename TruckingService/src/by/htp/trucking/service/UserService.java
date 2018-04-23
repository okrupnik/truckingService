package by.htp.trucking.service;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;
import by.htp.trucking.service.exception.ServiceException;

public interface UserService {
	User create(User user, UserInfo userInfo, String locale) throws ServiceException;
	User logination(String login, String password, String locale) throws ServiceException;
	User checkLoginUser(String login) throws ServiceException;
	UserInfo getuserInfo(String login) throws ServiceException;
	boolean edit(User user, UserInfo userInfo, String locale) throws ServiceException;
}
