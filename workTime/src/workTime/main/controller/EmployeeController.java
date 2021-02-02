package workTime.main.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import workTime.main.form.AddEmployeeForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.model.Employee;
import workTime.main.service.EmployeeService;
import workTime.main.service.impl.EmployeeServiceImpl;

import java.sql.Connection;
import java.util.*;

@Path("/employees")
public class EmployeeController<T> extends BaseController {
	Connection conn = super.getConnection();
	
	EmployeeService<T> employeeService = new EmployeeServiceImpl<T>();
	
	@GET
	public Response getPaginationByQuery() {
		int page = 1;
		int size = 10;
		
		ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(conn, page, size);
		super.closeConnection();
		return super.OK(employeeList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		Employee employee = employeeService.getById(conn, id);
		super.closeConnection();
		return super.OK(employee);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(AddEmployeeForm createEmployeeForm) {
		String primaryKey = employeeService.addEmployee(conn, createEmployeeForm);
		super.closeConnection();
		return super.OK(primaryKey);
	}

	@PUT
	@Path("/{id}")
	public Response updateById(@PathParam("id") String id,UpdateEmployeeForm updateEmployeeForm) {
		employeeService.updateById(conn, id, updateEmployeeForm);
		super.closeConnection();
		return super.OK(updateEmployeeForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		employeeService.deleteById(conn, id);
		super.closeConnection();
		return "deleteById : " + id;
	}
}