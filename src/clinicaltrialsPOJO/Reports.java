package clinicaltrialsPOJO;

import java.io.Serializable;
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
	
	
	public Reports(String medicalHistory, String treatment) {
		super();
		this.medicalHistory = medicalHistory;
		this.treatment = treatment;
	}
	
	
	public Reports(Integer report_id, String medicalHistory, String treatment) {
		super();
		this.report_id = report_id;
		this.medicalHistory = medicalHistory;
		this.treatment = treatment;
	}


	public Reports(Integer report_id) {
		super();
		this.report_id = report_id;
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


	@Override
	public int hashCode() {
		return Objects.hash(medicalHistory, report_id, sponsor, treatment);
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
		return Objects.equals(medicalHistory, other.medicalHistory) && Objects.equals(report_id, other.report_id)
				&& Objects.equals(sponsor, other.sponsor) && Objects.equals(treatment, other.treatment);
	}


	@Override
	public String toString() {
		return "Reports [report_id=" + report_id + ", medicalHistory=" + medicalHistory + ", treatment=" + treatment
				+ ", sponsor=" + sponsor + "]";
	}
	

}
