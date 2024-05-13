package ClinicalTrialJDBC;

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
		
	}

	@Override
	public void createInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateInvPr(Integer investigationalProduct_id, String newDescription) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InvestigationalProduct> getInvPr(Integer investigationalProduct_id) {
		// TODO Auto-generated method stub
		return null;
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
}
