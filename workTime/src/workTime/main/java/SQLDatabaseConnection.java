package workTime.main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws ClassNotFoundException, SQLException {  
    	String connectionString = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";
		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM [employee]");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("name"));
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
    }
}