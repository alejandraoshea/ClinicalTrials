package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;
import clinicaltrialsPOJO.Trial;

public interface SponsorManager {

	public void createSponsor(Sponsor sponsor);
	public void assignSponsorToTrial(Integer trial_id, Integer sponsor_id);
	public void updateInvestment(Integer trial_id, Integer sponsor_id);
	public List<Reports> getReportsOfAPatient(Integer patient_id);
	public List<Sponsor> getListOfSponsor();
	public List<Trial> getListOfTrials();
	public Sponsor searchSponsorById(Integer sponsor_id);
	
	
}
