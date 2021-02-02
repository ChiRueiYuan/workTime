package workTime.main.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimeClockRecord implements Serializable {	
	private String id;	
	private String type;
	private String employee_id;
	private Timestamp createAt;
	
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
	
	public String getEmployeeId() {
		return this.employee_id;
	}
	
	public void setEmployeeId(String employee_id) {
		this.employee_id = employee_id;
	}
	
	public Timestamp getCreateAt() {
		return this.createAt;
	}
	
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
}
