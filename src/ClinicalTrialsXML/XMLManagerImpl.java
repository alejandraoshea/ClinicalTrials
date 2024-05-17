package ClinicalTrialsXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.DoctorManager;
import ClinicalTrialInterfaces.PatientManager;
import ClinicalTrialInterfaces.SponsorManager;
import ClinicalTrialInterfaces.XMLManager;
import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCPatientManager;
import ClinicalTrialJDBC.JDBCDoctorManager;
import ClinicalTrialJDBC.JDBCSponsorManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
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
		
	}

	@Override
	public Patient xml2Patient(File xml) {
		// TODO Auto-generated method stub
		return null;
		
	}

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
		
		Integer patient_id = null; 
		List<Reports> reportsPatient = new ArrayList<Reports>();
			
		try {
			//Do a sql query to get the sponsor by the id
			s = sponsormanager.searchSponsorById(id);
		
			trials = sponsormanager.getListOfTrials();
			s.setTrials(trials);
				
			//Como pongo el id de los patients ahi? Como lo busco?
			patients = adminmanager.getPatients();
			
		
			for(Patient patient: patients) {
				patient_id = patient.getPatient_id();
				reportsPatient = sponsormanager.getReportsOfAPatient(patient_id);
				reports.addAll(reportsPatient);
			}
				
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
		
	}

	@Override
	public Engineer xml2Engineer(File xml) {
		// TODO Auto-generated method stub
		return null;
	}

}
