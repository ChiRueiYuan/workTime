package workTime.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import javax.ws.rs.core.Response;

public class BaseServlet {
	
	private final String CONNECTION_STRING = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";

    private Connection connection;
    
    protected Connection getConnection() throws SQLException, SQLTimeoutException {
    	if (this.connection == null) {
    		this.connection = DriverManager.getConnection(this.CONNECTION_STRING);
    	}
    	return this.connection;
    }

    protected ResultSet executeQuery(String query) throws Exception {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    
    protected void closeConnection() throws SQLException {
    	if (this.connection != null) {
    		this.connection.close();
    	}
    }
    
    protected Response OK(Object value) {
    	return Response.ok(value).build();
    }
}
