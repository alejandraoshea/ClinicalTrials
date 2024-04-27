package UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
import ClinicalTrialJDBC.JDBCEngineerManager;

import clinicaltrialsPOJO.*;


public class Menu {

	private static JDBCManager jdbcmanager;
	private static AdministratorManager adminmanager;
	private static DoctorManager doctormanager;
	private static PatientManager patientmanager;
	private static SponsorManager sponsormanager;
	private static EngineerManager engineermanager;
	
	private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	public static void main(String[] args) {
	
		jdbcmanager = new JDBCManager();
		adminmanager = new JDBCAdministratorManager(jdbcmanager); 
		doctormanager = new JDBCDoctorManager(jdbcmanager);
		patientmanager = new JDBCPatientManager(jdbcmanager);
		sponsormanager = new JDBCSponsorManager(jdbcmanager);
		engineermanager = new JDBCEngineerManager(jdbcmanager);
		
		try {
			int choice;
			do {
				System.out.println("Choose an option");
				System.out.println("1. Add a new doctor.");
				System.out.println("2. Print all the doctors in DB.");
				System.out.println("0. Exit.");
				
				choice = Integer.parseInt(reader.readLine());
								
				switch(choice){
				case 1: 
					createDoctor();
					break;
				case 2:
					getAllDoctors();
					
				case 0:
					jdbcmanager.disconnect();
					System.exit(0);
				}
			}while(true);
					
		}catch(Exception e){
			e.printStackTrace();}
	}

	
	private static void createDoctor() throws Exception{
		System.out.println("Type the name of the doctor");
		String name = reader.readLine();
		System.out.println("Type the email of the doctor");
		String email = reader.readLine();
		System.out.println("Type the phone of the doctor");
		Integer phone = Integer.parseInt(reader.readLine());
		System.out.println("Type the specialization of the doctor");
		String specialization = reader.readLine();
		System.out.println("Type the number of the license of the doctor");
		Integer license = Integer.parseInt(reader.readLine());
		
		Doctor doctor = new Doctor(name, email, phone, specialization, license);
		doctormanager.createDoctor(doctor);
	}
	
	
	private static void getAllDoctors() throws Exception{
		List<Doctor> doctors = null;
		doctors = doctormanager.getListOfDoctors();
		
		System.out.println(doctors);
		
	}
	
}
