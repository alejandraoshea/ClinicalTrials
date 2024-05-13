package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.DoctorManager;
import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
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
		
	}

	@Override
	public void updateSpeciality(Integer doctor_id, String newSpecialization) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE doctor SET specialization= ? WHERE id= ?;";
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			
			prep.setString(1, newSpecialization);
			prep.setInt(2, doctor_id);
			prep.executeQuery();
		}catch(Exception e){
			e.printStackTrace();	
		}
		
	}
	
	

	@Override
	public void createReport(Reports report) {
		// TODO Auto-generated method stub
		try {
			String sql= "INSERT INTO report (medicalHistory,"
					+ "VALUES (?)";
			
			PreparedStatement prep = manager.getConnection().prepareStatement(sql);
			prep.setString(1, report.getMedicalHistory());
			
			prep.executeUpdate();				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void assignReportToPatient(Integer report_id, Integer patient_id) {
		// TODO Auto-generated method stub
		
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
	public InvestigationalProduct chooseInvProductById(Integer investigationalProduct_id, Integer doctor_id){
		// TODO Auto-generated method stub
		InvestigationalProduct invP = null;
		
		
		try {
			Statement stmt = manager.getConnection().createStatement();
			String sql = "SELECT * FROM investigationalProduct WHERE id=" + investigationalProduct_id;
		
			ResultSet rs = stmt.executeQuery(sql);
			
			Integer invPr_id = rs.getInt("id");
			String description = rs.getString("description");
			String type = rs.getString("type");
			Integer amountMoney = rs.getInt("amountMoney");
			
		    invP = new InvestigationalProduct(invPr_id, amountMoney, description, type);
		    
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

	
}
