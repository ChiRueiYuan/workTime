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
	FormServiceImpl<T> formService = new FormServiceImpl<T>();
	
	@GET
	public Response getPaginationByQuery(@QueryParam("page") int page, @QueryParam("size") int size) {
		Connection connection = getConnection();
		ArrayList<BaseForm> formList = formService.getPaginationByQuery(connection, page, size);
		return super.OK(connection, formList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		Connection connection = getConnection();
		BaseForm baseForm = formService.getById(connection, id);
		return super.OK(connection, baseForm);
	}

	@POST
	@Path("/addLeaveForm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLeaveForm(AddLeaveForm addLeaveForm) {
		Connection connection = getConnection();
		String primaryKey = formService.addLeaveForm(connection, addLeaveForm);
		return super.OK(connection, primaryKey);
	}
	
	@POST
	@Path("/addQuitForm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuitForm(AddQuitForm addQuitForm) {
		Connection connection = getConnection();
		String primaryKey = formService.addQuitForm(connection, addQuitForm);
		return super.OK(connection, primaryKey);
	}

	@PUT
	@Path("/{id}/leave")
	public Response updateLeaveFormById(@PathParam("id") String id, UpdateLeaveForm updateLeaveForm) {
		Connection connection = getConnection();
		formService.updateLeaveFormById(connection, id, updateLeaveForm);
		return super.OK(connection, updateLeaveForm);
	}
	
	@PUT
	@Path("/{id}/quit")
	public Response updateQuitFormById(@PathParam("id") String id, UpdateQuitForm updateQuitForm) {
		Connection connection = getConnection();
		formService.updateQuitFormById(connection, id, updateQuitForm);
		return super.OK(connection, updateQuitForm);
	}
	
	@PUT
	@Path("/{id}/approve")
	public Response approveById(@PathParam("id") String id, ApproveForm approveForm) {
		Connection connection = getConnection();
		formService.approveById(connection, id, approveForm);
		return super.OK(connection, approveForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		Connection connection = getConnection();
		formService.deleteById(connection, id);
		return "deleteById : " + id;
	}
}