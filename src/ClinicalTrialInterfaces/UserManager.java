package ClinicalTrialInterfaces;

import java.util.List;
import clinicaltrialsPOJO.Role;
import clinicaltrialsPOJO.User;

public class UserManager {
	
	public void connect();
	public void disconnect();
	public void newRole(Role r);
	public void newUser(User u);
	public List<Role> getRoles();
	public Role getRole(Integer id);
	public User getUser(String email);
	public User checkPassword(String email, String pass);


}
