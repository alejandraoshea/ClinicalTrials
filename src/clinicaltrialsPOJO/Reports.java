1package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reports implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 55142867368991496L;
	
	private Integer report_id; 
	private String medicalHistory;
	private String treatment;
	private List<Sponsor> sponsor;
	private Doctor doctor; //1 to N relation
	private Patient patient; //1 to N relation 
	
	
	

	public Reports() {
		super();
		this.sponsor = new ArrayList<Sponsor>();
	}


	public Reports(String medicalHistory, String treatment) {
		super();
		this.medicalHistory = medicalHistory;
		this.treatment = treatment;
		this.sponsor = new ArrayList<Sponsor>();
	}
	
	
	public Reports(Integer report_id, String medicalHistory, String treatment) {
		super();
		this.report_id = report_id;
		this.medicalHistory = medicalHistory;
		this.treatment = treatment;
		this.sponsor = new ArrayList<Sponsor>();
	}


	public Reports(Integer report_id) {
		super();
		this.report_id = report_id;
		this.sponsor = new ArrayList<Sponsor>();
	}


	public Integer getReport_id() {
		return report_id;
	}

	public void setReport_id(Integer report_id) {
		this.report_id = report_id;
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


	public List<Sponsor> getSponsor() {
		return sponsor;
	}


	public void setSponsor(List<Sponsor> sponsor) {
		this.sponsor = sponsor;
	}

	
	

	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	@Override
	public int hashCode() {
		return Objects.hash(doctor, medicalHistory, patient, report_id, sponsor, treatment);
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
		return Objects.equals(doctor, other.doctor) && Objects.equals(medicalHistory, other.medicalHistory)
				&& Objects.equals(patient, other.patient) && Objects.equals(report_id, other.report_id)
				&& Objects.equals(sponsor, other.sponsor) && Objects.equals(treatment, other.treatment);
	}


	@Override
	public String toString() {
		return "Reports [report_id=" + report_id + ", medicalHistory=" + medicalHistory + ", treatment=" + treatment
				+ ", sponsor=" + sponsor + ", doctor=" + doctor + ", patient=" + patient + "]";
	}



	
}
