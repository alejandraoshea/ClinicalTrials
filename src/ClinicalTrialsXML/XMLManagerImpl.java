package ClinicalTrialsXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.DoctorManager;
import ClinicalTrialInterfaces.PatientManager;
import ClinicalTrialInterfaces.XMLManager;
import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCPatientManager;
import ClinicalTrialJDBC.JDBCDoctorManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Patient;


public class XMLManagerImpl implements XMLManager{
	JDBCManager manager;
	DoctorManager doctormanager; 
	PatientManager patientmanager; 
	AdministratorManager adminmanager;

	@Override
	public void doctor2xml(Integer id) {
		// TODO Auto-generated method stub
		Doctor doctor = null;
		List<Patient> patients = new ArrayList<Patient>();
		manager = new JDBCManager();
		doctormanager = new JDBCDoctorManager(manager);
		patientmanager = new JDBCPatientManager(manager);
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
	public void xml2Doctor(File xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void admin2xml(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xml2Admin(File xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void patient2xml(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xml2Patient(File xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sponsor2xml(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xml2Sponsor(File xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void engineer2xml(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xml2Engineer(File xml) {
		// TODO Auto-generated method stub
		
	}

}
