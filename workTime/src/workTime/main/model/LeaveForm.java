package workTime.main.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class LeaveForm implements Serializable {	
	private String id;	
	private String type;
	private String reason;
	private Date dateFrom;
	private Date dateTo;
	
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
	
	public String getReason() {
		return this.reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Date getDateFrom() {
		return this.dateFrom;
	}
	
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	public Date getDateTo() {
		return this.dateTo;
	}
	
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}
