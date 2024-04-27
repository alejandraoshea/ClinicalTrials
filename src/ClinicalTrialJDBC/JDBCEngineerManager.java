package ClinicalTrialJDBC;

import java.util.List;

import ClinicalTrialInterfaces.EngineerManager;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.InvestigationalProduct;

public class JDBCEngineerManager implements EngineerManager{
	private JDBCManager manager;
	
	public JDBCEngineerManager (JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void createEngineer(Engineer engineer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InvestigationalProduct> getInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Engineer> getListOfEnginners() {
		// TODO Auto-generated method stub
		return null;
	}

}
