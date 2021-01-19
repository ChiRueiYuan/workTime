package workTime.main.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class QuitForm implements Serializable {	
	private String id;	
	private String reason;
	private Date quit_date;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Date getQuitDate() {
		return this.quit_date;
	}
	
	public void setQuitDate(Date quit_date) {
		this.quit_date = quit_date;
	}
}
