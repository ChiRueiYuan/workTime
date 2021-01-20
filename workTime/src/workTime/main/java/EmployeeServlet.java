package workTime.main.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
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
public class EmployeeServlet extends BaseServlet {
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
	public String getById(@PathParam("id") String id) {
		String sql = "SELECT * FROM [employee] where id = '" + id + "'";
		String testId = "";
		try {
			super.getConnection();
			ResultSet rs = super.executeQuery(sql);
			while (rs.next()) {
				testId = rs.getString("id");
				System.out.println(testId);
			}
			super.closeConnection();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "getById : " + testId;
	}

	@POST
	public String addEmployee() {
		String name = "XXX XXX";
		Date date = new Date();
		String sql = "INSERT INTO [employee]([name], [created_at]) VALUES (N'" + name
				+ "', GETDATE());";
		try {
			super.getConnection();
			ResultSet rs = super.executeQuery(sql);
			super.closeConnection();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "addEmployee";
	}

	@PUT
	@Path("/{id}")
	public String updateById(@PathParam("id") String id) {
//		String sql = "update [employee] SET ";
//		if (!name.isEmpty() && leave_at != null) {
//			sql += "name = '" + name + "', ";
//			sql += "leave_at = to_date(" + leave_at.toString() + ", yyyy-MM-dd) ";
//		} else if (!name.isEmpty() && leave_at == null) {
//			sql += "name = '" + name + "' ";
//		} else if (name.isEmpty() && leave_at != null) {
//			sql += "leave_at = to_date(" + leave_at.toString() + ", yyyy-MM-dd) ";
//		}
//		sql += "where id = '" + id + "'";
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
		return "updateById : " + id;
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		String sql = "delete from [employee] where id = '" + id + "'";
		try {
			super.getConnection();
			ResultSet rs = super.executeQuery(sql);
			super.closeConnection();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "deleteById : " + id;
	}
}