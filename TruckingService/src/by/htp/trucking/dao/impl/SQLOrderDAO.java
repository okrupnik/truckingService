package by.htp.trucking.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.trucking.dao.OrderDAO;
import by.htp.trucking.dao.connectionpool.ConnectionPool;
import by.htp.trucking.dao.exception.DAOException;
import by.htp.trucking.entity.AddressFrom;
import by.htp.trucking.entity.AddressTo;
import by.htp.trucking.entity.City;
import by.htp.trucking.entity.Order;
import by.htp.trucking.entity.OrderInfo;
import by.htp.trucking.entity.User;

public class SQLOrderDAO implements OrderDAO{
	private static final Logger log = (Logger) LogManager.getLogger(SQLOrderDAO.class.getName());

	@Override
	public boolean create(Order order) throws DAOException {
		
		PreparedStatement pstmCity = null;	
		PreparedStatement pstmAddress = null;
		PreparedStatement pstmOrderDetails = null;
		PreparedStatement pstmUser = null;
		PreparedStatement pstmOrder = null;
		ResultSet rs = null;
		
		int idCityFrom = 0;
		int idCityTo = 0;
		int idAddressFrom = 0;
		int idAddressTo = 0;
		int idUser = 0;		
		int idOrderDetails = 0;	
		
		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			
			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, order.getOrderInfo().getAddressFrom().getCity().getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCityFrom = rs.getInt("id");
			}
			if (idCityFrom == 0) {
				pstmCity = connection.prepareStatement("INSERT INTO trucking.cities (cities.city) VALUES (?)");
				pstmCity.setString(1, order.getOrderInfo().getAddressFrom().getCity().getCity());
				pstmCity.executeUpdate();
				
				pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
				pstmCity.setString(1, order.getOrderInfo().getAddressFrom().getCity().getCity());
				rs = pstmCity.executeQuery();
				while (rs.next()) {
					idCityFrom = rs.getInt("id");
				}
			}
			
			pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
			pstmCity.setString(1, order.getOrderInfo().getAddressTo().getCity().getCity());
			rs = pstmCity.executeQuery();
			while (rs.next()) {
				idCityTo = rs.getInt("id");
			}
			if (idCityTo == 0) {
				pstmCity = connection.prepareStatement("INSERT INTO trucking.cities (cities.city) VALUES (?)");
				pstmCity.setString(1, order.getOrderInfo().getAddressTo().getCity().getCity());
				pstmCity.executeUpdate();
				
				pstmCity = connection.prepareStatement("SELECT cities.id FROM trucking.cities WHERE cities.city=?");
				pstmCity.setString(1, order.getOrderInfo().getAddressTo().getCity().getCity());
				rs = pstmCity.executeQuery();
				while (rs.next()) {
					idCityTo = rs.getInt("id");
				}
			}
			
			if (idCityFrom != 0) {
				pstmAddress = connection.prepareStatement(
						"INSERT INTO trucking.addressfrom (addressfrom.streets, addressfrom.houses, addressfrom.flats, addressfrom.cities_id) VALUES (?, ?, ?, ?)");
				pstmAddress.setString(1, order.getOrderInfo().getAddressFrom().getStreet());
				pstmAddress.setString(2, order.getOrderInfo().getAddressFrom().getHouse());
				pstmAddress.setString(3, order.getOrderInfo().getAddressFrom().getFlat());
				pstmAddress.setInt(4, idCityFrom);
				pstmAddress.executeUpdate();
			}
			
			if (idCityTo != 0) {
				pstmAddress = connection.prepareStatement(
						"INSERT INTO trucking.addressto (addressto.streets, addressto.houses, addressto.flats, addressto.cities_id) VALUES (?, ?, ?, ?)");
				pstmAddress.setString(1, order.getOrderInfo().getAddressTo().getStreet());
				pstmAddress.setString(2, order.getOrderInfo().getAddressTo().getHouse());
				pstmAddress.setString(3, order.getOrderInfo().getAddressTo().getFlat());
				pstmAddress.setInt(4, idCityTo);
				pstmAddress.executeUpdate();
			}

			pstmAddress = connection.prepareStatement("SELECT addressfrom.id FROM trucking.addressfrom WHERE addressfrom.streets=? AND addressfrom.houses=? AND addressfrom.flats=?");
			pstmAddress.setString(1, order.getOrderInfo().getAddressFrom().getStreet());
			pstmAddress.setString(2, order.getOrderInfo().getAddressFrom().getHouse());
			pstmAddress.setString(3, order.getOrderInfo().getAddressFrom().getFlat());
			rs = pstmAddress.executeQuery();
			while (rs.next()) {
				idAddressFrom = rs.getInt("id");
			}
			
