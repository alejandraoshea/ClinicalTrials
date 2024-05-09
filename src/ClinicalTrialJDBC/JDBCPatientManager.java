package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import ClinicalTrialInterfaces.PatientManager;
import clinicaltrialsPOJO.Doctor;
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
	public List<Patient> getPatientsOfTrial(Integer trial_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePatientbyId(Integer patient_id) {
		try {
			String sql = "DELETE FROM patient WHERE id=?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, patient_id);
			
			prep.executeUpdate();			
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@Override
	public void getStateRequest(Integer patient_id) {
		// TODO Auto-generated method stub
		
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
	
	
	

}
