package workTime.main.service;

import java.sql.Connection;
import java.util.ArrayList;

import workTime.main.form.AddLeaveForm;
import workTime.main.form.AddQuitForm;
import workTime.main.form.ApproveForm;
import workTime.main.form.UpdateLeaveForm;
import workTime.main.form.UpdateQuitForm;
import workTime.main.model.BaseForm;
import workTime.main.model.LeaveForm;
import workTime.main.model.QuitForm;

public interface FormService<T> {
	public ArrayList<BaseForm> getAllBaseForm(Connection conn);
	public ArrayList<LeaveForm> getAllLeaveForm(Connection conn);
	public ArrayList<QuitForm> getAllQuitForm(Connection conn);
	public ArrayList<BaseForm> getPaginationByQuery(Connection conn, int page, int size);
	public BaseForm getById(Connection conn, String id);
	public LeaveForm getLeaveFormById(Connection conn, String id);
	public QuitForm getQuitFormById(Connection conn, String id);
	public String addLeaveForm(Connection conn, AddLeaveForm addLeaveForm);
	public String addQuitForm(Connection conn, AddQuitForm addQuitForm);
	public void updateLeaveFormById(Connection conn, String id,UpdateLeaveForm updateLeaveForm);
	public void updateQuitFormById(Connection conn, String id,UpdateQuitForm updateQuitForm);
	public void approveById(Connection conn, String id,ApproveForm approveForm);
	public void deleteById(Connection conn, String id);
}
