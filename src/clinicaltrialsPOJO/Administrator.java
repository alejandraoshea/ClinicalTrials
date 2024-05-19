package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

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
@XmlRootElement(name = "Admin")
@XmlType(propOrder = {"name", "phone", "trials"})  
public class Administrator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5699187109494451255L;

	@XmlTransient
	private Integer admin_id;
	@XmlElement
	private String name; 
	@XmlAttribute
	private String email;
	@XmlElement
	private Integer phone;
	@XmlTransient
	private List<TrialsApplication> trialsApplications;
	@XmlElement 
	private List<Trial> trials;
	
	
	
	public Administrator(Integer admin_id, String name, String email, Integer phone) {
		super();
		this.admin_id = admin_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.trials = new ArrayList<Trial>();
	}
	
	
	
	
	
	public Administrator(String name, String email, Integer phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.trials = new ArrayList<Trial>();
	}





	public Integer getAdmin_id() {
		return admin_id;
	}
	
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
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
	
	
	
	public List<TrialsApplication> getTrialsApplications() {
		return trialsApplications;
	}

	public void setTrialsApplications(List<TrialsApplication> trialsApplications) {
		this.trialsApplications = trialsApplications;
	}

	public List<Trial> getTrials() {
		return trials;
	}

	public void setTrials(List<Trial> trials) {
		this.trials = trials;
	}

	@Override
	public int hashCode() {
		return Objects.hash(admin_id, email, name, phone, trials, trialsApplications);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrator other = (Administrator) obj;
		return Objects.equals(admin_id, other.admin_id) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(phone, other.phone)
				&& Objects.equals(trials, other.trials) && Objects.equals(trialsApplications, other.trialsApplications);
	}

	@Override
	public String toString() {
		return "Administrator [admin_id=" + admin_id + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", trialsApplications=" + trialsApplications + ", trials=" + trials + "]";
	}
	

}
