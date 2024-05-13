package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.util.List;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.DoctorManager;
import ClinicalTrialInterfaces.PatientManager;
import ClinicalTrialInterfaces.SponsorManager;
import ClinicalTrialInterfaces.EngineerManager;

import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import ClinicalTrialJDBC.JDBCDoctorManager;
import ClinicalTrialJDBC.JDBCPatientManager;
import ClinicalTrialJDBC.JDBCSponsorManager;
import ClinicalTrialJPA.JPAUserManager;
import ClinicalTrialJDBC.JDBCEngineerManager;
import ClinicalTrialInterfaces.UserManager;


import clinicaltrialsPOJO.*;


public class Menu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager adminmanager;
	private static DoctorManager doctormanager;
	private static PatientManager patientmanager;
	private static SponsorManager sponsormanager;
	private static EngineerManager engineermanager;
	private static UserManager usermanager;
	
	
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) {
	
		jdbcmanager = new JDBCManager();
		adminmanager = new JDBCAdministratorManager(jdbcmanager); 
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		patientmanager = new JDBCPatientManager(jdbcmanager);
		sponsormanager = new JDBCSponsorManager(jdbcmanager);
		engineermanager = new JDBCEngineerManager(jdbcmanager);
		usermanager = new JPAUserManager();
		
		try {
			int choice;
			do {
				System.out.println("Choose an option\n");
				System.out.println("1. Login User\n");
				System.out.println("2. Sign-up new user\n");
				System.out.println("0. Exit.\n");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					login();	
					break;
				case 2:
					System.out.println("Add info of new user\n.");
					signUpUser();
					break;
				case 3: 
					System.out.println("Udpate the password of an existing user.\n");
					updatePassword();
					break;
					
				case 0:
					System.out.println("Exiting application.");
					jdbcmanager.disconnect();
				}
			}while(choice!=0);
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	private static void login() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: \n");
		String email = reader.readLine();
		
		System.out.println("Password: \n");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if(u!=null & u.getRole().getName().equals("administrator")){
			System.out.println("Login of administrator successful!");
			//call for admin menu
			adminMenu(email);
		}else if(u!=null & u.getRole().getName().equals("doctor")){
			System.out.println("Login of doctor successful!");
			//call for doctor menu
			//create doctor 
			doctorMenu(email);
		}else if(u!=null & u.getRole().getName().equals("patient")){
			System.out.println("Login of patient successful!");
			//call for patient menu
			patientMenu(email);
		}else if(u!=null & u.getRole().getName().equals("sponsor")){
			System.out.println("Login of sponsor successful!");
			//call for sponsor menu
			sponsorMenu(email);
		}else if(u!=null & u.getRole().getName().equals("engineer")){
			System.out.println("Login of engineer successful!");
			//call for engineer menu
			engineerMenu(email);
		}
		
	}
	
	
private static void updatePassword() throws Exception {
		
		System.out.println("Email: ");
		String email = reader.readLine();
				
		System.out.println("Enter current Password");
		String passwd = reader.readLine();
		
		System.out.println("Enter new Password");
		String new_passwd = reader.readLine();
				
		User u = usermanager.checkPassword(email, passwd);
				
		if(u!=null){
			System.out.println("Login of user successful!");
			usermanager.changePassword(u, new_passwd);
		}
				
	}



