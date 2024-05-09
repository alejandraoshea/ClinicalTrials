package ClinicalTrialInterfaces;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.InvestigationalProduct;
import java.util.List;

public interface EngineerManager {
	public void createEngineer(Engineer engineer);
	public void createInvPr(Integer investigationalProduct_id, Integer trial_id);
	public List<Engineer> getListOfEnginners();
	public void updateInvPr(Integer investigationalProduct_id, Integer trial_id, String newDescription);
	public List<InvestigationalProduct> getInvPr(Integer investigationalProduct_id, Integer trial_id);

	
}
