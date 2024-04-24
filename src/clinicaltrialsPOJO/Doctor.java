package clinicaltrialsPOJO;

import java.io.Serializable;
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
	
	
	public Doctor(Integer doctor_id, String name, String email, Integer phone, String specialization) {
		super();
		this.doctor_id = doctor_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.specialization = specialization;
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


	@Override
	public int hashCode() {
		return Objects.hash(doctor_id, email, name, phone, specialization);
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
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(specialization, other.specialization);
	}


	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", specialization=" + specialization + "]";
	}
	
	
	
	
}
