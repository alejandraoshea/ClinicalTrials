package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Engineer")
@XmlType(propOrder = {"name", "phone", "investigationalProducts"})

public class Engineer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3712979237155921233L;
	
	@XmlTransient
	private Integer engineer_id;
	@XmlElement
	private String name; 
	@XmlAttribute
	private String email;
	@XmlElement
	private Integer phone;
	@XmlElement
	private List<InvestigationalProduct> investigationalProduct;
	
	
	
	
	
	public Engineer() {
		super();
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
	}



	public Engineer(Integer engineer_id, String name, String email, Integer phone) {
		super();
		this.engineer_id = engineer_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
	}
	
	
	
	public Engineer(String name, String email, Integer phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
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
	
	public List<InvestigationalProduct> getInvestigationalProduct() {
		return investigationalProduct;
	}


	public void setInvestigationalProduct(List<InvestigationalProduct> investigationalProduct) {
		this.investigationalProduct = investigationalProduct;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, engineer_id, investigationalProduct, name, phone);
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
				&& Objects.equals(investigationalProduct, other.investigationalProduct)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone);
	}


	@Override
	public String toString() {
		return "Engineer [engineer_id=" + engineer_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", investigationalProduct=" + investigationalProduct + "]";
	}
		
}
