package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Trial;


public interface AdministratorManager {
	public void createAdmin(Administrator admin);
	public void createTrial(Trial trial);
	public List<Administrator> getListOfAdmins();
	public void getAmountInvested(Integer trial_id);
	public void updateAcceptancePatient(Integer patient_id);	
	public void assignPatientToTrial(Integer patient_id, Integer trial_id);
	
	//public void getTrialByID o getListOfTrials
	
	
}