private static void signUpUser() {
	// TODO Auto-generated method stub
	try {
		System.out.println("Introduce email: \n");
		String email = reader.readLine();
		System.out.println("Introduce the password: \n");
		String password = reader.readLine();
		
		MessageDigest md= MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] pass = md.digest();
		
		System.out.println("Introduce the role of the user. 1: Administrator, 2: Doctor, 3. Patient, 4. Sponsor, 5. Engineer ");
		Integer rol = Integer.parseInt(reader.readLine());
		Role r = usermanager.getRole(rol);
		
		User u = new User(email, pass, r);
		usermanager.newUser(u);
	
	}catch(Exception e){
		e.printStackTrace();
		}
}



	//admin menu:
	private static void adminMenu(String email) {
		//menu
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new administrator.");
				System.out.println("2. Add a new trial.");
				System.out.println("3. Print all the administrators in DB.");
				System.out.println("4. Print the amount of money invested in a trial.");
				System.out.println("5. Update the resolution state of a patient (of a clinical trial).");
				//when we update the resolution state, we also update the dateapproved of the patient
				System.out.println("6. Assign patient to a clinical trial.");
				System.out.println("7. Print all the reports of a Patient.");
				System.out.println("8. Choose an investigational products.");
				//System.out.println("9. Modify treatment of a patient.");
				System.out.println("0. Return.\n");	
				
				choice = Integer.parseInt(reader.readLine());
				
				switch(choice){
				case 1: 
					break;
				case 0:
					System.out.println("Back to main menu");
					
				}
				
			}while(choice!=0);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//admin methods:
	
	
	
	
	

	private static void doctorMenu(String email) {
	// TODO Auto-generated method stub
	try {
		int choice;
		do {
			System.out.println("Choose an option");
			System.out.println("1. Add a new doctor.");
			System.out.println("2. Print all the doctors in DB.");
			System.out.println("3. Assign Doctor to a Patient.");
			System.out.println("4. Update Speciality of a doctor.");
			System.out.println("5. Create a report.");
			System.out.println("6. Assign report to a Patient.");
			System.out.println("7. Print all the reports of a Patient.");
			System.out.println("8. Choose an investigational products.");
			//System.out.println("9. Modify treatment of a patient.");
			System.out.println("0. Return.\n");	
			
			choice = Integer.parseInt(reader.readLine());

			switch(choice){
			case 1: 
				createDoctor();
				break;
			case 2:
				getAllDoctors();
				break;
			
			case 3:
				assignDoctorToPatient();
				break;
			
			case 4:
				updateSpeciality();
				break;
			
			case 5:
				createReport();
				break;
				
			case 6:
				assignReportToPatient();
				break;
			
			case 7:
				System.out.println("Introduce the patient id: \n");
				//patient_id = Integer.parseInt(reader.readLine());
				//get all reports of patient
				break;
				
			case 8:
				getAllInvProd();
				InvestigationalProduct invP = chooseInvProductById();
				break;
				
			case 0:
				System.out.println("Back to main menu");
				
			}
			
		}while(choice!=0);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}




	private static void createDoctor() throws Exception{
		System.out.println("Type the name of the doctor\n");
		String name = reader.readLine();
		System.out.println("Type the email of the doctor\n");
		String email = reader.readLine();
		System.out.println("Type the phone of the doctor\n");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the specialization of the doctor\n");
		String specialization = reader.readLine();
		
		Doctor doctor = new Doctor(name, phone, email, specialization);
		doctormanager.createDoctor(doctor);
	}
	
	
	private static void getAllDoctors() throws Exception{
		List<Doctor> doctors = null;
		doctors = doctormanager.getListOfDoctors();
		System.out.println(doctors);	
	}
	
	
	public static void createReport() throws Exception {
		System.out.println("Introduce the medical History: \n");
		String medicalHistory = reader.readLine();
		System.out.println("Introduce the treatment: \n");
		String treatment = reader.readLine();
		Reports report = new Reports(medicalHistory, treatment);
		doctormanager.createReport(report);
		
	}
	
	
	public static void assignDoctorToPatient() throws Exception{
		System.out.println("Introduce the patient id: \n");
		Integer patient_id = Integer.parseInt(reader.readLine());
		System.out.println("Introduce the doctor id: \n");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		Patient patient = patientmanager.searchPatientById(patient_id);
		doctor.getPatients().add(patient);
	}
	
	
	public static void updateSpeciality() throws Exception{
		System.out.println("Introduce the new specialty: \n");
		String newSpeciality = reader.readLine();
		System.out.println("Introduce the doctor id: \n");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		doctor.setSpecialization(newSpeciality);
	}
	
	
	public static void assignReportToPatient() throws Exception{
		System.out.println("Introduce the report id: \n");
		Integer report_id = Integer.parseInt(reader.readLine());
		System.out.println("Introduce the doctor id: \n");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		System.out.println("Introduce the patient id: \n");
		Integer patient_id = Integer.parseInt(reader.readLine());
		
		Patient patient = patientmanager.searchPatientById(patient_id);
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		Reports report = new Reports(report_id);
		doctor.getReports().add(report);
		patient.getReports().add(report);
	}
	

	public static void getAllInvProd() throws Exception{
		List<InvestigationalProduct> invProducts = null;
		invProducts = doctormanager.getlistInvProd();
		System.out.println(invProducts);
	}
	
	
	public static InvestigationalProduct chooseInvProductById() throws Exception{
		System.out.println("Introduce the doctor id: \n");
		Integer doctor_id = Integer.parseInt(reader.readLine());
		System.out.println("Introduce the investigational product id: \n");
		Integer invPr_id = Integer.parseInt(reader.readLine());
		InvestigationalProduct invPr = new InvestigationalProduct(invPr_id);
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		doctor.getInvestigationalProducts().add(invPr);
		return invPr;
		
	}
	
	
	
	
	
	//patient menu:
		private static void patientMenu(String email) {
			try {
				int choice;
				
				do {
					System.out.println("Choose an option");
					System.out.println("1. Add a new patient.");
					System.out.println("2. Print all the patients of a clinical trial.");
					System.out.println("3. Delete a patient.");
					System.out.println("4. Print state of request of a patient.");
					System.out.println("5. Print all the reports of a patient.");
					System.out.println("0. Return.\n");	
					
					choice = Integer.parseInt(reader.readLine());
					Integer trial_id;
					
					switch(choice){
					case 1: 
						createPatient();
						break;
					case 2:
						//print all patients
						System.out.println("Introduce the trial id: \n");
						trial_id = Integer.parseInt(reader.readLine());
						getAllPatientsOfTrial(trial_id);
						break;
					
					case 3:
						//delete
						break;
					
					case 4:
						//print state of request
						break;
					
					case 5:
						//print all reports
						break;
					
					case 0:
						System.out.println("Back to main menu");
						
					}
					
				}while(choice!=0);
				
			}catch(Exception e){
				e.printStackTrace();}
		}


		
		
		//patient methods:
		private static void createPatient() throws Exception {
			System.out.println("Type the name of the patient\n");
			String name = reader.readLine();
			System.out.println("Type the email of the patient\n");
			String email = reader.readLine();
			System.out.println("Type the phone of the doctor\n");
			Integer phone = Integer.parseInt(reader.readLine());
			System.out.println("Type the date of birth");
			Date dateOfBirth = Date.valueOf(reader.readLine());
			System.out.println("Type the blood type of the patient");
			String bloodType = reader.readLine();
			System.out.println("Type the type of disease of the patient");
			String disease = reader.readLine();
			System.out.println("Type if it's cured or not");
			Boolean cured = Boolean.valueOf(reader.readLine());
			Patient patient = new Patient(name, email, phone, dateOfBirth, bloodType, disease, cured);
			patientmanager.createPatient(patient);
		}
		
		
		public static void getAllPatientsOfTrial(Integer trial_id){
			List<Patient> patients = null;
			patients = patientmanager.getPatientsOfTrial(trial_id);
			System.out.println(patients);		
		}
		
		
		public static void deletePatientbyId(Integer patient_id) {
			
		}
		
		public static void getStateRequest(Integer patient_id) {
			//
		}
		
		
		public static List<Reports> getListReportsOfPatient(Patient patient_id){
			//search patient by id
			return null;
			
		}
		
		
		
		//sponsor menu:
		private static void sponsorMenu(String email) {
			//menu
			try {
				int choice;
				do {
					System.out.println("Choose an option");
					System.out.println("1. Add a new sponsor.");
					System.out.println("2. Print all the trials");
					System.out.println("3. Print all the sponsors.");
					System.out.println("4. Update an investment.");
					System.out.println("5. Print all the reports of a trial.");
					System.out.println("6. Assign a sponsor to a trial.");
					System.out.println("0. Return.\n");	
						
					choice = Integer.parseInt(reader.readLine());
						
					switch(choice){
					case 1: 
						createSponsor();
						break;
					case 2:
						getListOfTrials();
						break;
					
					case 3:
						getListOfSponsor();
						break;
						
					case 4:
						//update
						//updateInvestment(Integer trial_id, Integer sponsor_id)
						break;
						
					case 5:
						//print reports
						//List<Reports> getReportsOfAPatient(Integer patient_id)
						break;
						
					case 6:
						//assign sponsor to a trial
					
					case 0:
						System.out.println("Back to main menu");
							
					}
				}while(choice!=0);
				}catch(Exception e){
					e.printStackTrace();}
		}
			
		
		//sponsor methods:
		private static void createSponsor() throws Exception {
			System.out.println("Type the name of the sponsor\n");
			String name = reader.readLine();
			System.out.println("Type the email of the sponsor\n");
			String email = reader.readLine();
			System.out.println("Type the phone of the sponsor\n");
			Integer phone = Integer.parseInt(reader.readLine());			
			System.out.println("Type the card number");
			Integer cardnumber = Integer.parseInt(reader.readLine());
			
			Sponsor sponsor = new Sponsor(name, email, phone, cardnumber);
			sponsormanager.createSponsor(sponsor);
		}
		
		
		private static void getListOfTrials() throws Exception{
			//add
		}
		
		
		private static void getListOfSponsor() throws Exception{
			//add
		}
		
		public void updateInvestment(Integer trial_id, Integer sponsor_id) {
			// TODO Auto-generated method stub
			
		}
		
		public void assignSponsorToTrial(Integer trial_id, Integer sponsor_id) {
			// TODO Auto-generated method stub
			
		}
		
		public List<Reports> getListReportsOfPatient(Integer patient_id) {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		
		
		//engineer menu:
		private static void engineerMenu(String email) {
			try {
				int choice;
				do {
					System.out.println("Choose an option");
					System.out.println("1. Add a new engineer.");
					System.out.println("2. Print all the engineers in DB.");
					System.out.println("3. Add a new Investigational product to a trial.");
					System.out.println("4. Update an investigational product of a trial.");
					System.out.println("5. Print all the investigational products of a trial.");
					System.out.println("0. Return.\n");	
					
					choice = Integer.parseInt(reader.readLine());
					Integer invProduct_id;
					
					switch(choice){
					case 1: 
						createEngineer();
						break;
					case 2:
						getListOfEnginners();
						break;
					
					case 3:
						System.out.println("Introduce the investigational product id: \n");
						invProduct_id = Integer.parseInt(reader.readLine());
						createInvPr(invProduct_id);
						break;
					
					case 4:
						System.out.println("Introduce the investigational product id: \n");
						invProduct_id = Integer.parseInt(reader.readLine());
						System.out.println("Introduce the new description: \n");
						String newDescription = reader.readLine();
						updateInvPr(invProduct_id, newDescription);
						break;
					
					case 5:
						System.out.println("Introduce the investigational product id: \n");
						invProduct_id = Integer.parseInt(reader.readLine());
						getInvPr(invProduct_id);
						break;
					
					case 0:
						System.out.println("Back to main menu");
						
					}
					
				}while(choice!=0);
				
			}catch(Exception e){
				e.printStackTrace();}
		}
		
		
		//engineer methods:
		private static void createEngineer() throws Exception{
			System.out.println("Type the name of the engineer\n");
			String name = reader.readLine();
			System.out.println("Type the email of the engineer\n");
			String email = reader.readLine();
			System.out.println("Type the phone of the engineer\n");
			Integer phone = Integer.parseInt(reader.readLine());
			
			
			Engineer engineer = new Engineer(name, email, phone);
			engineermanager.createEngineer(engineer);
		}
		
		
		private static void getListOfEnginners() throws Exception {
			List<Engineer> engineers = null;
			engineers = engineermanager.getListOfEnginners();
			System.out.println(engineers);
		}
		
		
		private static void createInvPr(Integer investigationalProduct_id) throws Exception{
			
		}
		
		
	    public static void updateInvPr(Integer investigationalProduct_id, String newDescription) {
	   
		
		}
	    
	    
	    private static void getInvPr(Integer investigationalProduct_id) throws Exception {
			
		}
	
	
	
}
