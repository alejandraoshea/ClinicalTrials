package ClinicalTrialsXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
    
    public XMLManagerImpl(JDBCManager m) {
		super();
		this.manager = m;
		this.adminmanager = new JDBCAdministratorManager(manager);
		this.doctormanager = new JDBCDoctorManager(manager);
		this.patientmanager = new JDBCPatientManager(manager);
		this.sponsormanager = new JDBCSponsorManager(manager);
		this.engineermanager = new JDBCEngineerManager(manager);
	}
    
    
	@Override
	public void doctor2xml(Integer id) {
		// TODO Auto-generated method stub
		Doctor doctor = null;
		List<InvestigationalProduct> invPr = new ArrayList<InvestigationalProduct>();
		
		try {
			doctor = doctormanager.searchDoctorById(id);
			invPr = doctormanager.getlistInvProd();
			doctor.setInvestigationalProducts(invPr);
					
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
		Administrator admin = null;
		List<Trial> trials = new ArrayList<Trial>();
		
		try {
			admin = adminmanager.searchAdminById(id);
			trials = adminmanager.getListOfTrials();
			admin.setTrials(trials);
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Administrator.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Admin.xml");
			marshaller.marshal(admin, file);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Administrator xml2Admin(File xml) {
		// TODO Auto-generated method stub
		Administrator admin = null;

		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Administrator.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		admin = (Administrator) unmarshaller.unmarshal(xml);
		adminmanager.createAdmin(admin);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return admin; 

	}

	@Override
	public void patient2xml(Integer id) {
		// TODO Auto-generated method stub
		Patient patient = null;
		Doctor doctor = null; 
		List<InvestigationalProduct> invPr = new ArrayList<InvestigationalProduct>();
		
		try {
			patient = patientmanager.searchPatientById(id);
			
			Integer doctorId = doctormanager.searchDoctorIDByIdPatient(id);
			doctor = doctormanager.searchDoctorById(doctorId);
			
			invPr = doctormanager.getlistInvProd();
			doctor.setInvestigationalProducts(invPr);
			
			patient.setDoctor(doctor);
			
			
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
		Doctor doctor = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			patient = (Patient) unmarshaller.unmarshal(xml);
			doctor = doctormanager.searchDoctorByEmail(patient.getDoctor().getEmail());
			
			if(doctor!=null) {
				patient.setDoctor(doctor);
				patientmanager.createPatient(patient);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patient;
		
	}

	@Override
	public void sponsor2xml(Integer id) {
		// TODO Auto-generated method stub
		Sponsor sponsor = null;		
			
		try {
			sponsor = sponsormanager.searchSponsorById(id);

			JAXBContext jaxbContext = JAXBContext.newInstance(Sponsor.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			File file = new File("./xmls/Sponsor.xml");
			marshaller.marshal(sponsor, file);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Sponsor xml2Sponsor(File xml) {
		// TODO Auto-generated method stub
		Sponsor sponsor = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Sponsor.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			
			sponsor = (Sponsor) unmarshaller.unmarshal(xml);
			sponsormanager.createSponsor(sponsor);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sponsor;
	}
	

	@Override
	public void engineer2xml(Integer id) {
		// TODO Auto-generated method stub
		Engineer engineer = null;
	    List<InvestigationalProduct> invPr = new ArrayList<>();
	    
	    try {
	    	engineer = engineermanager.searchEngineerById(id);
	    	invPr = engineermanager.getListInvPrOfEngineer(id);
			engineer.setInvestigationalProducts(invPr);
			
	        
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
	
	
	@Override
	public void simpleTransform(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
