package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;

public interface SponsorManager {

	public void createSponsor(Sponsor sponsor);
	public void assignSponsorToTrial(Integer trial_id, Integer sponsor_id);
	
	public void updateInvestment(Integer trial_id, Integer sponsor_id);
	public List<Reports> getReportsOfAPatient(Integer patient_id);
	
	
}
