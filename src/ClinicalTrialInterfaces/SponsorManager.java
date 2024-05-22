package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;
import clinicaltrialsPOJO.Trial;

public interface SponsorManager {

	public void createSponsor(Sponsor sponsor);
	public void createInvestment(Integer trial_id, Integer sponsor_id, Integer amountMoney);
	public void updateInvestment(Integer trial_id, Integer sponsor_id, Integer moneyInvested);
	public List<Reports> getReportsOfAPatient(Integer patient_id);
	public List<Sponsor> getListOfSponsor();
	public List<Trial> getListOfTrials();
	public Sponsor searchSponsorById(Integer sponsor_id);
	public Sponsor searchSponsorByEmail(String email);
	
	
}
