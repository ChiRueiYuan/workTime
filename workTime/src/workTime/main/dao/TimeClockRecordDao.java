package workTime.main.dao;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.model.TimeClockRecord;

public interface TimeClockRecordDao<T> {
	public ArrayList<TimeClockRecord> getAll(Connection conn);
	public ArrayList<TimeClockRecord> getPaginationByQuery(Connection conn, int page, int size);
	public TimeClockRecord getById(Connection conn, String id);
}
