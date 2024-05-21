package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialJDBC.JDBCManager;
import clinicaltrialsPOJO.Administrator;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Trial;

public class JDBCAdministratorManager implements AdministratorManager{

private JDBCManager manager;
	
	public JDBCAdministratorManager (JDBCManager m) {
		this.manager = m;
	}

	@Override
	public void createAdmin(Administrator admin) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO administrator (name,"
					+ "phone, email)"
					+ "VALUES (?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, admin.getName());
			prep.setInt(2, admin.getPhone());
			prep.setString(3, admin.getEmail());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
	

	@Override
	public void createTrial(Trial trial) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO trial (requirements,"
					+ "amountMoneyInvestedTotal)"
					+ "VALUES (?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, trial.getRequirements());
			prep.setInt(2, trial.getTotalAmountInvested());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

	@Override
	public Integer getAmountInvested(Integer trial_id) {
		// TODO Auto-generated method stub
		Integer moneyInvested = null; 
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM trial WHERE id=" + trial_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			String requirements = rs.getString("requirements");
			Integer amountMoney = rs.getInt("amountMoneyInvestedTotal");
			
		    Trial trial = new Trial(trial_id, requirements, amountMoney);
		    
		    rs.close();
		    stmt.close();
		    
		    
		    moneyInvested = trial.getTotalAmountInvested();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return moneyInvested;
		
	}

	
	@Override
	public void updateAcceptancePatient(Integer patient_id) {
		try {
			String sql = "UPDATE trialApplication SET approved = ? WHERE patient_id =?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setBoolean(1, true);
			prep.setInt(2, patient_id);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void assignPatientToTrial(Integer patient_id, Integer trial_id) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE patient SET trial_id = ? WHERE id = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, trial_id);
			prep.setInt(2, patient_id);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public List<Administrator> getListOfAdmins() {
		// TODO Auto-generated method stub
		List<Administrator> admins= new ArrayList<Administrator>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrator";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				Administrator admin = new Administrator(id, name, email, phone);
				admins.add(admin);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return admins;
	}
	
	

	@Override
	public Trial getTrialByID(Integer trial_id) {
		// TODO Auto-generated method stub
		Trial trial = null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM trial WHERE id=" + trial_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String requirements = rs.getString("requirements");
			Integer amountMoneyInvested = rs.getInt("amountMoneyInvestedTotal");
			
		    trial = new Trial(id, requirements, amountMoneyInvested);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return trial;
	}

	@Override
	public void deletePatientbyId(Integer patient_id) {
		// TODO Auto-generated method stub
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
	public List<Trial> getListOfTrials() {
		// TODO Auto-generated method stub
		List<Trial> trials= new ArrayList<Trial>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM trial";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String requirements = rs.getString("requirements");
				Integer amountMoney = rs.getInt("amountMoneyInvestedTotal");
				Trial trial = new Trial(id, requirements, amountMoney);
				trials.add(trial);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return trials;
	}
	
	
	@Override
	public List<Patient> getPatientsOfTrial(Integer trial_id) {
		// TODO Auto-generated method stub
		List<Patient> patients = new ArrayList<>();
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient WHERE trial_id=" + trial_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Integer patient_id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
			
				Patient patient = new Patient (patient_id, name, email, phone);
				patients.add(patient);
			}
		  rs.close();
		  stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	
	@Override
	public List<Patient> getPatients() {
		// TODO Auto-generated method stub
		List<Patient> patients = new ArrayList<>();
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Integer patient_id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
			
				Patient patient = new Patient (patient_id, name, email, phone);
				patients.add(patient);
			}
		  rs.close();
		  stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patients;
	}
	
	@Override
	public Administrator searchAdminById(Integer id) {
		// TODO Auto-generated method stub
		Administrator admin = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrator WHERE id=" + id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer admin_id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Integer phone = rs.getInt("phone");
			
		    admin = new Administrator (admin_id, name, email, phone);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return admin;
	}
	
	
	@Override
	public Reports getReportByID(Integer report_id) {
		// TODO Auto-generated method stub
		Reports report = null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM report WHERE id=" + report_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String medicalHistory = rs.getString("medicalHistory");
			String treatment = rs.getString("treatment");
			
		    report = new Reports(id, medicalHistory, treatment);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();
		}
		return report;
	}
	
}
