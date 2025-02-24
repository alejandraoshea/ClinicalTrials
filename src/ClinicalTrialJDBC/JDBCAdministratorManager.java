package ClinicalTrialJDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
			String sql = "SELECT amountMoneyInvestedTotal FROM trial WHERE id=" + trial_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				moneyInvested = rs.getInt("amountMoneyInvestedTotal");
			}

		    rs.close();
		    stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return moneyInvested;
		
	}

	
	@Override
	public void updateAcceptancePatient(Integer id) {
		try {
			String sql = "UPDATE trialApplication SET approved = ? WHERE id =?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setBoolean(1, true);
			prep.setInt(2, id);
			prep.executeUpdate();
			
			String sql2 = "UPDATE trialApplication SET dateApproved = CURRENT_DATE WHERE id =?;";
			PreparedStatement prep2 = manager.getConnection().prepareStatement(sql2);
			
			prep2.setInt(1, id);
			prep2.executeUpdate();
			
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
			
		}catch(Exception e){
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
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
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
				Integer adminId = rs.getInt("admin_id");
				Administrator admin = searchAdminById(adminId);
				Trial trial = new Trial(id, requirements, amountMoney);
				trial.setAdmin(admin);
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
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			while(rs.next()) {
				Integer patient_id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				Date dateStr = rs.getDate("dateOfBirth");
				String bloodT = rs.getString("bloodType");
				String disease = rs.getString("nameOfDisease");
				Boolean cured = rs.getBoolean("cured");
				Patient patient = new Patient (patient_id, name, email, phone, dateStr, bloodT, disease, cured);
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
		List<Patient> patients = new ArrayList<Patient>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM patient";
		
			ResultSet rs = stmt.executeQuery(sql);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			while(rs.next()) {
				Integer patient_id = rs.getInt("id");
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				//String dateStr = rs.getString("dateOfBirth");
				//LocalDate localDob = LocalDate.parse(dateStr, formatter);
				//Date dateOfBirth = Date.valueOf(localDob);
				Date dateOfBirth = rs.getDate("dateOfBirth");
				Boolean cured = rs.getBoolean("cured");
				String bloodT = rs.getString("bloodType");
				String disease = rs.getString("nameOfDisease");
			
				Patient patient = new Patient (patient_id, name, email, phone, dateOfBirth, bloodT, disease, cured);
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
	
	
	@Override
	public Administrator searchAdminByEmail(String email) {
		// TODO Auto-generated method stub
		Administrator admin =  null; 
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM administrator WHERE email= \"" + email + "\"";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String mail = rs.getString("email");
			Integer phone = rs.getInt("phone");
			
		    admin = new Administrator (id, name, mail, phone);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return admin;
	}

	@Override
	public List<Double> getSuccessRateTrial() {
		// TODO Auto-generated method stub
		List<Double> successRate = new ArrayList<Double>(); 		
		
		List<Trial> trials = getListOfTrials();
		List<Integer> trials_id = new ArrayList<Integer>();
		
		for(Trial trial : trials) {
			Integer t_id = trial.getTrial_id();
			trials_id.add(t_id);
		}
		
		try {
			Statement stmt = manager.getConnection().createStatement();

			for(Integer trialID : trials_id) {
				Integer totalPatientsCT = 0;
				Integer curedPatients = 0;
				
				String queryTotalPatients = "SELECT COUNT(*) AS total_patients FROM patient WHERE trial_id = " + trialID;
				ResultSet rsTotal = stmt.executeQuery(queryTotalPatients);
			    if(rsTotal.next()) {
			    	totalPatientsCT = rsTotal.getInt("total_patients");
			    }
			    
			    rsTotal.close();
			    
			    String querycuredPatients = "SELECT COUNT(*) AS curedPatients FROM patient WHERE cured = 1 AND trial_id = " + trialID;
			    ResultSet rsCured = stmt.executeQuery(querycuredPatients);
			    if(rsCured.next()) {
			    	curedPatients = rsCured.getInt("curedPatients");
			    }
			    rsCured.close();
			    
			    
			    if(totalPatientsCT>0) {
			    	Double successRateTrial = ((double) curedPatients/totalPatientsCT)*100;
			    	successRate.add(successRateTrial);
			    }
			    else {
			    	successRate.add(0.0);
			    }
			}

			stmt.close();
			
		}catch(Exception e) {e.printStackTrace();}

		return successRate;
	}
	
	
	
	
	
}
