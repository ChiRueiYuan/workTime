package workTime.main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface BaseDao<T> {
	ResultSet executeQuery(Connection conn, String query);
	void executeUpdate(Connection conn, String query, ArrayList parameter);
}