			pstmAddress = connection.prepareStatement("SELECT addressto.id FROM trucking.addressto WHERE addressto.streets=? AND addressto.houses=? AND addressto.flats=?");
			pstmAddress.setString(1, order.getOrderInfo().getAddressTo().getStreet());
			pstmAddress.setString(2, order.getOrderInfo().getAddressTo().getHouse());
			pstmAddress.setString(3, order.getOrderInfo().getAddressTo().getFlat());
			rs = pstmAddress.executeQuery();
			while (rs.next()) {
				idAddressTo = rs.getInt("id");
			}
			
			if (idAddressFrom != 0 && idAddressTo !=0) {
				pstmOrderDetails = connection.prepareStatement(
						"INSERT INTO trucking.ordersdetails (ordersdetails.title, ordersdetails.datedelivery, ordersdetails.priceshipment, ordersdetails.weight, ordersdetails.comment, ordersdetails.statusdelete, ordersdetails.addressfrom_id, ordersdetails.addressto_id ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				pstmOrderDetails.setString(1, order.getOrderInfo().getTitle());
				pstmOrderDetails.setDate(2, Date.valueOf(order.getOrderInfo().getDateOfDelivery()));
				pstmOrderDetails.setDouble(3, order.getOrderInfo().getPriceShipment());
				pstmOrderDetails.setInt(4, order.getOrderInfo().getWeight());
				pstmOrderDetails.setString(5, order.getOrderInfo().getComment());
				pstmOrderDetails.setString(6, order.getOrderInfo().getStatusDelete());
				pstmOrderDetails.setInt(7, idAddressFrom);
				pstmOrderDetails.setInt(8, idAddressTo);
				pstmOrderDetails.executeUpdate();
			}
			
			pstmUser = connection.prepareStatement("SELECT users.id FROM trucking.users WHERE users.login=?");
			pstmUser.setString(1, order.getUser().getLogin());
			rs = pstmUser.executeQuery();
			while (rs.next()) {
				idUser = rs.getInt("id");
			}
			
			pstmOrderDetails = connection.prepareStatement("SELECT ordersdetails.id FROM trucking.ordersdetails WHERE ordersdetails.priceshipment=? AND ordersdetails.weight=? AND ordersdetails.addressfrom_id=? AND ordersdetails.addressto_id=?");
			pstmOrderDetails.setDouble(1, order.getOrderInfo().getPriceShipment());
			pstmOrderDetails.setInt(2, order.getOrderInfo().getWeight());
			pstmOrderDetails.setInt(3, idAddressFrom);
			pstmOrderDetails.setInt(4, idAddressTo);
			rs = pstmOrderDetails.executeQuery();
			while (rs.next()) {
				idOrderDetails = rs.getInt("id");
			}
			
			if (idUser != 0 && idOrderDetails !=0) {
				pstmOrder = connection.prepareStatement(
						"INSERT INTO trucking.orders (orders.datecreate, orders.users_id, orders.ordersdetails_id) VALUES (?, ?, ?)");
				pstmOrder.setDate(1, Date.valueOf(order.getDateOfCreating()));
				pstmOrder.setInt(2, idUser);
				pstmOrder.setInt(3, idOrderDetails);				
				pstmOrder.executeUpdate();
			}
			
			return true;
			
		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} finally {
			if (pstmUser != null && pstmAddress != null && pstmCity != null && pstmOrderDetails != null && pstmOrder != null) {
				try {
					pstmUser.close();
					pstmAddress.close();
					pstmCity.close();
					pstmOrderDetails.close();
					pstmOrder.close();
					
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}		
		
	}

	@Override
	public List<Order> showOrderHistory(User user, String dateFrom, String dateTo) throws DAOException {
		
		List<Order> orderList = new ArrayList<>();
		Order order = null;
		OrderInfo orderInfo = null;
		AddressFrom addressFrom = null;
		AddressTo addressTo = null;
		City cityFromOb = null;
		City cityToOb = null;
		
		PreparedStatement pstmOrderFrom = null;
		PreparedStatement pstmOrderTo = null;
		ResultSet rsFrom = null;
		ResultSet rsTo = null;
		
		LocalDate dateCreate = null;
		String title = null;
		LocalDate dateDelivery = null;
		double price = 0;
		int weight = 0;		
		String comment = null;
		
		String cityFrom = null;
		String streetFrom = null;
		String houseFrom = null;
		String flatFrom = null;
		
		String cityTo = null;
		String streetTo = null;
		String houseTo = null;
		String flatTo = null;
		
		ConnectionPool conPool = ConnectionPool.getInstance();

		try (Connection connection = conPool.takeConnection()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate historyOrderDateFrom = LocalDate.parse(dateFrom, formatter);
			LocalDate historyOrderDateTo = LocalDate.parse(dateTo, formatter);
			
			pstmOrderFrom = connection.prepareStatement("SELECT orders.dateCreate, ordersdetails.title, ordersdetails.dateDelivery, ordersdetails.priceShipment, ordersdetails.weight, ordersdetails.comment, cities.city, addressfrom.streets, addressfrom.houses, addressfrom.flats \r\n" + 
					"FROM trucking.users \r\n" + 
					"INNER JOIN trucking.orders ON orders.users_id=users.id\r\n" + 
					"INNER JOIN trucking.ordersdetails ON ordersdetails.id=orders.ordersDetails_id\r\n" + 
					"INNER JOIN trucking.addressfrom ON addressfrom.id=ordersdetails.addressFrom_id\r\n" + 
					"INNER JOIN trucking.cities ON cities.id=addressfrom.cities_id \r\n" + 
					"WHERE users.login=? AND orders.dateCreate>=? AND orders.dateCreate<=?");
			pstmOrderFrom.setString(1, user.getLogin());
			pstmOrderFrom.setDate(2, Date.valueOf(historyOrderDateFrom));
			pstmOrderFrom.setDate(3, Date.valueOf(historyOrderDateTo));
			rsFrom = pstmOrderFrom.executeQuery();
			
			pstmOrderTo = connection.prepareStatement("SELECT cities.city, addressto.streets, addressto.houses, addressto.flats\r\n" + 
					"FROM trucking.users \r\n" + 
					"INNER JOIN trucking.orders ON orders.users_id=users.id\r\n" + 
					"INNER JOIN trucking.ordersdetails ON ordersdetails.id=orders.ordersDetails_id\r\n" + 
					"INNER JOIN trucking.addressto ON addressto.id=ordersdetails.addressTo_id\r\n" + 
					"INNER JOIN trucking.cities ON cities.id=addressto.cities_id \r\n" + 
					"WHERE users.login=? AND orders.dateCreate>=? AND orders.dateCreate<=?");
			pstmOrderTo.setString(1, user.getLogin());
			pstmOrderTo.setDate(2, Date.valueOf(historyOrderDateFrom));
			pstmOrderTo.setDate(3, Date.valueOf(historyOrderDateTo));
			rsTo = pstmOrderTo.executeQuery();
			
			while (rsFrom.next() & rsTo.next()) {
				dateCreate = rsFrom.getDate("dateCreate").toLocalDate();
				title = rsFrom.getString("title");
				dateDelivery = rsFrom.getDate("dateDelivery").toLocalDate();
				price = rsFrom.getDouble("priceShipment");
				weight = rsFrom.getInt("weight");
				comment = rsFrom.getString("comment");
				
				cityFrom = rsFrom.getString("city");
				streetFrom = rsFrom.getString("streets");
				houseFrom = rsFrom.getString("houses");
				flatFrom = rsFrom.getString("flats");
				
				cityTo = rsTo.getString("city");
				streetTo = rsTo.getString("streets");
				houseTo = rsTo.getString("houses");
				flatTo = rsTo.getString("flats");
				
				cityFromOb = new City(cityFrom);
				addressFrom = new AddressFrom(streetFrom, houseFrom, flatFrom, cityFromOb);
				
				cityToOb = new City(cityTo);
				addressTo = new AddressTo(streetTo, houseTo, flatTo, cityToOb);
				
				orderInfo = new OrderInfo(title, dateDelivery, price, weight, comment, addressFrom, addressTo);
				
				order = new Order(dateCreate, user, orderInfo);
				
				orderList.add(order);				
			}
			
			return orderList;
			
		} catch (SQLException e) {
			throw new DAOException("Can't to get access to DataBase or get the data from table", e);
		} catch (InterruptedException e) {
			throw new DAOException("The thread was interrupted", e);
		} finally {
			if (pstmOrderFrom != null && pstmOrderTo != null) {
				try {					
					pstmOrderFrom.close();
					pstmOrderTo.close();
				} catch (SQLException e) {
					for (StackTraceElement stackTraceElement : e.getStackTrace()) {
						log.error(stackTraceElement);
					}
				}
			}
		}	
	}

}
