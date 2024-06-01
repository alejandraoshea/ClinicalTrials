package clinicaltrialsPOJO;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ClinicalTrialsXMLutils.SQLDateAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient")
@XmlType(propOrder = {"namePatient", "phonePatient",  "bloodType", "disease", "cured","dateOfBirth"})

public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4568065003661484119L;
	
	@XmlTransient
	private Integer patient_id;
	@XmlAttribute
	private String emailPatient;
	@XmlElement
	private String namePatient; 
	@XmlElement
	private Integer phonePatient;
	@XmlElement
	private Date dateOfBirth; 
	@XmlElement
	private String bloodType;
	@XmlElement
	private String disease;
	@XmlElement
	private boolean cured;
	@XmlTransient
	private byte[] photo;
	@XmlTransient
	private List<Reports> reports;
	@XmlTransient
	private Doctor doctor;  //1 to N relation
	@XmlTransient
	private Trial trial; //1 to N relation
	@XmlTransient
	private List<TrialsApplication> trialApplications; //1 to N relation
	

	
	
	public Patient() {
		super();
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}




	public Patient(Integer patient_id, String email, String namePatient, Integer phonePatient, Date dateOfBirth) {
		super();
		this.patient_id = patient_id;
		this.emailPatient = email;
		this.namePatient = namePatient;
		this.phonePatient = phonePatient;
		this.dateOfBirth = dateOfBirth;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}




	public Patient(Integer patient_id, String namePatient, String email, Integer phonePatient, Date dateOfBirth, String bloodType,
			String disease, boolean cured) {
		super();
		this.patient_id = patient_id;
		this.namePatient = namePatient;
		this.emailPatient = email;
		this.phonePatient = phonePatient;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}
	
	
	
	
	public Patient(String namePatient, String email, Integer phonePatient, Date dateOfBirth, String bloodType, String disease,
			boolean cured, byte[] photo) {
		super();
		this.namePatient = namePatient;
		this.emailPatient = email;
		this.phonePatient = phonePatient;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.photo = photo;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}



	public Patient(Integer patient_id, String namePatient, String email, Integer phonePatient) {
		super();
		this.patient_id = patient_id;
		this.namePatient = namePatient;
		this.emailPatient = email;
		this.phonePatient = phonePatient;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}



	public Patient(String namePatient, String email, Integer phonePatient, Date dateOfBirth, String bloodType, String disease,
			boolean cured) {
		super();
		this.namePatient = namePatient;
		this.emailPatient = email;
		this.phonePatient = phonePatient;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}
	
	
	
	

	public Patient(String namePatient, String email, Integer phonePatient, Date dateOfBirth) {
		super();
		this.namePatient = namePatient;
		this.emailPatient = email;
		this.phonePatient = phonePatient;
		this.dateOfBirth = dateOfBirth;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}




	public Patient(Integer patient_id) {
		super();
		this.patient_id = patient_id;
		this.reports = new ArrayList<Reports>();
		this.trialApplications = new ArrayList<TrialsApplication>();
	}





	public Integer getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}

	
	public String getNamePatient() {
		return namePatient;
	}


	public void setNamePatient(String namePatient) {
		this.namePatient = namePatient;
	}
	
	
	public String getEmailPatient() {
		return emailPatient;
	}


	public void setEmailPatient(String emailPatient) {
		this.emailPatient = emailPatient;
	}



	public Integer getPhonePatient() {
		return phonePatient;
	}


	public void setPhonePatient(Integer phonePatient) {
		this.phonePatient = phonePatient;
	}



	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getBloodType() {
		return bloodType;
	}
	
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	public String getDisease() {
		return disease;
	}
	
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	public boolean isCured() {
		return cured;
	}
	
	public void setCured(boolean cured) {
		this.cured = cured;
	}
	


	public Doctor getDoctor() {
		return doctor;
	}




	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}




	public byte[] getPhoto() {
		return photo;
	}




	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}




	public List<Reports> getReports() {
		return reports;
	}




	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}

	

	public Trial getTrial() {
		return trial;
	}




	public void setTrial(Trial trial) {
		this.trial = trial;
	}


	
	


	public List<TrialsApplication> gettrialApplications() {
		return trialApplications;
	}




	public void settrialApplications(List<TrialsApplication> trialApplications) {
		this.trialApplications = trialApplications;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + Objects.hash(bloodType, cured, dateOfBirth, disease, doctor, emailPatient, namePatient, patient_id,
				phonePatient, reports, trial, trialApplications);
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(bloodType, other.bloodType) && cured == other.cured
				&& Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(disease, other.disease)
				&& Objects.equals(doctor, other.doctor) && Objects.equals(emailPatient, other.emailPatient)
				&& Objects.equals(namePatient, other.namePatient) && Objects.equals(patient_id, other.patient_id)
				&& Objects.equals(phonePatient, other.phonePatient) && Arrays.equals(photo, other.photo)
				&& Objects.equals(reports, other.reports) && Objects.equals(trial, other.trial)
				&& Objects.equals(trialApplications, other.trialApplications);
	}




	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", email=" + emailPatient + ", namePatient=" + namePatient + ", phonePatient=" + phonePatient
				+ ", dateOfBirth=" + dateOfBirth + ", bloodType=" + bloodType + ", disease=" + disease + ", cured="
				+ cured + ", photo=" + Arrays.toString(photo) + ", reports=" + reports + ", doctor=" + doctor
				+ ", trial=" + trial + ", trialApplications=" + trialApplications + "]";
	}


	


}
