package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;

public class AddQuitForm implements Serializable {	
	private String type;
	private String createBy;
	private String agentId;
	private String note;
	private String reason;
	private String quitDate;
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
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
	
	public String getQuitDate() {
		return this.quitDate;
	}
	
	public void setQuitDate(String quitDate) {
		this.quitDate = quitDate;
	}
}
