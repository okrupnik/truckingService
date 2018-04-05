package by.htp.trucking.dao;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;

public interface UserDAO {
	
	boolean create(User user, UserInfo userInfo) throws DAOException;
	User logination(String login, String password) throws DAOException;
	void edit(UserInfo userInfo) throws DAOException;
}
