package workTime.main.service;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.form.AddEmployeeForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.model.Employee;

public interface EmployeeService<T> {
	public ArrayList<Employee> getAll(Connection conn);
	public ArrayList<Employee> getPaginationByQuery(Connection conn, int page, int size);
	public Employee getById(Connection conn, String id);
	public String addEmployee(Connection conn, AddEmployeeForm addEmployeeForm);
	public void updateById(Connection conn, String id,UpdateEmployeeForm updateEmployeeForm);
	public void deleteById(Connection conn, String id);
}
