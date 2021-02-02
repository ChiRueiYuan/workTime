package workTime.main.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class LeaveForm implements Serializable {	
	private String id;	
	private int type;
	private String reason;
	private Timestamp dateFrom;
	private Timestamp dateTo;
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Timestamp getDateFrom() {
		return this.dateFrom;
	}
	
	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public Timestamp getDateTo() {
		return this.dateTo;
	}
	
	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}
}
