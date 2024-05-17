package clinicaltrialsPOJO;

import java.util.*;

public class TrialsAplication {
	private Date dateOfResolution;
	private Date dateOfApplication;
	private String resolution;
	private Patient patient; //1 to N relation
	
	
	public Date getDateOfResolution() {
		return dateOfResolution;
	}
	public void setDateOfResolution(Date dateOfResolution) {
		this.dateOfResolution = dateOfResolution;
	}
	public Date getDateOfApplication() {
		return dateOfApplication;
	}
	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(dateOfApplication, dateOfResolution, patient, resolution);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrialsAplication other = (TrialsAplication) obj;
		return Objects.equals(dateOfApplication, other.dateOfApplication)
				&& Objects.equals(dateOfResolution, other.dateOfResolution) && Objects.equals(patient, other.patient)
				&& Objects.equals(resolution, other.resolution);
	}
	
	
	
	@Override
	public String toString() {
		return "TrialsAplication [dateOfResolution=" + dateOfResolution + ", dateOfApplication=" + dateOfApplication
				+ ", resolution=" + resolution + ", patient=" + patient + "]";
	}
	
	

	
	
	

}
