package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.EngineerManager;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.Engineer;
import clinicaltrialsPOJO.InvestigationalProduct;

public class JDBCEngineerManager implements EngineerManager{
	
	private JDBCManager manager;
	
	public JDBCEngineerManager (JDBCManager m) {
		this.manager = m;
	}
	
	@Override
	public void createEngineer(Engineer engineer) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO engineer (name,"
					+ "phone, email)"
					+ "VALUES (?,?,?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, engineer.getName());
			prep.setInt(2, engineer.getPhone());
			prep.setString(3, engineer.getEmail());
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void createInvPr(InvestigationalProduct invP) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO investigationalProduct (description,"
					+ "type, amountMoney, engineer_id, doctor_id)"
					+ "VALUES (?,?,?,?, NULL)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, invP.getDescription());
			prep.setString(2, invP.getType());
			prep.setInt(3, invP.getAmount());
			prep.setInt(4, invP.getEngineer().getEngineer_id());
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void updateInvPr(Integer investigationalProduct_id, String newDescription) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE investigationalProduct SET description = ? WHERE id = ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, newDescription);
			prep.setInt(2, investigationalProduct_id);
			prep.executeUpdate();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public InvestigationalProduct getInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		InvestigationalProduct invPr= null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM investigationalProduct WHERE id="+investigationalProduct_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Integer amountMoney = rs.getInt("amountMoney");
				invPr = new InvestigationalProduct(id, amountMoney, description, type);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invPr;
	}

	
	
	@Override
	public List<Engineer> getListOfEnginners() {
		List<Engineer> engineers= new ArrayList<Engineer>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM engineer";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
				Engineer engineer = new Engineer(id, name, email, phone);
				engineers.add(engineer);
			}
			
			rs.close();
			stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return engineers;
	}
	
	
	@Override
	public Engineer searchEngineerById(Integer engineer_id) {
		Engineer engineer= null;
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM engineer WHERE id=" + engineer_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Integer eng_id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Integer phone = rs.getInt("phone");
			    engineer = new Engineer (eng_id, name, email, phone);
			}
		    rs.close();
		    stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return engineer;
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
	public List<InvestigationalProduct> getListInvPrOfEngineer(Integer engineer_id) {
		List<InvestigationalProduct> invPr = new ArrayList<>();
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM investigationalProduct WHERE engineer_id=" + engineer_id;
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String description = rs.getString("description");
				String type = rs.getString("type");
				Integer amountMoney = rs.getInt("amountMoney");
			
				InvestigationalProduct invP = new InvestigationalProduct(id, amountMoney, description, type);
				invPr.add(invP);
			}
			
		    rs.close();
		    stmt.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return invPr;
	}
	
	
	
	@Override
	public Engineer searchEngineerByEmail(String email) {
		// TODO Auto-generated method stub
		Engineer eng = null;  
		System.out.println(email);
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM engineer WHERE email= \"" + email + "\"";
		
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");	
				String mail = rs.getString("email");
				
			    eng = new Engineer (id, name, mail, phone);
			}
		    rs.close();
		    stmt.close();
		    
		}catch(Exception e) {e.printStackTrace();}
		return eng;
	}
	
	
	
	
}
