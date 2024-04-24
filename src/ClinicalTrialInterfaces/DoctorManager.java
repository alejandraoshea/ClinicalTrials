package ClinicalTrialInterfaces;

import java.util.List;

public interface DoctorManager {

	public void addPet(Pet p);
	public List<Pet> getPetsOfanOwner(Integer owner_id);
	public void assignPetToVet(Integer pet_id, Integer vet_id);
}
