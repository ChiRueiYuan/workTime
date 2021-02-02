package workTime.main.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.dao.TimeClockRecordDao;
import workTime.main.model.TimeClockRecord;
import workTime.main.service.TimeClockRecordService;

public class TimeClockRecordServiceImpl<T> implements TimeClockRecordService<T> {
	TimeClockRecordDao<T> timeClockRecordDao;
	
	public ArrayList<TimeClockRecord> getPaginationByQuery(Connection conn, int page, int size) {
		ArrayList<TimeClockRecord> timeClockRecordList = timeClockRecordDao.getPaginationByQuery(conn, page, size);
		return timeClockRecordList;
	}
	
	public TimeClockRecord getById(Connection conn, String id) {
		TimeClockRecord timeClockRecord = timeClockRecordDao.getById(conn, id);
		return timeClockRecord;
	}
}
