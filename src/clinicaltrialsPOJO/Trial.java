package clinicaltrialsPOJO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Trial implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7385326989948946572L;
	
	private Integer trial_id;
	private Integer totalAmountInvested;
	private List<Trial> patients;
	private List<TrialsApplication> trialsApplications; 
	private List<InvestigationalProduct> investigationalProduct;
	private List<Sponsor> sponsor;
	
	
	
	
	public Trial(Integer trial_id, Integer totalAmountInvested) {
		super();
		this.trial_id = trial_id;
		this.totalAmountInvested = totalAmountInvested;
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


	@Override
	public int hashCode() {
		return Objects.hash(totalAmountInvested, trial_id);
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
		return Objects.equals(totalAmountInvested, other.totalAmountInvested)
				&& Objects.equals(trial_id, other.trial_id);
	}


	@Override
	public String toString() {
		return "Trial [trial_id=" + trial_id + ", totalAmountInvested=" + totalAmountInvested + "]";
	}


	public List<Trial> getPatients() {
		return patients;
	}


	public void setPatients(List<Trial> patients) {
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
	
	

}
