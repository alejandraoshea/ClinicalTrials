package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Reports;


public interface PatientManager {
	public void createPatient(Patient patient);
	public void applyToClinicalTrial(Integer admin_id, Integer trial_id, Integer patient_id);
	public boolean getStateRequest(Integer patient_id);
	public Patient searchPatientById(Integer patient_id);
	public List<Reports> getListReportsOfPatient(Patient patient);
	
}
