package ClinicalTrialJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
private Connection c = null;

public JDBCManager() {

	/*try {
		
		
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	catch(ClassNotFoundException e) {
		System.out.print("Libraries not loaded");
	}
}
*/
	
	private void createTables() {
		/*try {
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}			
		}*/
	}

	public Connection getConnection() {
		return c;
	}

	public void disconnect(){
		try {
			c.close();
		}
		catch(SQLException e){ 
			e.printStackTrace();
		}		
	}
	
}

