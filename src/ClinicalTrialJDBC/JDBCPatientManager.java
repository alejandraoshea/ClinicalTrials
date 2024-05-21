package ClinicalTrialJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.PatientManager;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Patient;

public class JDBCPatientManager implements PatientManager{

	private JDBCManager manager;
	
	public JDBCPatientManager (JDBCManager m) {
		this.manager = m;
	}

	
	@Override
	public void createPatient(Patient patient) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO patient (name,"
					+ "phone, email, dateOfBirth, cured, bloodType, nameOfDisease)"
					+ "VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, patient.getName());
			prep.setInt(2, patient.getPhone());
			prep.setString(3, patient.getEmail());
			prep.setDate(4, patient.getDateOfBirth());
			prep.setBoolean(5, patient.isCured());
			prep.setString(6, patient.getBloodType());
			prep.setString(7, patient.getDisease());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	
	@Override
	public boolean getStateRequest(Integer patient_id) {
		// TODO Auto-generated method stub
		boolean stateRequest = false;
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT approved FROM trialApplication WHERE patient_id=" + patient_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				stateRequest = rs.getBoolean("approved");
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return stateRequest;
	}
	
	
	@Override
	public Patient searchPatientById(Integer id) {
		// TODO Auto-generated method stub
		Patient patient = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer patient_id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Integer phone = rs.getInt("phone");
			
		    patient = new Patient (patient_id, name, email, phone);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return patient;
	}


	
	
	
	@Override
	public List<Reports> getListReportsOfPatient(Patient patient) {
		// TODO Auto-generated method stub
		List<Reports> reports= new ArrayList<Reports>();
		Integer patient_id = patient.getPatient_id();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM report WHERE patient_id=" + patient_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String medicalHistory = rs.getString("medicalHistory");
				String treatment = rs.getString("treatment");
				Reports report = new Reports(id, medicalHistory, treatment);
				reports.add(report);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reports;
	}


	
	@Override
	public void applyToClinicalTrial(Integer admin_id, Integer trial_id, Integer id) { 
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO trialApplication (admin_id, trial_id, dateRequest)"
					+ "VALUES (?,?,CURRENT_DATE);";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, admin_id);
			prep.setInt(2, trial_id);
			prep.executeUpdate();
			
			
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p = manager.getConnection().prepareStatement(query);
			ResultSet rs = p.executeQuery();
			Integer lastId = rs.getInt("lastId");
			
			
			String updateQuery= "UPDATE patient SET trialApplication_id=? WHERE id=?;";
			PreparedStatement prep2 = manager.getConnection().prepareStatement(updateQuery);
			prep2.setInt(1, lastId);
			prep2.setInt(2, id);
			prep2.executeUpdate();
			
			
			rs.close();
			prep.close();
			p.close();
			prep2.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	@Override
	public Patient searchPatientByEmail(String email) {
		// TODO Auto-generated method stub
Patient patient = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE email=" + email;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer patient_id = rs.getInt("id");
			String name = rs.getString("name");
			Integer phone = rs.getInt("phone");
			
		    patient = new Patient (patient_id, name, email, phone);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return patient;
	}
	
	
	

}
