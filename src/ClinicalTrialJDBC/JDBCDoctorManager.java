package ClinicalTrialJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.DoctorManager;
import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Reports;

public class JDBCDoctorManager implements DoctorManager{
	private JDBCManager manager;
	
	public JDBCDoctorManager (JDBCManager m) {
		this.manager = m;
	}
	
	
	@Override
	public void createDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO doctor (name,"
					+ "phone, email, specialization)"
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, doctor.getName());
			prep.setInt(2, doctor.getPhone());
			prep.setString(3, doctor.getEmail());
			prep.setString(4, doctor.getSpecialization());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void assignDoctorToPatient(Integer patient_id, Integer doctor_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE patient SET doctor_id = ? WHERE id = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, doctor_id);
			prep.setInt(2, patient_id);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void updateSpeciality(Integer doctor_id, String newSpecialization) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE doctor SET specialization= ? WHERE id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, newSpecialization);
			prep.setInt(2, doctor_id);
			prep.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();	
		}
		
	}
	
	

	@Override
	public void createReport(Reports report) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO report (medicalHistory,treatment)"
					+ "VALUES (?,?);";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, report.getMedicalHistory());
			prep.setString(2, report.getTreatment());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void assignReportToPatient(Integer report_id, Integer patient_id, Integer doctor_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE report SET patient_id = ? WHERE id = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, patient_id);
			prep.setInt(2, report_id);
			prep.executeUpdate();
			
			
			String sql2 = "UPDATE report SET doctor_id = ? WHERE id = ?;";
			PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
			
			prep2.setInt(1, doctor_id);
			prep2.setInt(2, report_id);
			prep2.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<InvestigationalProduct> getlistInvProd() {
		List<InvestigationalProduct> invProducts= new ArrayList<InvestigationalProduct>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM investigationalProduct";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Integer amountMoney = rs.getInt("amountMoney");
				InvestigationalProduct invProduct = new InvestigationalProduct(id, amountMoney, description, type);
				invProducts.add(invProduct);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invProducts;
	}

	
	@Override
	public InvestigationalProduct chooseInvProductById(Integer investigationalProduct_id, Integer doctor_id, Integer trial_id){
		// TODO Auto-generated method stub
		InvestigationalProduct invP = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM investigationalProduct WHERE id=" + investigationalProduct_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
			Integer invPr_id = rs.getInt("id");
			String description = rs.getString("description");
			String type = rs.getString("type");
			Integer amountMoney = rs.getInt("amountMoney");
			
		    invP = new InvestigationalProduct(invPr_id, amountMoney, description, type);
		    
		    System.out.println("The invP is: " + invP);
		    
		    
		    String sql2 = "INSERT INTO investigationalProductChosen(invProduct_id, trial_id)"
		    		+ "VALUES(?,?);";
		    PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
			
			prep2.setInt(1, investigationalProduct_id);
			prep2.setInt(2, trial_id);
			prep2.executeUpdate();
			
			String sql3 = "UPDATE investigationalProduct SET doctor_id = ? WHERE id = ?";
		    PreparedStatement prep3 = manager.getConnection().prepareStatement(sql3);
			
			prep3.setInt(1, doctor_id);
			prep3.setInt(2, investigationalProduct_id);
			prep3.executeUpdate();
			}
		    
			rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return invP;
	}



	@Override
	public List<Doctor> getListOfDoctors() {
		// TODO Auto-generated method stub
		List<Doctor> doctors= new ArrayList<Doctor>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				String specialization = rs.getString("specialization");
				Doctor doctor = new Doctor (id, name, email, phone, specialization);
				doctors.add(doctor);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return doctors;
	}
	
	@Override
	public Doctor searchDoctorById(Integer id) {
		// TODO Auto-generated method stub
		Doctor doctor = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer doctor_id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Integer phone = rs.getInt("phone");
			String specialization = rs.getString("specialization");
			
		    doctor = new Doctor (doctor_id, name, email, phone, specialization);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return doctor;
	}
	
	
	@Override
	public Doctor searchDoctorByEmail(String email) {
		// TODO Auto-generated method stub
		Doctor doctor =  null; 
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM doctor WHERE email= \"" + email + "\"";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String mail = rs.getString("email");
				Integer phone = rs.getInt("phone");
				String specialization = rs.getString("specialization");
				
			    doctor = new Doctor (id, name, mail, phone, specialization);
			}
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return doctor;
	}


	@Override
	public void updateCuredState(Integer patient_id, Boolean newCured) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE patient SET cured = ? WHERE id =?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setBoolean(1, newCured);
			prep.setInt(2, patient_id);
			prep.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public List<Patient> getListOfMyPatients(Integer doctor_id) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE doctor_id = " + doctor_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer patient_id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				Date date = rs.getDate("dateOfBirth");
				String bloodT = rs.getString("bloodType");
				String disease = rs.getString("nameOfDisease");
				Boolean cured = rs.getBoolean("cured");
				Patient patient = new Patient (patient_id, name, email, phone, date, bloodT, disease, cured);
				patients.add(patient);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return patients;
	}


	@Override
	public Integer searchDoctorIDByIdPatient(Integer patientId) {
		// TODO Auto-generated method stub
		Integer doctorId = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT doctor_id FROM patient WHERE id=" + patientId;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				doctorId = rs.getInt("doctor_id");
			}
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return doctorId;
	}
	
}
