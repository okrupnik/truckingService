package by.htp.trucking.dao.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.trucking.dao.UserDAO;
import by.htp.trucking.dao.connectionpool.ConnectionPool;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.dao.myconnectionpool.DataSourseProperty;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;

public class SQLUserDAO implements UserDAO {
	private static final Logger log = (Logger) LogManager.getLogger(SQLUserDAO.class.getName());

	@Override
	public User create(User user, UserInfo userInfo) throws DAOException {

		PreparedStatement pstmUser = null;
		PreparedStatement pstmUserInfo = null;
		PreparedStatement pstmCity = null;
		ResultSet rs = null;
		int idUser = 0;
		int idCity = 0;

		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			// conn = DataSourseProperty.getInstance().myConnectionPool.getConnection();
			pstmUser = connection.prepareStatement(
					"INSERT INTO trucking.users (users.login, users.password, users.status, users.roles_id) VALUES (?, ?, ?, ?)");
			pstmUser.setString(1, user.getLogin());
			pstmUser.setString(2,
					EncryptPassword.byteArrayToHexString(EncryptPassword.computeHash(user.getPassword())));
			pstmUser.setString(3, user.getStatus());
			pstmUser.setInt(4, Integer.valueOf(user.getRole()));
			pstmUser.executeUpdate();

			pstmUser = connection.prepareStatement("SELECT users.id FROM trucking.users WHERE users.login=?");
			pstmUser.setString(1, user.getLogin());
			rs = pstmUser.executeQuery();

			while (rs.next()) {
				idUser = rs.getInt("id");
			}

			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, userInfo.getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCity = rs.getInt("id");
			}
			if (idCity == 0) {
				pstmCity = connection.prepareStatement("INSERT INTO trucking.cities (cities.city) VALUES (?)");
				pstmCity.setString(1, userInfo.getCity());
				pstmCity.executeUpdate();
			}
			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, userInfo.getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCity = rs.getInt("id");
			}

			if (idUser != 0 && idCity != 0) {
				pstmUserInfo = connection.prepareStatement(
						"INSERT INTO trucking.detailsusers (detailsusers.name, detailsusers.surname, detailsusers.email, detailsusers.phoneNumber, detailsusers.address, detailsusers.users_id, detailsusers.cities_id) VALUES (?, ?, ?, ?, ?, ?, ?)");
				pstmUserInfo.setString(1, userInfo.getName());
				pstmUserInfo.setString(2, userInfo.getSurname());
				pstmUserInfo.setString(3, userInfo.getEmail());
				pstmUserInfo.setString(4, userInfo.getPhoneNumber());
				pstmUserInfo.setString(5, userInfo.getAddress());
				pstmUserInfo.setInt(6, idUser);
				pstmUserInfo.setInt(7, idCity);
				pstmUserInfo.executeUpdate();
			}
			
			pstmUser = connection.prepareStatement(
					"SELECT users.login, users.password, users.status, roles.role FROM trucking.users, trucking.roles WHERE roles.id=users.roles_id AND users.login=? AND users.password=?");
			pstmUser.setString(1, user.getLogin());
			pstmUser.setString(2,
					EncryptPassword.byteArrayToHexString(EncryptPassword.computeHash(user.getPassword())));
			rs = pstmUser.executeQuery();

			while (rs.next()) {
				String status = rs.getString("status");
				String role = rs.getString("role");
				user = new User(user.getLogin(), status, role);
			}

		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException("Isn't found algorithm for encryption", e);
		} finally {
			if (pstmUser != null && pstmUserInfo != null && pstmCity != null) {
				try {
					pstmUser.close();
					pstmUserInfo.close();
					pstmCity.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}
		return user;
	}

	@Override
	public User checkUser(String login, String password) throws DAOException {

		PreparedStatement pstm = null;
		ResultSet rs = null;

		User user = null;
		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			// connection =
			// DataSourseProperty.getInstance().myConnectionPool.getConnection(); //for
			// MyConnectionPool
			pstm = connection.prepareStatement(
					"SELECT users.login, users.password, users.status, roles.role FROM trucking.users, trucking.roles WHERE roles.id=users.roles_id AND users.login=? AND users.password=?");
			pstm.setString(1, login.toLowerCase());
			pstm.setString(2, EncryptPassword.byteArrayToHexString(EncryptPassword.computeHash(password)));
			rs = pstm.executeQuery();

			while (rs.next()) {
				String status = rs.getString("status");
				String role = rs.getString("role");
				user = new User(login, status, role);
			}

		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException("Isn't found algorithm for encryption", e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}
		return user;
	}

	@Override
	public UserInfo getuserInfo(String login) throws DAOException {
		PreparedStatement pstm = null;
		ResultSet rs = null;

		UserInfo userInfo = null;
		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			// conn = DataSourseProperty.getInstance().myConnectionPool.getConnection();
			pstm = connection.prepareStatement(
					"SELECT detailsusers.name, detailsusers.surname, detailsusers.email, detailsusers.phoneNumber, detailsusers.address, cities.city FROM trucking.detailsusers, trucking.cities, trucking.users WHERE detailsusers.users_id=users.id AND detailsusers.cities_id=cities.id AND users.login=?");
			pstm.setString(1, login.toLowerCase());
			rs = pstm.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				String address = rs.getString("address");
				String city = rs.getString("city");
				userInfo = new UserInfo(name, surname, email, phoneNumber, address, city);
			}

		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}
		return userInfo;
	}

	@Override
	public User checkLoginUser(String login) throws DAOException {

		PreparedStatement pstm = null;
		ResultSet rs = null;

		User user = null;
		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			// connection =
			// DataSourseProperty.getInstance().myConnectionPool.getConnection(); //for
			// MyConnectionPool
			pstm = connection.prepareStatement(
					"SELECT users.login, users.status, roles.role FROM trucking.users, trucking.roles WHERE roles.id=users.roles_id AND users.login=?");
			pstm.setString(1, login.toLowerCase());
			rs = pstm.executeQuery();

			while (rs.next()) {
				String status = rs.getString("status");
				String role = rs.getString("role");
				user = new User(login, status, role);
			}

		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} finally {
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}
		return user;
	}

	@Override
	public boolean edit(User user, UserInfo userInfo) throws DAOException {
		
		PreparedStatement pstmUserUpdate = null;		
		PreparedStatement pstmCity = null;
		ResultSet rs = null;
		int idUser = 0;
		int idCity = 0;

		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			// conn = DataSourseProperty.getInstance().myConnectionPool.getConnection();
			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, userInfo.getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCity = rs.getInt("id");
			}
			if (idCity == 0) {
				pstmCity = connection.prepareStatement("INSERT INTO trucking.cities (cities.city) VALUES (?)");
				pstmCity.setString(1, userInfo.getCity());
				pstmCity.executeUpdate();
			}
			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, userInfo.getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCity = rs.getInt("id");
			}
			
			pstmUserUpdate = connection.prepareStatement(
					"UPDATE trucking.users, trucking.detailsusers SET users.password=?, detailsusers.name=?, detailsusers.surname=?, detailsusers.email=?, detailsusers.phonenumber=?, detailsusers.address=?, detailsusers.cities_id=? WHERE users.id=detailsusers.users_id AND users.login=?;");
			pstmUserUpdate.setString(1, EncryptPassword.byteArrayToHexString(EncryptPassword.computeHash(user.getPassword())));
			pstmUserUpdate.setString(2, userInfo.getName());
			pstmUserUpdate.setString(3, userInfo.getSurname());
			pstmUserUpdate.setString(4, userInfo.getEmail());
			pstmUserUpdate.setString(5, userInfo.getPhoneNumber());
			pstmUserUpdate.setString(6, userInfo.getAddress());
			pstmUserUpdate.setInt(7, idCity);
			pstmUserUpdate.setString(8, user.getLogin());			
			pstmUserUpdate.executeUpdate();

			return true;
			
		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} catch (NoSuchAlgorithmException e) {
			throw new DAOException("Isn't found algorithm for encryption", e);
		} finally {
			if (pstmUserUpdate != null && pstmCity != null) {
				try {
					pstmUserUpdate.close();
					pstmCity.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}
		
	}

}
