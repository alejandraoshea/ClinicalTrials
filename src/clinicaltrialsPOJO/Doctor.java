package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 95534687285040408L;
	
	private Integer doctor_id;
	private String name; 
	private String email;
	private Integer phone;
	private String specialization;
	private Integer license; 
	private List<Reports> reports; 
	private List<Patient> patients;
	private List<InvestigationalProduct> investigationalProducts;
	
	
	public Doctor(Integer doctor_id, String name, String email, Integer phone, String specialization, Integer license) {
		super();
		this.doctor_id = doctor_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
		this.license = license;
	}

	
	public Doctor(String name, String email, Integer phone, String specialization, Integer license) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
		this.license = license;
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

	

	
	public Integer getLicense() {
		return license;
	}



	public void setLicense(Integer license) {
		this.license = license;
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
		return Objects.hash(doctor_id, email, investigationalProducts, license, name, patients, phone, reports,
				specialization);
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
				&& Objects.equals(license, other.license) && Objects.equals(name, other.name)
				&& Objects.equals(patients, other.patients) && Objects.equals(phone, other.phone)
				&& Objects.equals(reports, other.reports) && Objects.equals(specialization, other.specialization);
	}



	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", specialization=" + specialization + ", license=" + license + ", reports=" + reports + ", patients="
				+ patients + ", investigationalProducts=" + investigationalProducts + "]";
	}
	
	
	
}
