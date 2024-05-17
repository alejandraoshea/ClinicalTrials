package clinicaltrialsPOJO;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TrialsApplication implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4498492161902931274L;
	
	private Integer trialsApplication_id;
	private Date dateApplication; 
	private Date dateResolution;
	private boolean approved;
	private Administrator admin;  //1 to N relation
	private Patient patient; //1 to N relation
	private Trial trial; //1 to N relation
	
	
	public TrialsApplication(Integer trialsApplication_id, Date dateApplication, Date dateResolution,
			boolean approved) {
		super();
		this.trialsApplication_id = trialsApplication_id;
		this.dateApplication = dateApplication;
		this.dateResolution = dateResolution;
		this.approved = approved;
	}


	public Integer getTrialsApplication_id() {
		return trialsApplication_id;
	}


	public void setTrialsApplication_id(Integer trialsApplication_id) {
		this.trialsApplication_id = trialsApplication_id;
	}


	public Date getDateApplication() {
		return dateApplication;
	}


	public void setDateApplication(Date dateApplication) {
		this.dateApplication = dateApplication;
	}


	public Date getDateResolution() {
		return dateResolution;
	}


	public void setDateResolution(Date dateResolution) {
		this.dateResolution = dateResolution;
	}


	public boolean isApproved() {
		return approved;
	}


	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	public Administrator getAdmin() {
		return admin;
	}


	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Trial getTrial() {
		return trial;
	}


	public void setTrial(Trial trial) {
		this.trial = trial;
	}


	@Override
	public int hashCode() {
		return Objects.hash(admin, approved, dateApplication, dateResolution, patient, trial, trialsApplication_id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrialsApplication other = (TrialsApplication) obj;
		return Objects.equals(admin, other.admin) && approved == other.approved
				&& Objects.equals(dateApplication, other.dateApplication)
				&& Objects.equals(dateResolution, other.dateResolution) && Objects.equals(patient, other.patient)
				&& Objects.equals(trial, other.trial)
				&& Objects.equals(trialsApplication_id, other.trialsApplication_id);
	}


	@Override
	public String toString() {
		return "TrialsApplication [trialsApplication_id=" + trialsApplication_id + ", dateApplication="
				+ dateApplication + ", dateResolution=" + dateResolution + ", approved=" + approved + ", admin=" + admin
				+ ", patient=" + patient + ", trial=" + trial + "]";
	}


	
	


}
