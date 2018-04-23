package by.htp.trucking.dao;

import java.util.List;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.OrderInfo;
import by.htp.trucking.entity.User;
import by.htp.trucking.entity.UserInfo;

public interface OrderDAO {
	
	boolean create(Order order) throws DAOException;
	List<Order> showOrderHistory(User user, String dateFrom, String dateTo) throws DAOException;
}
