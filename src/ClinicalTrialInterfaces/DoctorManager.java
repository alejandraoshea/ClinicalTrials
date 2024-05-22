package ClinicalTrialInterfaces;

import java.util.List;

import clinicaltrialsPOJO.Doctor;
import clinicaltrialsPOJO.InvestigationalProduct;
import clinicaltrialsPOJO.Patient;
import clinicaltrialsPOJO.Reports;


public interface DoctorManager {
	public void createDoctor(Doctor doctor);
	public void assignDoctorToPatient(Integer patient_id, Integer doctor_id);
	public List<Doctor> getListOfDoctors();
	public void updateSpeciality(Integer doctor_id, String newSpeciality);
	public void createReport(Reports report);
	public void assignReportToPatient(Integer report_id, Integer patient_id);
	public List<InvestigationalProduct> getlistInvProd(); 
	public InvestigationalProduct chooseInvProductById(Integer investigationalProduct_id,Integer doctor_id);
	public Doctor searchDoctorById(Integer doctor_id);
	public Doctor searchDoctorByEmail(String email);
}
