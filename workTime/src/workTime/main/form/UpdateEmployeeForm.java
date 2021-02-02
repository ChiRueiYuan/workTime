package workTime.main.form;

import java.sql.Timestamp;

public class UpdateEmployeeForm {	
	private String name;
	private Timestamp leave_at;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Timestamp getLeaveAt() {
		return this.leave_at;
	}
	
	public void setLeaveAt(Timestamp leave_at) {
		this.leave_at = leave_at;
	}
}
