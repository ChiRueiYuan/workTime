package workTime.main.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class QuitForm implements Serializable {	
	private String id;	
	private String reason;
	private Timestamp quit_date;
	
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
	
	public Timestamp getQuitDate() {
		return this.quit_date;
	}
	
	public void setQuitDate(Timestamp quit_date) {
		this.quit_date = quit_date;
	}
}
