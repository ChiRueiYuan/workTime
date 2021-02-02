package workTime.main.service.impl;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.dao.FormDao;
import workTime.main.dao.impl.FormDaoImpl;
import workTime.main.form.AddLeaveForm;
import workTime.main.form.AddQuitForm;
import workTime.main.form.ApproveForm;
import workTime.main.form.UpdateLeaveForm;
import workTime.main.form.UpdateQuitForm;
import workTime.main.model.BaseForm;
import workTime.main.service.FormService;

public class FormServiceImpl<T> implements FormService<T> {
	FormDao<T> formDao = new FormDaoImpl<T>();
	
	public ArrayList<BaseForm> getPaginationByQuery(Connection conn, int page, int size) {
		ArrayList<BaseForm> formList = formDao.getPaginationByQuery(conn, page, size);
		return formList;
	};
	
	public BaseForm getById(Connection conn, String id) {
		BaseForm baseForm = formDao.getById(conn, id);
		return baseForm;
	};
	
	public String addLeaveForm(Connection conn, AddLeaveForm addLeaveForm) {
		String primaryKey = formDao.addLeaveForm(conn, addLeaveForm);
		return primaryKey;
	};
	
	public String addQuitForm(Connection conn, AddQuitForm addQuitForm) {
		String primaryKey = formDao.addQuitForm(conn, addQuitForm);
		return primaryKey;
	};
	
	public void updateLeaveFormById(Connection conn, String id,UpdateLeaveForm updateLeaveForm) {
		BaseForm baseForm = formDao.getById(conn, id);
		if(baseForm.getApproveBy() == null) {
			formDao.updateLeaveFormById(conn, id, updateLeaveForm);
		} else {
			System.out.println("This form is approved");
		}
	};
	
	public void updateQuitFormById(Connection conn, String id,UpdateQuitForm updateQuitForm) {
		BaseForm baseForm = formDao.getById(conn, id);
		if(baseForm.getApproveBy() == null) {
			formDao.updateQuitFormById(conn, id, updateQuitForm);
		} else {
			System.out.println("This form is approved");
		}
	};
	
	public void approveById(Connection conn, String id,ApproveForm approveForm) {
		BaseForm baseForm = formDao.getById(conn, id);
		if(baseForm.getApproveBy() == null) {
			formDao.approveById(conn, id, approveForm);
		} else {
			System.out.println("This form is approved");
		}
	};
	
	public void deleteById(Connection conn, String id) {
		formDao.deleteById(conn, id);
	};
}
