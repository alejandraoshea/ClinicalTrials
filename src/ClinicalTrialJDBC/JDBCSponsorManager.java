package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.SponsorManager;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Reports;
import clinicaltrialsPOJO.Sponsor;
import clinicaltrialsPOJO.Trial;

public class JDBCSponsorManager implements SponsorManager{

	private JDBCManager manager;
	
	public JDBCSponsorManager (JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void createSponsor(Sponsor sponsor) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO sponsor (name,"
					+ "phone, email, cardNumber)"
					+ "VALUES (?,?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, sponsor.getName());
			prep.setInt(2, sponsor.getPhone());
			prep.setString(3, sponsor.getEmail());
			prep.setInt(4, sponsor.getCardNumber());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void createInvestment(Integer trial_id, Integer sponsor_id, Integer amountMoney) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO invest (sponsor_id, trial_id, amountOfMoneyInvested) VALUES (?,?,?);";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, sponsor_id);
			prep.setInt(2, trial_id);
			prep.setInt(3, amountMoney);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void updateInvestment(Integer trial_id, Integer sponsor_id, Integer amountMoney) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE invest SET amountOfMoneyInvested =? WHERE trial_id =? AND sponsor_id =?";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setInt(1, amountMoney);
			prep.setInt(2, trial_id);
			prep.setInt(3, sponsor_id);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	

	@Override
	public List<Reports> getReportsOfAPatient(Integer patient_id) {
		// TODO Auto-generated method stub
		List<Reports> reports= new ArrayList<Reports>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM report WHERE patient_id =?"+ patient_id;
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
	public List<Sponsor> getListOfSponsor() {
		// TODO Auto-generated method stub
		List<Sponsor> sponsors= new ArrayList<Sponsor>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM sponsor";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				Integer cardNumber = rs.getInt("cardNumber");
				Sponsor sponsor = new Sponsor(id, name, email, phone, cardNumber);
				sponsors.add(sponsor);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sponsors;
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
	public Sponsor searchSponsorById(Integer sponsor_id) {
		// TODO Auto-generated method stub
		Sponsor sponsor = null;	
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM sponsor WHERE id=" + sponsor_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer doctor_id = rs.getInt("id");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Integer phone = rs.getInt("phone");
			Integer cardNumber = rs.getInt("cardNumber");
		    sponsor = new Sponsor (doctor_id, name, email, phone, cardNumber);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {
			e.printStackTrace();}
		return sponsor;
	}
	
	
	
	@Override
	public Sponsor searchSponsorByEmail(String email) {
		// TODO Auto-generated method stub
		Sponsor s = null; 
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM sponsor WHERE email= \"" + email + "\"";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer id = rs.getInt("id");
			String name = rs.getString("name");
			String mail = rs.getString("email");
			Integer phone = rs.getInt("phone");
			Integer cN = rs.getInt("cardNumber");
			
		    s = new Sponsor (id, name, mail, phone, cN);
		    
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return s;
	}
}
