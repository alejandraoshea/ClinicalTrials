package ClinicalTrialJDBC;

import java.util.List;

import ClinicalTrialInterfaces.SponsorManager;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;

public class JDBCSponsorManager implements SponsorManager{

	private JDBCManager manager;
	
	public JDBCSponsorManager (JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void createSponsor(Sponsor sponsor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignSponsorToTrial(Integer trial_id, Integer sponsor_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInvestment(Integer trial_id, Integer sponsor_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reports> getReportsOfAPatient(Integer patient_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
