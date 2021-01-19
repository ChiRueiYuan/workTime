package workTime.main.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class BaseForm implements Serializable {	
	private String id;	
	private String type;
	private String createBy;
	private String approveBy;
	private String agent_id;
	private String note;
	private Date create_at;
	private Date last_modified_at;
	
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
	
	public Date getCreateAt() {
		return this.create_at;
	}
	
	public void setCreateAt(Date create_at) {
		this.create_at = create_at;
	}
	
	public Date getLastModifiedAt() {
		return this.last_modified_at;
	}
	
	public void setLastModifiedAt(Date last_modified_at) {
		this.last_modified_at = last_modified_at;
	}
}
