package by.htp.trucking.dao;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;

public interface UserDAO {
	
	User create(User user, UserInfo userInfo) throws DAOException;
	User checkUser(String login, String password) throws DAOException;
	User checkLoginUser(String login) throws DAOException;
	UserInfo getuserInfo(String login) throws DAOException;
	boolean edit(User user, UserInfo userInfo) throws DAOException;
}
