package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "InvestigationalProduct") 
@XmlType(propOrder = {"amount", "description", "type"})  
public class InvestigationalProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715210654882847041L;
	
	@XmlTransient
	private Integer invProduct_id;
	@XmlElement
	private Integer amount; 
	@XmlElement
	private String description; 
	@XmlElement
	private String type;
	@XmlTransient
	private List<Trial> trial;
	@XmlTransient
	private Engineer engineer; //relation 1 to N
	@XmlTransient
	private Doctor doctor; //relation 1 to N
	 
	
	
	
	
	public InvestigationalProduct() {
		super();
		this.trial = new ArrayList<Trial>();
	}



	public InvestigationalProduct(Integer amount, String description, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.trial = new ArrayList<Trial>();
	}

	
	
	public InvestigationalProduct(Integer invProduct_id, Integer amount, String description, String type) {
		super();
		this.invProduct_id = invProduct_id;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.trial = new ArrayList<Trial>();
	}

	
	
	public InvestigationalProduct(Integer amount, String description, String type, Engineer engineer) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.engineer = engineer;
	}



	public InvestigationalProduct(Integer invProduct_id) {
		super();
		this.invProduct_id = invProduct_id;
	}
	
	
	public Integer getInvProduct_id() {
		return invProduct_id;
	}

	public void setInvProduct_id(Integer invProduct_id) {
		this.invProduct_id = invProduct_id;
	}

	public Integer getAmount() {
		return amount;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


	public List<Trial> getTrial() {
		return trial;
	}



	public void setTrial(List<Trial> trial) {
		this.trial = trial;
	}



	public Engineer getEngineer() {
		return engineer;
	}



	public void setEngineer(Engineer engineer) {
		this.engineer = engineer;
	}



	public Doctor getDoctor() {
		return doctor;
	}



	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}



	@Override
	public int hashCode() {
		return Objects.hash(amount, description, doctor, engineer, invProduct_id, trial, type);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestigationalProduct other = (InvestigationalProduct) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(description, other.description)
				&& Objects.equals(doctor, other.doctor) && Objects.equals(engineer, other.engineer)
				&& Objects.equals(invProduct_id, other.invProduct_id) && Objects.equals(trial, other.trial)
				&& Objects.equals(type, other.type);
	}



	@Override
	public String toString() {
		return "InvestigationalProduct [invProduct_id=" + invProduct_id + ", amount=" + amount + ", description="
				+ description + ", type=" + type + ", trial=" + trial + ", engineer=" + engineer + ", doctor=" + doctor
				+ "]";
	}

	

}
