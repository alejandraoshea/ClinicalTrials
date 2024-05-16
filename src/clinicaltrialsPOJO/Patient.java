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
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Patient") 
@XmlType(propOrder = {"disease", "cured", "bloodType", "dateOfBirth", "name", "email", "phone"})   //possible order
public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4568065003661484119L;
	
	@XmlTransient
	private Integer patient_id;
	
	@XmlAttribute
	private String email;
	@XmlElement
	private String name; 
	@XmlElement
	private Integer phone;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dateOfBirth; 
	@XmlElement
	private String bloodType;
	@XmlElement
	private String disease;
	@XmlElement
	private boolean cured;
	@XmlTransient
	private Blob photo;
	@XmlTransient
	private List<Reports> reports;
	
	
	
	public Patient(Integer patient_id, String name, String email, Integer phone, Date dateOfBirth, String bloodType,
			String disease, boolean cured) {
		super();
		this.patient_id = patient_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.reports = new ArrayList<Reports>();
	}
	
	
	
	
	public Patient(String name, String email, Integer phone, Date dateOfBirth, String bloodType, String disease,
			boolean cured, Blob photo) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.photo = photo;
		this.reports = new ArrayList<Reports>();
	}



	public Patient(Integer patient_id, String name, String email, Integer phone) {
		super();
		this.patient_id = patient_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.reports = new ArrayList<Reports>();
	}



	public Patient(String name, String email, Integer phone, Date dateOfBirth, String bloodType, String disease,
			boolean cured) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.bloodType = bloodType;
		this.disease = disease;
		this.cured = cured;
		this.reports = new ArrayList<Reports>();
	}
	
	
	
	

	public Patient(String name, String email, Integer phone, Date dateOfBirth) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dateOfBirth = dateOfBirth;
		this.reports = new ArrayList<Reports>();
	}




	public Patient(Integer patient_id) {
		super();
		this.patient_id = patient_id;
		this.reports = new ArrayList<Reports>();
	}





	public Integer getPatient_id() {
		return patient_id;
	}
	
	public void setPatient_id(Integer patient_id) {
		this.patient_id = patient_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getPhone() {
		return phone;
	}
	
	public void setPhone(Integer phone) {
		this.phone = phone;
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
	
	
	
	public Blob getphoto() {
		return photo;
	}


	public void setphoto(Blob photo) {
		this.photo = photo;
	}




	public List<Reports> getReports() {
		return reports;
	}




	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}




	@Override
	public int hashCode() {
		return Objects.hash(bloodType, cured, dateOfBirth, disease, email, name, patient_id, phone, photo, reports);
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
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(patient_id, other.patient_id) && Objects.equals(phone, other.phone)
				&& Objects.equals(photo, other.photo) && Objects.equals(reports, other.reports);
	}




	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", dateOfBirth=" + dateOfBirth + ", bloodType=" + bloodType + ", disease=" + disease + ", cured="
				+ cured + ", photo=" + photo + "]";
	}
}