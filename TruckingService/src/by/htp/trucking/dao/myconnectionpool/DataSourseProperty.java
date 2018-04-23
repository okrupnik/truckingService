package by.htp.trucking.dao.myconnectionpool;

import java.sql.SQLException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * ������ ��������� (����� ��� � ������������ ����������) � ������� 
 * ��� ���������� - myConnectionPool, ��� ���� �������� � ������ ����������,
 * ���������� � ���� ����� � ����� �����.
 * � ���� ��� ���������� ������� � ���������� ProxyConnection, �� ����������, 
 * ����������� �� ����, ��������� ��������� ��� ����������� ��� ������� ������ 
 * ���� ��������. ��� ����������� ���������� � ��� ������� retrieveConnection(E connection) 
 * ����� �������� ������ ������-������. ������� ����������� �������� ���������� 
 * Connection �������� � ������ ����������. �� ���� ������ ���������� ������� 
 * � ��� �� �����, � ������������ ���� ����������.
 * 
 * @author Fisher
 *
 */

public class DataSourseProperty {
	private static final Logger log = (Logger) LogManager.getLogger(DataSourseProperty.class.getName());
	private static final DataSourseProperty instance = new DataSourseProperty();

	public static final String CLASS_FOR_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/trucking?autoReconnect=true";
	public static final String HOST_NAME = "localhost";
	public static final String PORT = "3306";
	public static final String DB_NAME = "trucking";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "root";
	public static final int AMOUNT_CONNECTION = 30;

	public MyConnectionPool<ProxyConnection> myConnectionPool;

	private DataSourseProperty() {
		try {
			myConnectionPool = new MyConnectionPool(URL, USER_NAME, PASSWORD, CLASS_FOR_NAME, AMOUNT_CONNECTION);
		} catch (SQLException | ClassNotFoundException e) {
			for (StackTraceElement stackTraceElement : e.getStackTrace()) {
				log.error(stackTraceElement);
			}
		}
	}

	public static DataSourseProperty getInstance() {
		return instance;
	}

}
