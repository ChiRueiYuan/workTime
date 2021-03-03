package workTime.main.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import workTime.main.dao.TimeClockRecordDao;
import workTime.main.model.TimeClockRecord;

public class TimeClockRecordDaoImpl<T> extends BaseDaoImpl<T> implements TimeClockRecordDao<T> {
	public ArrayList<TimeClockRecord> getPaginationByQuery(Connection conn, int page, int size) {
		ResultSet resultSet = null;
		int offset = (page-1)*size;
		String sql = "SELECT * FROM [time_clock_record] "
				+ "ORDER BY created_at ASC OFFSET " + offset + " ROWS FETCH NEXT " + size + " ROWS ONLY";
		
		ArrayList<TimeClockRecord> timeClockRecordList = new ArrayList<>();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				TimeClockRecord timeClockRecord = new TimeClockRecord();
				timeClockRecord.setId(resultSet.getString("id"));
				timeClockRecord.setType(resultSet.getString("type"));
				timeClockRecord.setEmployeeId(resultSet.getString("employee_id"));
				timeClockRecord.setCreateAt(resultSet.getTimestamp("createAt"));
				timeClockRecordList.add(timeClockRecord);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return timeClockRecordList;
	}
	
	public TimeClockRecord getById(Connection conn, String id) {
		ResultSet resultSet = null;
		String sql = "SELECT * FROM [time_clock_record] where id = '" + id + "'";
		
		TimeClockRecord timeClockRecord = new TimeClockRecord();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				timeClockRecord.setId(resultSet.getString("id"));
				timeClockRecord.setType(resultSet.getString("type"));
				timeClockRecord.setEmployeeId(resultSet.getString("employee_id"));
				timeClockRecord.setCreateAt(resultSet.getTimestamp("createAt"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return timeClockRecord;
	}
}
