package by.htp.trucking.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean create(User user, UserInfo userInfo) throws DAOException {

		return false;
	}

	@Override
	public User logination(String login, String password) throws DAOException {
		Connection conn = null;

		try {
			conn = DataSourseProperty.getInstance().myConnectionPool.getConnection();
			PreparedStatement pstm = conn
					.prepareStatement("SELECT * FROM trucking.users WHERE login = ? AND password = ?");
			pstm.setString(1, login.toLowerCase());
			pstm.setString(2, EncryptPassword.byteArrayToHexString(EncryptPassword.computeHash(password)));
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String status = rs.getString("status");
				int role = rs.getInt("roles_id");
				User user = new User(login, password, status, role);
				return user;
			}

		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException("Isn't found algorithm for encryption", e);
		} 

		return null;
	}

	@Override
	public void edit(UserInfo userInfo) throws DAOException {

	}

}
