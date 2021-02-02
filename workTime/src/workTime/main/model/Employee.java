package workTime.main.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class Employee implements Serializable {	
	private String id;	
	private String name;
	private Timestamp created_at;
	private Timestamp leave_at;
	
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
	
	public Timestamp getCreatedAt() {
		return this.created_at;
	}
	
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	public Timestamp getLeaveAt() {
		return this.leave_at;
	}
	
	public void setLeaveAt(Timestamp leave_at) {
		this.leave_at = leave_at;
	}
}
