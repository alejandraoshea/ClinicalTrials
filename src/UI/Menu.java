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
	
		// test
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
		
		if(r.getName() == "administrator") {
			createAdmin(email);
		}else if(r.getName() == "doctor") {
			createDoctor(email);
		}else if(r.getName() == "patient") {
			createPatient(email);
		}else if(r.getName() == "sponsor") {
			createSponsor(email);
		}else if(r.getName() == "engineer") {
			createEngineer(email);
		}
		
		User u = new User(email, pass, r);
		usermanager.newUser(u);
	
	}catch(Exception e){
		e.printStackTrace();
		}
}



	//admin menu:
	private static void adminMenu(String email) {

		try{
			int choice; 
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new Clinical Trial"); 
				System.out.println("2. Add a new administrator"); 
				System.out.println("3. Print all the administrators in DB");
				System.out.println("4. Show the amount invested in a Clinical Trial"); 
				System.out.println("5. Update Acceptance Patient");
				System.out.println("6. Assign Patient to Trial"); 
				System.out.println("7. Delete a Patient of a Trial"); 
				System.out.println("8. Print all the patients of a Clinical Trial");
				System.out.println("0. Return.\n"); 
         
				choice = Integer.parseInt(reader.readLine()); 
				 
				switch(choice) {
				case 1: 
					createTrial();
					break; 
				case 2: 
					addNewAdmin();
					break; 
				case 3: 
					getListOfAdmins(); 
					break; 
				case 4: 
					getAmountInvested(); 
					break; 
				case 5: 
					updateAcceptancePatient(); 
					break; 
				case 6: 
					assignPatientToTrial(); 
					break; 
				case 7: 
					deletePatientFromTrial(); 
					break; 
				case 8: 
					getAllPatientsCT(); 
					break; 
				case 0: 
					System.out.println("Back to main menu"); 
				}
			} while (choice!=0); 
			
		}catch(Exception e) {
			e.printStackTrace(); 
		}

}

	private static void createTrial() throws Exception{
		System.out.println("Type the requirements of the trial\n"); 
		String requirements = reader.readLine(); 
		System.out.println("Type the amount of money invested\n");
		Integer moneyInv = Integer.parseInt(reader.readLine());
		Trial trial = new Trial(requirements, moneyInv); 
		adminmanager.createTrial(trial); 
	}
	
	
	private static void createAdmin(String email) throws Exception{
		System.out.println("Type the name of the admistrator\n"); 
		String name = reader.readLine(); 
		System.out.println("Type the phone of the admistrator\n");
		Integer phone = Integer.parseInt(reader.readLine());
		Administrator admin = new Administrator(name, email, phone); 
		adminmanager.createAdmin(admin); 
	}
	
	
	private static void addNewAdmin() throws Exception{
		System.out.println("Type the name of the admistrator\n"); 
		String name = reader.readLine(); 
		System.out.println("Type the email of the admistrator\n");
		String email = reader.readLine();
		System.out.println("Type the phone of the admistrator\n");
		Integer phone = Integer.parseInt(reader.readLine());
		Administrator admin = new Administrator(name, email, phone); 
		adminmanager.createAdmin(admin); 
	}
	
	private static void getListOfAdmins() throws Exception{
		List<Administrator> admins = null; 
		admins = adminmanager.getListOfAdmins(); 
		System.out.println(admins); 

		}
	
	private static void updateAcceptancePatient(Integer patient_id) {
		Patient patient = patientmanager.searchPatientById(patient_id); 
		patientmanager.getStateRequest(patient_id);
	}
	
	private static Integer getAmountInvested() throws Exception{
		System.out.println("Type the id of the trial\n");
		Integer id = Integer.parseInt(reader.readLine());
		Trial trial = adminmanager.getTrialByID(id);
		Integer amountInv = trial.getTotalAmountInvested();
		return amountInv;
	}
	
	
	
	private static void updateAcceptancePatient()  throws Exception{
		System.out.println("Introduce the patient id : \n"); 
		Integer patient_id = Integer.parseInt(reader.readLine());
		Patient patient = patientmanager.searchPatientById(patient_id);
		adminmanager.updateAcceptancePatient(patient_id);
	}
	

	private static void assignPatientToTrial() throws Exception{
		System.out.println("Introduce the patient id: \n"); 
		Integer patient_id = Integer.parseInt(reader.readLine()); 
		System.out.println("Introduce the trial id: \n"); 
		Integer trial_id = Integer.parseInt(reader.readLine()); 
		Patient patient = patientmanager.searchPatientById(patient_id);
		Trial trial = adminmanager.getTrialByID(trial_id);
		trial.getPatients().add(patient);		
	}
	
	private static void deletePatientFromTrial() throws Exception{
		System.out.println("Introduce the patient id: \n"); 
		Integer patient_id = Integer.parseInt(reader.readLine()); 
		adminmanager.deletePatientbyId(patient_id);
	}
	
	
	
	private static void getAllPatientsCT() throws Exception {
		System.out.println("Type the id of the clinical trial\n");
		Integer trial_id = Integer.parseInt(reader.readLine());
		List<Patient> patientsTrial = null;
		patientsTrial = adminmanager.getPatientsOfTrial(trial_id);
		System.out.println(patientsTrial);
	}
	

	
	

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
				addNewDoctor();
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
				getAllReportsPatient();
				break;
				
			case 8:
				getAllInvProd();
				InvestigationalProduct invP = chooseInvProductById();
				invP.toString();
				break;
				
			case 0:
				System.out.println("Back to main menu");
				
			}
			
		}while(choice!=0);
		
	}catch(Exception e){
		e.printStackTrace();
	}
}




	private static void createDoctor(String email) throws Exception{
		System.out.println("Type the name of the doctor\n");
		String name = reader.readLine();
		System.out.println("Type the phone of the doctor\n");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the specialization of the doctor\n");
		String specialization = reader.readLine();
		
		Doctor doctor = new Doctor(name, phone, email, specialization);
		doctormanager.createDoctor(doctor);
	}
	
	
	
	private static void addNewDoctor() throws Exception{
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
		
	public static void assignDoctorToPatient(Integer patient_id, Integer doctor_id) {
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
	}
	
	public static void createReport() throws Exception{
		System.out.println("Introduce the medical History: \n");
		String medicalHistory = reader.readLine();
		System.out.println("Introduce the treatment: \n");
		String treatment = reader.readLine();
		Reports report = new Reports(medicalHistory, treatment);
		doctormanager.createReport(report);
	}
	
	
	public static void getAllReportsPatient() throws Exception{
		List<Reports> reports = null;
		System.out.println("Introduce the patient id: \n");
		Integer patient_id = Integer.parseInt(reader.readLine());
		Patient patient = patientmanager.searchPatientById(patient_id);
		reports = patient.getReports();
		reports.toString();
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
			//menu
			
			try {
				int choice;
				do {
					System.out.println("Choose an option");
					System.out.println("1. Apply to a Clinical Trial.");
					System.out.println("2. Get the state of request of a patient.");
					System.out.println("3. Print all the reports of a patient.");
					System.out.println("4. Print all the clinical trials.");
					System.out.println("0. Return.\n");	
					
					choice = Integer.parseInt(reader.readLine());
					
					switch(choice){
					case 1: 
						applyToCT();
						break;
					case 2:
						getStateRequest();
						break;
					
					case 3:
						getAllReportsPatient();
						break;
					
					case 4:
						getAllClinicalTrials();
						break;
						
					case 0:
						System.out.println("Back to main menu");
						
					}
					
				}while(choice!=0);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		
		}
		
		
		
		//patient methods:
		private static void createPatient(String email) throws Exception {
			System.out.println("Type the name of the patient\n");
			String name = reader.readLine();
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
		
		
	
		private static void applyToCT() throws Exception {
			System.out.println("Type the name of the patient\n");
			String name = reader.readLine();
			System.out.println("Type the email of the patient\n");
			String email = reader.readLine();
			System.out.println("Type the phone of the doctor\n");
			Integer phone = Integer.parseInt(reader.readLine());
			System.out.println("Type the date of birth");
			Date dateOfBirth = Date.valueOf(reader.readLine());

			Patient patient = new Patient(name, email, phone, dateOfBirth);
			
			
			System.out.println("Type the id of the clinical trial");
			Integer trial_id = Integer.parseInt(reader.readLine());
			
			patientmanager.applyToClinicalTrial(trial_id, patient);
			
		}
		
		
		private static void getStateRequest() throws Exception {
			System.out.println("Type the id of the patient");
			Integer patient_id = Integer.parseInt(reader.readLine());
			
			boolean state = patientmanager.getStateRequest(patient_id);
			System.out.println("The state of the request of the patient is: " + state);
		}
		
		
		private static void getAllClinicalTrials() throws Exception {
			List<Trial> trials = null;
			trials = adminmanager.getListOfTrials();
			
			System.out.println(trials);
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
					System.out.println("4. Create an investment.");
					System.out.println("5. Update an investment.");
					System.out.println("6. Print all the reports of a trial.");
					System.out.println("0. Return.\n");	
						
					choice = Integer.parseInt(reader.readLine());
						
					switch(choice){
					case 1: 
						addNewSponsor();
						break;
					case 2:
						getListOfTrials();
						break;
					
					case 3:
						getListOfSponsor();
						break;
						
					case 4:
						createInvestment();
						break;
						
					case 5:
						updateInvestment();
						break;
						
					case 6:
						getListReportsOfPatient();
						break;
						
					
					case 0:
						System.out.println("Back to main menu");
							
					}
				}while(choice!=0);
				}catch(Exception e){
					e.printStackTrace();}
		}
			
		
		//sponsor methods:
		private static void createSponsor(String email) throws Exception {
			System.out.println("Type the name of the sponsor\n");
			String name = reader.readLine();
			System.out.println("Type the phone of the sponsor\n");
			Integer phone = Integer.parseInt(reader.readLine());			
			System.out.println("Type the card number");
			Integer cardnumber = Integer.parseInt(reader.readLine());
			
			Sponsor sponsor = new Sponsor(name, email, phone, cardnumber);
			sponsormanager.createSponsor(sponsor);
		}
		
		
		private static void addNewSponsor() throws Exception {
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
			List<Trial> trials = null;
			trials= sponsormanager.getListOfTrials();
			System.out.println(trials);
		}
		
		
		
		private static void getListOfSponsor() throws Exception{
			List<Sponsor> sponsor = null;
			sponsor = sponsormanager.getListOfSponsor();
			System.out.println(sponsor);
		}
		
		
		public static void createInvestment() throws Exception{
			System.out.println("Type the id of the trial\n");
			Integer trial_id = Integer.parseInt(reader.readLine());
			System.out.println("Type the id of the sponsor\n");
			Integer sponsor_id = Integer.parseInt(reader.readLine());
			System.out.println("Type the amount of money invested\n");
			Integer moneyInv = Integer.parseInt(reader.readLine());
			
			Trial trial = adminmanager.getTrialByID(trial_id);
			Integer amount = trial.getTotalAmountInvested();
			trial.setTotalAmountInvested(amount+moneyInv);
			sponsormanager.createInvestment(trial_id, sponsor_id, moneyInv);
		}
		
		
		public static void updateInvestment() throws Exception{
			System.out.println("Type the id of the trial\n");
			Integer trial_id = Integer.parseInt(reader.readLine());
			System.out.println("Type the id of the sponsor\n");
			Integer sponsor_id = Integer.parseInt(reader.readLine());
			System.out.println("Type the amount of money invested\n");
			Integer moneyInv = Integer.parseInt(reader.readLine());
			
			Trial trial = adminmanager.getTrialByID(trial_id);
			Integer amount = trial.getTotalAmountInvested();
			trial.setTotalAmountInvested(amount+moneyInv);
			sponsormanager.updateInvestment(trial_id, sponsor_id, moneyInv);
		}
		
		
		public static void assignSponsorToTrial(Integer trial_id, Integer sponsor_id)throws Exception {
			Sponsor sponsor = sponsormanager.searchSponsorById(sponsor_id);
			Trial trial = new Trial(trial_id);
			trial.getSponsor().add(sponsor);			
		}

		public static void getListReportsOfPatient() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("Type the id of the patient\n");
			Integer patient_id = Integer.parseInt(reader.readLine());
			
			List<Reports> reports = null;
			reports = sponsormanager.getReportsOfAPatient(patient_id);
			System.out.println(reports);
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
					
					switch(choice){
					case 1: 
						addNewEngineer();
						break;
					case 2:
						getListOfEnginners();
						break;
					
					case 3:
						createInvPr();
						break;
					
					case 4:
						updateInvPr();
						break;
					
					case 5:
						getInvPr();
						break;
					
					case 0:
						System.out.println("Back to main menu");
						
					}
					
				}while(choice!=0);
				
			}catch(Exception e){
				e.printStackTrace();}
		}
		
		
		//engineer methods:
		private static void createEngineer(String email) throws Exception{
			System.out.println("Type the name of the engineer\n");
			String name = reader.readLine();
			System.out.println("Type the phone of the engineer\n");
			Integer phone = Integer.parseInt(reader.readLine());
			
			
			Engineer engineer = new Engineer(name, email, phone);
			engineermanager.createEngineer(engineer);
		}
		
		
		
		private static void addNewEngineer() throws Exception{
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
		
		
		private static void createInvPr() throws Exception{
			System.out.println("Introduce the investigational product id: \n");
			Integer invProduct_id = Integer.parseInt(reader.readLine());
			System.out.println("Introduce the type: \n");
			String type = reader.readLine();
			System.out.println("Introduce a description of the product: \n");
			String description = reader.readLine();
			InvestigationalProduct invPr = new InvestigationalProduct(invProduct_id, description, type);
			engineermanager.createInvPr(invPr);
			
		}
		
		
	    public static void updateInvPr() throws Exception{
			System.out.println("Introduce the investigational product id: \n");
			Integer invProduct_id = Integer.parseInt(reader.readLine());
			System.out.println("Introduce the new description: \n");
			String newDescription = reader.readLine();
			
			engineermanager.updateInvPr(invProduct_id, newDescription);
		}
	    
	    
	    private static void getInvPr() throws Exception {
	    	System.out.println("Introduce the investigational product id: \n");
			Integer invProduct_id = Integer.parseInt(reader.readLine());
			engineermanager.getInvPr(invProduct_id);
			
		}
	
	
}
