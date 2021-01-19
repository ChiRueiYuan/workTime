package workTime.main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/employees")
public class EmployeeServlet {
	String connectionString = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	public ResultSet getResultSet(String sql) throws SQLException {
		connection = DriverManager.getConnection(connectionString);
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		return resultSet;
	}

	public void tearDown() throws Exception {
		resultSet.close();
		connection.close();
		statement.close();
	}

	@GET
	public String getPaginationByQuery() {
		String connectionString = "jdbc:sqlserver://localhost;database=ruei-circle;integratedSecurity=true;";
		try {
			Connection connection = DriverManager.getConnection(connectionString);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM [employee]");
			while (resultSet.next()) {
				System.out.println(resultSet);
			}
			connection.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "FF";
	}

	@GET
	@Path("/{id}")
	public String getById(@PathParam("id") String id) throws Exception {
		String sql = "SELECT * FROM [employee] where id = '" + id + "'";
		ResultSet rs = this.getResultSet(sql);
		String testId = "";
		while(rs.next()) {
			testId = rs.getString("id");
			System.out.println(testId);
		}
		this.tearDown();
		return "getById:" + testId;
	}

	@POST
	public String addEmployee() {
		return "addEmployee";
	}

	@PUT
	@Path("/{id}")
	public String updateById(@PathParam("id") int id) {
		return "updateById";
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") int id) {
		return "deleteById";
	}
}