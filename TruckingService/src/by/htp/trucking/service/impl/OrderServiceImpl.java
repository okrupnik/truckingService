package by.htp.trucking.service.impl;

import java.util.List;

import by.htp.trucking.dao.DAOFactory;
import by.htp.trucking.dao.OrderDAO;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.User;
import by.htp.trucking.service.OrderService;
import by.htp.trucking.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {

	@Override
	public boolean create(Order order, String locale) throws ServiceException {

			if (!order.getOrderInfo().getAddressFrom().getCity().getCity().isEmpty()) {
				if (!order.getOrderInfo().getAddressFrom().getStreet().isEmpty()) {
					if (!order.getOrderInfo().getAddressFrom().getHouse().isEmpty()) {
						if (!order.getOrderInfo().getAddressTo().getCity().getCity().isEmpty()) {
							if (!order.getOrderInfo().getAddressFrom().getStreet().isEmpty()) {
								if (!order.getOrderInfo().getAddressFrom().getHouse().isEmpty()) {
									DAOFactory daoFactory = DAOFactory.getInstance();
									OrderDAO orderDAO = daoFactory.getOrderDAO();
									try {
										return orderDAO.create(order);
									} catch (DAOException e) {
										if (locale.equals("ru")) {
											throw new ServiceException("Ошибка создания заказа");
										} else {
											throw new ServiceException("Error of creating order");
										}
									}
								} else {
									if (locale.equals("ru")) {
										throw new ServiceException("Вы не ввели номер дома куда везти груз");
									} else {
										throw new ServiceException(
												"You didn't enter the house where to carry the goods");
									}
								}
							} else {
								if (locale.equals("ru")) {
									throw new ServiceException("Вы не ввели улицу куда везти груз");
								} else {
									throw new ServiceException("You didn't enter the street where to carry the goods");
								}
							}
						} else {
							if (locale.equals("ru")) {
								throw new ServiceException("Вы не ввели город куда везти груз");
							} else {
								throw new ServiceException("You didn't enter the city where to carry the goods");
							}
						}
					} else {
						if (locale.equals("ru")) {
							throw new ServiceException("Вы не ввели номер дома откуда везти груз");
						} else {
							throw new ServiceException("You didn't enter the house from where to deliver the goods");
						}
					}
				} else {
					if (locale.equals("ru")) {
						throw new ServiceException("Вы не ввели улицу откуда везти груз");
					} else {
						throw new ServiceException("You didn't enter the street from where to deliver the goods");
					}
				}
			} else {
				if (locale.equals("ru")) {
					throw new ServiceException("Вы не ввели город откуда везти груз");
				} else {
					throw new ServiceException("You didn't enter the city from where to deliver the goods");
				}
			}		

	}

	@Override
	public List<Order> showOrderHistory(User user, String dateFrom, String dateTo, String locale) throws ServiceException {
		List<Order> orderList = null;
		
		if (dateFrom != null && !dateFrom.isEmpty()) {
			if (dateTo != null && !dateTo.isEmpty()) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				OrderDAO orderDAO = daoFactory.getOrderDAO();
				try {
					orderList = orderDAO.showOrderHistory(user, dateFrom, dateTo);
				} catch (DAOException e) {
					if (locale.equals("ru")) {
						throw new ServiceException("Ошибка создания списка заказов");
					} else {
						throw new ServiceException("The error of creating orders list");
					}
				}
			} else {
				if (locale.equals("ru")) {
					throw new ServiceException("Вы не выбрали дату которой закончить");
				} else {
					throw new ServiceException("You didn't choose the date to finisht");
				}
			}
			
		} else {
			if (locale.equals("ru")) {
				throw new ServiceException("Вы не выбрали дату с которой начать");
			} else {
				throw new ServiceException("You didn't choose the date from which to start");
			}
		}
		
		return orderList;
	}

}
