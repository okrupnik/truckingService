package by.htp.trucking.service;

import java.util.List;

import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.User;
import by.htp.trucking.service.exception.ServiceException;

public interface OrderService {

	boolean create(Order order, String locale) throws ServiceException;
	List<Order> showOrderHistory(User user, String dateFrom, String dateTo, String locale) throws ServiceException;
}
