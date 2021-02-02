package workTime.main.controller;

import java.sql.Connection;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import workTime.main.model.TimeClockRecord;
import workTime.main.service.TimeClockRecordService;

import java.util.*;

@Path("/timeClockRecords")
public class TimeClockRecordController<T> extends BaseController {
	Connection conn = super.getConnection();
	
	TimeClockRecordService<T> timeClockRecordService;
	
	@GET
	public Response getPaginationByQuery() {
		int page = 1;
		int size = 10;
		
		ArrayList<TimeClockRecord> employeeList = timeClockRecordService.getPaginationByQuery(conn, page, size);
		super.closeConnection();
		return super.OK(employeeList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		TimeClockRecord timeClockRecord = timeClockRecordService.getById(conn, id);
		super.closeConnection();
		return super.OK(timeClockRecord);
	}
}