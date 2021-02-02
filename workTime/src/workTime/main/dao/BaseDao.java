package workTime.main.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public interface BaseDao<T> {
	ResultSet executeQuery(Connection conn, String query);
	void insert(Connection conn, String query);
}
