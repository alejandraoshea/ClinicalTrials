package ClinicalTrialJDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClinicalTrialInterfaces.DoctorManager;
import clinicaltrialsPOJO.Doctor;

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
	public void updateSpeciality(Integer doctor_id, String newSpeciality) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignReportToPatient(Integer report_id, Integer patient_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void assignInvProdToPatient(Integer investigationalProduct_id, Integer patient_id) {
		// TODO Auto-generated method stub
		
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
	
}
