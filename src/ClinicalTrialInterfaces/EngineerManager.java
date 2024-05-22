package ClinicalTrialInterfaces;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Patient;

import java.util.List;

public interface EngineerManager {
	public void createEngineer(Engineer engineer);
	public void createInvPr(InvestigationalProduct invP);
	public List<Engineer> getListOfEnginners();
	public void updateInvPr(Integer investigationalProduct_id, String newDescription);
	public InvestigationalProduct getInvPr(Integer investigationalProduct_id);	
	public Engineer searchEngineerById(Integer engineer_id);
	public List<InvestigationalProduct> getListInvPrOfEngineer(Integer engineer_id);
	public Engineer searchEngineerByEmail(String email);
}
