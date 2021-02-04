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
		ArrayList<BaseForm> formList = formService.getPaginationByQuery(getConnection(), page, size);
		return super.OK(formList);
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") String id) {
		BaseForm baseForm = formService.getById(getConnection(), id);
		return super.OK(baseForm);
	}

	@POST
	@Path("/addLeaveForm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addLeaveForm(AddLeaveForm addLeaveForm) {
		String primaryKey = formService.addLeaveForm(getConnection(), addLeaveForm);
		return super.OK(primaryKey);
	}
	
	@POST
	@Path("/addQuitForm")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addQuitForm(AddQuitForm addQuitForm) {
		String primaryKey = formService.addQuitForm(getConnection(), addQuitForm);
		return super.OK(primaryKey);
	}

	@PUT
	@Path("/{id}/leave")
	public Response updateLeaveFormById(@PathParam("id") String id, UpdateLeaveForm updateLeaveForm) {
		formService.updateLeaveFormById(getConnection(), id, updateLeaveForm);
		return super.OK(updateLeaveForm);
	}
	
	@PUT
	@Path("/{id}/quit")
	public Response updateQuitFormById(@PathParam("id") String id, UpdateQuitForm updateQuitForm) {
		formService.updateQuitFormById(getConnection(), id, updateQuitForm);
		return super.OK(updateQuitForm);
	}
	
	@PUT
	@Path("/{id}/approve")
	public Response approveById(@PathParam("id") String id,ApproveForm approveForm) {
		formService.approveById(getConnection(), id, approveForm);
		return super.OK(approveForm);
	}

	@DELETE
	@Path("/{id}")
	public String deleteById(@PathParam("id") String id) {
		formService.deleteById(getConnection(), id);
		return "deleteById : " + id;
	}
}