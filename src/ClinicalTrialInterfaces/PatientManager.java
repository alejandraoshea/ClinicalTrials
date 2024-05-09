package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Patient;


public interface PatientManager {
	public void createPatient(Patient patient);
	public List<Patient> getPatientsOfTrial(Integer trial_id);
	
	public void deletePatientbyId(Integer patient_id);
	
	public void getStateRequest(Integer patient_id);
	public Patient searchPatientById(Integer patient_id);
	
}
