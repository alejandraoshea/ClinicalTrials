package ClinicalTrialInterfaces;

import java.io.File;

public interface XMLManager {
	public void doctor2xml(Integer id);
	public void xml2Doctor(File xml);

	public void admin2xml(Integer id);
	public void xml2Admin(File xml);
	
	public void patient2xml(Integer id);
	public void xml2Patient(File xml);
	
	public void sponsor2xml(Integer id);
	public void xml2Sponsor(File xml);
	
	public void engineer2xml(Integer id);
	public void xml2Engineer(File xml);

	

	

	

	
	
	
}
