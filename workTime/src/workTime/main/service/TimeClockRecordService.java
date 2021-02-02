package workTime.main.service;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.model.TimeClockRecord;

public interface TimeClockRecordService<T> {
	public ArrayList<TimeClockRecord> getPaginationByQuery(Connection conn, int page, int size);
	public TimeClockRecord getById(Connection conn, String id);
}
