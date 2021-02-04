package workTime.main.form;

import java.sql.Timestamp;

public class UpdateEmployeeForm {	
	private String name;
	private String leaveAt;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLeaveAt() {
		return this.leaveAt;
	}
	
	public void setLeaveAt(String leaveAt) {
		this.leaveAt = leaveAt;
	}
}
