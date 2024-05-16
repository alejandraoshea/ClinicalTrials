package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor") 
@XmlType(propOrder = {"email", "name", "phone", "specialization", "patients", "investigationalProducts"})  
public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 95534687285040408L;
	
	@XmlTransient
	private Integer doctor_id;
	@XmlElement
	private String name;
	@XmlAttribute
	private String email;
	@XmlElement
	private Integer phone;
	@XmlElement
	private String specialization;
	@XmlTransient
	private List<Reports> reports;
	@XmlElement
	private List<Patient> patients;
	@XmlElement
	private List<InvestigationalProduct> investigationalProducts;
	
	
	public Doctor(Integer doctor_id, String name, String email, Integer phone, String specialization) {
		super();
		this.doctor_id = doctor_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
		this.reports = new ArrayList<Reports>();
		this.patients = new ArrayList<Patient>();
		this.investigationalProducts = new ArrayList<InvestigationalProduct>();		
	}

	
	public Doctor(String name, Integer phone, String email, String specialization) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.specialization = specialization;
		this.reports = new ArrayList<Reports>();
		this.patients = new ArrayList<Patient>();
		this.investigationalProducts = new ArrayList<InvestigationalProduct>();
	}



	public Integer getDoctor_id() {
		return doctor_id;
	}


	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
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


	public String getSpecialization() {
		return specialization;
	}


	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


	public List<Reports> getReports() {
		return reports;
	}


	public void setReports(List<Reports> reports) {
		this.reports = reports;
	}


	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public List<InvestigationalProduct> getInvestigationalProducts() {
		return investigationalProducts;
	}


	public void setInvestigationalProducts(List<InvestigationalProduct> investigationalProducts) {
		this.investigationalProducts = investigationalProducts;
	}




	@Override
	public int hashCode() {
		return Objects.hash(doctor_id, email, investigationalProducts, name, patients, phone, reports, specialization);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(doctor_id, other.doctor_id) && Objects.equals(email, other.email)
				&& Objects.equals(investigationalProducts, other.investigationalProducts)
				&& Objects.equals(name, other.name) && Objects.equals(patients, other.patients)
				&& Objects.equals(phone, other.phone) && Objects.equals(reports, other.reports)
				&& Objects.equals(specialization, other.specialization);
	}


	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", specialization=" + specialization + ", reports=" + reports + ", patients=" + patients
				+ ", investigationalProducts=" + investigationalProducts + "]\n";
	}


	
}
