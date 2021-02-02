package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;

public class AddQuitForm implements Serializable {	
	private String type;
	private String createBy;
	private String agent_id;
	private String note;
	private String reason;
	private Timestamp quit_date;
	
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
