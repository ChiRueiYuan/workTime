package workTime.main.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import workTime.main.form.AddLeaveForm;
import workTime.main.form.AddQuitForm;
import workTime.main.form.ApproveForm;
import workTime.main.form.UpdateLeaveForm;
import workTime.main.form.UpdateQuitForm;
import workTime.main.model.BaseForm;
import workTime.main.service.impl.FormServiceImpl;

import java.sql.Connection;
import java.util.*;

@Path("/forms")
public class FormController<T> extends BaseController {
	Connection conn = super.getConnection();
	
	FormServiceImpl<T> formService = new FormServiceImpl<T>();
	
	@GET
	public Response getPaginationByQuery() {
		super.getConnection();
		int page = 1;
		int size = 10;
		
		ArrayList<BaseForm> formList = formService.getPaginationByQuery(conn, page, size);
		super.closeConnection();
		return super.OK(formList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		super.getConnection();
		BaseForm baseForm = formService.getById(conn, id);
		super.closeConnection();
		return super.OK(baseForm);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLeaveForm(AddLeaveForm addLeaveForm) {
		super.getConnection();
		String primaryKey = formService.addLeaveForm(conn, addLeaveForm);
		super.closeConnection();
		return super.OK(primaryKey);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuitForm(AddQuitForm addQuitForm) {
		super.getConnection();
		String primaryKey = formService.addQuitForm(conn, addQuitForm);
		super.closeConnection();
		return super.OK(primaryKey);
	}

	@PUT
	@Path("/{id}/leave")
	public Response updateLeaveFormById(@PathParam("id") String id, UpdateLeaveForm updateLeaveForm) {
		super.getConnection();
		formService.updateLeaveFormById(conn, id, updateLeaveForm);
		super.closeConnection();
		return super.OK(updateLeaveForm);
	}
	
	@PUT
	@Path("/{id}/quit")
	public Response updateQuitFormById(@PathParam("id") String id, UpdateQuitForm updateQuitForm) {
		super.getConnection();
		formService.updateQuitFormById(conn, id, updateQuitForm);
		super.closeConnection();
		return super.OK(updateQuitForm);
	}
	
	@PUT
	@Path("/{id}/approve")
	public Response approveById(@PathParam("id") String id,ApproveForm approveForm) {
		super.getConnection();
		formService.approveById(conn, id, approveForm);
		super.closeConnection();
		return super.OK(approveForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		super.getConnection();
		formService.deleteById(conn, id);
		super.closeConnection();
		return "deleteById : " + id;
	}
}