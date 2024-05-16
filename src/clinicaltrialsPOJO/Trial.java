package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7385326989948946572L;
	
	private Integer trial_id;
	private String requirements;
	private Integer totalAmountInvested;
	private List<Patient> patients;
	private List<TrialsApplication> trialsApplications; 
	private List<InvestigationalProduct> investigationalProduct;
	private List<Sponsor> sponsor;
	
	
	
	
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




	@Override
	public int hashCode() {
		return Objects.hash(investigationalProduct, patients, requirements, sponsor, totalAmountInvested, trial_id,
				trialsApplications);
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
		return Objects.equals(investigationalProduct, other.investigationalProduct)
				&& Objects.equals(patients, other.patients) && Objects.equals(requirements, other.requirements)
				&& Objects.equals(sponsor, other.sponsor)
				&& Objects.equals(totalAmountInvested, other.totalAmountInvested)
				&& Objects.equals(trial_id, other.trial_id)
				&& Objects.equals(trialsApplications, other.trialsApplications);
	}




	@Override
	public String toString() {
		return "Trial [trial_id=" + trial_id + ", requirements=" + requirements + ", totalAmountInvested="
				+ totalAmountInvested + ", patients=" + patients + ", trialsApplications=" + trialsApplications
				+ ", investigationalProduct=" + investigationalProduct + ", sponsor=" + sponsor + "]";
	}
	
	
	
	
	
	
	
	
}