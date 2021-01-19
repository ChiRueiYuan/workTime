package workTime.main.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class TimeClockRecord implements Serializable {	
	private String id;	
	private String type;
	private String employee_id;
	private Date createAt;
	
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
	
	public Date getCreateAt() {
		return this.createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
