package ClinicalTrialInterfaces;


import java.io.File;

import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Sponsor;

public interface XMLManager {
	public void doctor2xml(Integer id);
	public Doctor xml2Doctor(File xml);

	public void admin2xml(Integer id);
	public Administrator xml2Admin(File xml);
	
	public void patient2xml(Integer id);
	public Patient xml2Patient(File xml);
	
	public void sponsor2xml(Integer id);
	public Sponsor xml2Sponsor(File xml);
	
	public void engineer2xml(Integer id);
	public Engineer xml2Engineer(File xml);

}