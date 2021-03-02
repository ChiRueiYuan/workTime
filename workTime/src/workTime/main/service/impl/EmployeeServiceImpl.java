package workTime.main.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.dao.EmployeeDao;
import workTime.main.dao.impl.EmployeeDaoImpl;
import workTime.main.form.AddEmployeeForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.model.Employee;
import workTime.main.service.EmployeeService;

public class EmployeeServiceImpl<T> implements EmployeeService<T> {
	EmployeeDao<T> employeeDao = new EmployeeDaoImpl<T>();
	
	public ArrayList<Employee> getAll(Connection conn) {
		ArrayList<Employee> employeeList = employeeDao.getAll(conn);
		return employeeList;
	}
	
	public ArrayList<Employee> getPaginationByQuery(Connection conn, int page, int size) {
		ArrayList<Employee> employeeList = employeeDao.getPaginationByQuery(conn, page, size);
		return employeeList;
	}
	
	public Employee getById(Connection conn, String id) {
		Employee employee = employeeDao.getById(conn, id);
		return employee;
	}
	
	public String addEmployee(Connection conn, AddEmployeeForm addEmployeeForm) {
		String primaryKey = employeeDao.addEmployee(conn, addEmployeeForm);
		return primaryKey;
	}
	
	public void updateById(Connection conn, String id,UpdateEmployeeForm updateEmployeeForm) {
		employeeDao.updateById(conn, id, updateEmployeeForm);
	}

	public void deleteById(Connection conn, String id) {
		employeeDao.deleteById(conn, id);
	}
}
