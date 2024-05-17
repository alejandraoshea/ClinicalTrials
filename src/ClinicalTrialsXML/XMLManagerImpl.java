package ClinicalTrialsXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.DoctorManager;
import ClinicalTrialInterfaces.EngineerManager;
import ClinicalTrialInterfaces.PatientManager;
import ClinicalTrialInterfaces.SponsorManager;
import ClinicalTrialInterfaces.XMLManager;
import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCPatientManager;
import ClinicalTrialJDBC.JDBCDoctorManager;
import ClinicalTrialJDBC.JDBCEngineerManager;
import ClinicalTrialJDBC.JDBCSponsorManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Sponsor;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Trial;


public class XMLManagerImpl implements XMLManager{
	JDBCManager manager;
	DoctorManager doctormanager; 
	PatientManager patientmanager; 
	AdministratorManager adminmanager;
	SponsorManager sponsormanager;
    EngineerManager engineermanager;
    
	@Override
	public void doctor2xml(Integer id) {
		// TODO Auto-generated method stub
		Doctor doctor = null;
		List<Patient> patients = new ArrayList<Patient>();
		manager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(manager);
		adminmanager = new JDBCAdministratorManager(manager);
		
		try {
			doctor = doctormanager.searchDoctorById(id);
			//search for the patients of the doctor
			patients = adminmanager.getPatients();
					
			JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Doctor.xml");
			marshaller.marshal(doctor, file);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public Doctor xml2Doctor(File xml) {
		// TODO Auto-generated method stub
		Doctor doctor = null;
		manager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(manager);
		
		//read doctor from xml file
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Doctor.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		doctor = (Doctor) unmarshaller.unmarshal(xml);
		
		doctormanager.createDoctor(doctor);
		//we will add it to the database
	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return doctor; 
		
	}

	@Override
	public void admin2xml(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Administrator xml2Admin(File xml) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void patient2xml(Integer id) {
		// TODO Auto-generated method stub
		Patient patient = null;
		manager = new JDBCManager();
		patientmanager = new JDBCPatientManager(manager);
		
		try {
			patient = patientmanager.searchPatientById(id);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Patient.xml");
			marshaller.marshal(patient, file);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	}

	@Override
	public Patient xml2Patient(File xml) {
		// TODO Auto-generated method stub
		Patient patient = null;
		manager = new JDBCManager();
		patientmanager = new JDBCPatientManager(manager);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			patient = (Patient) unmarshaller.unmarshal(xml);
			patientmanager.createPatient(patient);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patient;
		
	}

	@Override
	public void sponsor2xml(Integer id) {
		// TODO Auto-generated method stub
		Sponsor s = null;
		manager = new JDBCManager();
		sponsormanager = new JDBCSponsorManager(manager);
		
			
		try {
			//Do a sql query to get the sponsor by the id
			s = sponsormanager.searchSponsorById(id);

			//export the owner to an xml file
			JAXBContext jaxbContext = JAXBContext.newInstance(Sponsor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("Sponsor.xml");
			marshaller.marshal(s, file);
			System.out.print(s);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Sponsor xml2Sponsor(File xml) {
		// TODO Auto-generated method stub
		
		return null;
		
	}
	

	@Override
	public void engineer2xml(Integer id) {
		// TODO Auto-generated method stub
		Engineer engineer = null;
	    manager = new JDBCManager();
	    engineermanager = new JDBCEngineerManager(manager);
	    List<InvestigationalProduct> invPr = new ArrayList<InvestigationalProduct>();
	    
	    try {
	        
	    	invPr = engineermanager.getListInvPrOfEngineer(id);
			engineer = engineermanager.searchEngineerById(id);
			engineer.getInvestigationalProduct().addAll(invPr);
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(Engineer.class);
	        Marshaller marshaller = jaxbContext.createMarshaller();
	       
	        File file = new File("./xmls/Engineer.xml");
	        marshaller.marshal(engineer, file);
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	

	@Override
	public Engineer xml2Engineer(File xml) {
		// TODO Auto-generated method stub
		Engineer engineer = null;
	    manager = new JDBCManager();
	    engineermanager = new JDBCEngineerManager(manager);
	    
	    try {
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(Engineer.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        
	        
	        engineer = (Engineer) unmarshaller.unmarshal(xml);
	        
	        engineermanager.createEngineer(engineer);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return engineer;
	    
	}

}
