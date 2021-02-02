package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UpdateQuitForm implements Serializable {	
	private String agent_id;
	private String note;
	private Timestamp last_modified_at;
	private String reason;
	private Timestamp quit_date;
	
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
	
	public Timestamp getQuitDate() {
		return this.quit_date;
	}
	
	public void setQuitDate(Timestamp quit_date) {
		this.quit_date = quit_date;
	}
}
