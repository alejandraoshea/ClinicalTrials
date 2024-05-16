package ClinicalTrialsXML;

import java.io.File;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.SponsorManager;
import ClinicalTrialInterfaces.XMLManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCSponsorManager;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;
import clinicaltrialsPOJO.Trial;
import clinicaltrialsPOJO.Patient;

public class XMLManagerImpl implements XMLManager {

	JDBCManager manager;
	SponsorManager sponsormanager;
	AdministratorManager adminmanager;
	
	@Override
	public void sponsor2xml(Integer id) {
		// TODO Auto-generated method stub
		Sponsor s = null;
		List<Trial> trials = new ArrayList<Trial>();
		List<Patient> patients= new ArrayList<Patient>(); 
		List<Reports> reports = new ArrayList<Reports>();
		manager = new JDBCManager();
		sponsormanager = new JDBCSponsorManager(manager);
		adminmanager = new JDBCAdministratorManager(manager);
		
		try {
			//Do a sql query to get the sponsor by the id
			s = sponsormanager.searchSponsorById(id);
	
			trials = sponsormanager.getListOfTrials();
			s.setTrials(trials);
			
			//Como pongo el id de los patients ahi? Como lo busco?
			reports= sponsormanager.getReportsOfAPatient(id);
			
			//export the owner to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Sponsor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Sponsor.xml");
			marshaller.marshal(s, file);
			System.out.print(s);
		}
		
	}
	
	

}
