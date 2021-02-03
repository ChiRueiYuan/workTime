package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;

public class AddLeaveForm implements Serializable {	
	private int leaveType;
	private String createBy;
	private String agentId;
	private String note;
	private String reason;
	private String dateFrom;
	private String dateTo;
	
	public int getLeaveType() {
		return this.leaveType;
	}
	
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}
	
	public String getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getAgentId() {
		return this.agentId;
	}
	
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getDateFrom() {
		return this.dateFrom;
	}
	
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public String getDateTo() {
		return this.dateTo;
	}
	
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
}
