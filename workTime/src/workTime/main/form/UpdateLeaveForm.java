package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UpdateLeaveForm implements Serializable {	
	private String leaveType;
	private String agentId;
	private String note;
	private String reason;
	private String dateFrom;
	private String dateTo;
	
	public String getLeaveType() {
		return this.leaveType;
	}
	
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
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
