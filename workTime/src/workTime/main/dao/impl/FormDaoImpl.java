package workTime.main.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import workTime.main.dao.FormDao;
import workTime.main.form.AddLeaveForm;
import workTime.main.form.AddQuitForm;
import workTime.main.form.ApproveForm;
import workTime.main.form.UpdateLeaveForm;
import workTime.main.form.UpdateQuitForm;
import workTime.main.model.BaseForm;

public class FormDaoImpl<T> extends BaseDaoImpl<T> implements FormDao<T> {
	public ArrayList<BaseForm> getPaginationByQuery(Connection conn, int page, int size) {
		ResultSet resultSet = null;
		int offset = (page-1)*size;
		String sql = "SELECT TOP " + size + " * FROM [base_form] ";
		if(offset != 0) {
			sql += "OFFSET " + offset;
		}
		
		ArrayList<BaseForm> baseFormList = new ArrayList<>();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				BaseForm baseForm = new BaseForm();
				baseForm.setId(resultSet.getString("id"));
				baseForm.setType(resultSet.getInt("type"));
				baseForm.setCreateBy(resultSet.getString("created_by"));
				baseForm.setApproveBy(resultSet.getString("approved-by"));
				baseForm.setAgentId(resultSet.getString("agent-id"));
				baseForm.setNote(resultSet.getString("note"));
				baseForm.setCreateAt(resultSet.getTimestamp("created_at"));
				baseForm.setLastModifiedAt(resultSet.getTimestamp("last_modified_at"));
				baseFormList.add(baseForm);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return baseFormList;
	};
	
