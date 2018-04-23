package by.htp.trucking.dao.myconnectionpool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**���������� �� ���� ������������� ����������� �� ���� � ����� ������������� ������������. 
 * ��� ����� ���������� ��� ����������� ������������ �� ��������� � ���� ������� ����������,
 * ��������� ��� ����, � �� ����� ��� ����������, ������ �� ����, �� ������ ������������ � 
 * ����, ��� ��� ��� ����� ��������� �������� ������� ������������. � ����� ���������� 
 * ��-�� �������� ������������������� ���������� � ��������� ������ ���� ����� �������� 
 * ��������� � � ��������, ��������, ������������.
 *  
 * @author Fisher
 *
 */

public class MyConnectionPool<E> { 
	private BlockingQueue<E> connectionPool;
	String url;
	String user;
	String password;	
	
	public MyConnectionPool(String url, String user, String password, String classForName, final int AMOUNT_CONNECTION) throws SQLException, ClassNotFoundException {
		this.url = url;
		this.user = user;
		this.password = password;
		Class.forName(classForName);
		connectionPool = new ArrayBlockingQueue<E>(AMOUNT_CONNECTION);
		for(int i = 0; i < AMOUNT_CONNECTION; i++) {			
			E connection = (E) DriverManager.getConnection(url, user, password);
			connectionPool.offer(connection);
		}
	}
	
	public E getConnection() throws InterruptedException {
		E connection = null;
		connection = connectionPool.take();
		return connection;
	}
	
	public void retrieveConnection(E connection) throws InterruptedException {
		connectionPool.put(connection);
	}

}
