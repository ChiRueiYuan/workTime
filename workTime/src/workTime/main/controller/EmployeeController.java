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
	EmployeeService<T> employeeService = new EmployeeServiceImpl<T>();
	
	@GET
	public Response getPaginationByQuery(@QueryParam("page") int page, @QueryParam("size") int size) {
		Connection connection = getConnection();
		ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(connection, page, size);
		return super.OK(connection, employeeList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		Connection connection = getConnection();
		Employee employee = employeeService.getById(connection, id);
		return super.OK(connection, employee);
	}

	@POST
	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(AddEmployeeForm addEmployeeForm) {
		Connection connection = getConnection();
		String primaryKey = employeeService.addEmployee(connection, addEmployeeForm);
		return super.OK(connection, primaryKey);
	}

	@PUT
	@Path("/{id}")
	public Response updateById(@PathParam("id") String id,UpdateEmployeeForm updateEmployeeForm) {
		Connection connection = getConnection();
		employeeService.updateById(connection, id, updateEmployeeForm);
		return super.OK(connection, updateEmployeeForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		Connection connection = getConnection();
		employeeService.deleteById(connection, id);
		return "deleteById : " + id;
	}
}