	public BaseForm getById(Connection conn, String id) {
		ResultSet resultSet = null;
		String sql = "SELECT * FROM [base_form] where id = '" + id + "'";
		
		BaseForm baseForm = new BaseForm();
		
		try {
			resultSet = super.executeQuery(conn, sql);
			while (resultSet.next()) {
				baseForm.setId(resultSet.getString("id"));
				baseForm.setType(resultSet.getInt("type"));
				baseForm.setCreateBy(resultSet.getString("created_by"));
				baseForm.setApproveBy(resultSet.getString("approved-by"));
				baseForm.setAgentId(resultSet.getString("agent-id"));
				baseForm.setNote(resultSet.getString("note"));
				baseForm.setCreateAt(resultSet.getTimestamp("created_at"));
				baseForm.setLastModifiedAt(resultSet.getTimestamp("last_modified_at"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return baseForm;
	};
	
	public String addLeaveForm(Connection conn, AddLeaveForm addLeaveForm) {
		String id = UUID.randomUUID().toString();
		int type = 1;
		Timestamp time= new Timestamp(System.currentTimeMillis());
		String createdBy = addLeaveForm.getCreateBy();
		String agentId = addLeaveForm.getAgentId();
		String note = addLeaveForm.getNote();
		int leaveType = addLeaveForm.getLeaveType();
		String reason = addLeaveForm.getReason();
		Timestamp dateFrom = Timestamp.valueOf(addLeaveForm.getDateFrom());
		Timestamp dateTo = Timestamp.valueOf(addLeaveForm.getDateTo());
		ArrayList leaveFormParameterList = new ArrayList();
		leaveFormParameterList.add(id);
		leaveFormParameterList.add(leaveType);
		leaveFormParameterList.add(reason);
		leaveFormParameterList.add(dateFrom);
		leaveFormParameterList.add(dateTo);
		String baseFormSql = "INSERT INTO [base_form]([id], [type], [created_by], [agent_id], [note]) "
				+ "VALUES ('" + id + "', '" + type + "', '" + createdBy + "', '" + agentId + "', '" + note + "')";
		String leaveFormSql = "INSERT INTO [leave_form]([id], [type], [reason], [from], [to]) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try {
			super.insert(conn, baseFormSql);
			super.insertLeaveForm(conn, leaveFormSql, leaveFormParameterList);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return id;
	};
	
	public String addQuitForm(Connection conn, AddQuitForm addQuitForm) {
		String id = UUID.randomUUID().toString();
		int type = 2;
		String createdBy = addQuitForm.getCreateBy().toString();
		String agentId = addQuitForm.getAgentId().toString();
		String note = addQuitForm.getNote();
		String reason = addQuitForm.getReason();
		String quitDate = addQuitForm.getQuitDate().toString();
		String baseFormSql = "INSERT INTO [base_form]([id], [type], [created_by], [agent_id], [note]) "
				+ "VALUES ('" + id + "', '" + type + "', '" + createdBy + "', '" + agentId + "', '" + note + "')";
		String quitFormSql = "INSERT INTO [quit_form]([id], [reason], [estimated_quit_date]) "
				+ "VALUES ('" + id + "', '" + reason + "', TO_TIMESTAMP('" + quitDate + "','YYYY-MM-DD HH:MI:SS.FF'))";
		try {
			super.insert(conn, baseFormSql);
			super.insert(conn, quitFormSql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return id;
	};
	
	public void updateLeaveFormById(Connection conn, String id,UpdateLeaveForm updateLeaveForm) {
		StringBuilder baseFormCondition = new StringBuilder();
		baseFormCondition.append("update [base_form] SET ");
		if (!updateLeaveForm.getAgentId().isEmpty()) {
			baseFormCondition.append("agent_id = '").append(updateLeaveForm.getAgentId()).append("', ");
		}
		if (!updateLeaveForm.getNote().isEmpty()) {
			baseFormCondition.append("note = '").append(updateLeaveForm.getNote()).append("', ");
		}
		baseFormCondition.append("last_modified_at = GETDATE() ");
		baseFormCondition.append("where id = '").append(id).append("' ");
		String baseFormSql = baseFormCondition.toString();
		
		StringBuilder leaveFormCondition = new StringBuilder();
		leaveFormCondition.append("update [leave_form] SET ");
		if (updateLeaveForm.getLeaveType() != null) {
			leaveFormCondition.append("type = ").append(updateLeaveForm.getLeaveType()).append(", ");
		}
		if (!updateLeaveForm.getReason().isEmpty()) {
			leaveFormCondition.append("reason = '").append(updateLeaveForm.getReason()).append("', ");
		}
		if (updateLeaveForm.getDateFrom() != null) {
			leaveFormCondition.append("from = TO_TIMESTAMP('").append(updateLeaveForm.getDateFrom().toString()).append("', 'YYYY-MM-DD HH:MI:SS.FF'), ");
		}
		if (updateLeaveForm.getDateTo() != null) {
			leaveFormCondition.append("to = TO_TIMESTAMP('").append(updateLeaveForm.getDateTo().toString()).append("', 'YYYY-MM-DD HH:MI:SS.FF'), ");
		}
		leaveFormCondition.deleteCharAt(leaveFormCondition.lastIndexOf(","));
		leaveFormCondition.append("where id = '").append(id).append("' ");
		String leaveFormSql = leaveFormCondition.toString();
		
		try {
			super.executeQuery(conn, baseFormSql);
			super.executeQuery(conn, leaveFormSql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};
	
	public void updateQuitFormById(Connection conn, String id,UpdateQuitForm updateQuitForm) {
		StringBuilder baseFormCondition = new StringBuilder();
		baseFormCondition.append("update [base_form] SET ");
		if (!updateQuitForm.getAgentId().isEmpty()) {
			baseFormCondition.append("agent_id = '").append(updateQuitForm.getAgentId()).append("', ");
		}
		if (!updateQuitForm.getNote().isEmpty()) {
			baseFormCondition.append("note = '").append(updateQuitForm.getNote()).append("', ");
		}
		baseFormCondition.append("last_modified_at = GETDATE() ");
		baseFormCondition.append("where id = '").append(id).append("' ");
		String baseFormSql = baseFormCondition.toString();
		
		StringBuilder quitFormCondition = new StringBuilder();
		quitFormCondition.append("update [leave_form] SET ");
		if (!updateQuitForm.getReason().isEmpty()) {
			quitFormCondition.append("reason = '").append(updateQuitForm.getReason()).append("', ");
		}
		if (updateQuitForm.getQuitDate() != null) {
			quitFormCondition.append("estimated_quit_date = TO_TIMESTAMP('").append(updateQuitForm.getQuitDate().toString()).append("', 'YYYY-MM-DD HH:MI:SS.FF'), ");
		}
		quitFormCondition.deleteCharAt(quitFormCondition.lastIndexOf(","));
		quitFormCondition.append("where id = '").append(id).append("' ");
		String quitFormSql = quitFormCondition.toString();
		
		try {
			super.executeQuery(conn, baseFormSql);
			super.executeQuery(conn, quitFormSql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};
	
	public void approveById(Connection conn, String id,ApproveForm approveForm) {
		String sql = "update [base_form] SET approved_by = '" + approveForm.getApprovedBy() + "' where id = '" + id + "'";
				
		try {
			super.executeQuery(conn, sql);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};
	
	public void deleteById(Connection conn, String id) {
		String sql1 = "delete from [base_form] where id = '" + id + "'";
		String sql2 = "delete from [leave_form] where id = '" + id + "'";
		String sql3 = "delete from [quit_form] where id = '" + id + "'";
		try {
			super.executeQuery(conn, sql1);
			super.executeQuery(conn, sql2);
			super.executeQuery(conn, sql3);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	};
}
