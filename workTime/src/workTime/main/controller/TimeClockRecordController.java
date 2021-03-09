package workTime.main.controller;

import java.sql.Connection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import workTime.main.model.TimeClockRecord;
import workTime.main.service.TimeClockRecordService;
import workTime.main.service.impl.TimeClockRecordServiceImpl;

import java.util.*;

@Path("/timeClockRecords")
public class TimeClockRecordController<T> extends BaseController {
	TimeClockRecordService<T> timeClockRecordService = new TimeClockRecordServiceImpl<>();
	
	@GET
	public Response getPaginationByQuery(@QueryParam("page") int page, @QueryParam("size") int size) {
		Connection connection = getConnection();
		ArrayList<TimeClockRecord> timeClockRecordList = timeClockRecordService.getPaginationByQuery(connection, page, size);
		return super.OK(connection, timeClockRecordList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		Connection connection = getConnection();
		TimeClockRecord timeClockRecord = timeClockRecordService.getById(connection, id);
		return super.OK(connection, timeClockRecord);
	}
}