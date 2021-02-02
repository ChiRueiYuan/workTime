package workTime.main.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.core.Response;

public class BaseController {
	private final String CONNECTION_STRING = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";
	private Connection connection;
    
    public Connection getConnection() {
    	if (this.connection == null) {
    		try {
				this.connection = DriverManager.getConnection(this.CONNECTION_STRING);
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	return this.connection;
    }
    
    public void closeConnection() {
    	if (this.connection != null) {
    		try {
    			connection.commit();
    			connection.setAutoCommit(true);
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
    
    protected Response OK(Object value) {
    	return Response.ok(value).build();
    }
}
