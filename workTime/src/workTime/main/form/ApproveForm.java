package workTime.main.form;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class ApproveForm implements Serializable {	
	private String approvedBy;
	
	public String getApprovedBy() {
		return this.approvedBy;
	}
	
	public void setApprovedById(String approvedById) {
		this.approvedBy = approvedById;
	}
}
