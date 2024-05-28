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
@XmlRootElement(name = "Trial") 
@XmlType(propOrder = {"requirements", "totalAmountInvested"})  
public class Trial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7385326989948946572L;
	
	@XmlTransient
	private Integer trial_id;
	@XmlElement
	private String requirements;
	@XmlElement
	private Integer totalAmountInvested;
	@XmlElement
	private Float rateSuccess; 
	@XmlTransient
	private List<Patient> patients;
	@XmlTransient
	private List<TrialsApplication> trialsApplications; 
	@XmlTransient
	private List<InvestigationalProduct> investigationalProduct;
	@XmlTransient
	private List<Sponsor> sponsor;
	@XmlTransient
	private Administrator admin;
	
	
	
	public Trial() {
		super();
		this.patients = new ArrayList<Patient>();
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
		this.sponsor = new ArrayList<Sponsor>();
	}




	public Trial(Integer trial_id, Integer totalAmountInvested) {
		super();
		this.trial_id = trial_id;
		this.totalAmountInvested = totalAmountInvested;
		this.patients = new ArrayList<Patient>();
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
		this.sponsor = new ArrayList<Sponsor>();
	}
	
	
	
	
	public Trial(String requirements, Integer totalAmountInvested) {
		super();
		this.requirements = requirements;
		this.totalAmountInvested = totalAmountInvested;
		this.patients = new ArrayList<Patient>();
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
		this.sponsor = new ArrayList<Sponsor>();
	}


	


	public Trial(Integer trial_id, String requirements, Integer totalAmountInvested) {
		super();
		this.trial_id = trial_id;
		this.requirements = requirements;
		this.totalAmountInvested = totalAmountInvested;
		this.patients = new ArrayList<Patient>();
		this.trialsApplications = new ArrayList<TrialsApplication>();
		this.investigationalProduct = new ArrayList<InvestigationalProduct>();
		this.sponsor = new ArrayList<Sponsor>();
	}




	public Trial(Integer trial_id) {
		super();
		this.trial_id = trial_id;
	}


	public Integer getTrial_id() {
		return trial_id;
	}


	public void setTrial_id(Integer trial_id) {
		this.trial_id = trial_id;
	}


	public Integer getTotalAmountInvested() {
		return totalAmountInvested;
	}


	public void setTotalAmountInvested(Integer totalAmountInvested) {
		this.totalAmountInvested = totalAmountInvested;
	}


	
	

	public String getRequirements() {
		return requirements;
	}




	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}


	


	public Float getRateSuccess() {
		return rateSuccess;
	}




	public void setRateSuccess(Float rateSuccess) {
		this.rateSuccess = rateSuccess;
	}




	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


	public List<TrialsApplication> getTrialsApplications() {
		return trialsApplications;
	}


	public void setTrialsApplications(List<TrialsApplication> trialsApplications) {
		this.trialsApplications = trialsApplications;
	}


	public List<InvestigationalProduct> getInvestigationalProduct() {
		return investigationalProduct;
	}


	public void setInvestigationalProduct(List<InvestigationalProduct> investigationalProduct) {
		this.investigationalProduct = investigationalProduct;
	}


	public List<Sponsor> getSponsor() {
		return sponsor;
	}


	public void setSponsor(List<Sponsor> sponsor) {
		this.sponsor = sponsor;
	}




	public Administrator getAdmin() {
		return admin;
	}




	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}





	@Override
	public int hashCode() {
		return Objects.hash(admin, investigationalProduct, patients, rateSuccess, requirements, sponsor,
				totalAmountInvested, trial_id, trialsApplications);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trial other = (Trial) obj;
		return Objects.equals(admin, other.admin)
				&& Objects.equals(investigationalProduct, other.investigationalProduct)
				&& Objects.equals(patients, other.patients) && Objects.equals(rateSuccess, other.rateSuccess)
				&& Objects.equals(requirements, other.requirements) && Objects.equals(sponsor, other.sponsor)
				&& Objects.equals(totalAmountInvested, other.totalAmountInvested)
				&& Objects.equals(trial_id, other.trial_id)
				&& Objects.equals(trialsApplications, other.trialsApplications);
	}




	@Override
	public String toString() {
		return "Trial [trial_id=" + trial_id + ", requirements=" + requirements + ", totalAmountInvested="
				+ totalAmountInvested + ", patients=" + patients + ", trialsApplications=" + trialsApplications
				+ ", investigationalProduct=" + investigationalProduct + ", sponsor=" + sponsor + ", admin=" + admin
				+ "]";
	}	

	
}