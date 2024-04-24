package ClinicalTrialJDBC;

import java.util.List;

import ClinicalTrialInterfaces.PatientManager;
import clinicaltrialsPOJO.Patient;

public class JDBCPatientManager implements PatientManager{

	private JDBCManager manager;
	
	public JDBCPatientManager (JDBCManager m) {
		this.manager = m;
	}

	
	@Override
	public void addPatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Patient> getPatientsOfTrial(Integer trial_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatientbyId(Integer patient_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getStateRequest(Integer patient_id) {
		// TODO Auto-generated method stub
		
	}
	

}
