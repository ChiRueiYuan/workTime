package workTime.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

public class BaseController {
	private final String CONNECTION_STRING = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";
    
    public Connection getConnection() {
    	Connection connection = null;
    	if (connection == null) {
    		try {
				connection = DriverManager.getConnection(this.CONNECTION_STRING);
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return connection;
    }
    
    public void closeConnection(Connection connection) {
    	if (connection != null) {
    		try {
    			connection.commit();
    			connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    protected Response OK(Connection connection, Object value) {
    	this.closeConnection(connection);
    	return Response.ok(value).build();
    }
}
