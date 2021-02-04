package workTime.main.controller;

import java.sql.Connection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import workTime.main.model.TimeClockRecord;
import workTime.main.service.TimeClockRecordService;

import java.util.*;

@Path("/timeClockRecords")
public class TimeClockRecordController<T> extends BaseController {
	TimeClockRecordService<T> timeClockRecordService;
	
	@GET
	public Response getPaginationByQuery(@QueryParam("page") int page, @QueryParam("size") int size) {
		ArrayList<TimeClockRecord> employeeList = timeClockRecordService.getPaginationByQuery(getConnection(), page, size);
		closeConnection();
		return super.OK(employeeList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		TimeClockRecord timeClockRecord = timeClockRecordService.getById(getConnection(), id);
		closeConnection();
		return super.OK(timeClockRecord);
	}
}