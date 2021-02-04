package workTime.main.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import workTime.main.dao.EmployeeDao;
import workTime.main.form.AddEmployeeForm;
import workTime.main.form.UpdateEmployeeForm;
import workTime.main.model.Employee;

public class EmployeeDaoImpl<T> extends BaseDaoImpl<T> implements EmployeeDao<T> {
	public ArrayList<Employee> getPaginationByQuery(Connection conn, int page, int size) {
		ResultSet resultSet = null;
		int offset = (page-1)*size;
		String sql = "SELECT TOP " + size + " * FROM [employee] ";
		if(offset != 0) {
			sql += "OFFSET " + offset;
		}
		
		ArrayList<Employee> employeeList = new ArrayList<>();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getString("id"));
				employee.setName(resultSet.getString("name"));
				employee.setCreatedAt(resultSet.getTimestamp("created_at"));
				employee.setLeaveAt(resultSet.getTimestamp("leave_at"));
				employeeList.add(employee);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return employeeList;
	}
	
	public Employee getById(Connection conn, String id) {
		ResultSet resultSet = null;
		String sql = "SELECT * FROM [employee] where id = '" + id + "'";
		
		Employee employee = new Employee();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				employee.setId(resultSet.getString("id"));
				employee.setName(resultSet.getString("name"));
				employee.setCreatedAt(resultSet.getTimestamp("created_at"));
				employee.setLeaveAt(resultSet.getTimestamp("leave_at"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return employee;
	}
	
	public String addEmployee(Connection conn, AddEmployeeForm addEmployeeForm) {
		String name = addEmployeeForm.getName();
		String id = UUID.randomUUID().toString();
		String sql = "INSERT INTO [employee]([id], [name]) VALUES ('" + id + "', '" + name + "')";

		try {
			super.executeUpdate(conn, sql, null);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return id;
	}
	
	public void updateById(Connection conn, String id,UpdateEmployeeForm updateEmployeeForm) {
		ArrayList employeeFormParameterList = new ArrayList();
		StringBuilder condition = new StringBuilder();
		condition.append("update [employee] SET ");
		if (!updateEmployeeForm.getName().isEmpty()) {
			condition.append("name = '").append(updateEmployeeForm.getName()).append("', ");
		}
		if (updateEmployeeForm.getLeaveAt() != null) {
			condition.append("leave_at = ?, ");
			Timestamp leaveDate = Timestamp.valueOf(updateEmployeeForm.getLeaveAt());
			employeeFormParameterList.add(leaveDate);
		}
		condition.deleteCharAt(condition.lastIndexOf(","));
		condition.append("where id = '").append(id).append("' ");
		String sql = condition.toString();
		try {
			super.executeUpdate(conn, sql, employeeFormParameterList);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void deleteById(Connection conn, String id) {
		String sql = "delete from [employee] where id = '" + id + "'";
		try {
			super.executeQuery(conn, sql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
