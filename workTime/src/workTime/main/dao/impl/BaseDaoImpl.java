package workTime.main.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;

import workTime.main.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T> {
    public ResultSet executeQuery(Connection conn, String query) {
    	PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
		try {
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return resultSet;
    }
    
    public void insert(Connection conn, String query) {
    	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
