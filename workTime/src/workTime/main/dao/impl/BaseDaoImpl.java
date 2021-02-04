package workTime.main.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    
    public void executeUpdate(Connection conn, String query, ArrayList parameter) {
    	PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(query);
			if(parameter != null) {
//				this.setParameter(preparedStatement, parameter);
				for(int i = 0; i < parameter.size(); i++) {
	        		if(parameter.get(i) instanceof java.lang.Integer) {
	        			preparedStatement.setInt(i+1, (int) parameter.get(i));
	        		} else if(parameter.get(i) instanceof java.lang.String) {
	        			preparedStatement.setString(i+1, (String) parameter.get(i));
	        		} else if(parameter.get(i) instanceof java.sql.Timestamp) {
	        			preparedStatement.setTimestamp(i+1, (Timestamp) parameter.get(i));
	        		} 
	        	}
			}
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
//    private void setParameter(PreparedStatement preparedStatement, ArrayList parameter) {
//    	try {
//    		for(int i = 0; i < parameter.size(); i++) {
//        		if(parameter.get(i) instanceof java.lang.Integer) {
//        			preparedStatement.setInt(i+1, (int) parameter.get(i));
//        		} else if(parameter.get(i) instanceof java.lang.String) {
//        			preparedStatement.setString(i+1, (String) parameter.get(i));
//        		} else if(parameter.get(i) instanceof java.sql.Timestamp) {
//        			preparedStatement.setTimestamp(i+1, (Timestamp) parameter.get(i));
//        		} 
//        	}
//    	} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    }
}
