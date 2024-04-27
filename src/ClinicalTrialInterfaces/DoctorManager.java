package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;


public interface DoctorManager {
	public void createDoctor(Doctor doctor);
	public void assignDoctorToPatient(Integer patient_id, Integer doctor_id);
	public List<Doctor> getListOfDoctors();
	public void updateSpeciality(Integer doctor_id, String newSpeciality);
	public void assignReportToPatient(Integer report_id, Integer patient_id);
	public void assignInvProdToPatient(Integer investigationalProduct_id, Integer patient_id);
	
	//public void getDoctorByID
	
	
}
