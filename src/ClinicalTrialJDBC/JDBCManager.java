package ClinicalTrialJDBC;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
private Connection c = null;
	
	public JDBCManager() {
		
		try {
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			System.out.print("Libraries not loaded");
		}

		private void createTables() {
			try {
				Statement stmt = c.createStatement();
			}catch() {
				
			}
		}
		
				
}
