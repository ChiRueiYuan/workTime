package workTime.main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Path("/employee")
public class EmployeeServlet {

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
	public String getById(@PathParam("id") int id) {
		return "getById:" + id;
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