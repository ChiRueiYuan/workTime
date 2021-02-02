package workTime.main.form;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class AddQuitForm implements Serializable {	
	private String id;
	private String type;
	private String createBy;
	private String approveBy;
	private String agent_id;
	private String note;
	private Timestamp create_at;
	private Timestamp last_modified_at;
	private String reason;
	private Timestamp quit_date;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
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
	
	public String getApproveBy() {
		return this.approveBy;
	}
	
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
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
	
	public Timestamp getQuitDate() {
		return this.quit_date;
	}
	
	public void setQuitDate(Timestamp quit_date) {
		this.quit_date = quit_date;
	}
}
