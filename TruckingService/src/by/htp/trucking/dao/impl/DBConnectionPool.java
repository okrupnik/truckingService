package by.htp.trucking.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DBConnectionPool {
	private static final Logger log = LogManager.getLogger(UserDAOImpl.class.getName());
	private static DBConnectionPool instance = null;	
	
	private DBConnectionPool() {		
	}

	public static DBConnectionPool getInstance() {
		if (instance == null) {
			instance = new DBConnectionPool();
		}
		return instance;
	}
	
	public Connection getConnection() throws NamingException, SQLException {
		Context ctx;
        Connection conn = null;
        
        ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/truckingDB");
        conn = ds.getConnection();
        
        return conn;
	}
	
	public static void Disconnect (Connection conn) {
        try {
        	conn.close();
        } catch (SQLException e) {
        	for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                log.error(stackTraceElement);
            }
        }
    }
}
