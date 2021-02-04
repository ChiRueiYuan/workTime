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
		ArrayList<Employee> employeeList = employeeService.getPaginationByQuery(getConnection(), page, size);
		return super.OK(employeeList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		Employee employee = employeeService.getById(getConnection(), id);
		return super.OK(employee);
	}

	@POST
	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmployee(AddEmployeeForm addEmployeeForm) {
		String primaryKey = employeeService.addEmployee(getConnection(), addEmployeeForm);
		return super.OK(primaryKey);
	}

	@PUT
	@Path("/{id}")
	public Response updateById(@PathParam("id") String id,UpdateEmployeeForm updateEmployeeForm) {
		employeeService.updateById(getConnection(), id, updateEmployeeForm);
		return super.OK(updateEmployeeForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		employeeService.deleteById(getConnection(), id);
		return "deleteById : " + id;
	}
}