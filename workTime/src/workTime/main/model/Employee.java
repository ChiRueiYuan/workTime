package workTime.main.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class Employee implements Serializable {	
	private String id;	
	private String name;
	private Date created_at;
	private Date leave_at;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedAt() {
		return this.created_at;
	}
	
	public void setCreatedAt(Date created_at) {
		this.created_at = created_at;
	}
	
	public Date getLeaveAt() {
		return this.leave_at;
	}
	
	public void setLeaveAt(Date leave_at) {
		this.leave_at = leave_at;
	}
}
