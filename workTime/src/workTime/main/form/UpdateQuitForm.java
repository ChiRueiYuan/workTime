package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UpdateQuitForm implements Serializable {	
	private String agentId;
	private String note;
	private String reason;
	private String quitDate;
	
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
	
	public String getQuitDate() {
		return this.quitDate;
	}
	
	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
}
