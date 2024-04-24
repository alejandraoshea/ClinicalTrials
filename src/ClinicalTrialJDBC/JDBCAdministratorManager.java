package ClinicalTrialJDBC;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialJDBC.JDBCManager;
import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Trial;

public class JDBCAdministratorManager implements AdministratorManager{

private JDBCManager manager;
	
	public JDBCAdministratorManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void createAdmin(Administrator admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createTrial(Trial trial) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAmountInvested(Integer trial_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAcceptancePatient(Integer patient_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignPatientToTrial(Integer patient_id, Integer trial_id) {
		// TODO Auto-generated method stub
		
	}
	
}
