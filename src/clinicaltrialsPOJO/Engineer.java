package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Engineer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3712979237155921233L;
	
	private Integer engineer_id;
	private String name; 
	private String email;
	private Integer phone;
	private List<InvestigationalProduct> investigationalProduct;
	
	
	
	public Engineer(Integer engineer_id, String name, String email, Integer phone) {
		super();
		this.engineer_id = engineer_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	
	public Integer getEngineer_id() {
		return engineer_id;
	}
	
	public void setEngineer_id(Integer engineer_id) {
		this.engineer_id = engineer_id;
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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(email, engineer_id, name, phone);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Engineer other = (Engineer) obj;
		return Objects.equals(email, other.email) && Objects.equals(engineer_id, other.engineer_id)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
	}
	
	
	@Override
	public String toString() {
		return "Engineer [engineer_id=" + engineer_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ "]";
	}
	
	
	
	
}
