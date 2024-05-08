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
import clinicaltrialsPOJO.Reports;
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
				System.out.println("Choose an option");
				System.out.println("1. Login User");
				System.out.println("2. Sign-up new user");
				System.out.println("0. Exit.");
								
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					login();					
				case 2:
					System.out.println("Add info of new user.");
					signUpUser();
				case 3: 
					System.out.println("Udpate the password of an exissting user.");
					updatePassword();
					
				case 0:
					jdbcmanager.disconnect();
					System.exit(0);
				}
			}while(true);
					
		}catch(Exception e){
			e.printStackTrace();}
	}
	
	
	private static void login() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Email: \n");
		String email = reader.readLine();
		
		System.out.println("Password: \n");
		String passwd = reader.readLine();
		
		User u = usermanager.checkPassword(email, passwd);
		
		if(u!=null & u.getRole().getName().equals("admin")){
			System.out.println("Login of administrator successful!");
			//adminMenu(email);
		}else if(u!=null & u.getRole().getName().equals("doctor")){
			System.out.println("Login of doctor successful!");
			doctorMenu(email);
		}else if(u!=null & u.getRole().getName().equals("patient")){
			System.out.println("Login of patient successful!");
			//patientMenu(email);
		}else if(u!=null & u.getRole().getName().equals("sponsor")){
			System.out.println("Login of sponsor successful!");
			//sponsorMenu(email);
		}else if(u!=null & u.getRole().getName().equals("engineer")){
			System.out.println("Login of engineer successful!");
			//engineerMenu(email);
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
			System.out.println("Login of owner successful!");
			usermanager.changePassword(u, new_passwd);
		}
				
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
			System.out.println("5. Assign report to a Patient.");
			System.out.println("6. Assign Investigational product to a patient.");
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
				System.out.println("Introduce the patient id: \n");
				Integer patient_id = Integer.parseInt(reader.readLine());
				System.out.println("Introduce the doctor id: \n");
				Integer doctor_id = Integer.parseInt(reader.readLine());
				assignDoctorToPatient(patient_id, doctor_id);
				break;
			
			case 4:
				System.out.println("Introduce the new specialty: \n");
				String newSpeciality = reader.readLine();
				System.out.println("Introduce the doctor id: \n");
				Integer doctor_id2 = Integer.parseInt(reader.readLine());
				updateSpeciality(doctor_id2, newSpeciality);
				break;
			
			case 5:
				System.out.println("Introduce the report id: \n");
				Integer report_id = Integer.parseInt(reader.readLine());
				System.out.println("Introduce the doctor id: \n");
				Integer doctor_id3 = Integer.parseInt(reader.readLine());
				System.out.println("Introduce the patient id: \n");
				Integer patient_id2 = Integer.parseInt(reader.readLine());
				assignReportToPatient(report_id, doctor_id3, patient_id2);
				break;
			
			case 6:
				System.out.println("Introduce the investigational product id: \n");
				Integer investigationalProduct_id = Integer.parseInt(reader.readLine());
				System.out.println("Introduce the patient id: \n");
				Integer patient_id3 = Integer.parseInt(reader.readLine());
				assignInvProdToPatient(investigationalProduct_id, patient_id3);
				
			
			case 0:
				System.out.println("Back to main menu");
				
			}
			
		}while(choice!=0);
		
	}catch(Exception e){
		e.printStackTrace();}
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
}
	public static void assignDoctorToPatient(Integer patient_id, Integer doctor_id) {
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		Patient patient = patientmanager.searchPatientById(patient_id);
		
		doctor.getPatients().add(patient);
	}
	
	
	public static void updateSpeciality(Integer doctor_id, String newSpeciality) {
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		doctor.setSpecialization(newSpeciality);
	}
	
	
	public static void assignReportToPatient(Integer doctor_id, Integer report_id, Integer patient_id) {
		Patient patient = patientmanager.searchPatientById(patient_id);
		Doctor doctor = doctormanager.searchDoctorById(doctor_id);
		Reports report = new Reports(report_id);
		doctor.getReports().add(report);
		//verify
	}
	public static void assignReportToPatient(Integer report_id, Integer patient_id) {

	}

	
	public static void assignInvProdToPatient(Integer investigationalProduct_id, Integer patient_id) {
		Patient patient = patientmanager.searchPatientById(patient_id);
		
	}

}
