//package workTime.main.java;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.SQLTimeoutException;
//import java.sql.Statement;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import workTime.main.form.AddEmployeeForm;
//import workTime.main.form.UpdateEmployeeForm;
//import workTime.main.model.Employee;
//
//import java.util.*;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Path("/employees")
//public class EmployeeServlet extends BaseServlet {
//	@GET
//	public Response getPaginationByQuery() {
//		int page = 1;
//		int size = 10;
//		int offset = (page-1)*size;
//		String sql = "SELECT TOP " + size + " * FROM [employee] ";
//		if(offset != 0) {
//			sql += "OFFSET " + offset;
//		}
//		
//		ArrayList<Employee> employeeList = new ArrayList<>();
//		
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			while (rs.next()) {
//				Employee employee = new Employee();
//				employee.setId(rs.getString("id"));
//				employee.setName(rs.getString("name"));
//				employee.setCreatedAt(rs.getTimestamp("created_at"));
//				employee.setLeaveAt(rs.getTimestamp("leave_at"));
//				employeeList.add(employee);
//			}
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return super.OK(employeeList);
//	}
//
//	@GET
//	@Path("/{id}")
//	public Response getById(@PathParam("id") String id) {
//		String sql = "SELECT * FROM [employee] where id = '" + id + "'";
//		Employee employee = new Employee();
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			while (rs.next()) {
//				employee.setId(rs.getString("id"));
//				employee.setName(rs.getString("name"));
//				employee.setCreatedAt(rs.getTimestamp("created_at"));
//				employee.setLeaveAt(rs.getTimestamp("leave_at"));
//			}
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//
//		return super.OK(employee);
//	}
//
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response addEmployee(AddEmployeeForm createEmployeeForm) {
//		String name = createEmployeeForm.getName();
//		String sql = "INSERT INTO [employee]([name], [created_at]) VALUES (N'" + name
//				+ "', GETDATE());";
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return super.OK(createEmployeeForm);
//	}
//
//	@PUT
//	@Path("/{id}")
//	public Response updateById(@PathParam("id") String id,UpdateEmployeeForm updateEmployeeForm) {
//		StringBuilder condition = new StringBuilder();
//		condition.append("update [employee] SET ");
//		if (!updateEmployeeForm.getName().isEmpty()) {
//			condition.append("name = '").append(updateEmployeeForm.getName()).append("', ");
//		}
//		if (updateEmployeeForm.getLeaveAt() != null) {
//			condition.append("leave_at = to_date(").append(updateEmployeeForm.getLeaveAt().toString()).append(", yyyy-MM-dd), ");
//		}
//		condition.deleteCharAt(condition.lastIndexOf(","));
//		condition.append("where id = '").append(id).append("' ");
//		String sql = condition.toString();
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return super.OK(updateEmployeeForm);
//	}
//
//	@DELETE
//	@Path("/{id}")
//	public String deleteById(@PathParam("id") String id) {
//		String sql = "delete from [employee] where id = '" + id + "'";
//		try {
//			super.getConnection();
//			ResultSet rs = super.executeQuery(sql);
//			super.closeConnection();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return "deleteById : " + id;
//	}
//}