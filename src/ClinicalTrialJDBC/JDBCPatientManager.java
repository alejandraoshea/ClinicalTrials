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
				String medicalHistory = rs.getString("medialHistory");
				String treatment = rs.getString("treatment");
				
				Reports report = new Reports(id, medicalHistory, treatment);
				patient.getReports().add(report);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reports;
	}


	
	@Override
	public void applyToClinicalTrial(Integer trial_id, Patient patient) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO trialApplication (patient_id, trial_id, dateRequest)"
					+ "VALUES (?,?,GETDATE());";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setInt(1, patient.getPatient_id());
			prep.setInt(2, trial_id);

			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	

}
