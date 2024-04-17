package clinicaltrialsPOJO;

import java.util.Objects;

public class Reports {
	private String medicalHistory;
	private String treatment;
	
	
	public Reports(String medicalHistory, String treatment) {
		super();
		this.medicalHistory = medicalHistory;
		this.treatment = treatment;
	}


	public String getMedicalHistory() {
		return medicalHistory;
	}


	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}


	public String getTreatment() {
		return treatment;
	}


	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}


	@Override
	public int hashCode() {
		return Objects.hash(medicalHistory, treatment);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reports other = (Reports) obj;
		return Objects.equals(medicalHistory, other.medicalHistory) && Objects.equals(treatment, other.treatment);
	}


	@Override
	public String toString() {
		return "Reports [medicalHistory=" + medicalHistory + ", treatment=" + treatment + "]";
	}
	
	
	
	
	

}
