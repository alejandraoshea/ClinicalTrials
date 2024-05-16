package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Trial;


public interface AdministratorManager {
	public void createAdmin(Administrator admin);
	public void createTrial(Trial trial);
	public List<Administrator> getListOfAdmins();
	public Integer getAmountInvested(Integer trial_id);
	public void updateAcceptancePatient(Integer patient_id);	
	public void assignPatientToTrial(Integer patient_id, Integer trial_id);
	public Trial getTrialByID(Integer trial_id);
	public void deletePatientbyId(Integer patient_id); 
	public List<Trial> getListOfTrials();
	public List<Patient> getPatientsOfTrial(Integer trial_id);
	
	public List<Patient> getPatients();
	

}
