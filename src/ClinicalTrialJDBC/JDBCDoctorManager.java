package ClinicalTrialJDBC;

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

}
