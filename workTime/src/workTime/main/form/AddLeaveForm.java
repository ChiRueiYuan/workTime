package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class AddLeaveForm implements Serializable {	
	private String leaveType;
	private String createBy;
	private String agent_id;
	private String note;
	private Timestamp create_at;
	private Timestamp last_modified_at;
	private String reason;
	private Timestamp dateFrom;
	private Timestamp dateTo;
	
	public String getLeaveType() {
		return this.leaveType;
	}
	
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	public String getCreateBy() {
		return this.createBy;
	}
	
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public String getAgentId() {
		return this.agent_id;
	}
	
	public void setAgentId(String agent_id) {
		this.agent_id = agent_id;
	}
	
	public String getNote() {
		return this.note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Timestamp getCreateAt() {
		return this.create_at;
	}
	
	public void setCreateAt(Timestamp create_at) {
		this.create_at = create_at;
	}
	
	public Timestamp getLastModifiedAt() {
		return this.last_modified_at;
	}
	
	public void setLastModifiedAt(Timestamp last_modified_at) {
		this.last_modified_at = last_modified_at;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Timestamp getDateFrom() {
		return this.dateFrom;
	}
	
	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public Timestamp getDateTo() {
		return this.dateTo;
	}
	
	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}
}
