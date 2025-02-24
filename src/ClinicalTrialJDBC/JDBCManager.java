package ClinicalTrialJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCManager {
private Connection c = null;

public JDBCManager() {
	
	try {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:./db/ClinicalTrialDDBB.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		
		System.out.print("Database Connection opened.");
		this.createTables();		
	
	}catch(SQLException e) {
		e.printStackTrace();
	
	}catch(ClassNotFoundException e) {
		System.out.print("Libraries not loaded");
	}
}


	private void createTables() {
		try {	
			Statement stmt = c.createStatement();
			
			String sql = "CREATE TABLE administrator ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name text NOT NULL, phone INTEGER NOT NULL UNIQUE, "
					+ "email TEXT NOT NULL;";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE doctor  ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL, phone INTEGER NOT NULL UNIQUE"
					+ "email TEXT NOT NULL, specialization TEXT CHECK(specialization IN ('neurosurgeon', 'investigation')));";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE patient("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, phone INTEGER NOT NULL UNIQUE, "
					+ "email TEXT NOT NULL, "
					+ "dateOfBirth DATE NOT NULL, cured BOOLEAN NOT NULL, "
					+ "bloodType TEXT CHECK (bloodtype IN ('A+', 'A-','B+', 'B-', 'AB+', 'AB-','O+', 'O-')), "
					+ "nameOfDisease TEXT NOT NULL, "
					+ "doctor_id INTEGER REFERENCES doctor(id) ON DELETE CASCADE, trial_id REFERENCES trial(id) ON DELETE CASCADE,"
					+ "trialApplication_id INTEGER REFERENCES trialApplication(id) ON DELETE CASCADE,"
					+ "foto BLOB);";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE engineer(id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL UNIQUE, email TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE sponsor(id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "name TEXT NOT NULL, "
					+ "phone INTEGER NOT NULL UNIQUE, email TEXT NOT NULL,"
					+ "cardNumber INTEGER NOT NULL, reports INTEGER);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE investigationalProduct("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "description TEXT NOT NULL, "
					+ "type TEXT CHECK (type in ('drug', 'machine')), "
					+ "engineer_id INTEGER REFERENCES engineer(id), doctor_id INTEGER REFERENCES doctor(id),"
					+ "amountMoney INTEGER NOT NULL);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE report("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "medicalHistory TEXT NOT NULL, "
					+ "doctor_id INTEGER REFERENCES doctor(id), patient_id INTEGER REFERENCES patient(id),"
					+ "treatment TEXT NOT NULL);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE trialApplication("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "admin_id INTEGER REFERENCES administrator(id),"
					+ "trial_id INTEGER REFERENCES trial(id), approved BOOLEAN, "
					+ "dateRequest DATE NOT NULL, dateApproved DATE);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE trial("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "requirements TEXT NOT NULL, "
					+ "amountMoneyInvestedTotal INTEGER NOT NULL, "
					+ "admin_id INTEGER REFERENCES administrator(id);"; 
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE invest("
					+ "sponsor_id INTEGER REFERENCES sponsor(id), "
					+ "trial_id INTEGER REFERENCES trial(id) , "
					+ "amountOfMoneyInvested INTEGER NOT NULL);";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE checkReport("
					+ "report_id INTEGER REFERENCES report(id), "
					+ "sponsor_id INTEGER REFERENCES sponsor(id));";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE checkReport(report_id INTEGER REFERENCES report(id), "
					+ "sponsor_id INTEGER REFERENCES sponsor(id));";
			stmt.executeUpdate(sql);
			
			
			sql = "CREATE TABLE investigationalProductChosen("
					+ "invProduct_id INTEGER, "
					+ "trial_id	INTEGER,"
					+ "PRIMARY KEY(invProduct_id,trial_id),"
					+ "FOREIGN KEY(invProduct_id) REFERENCES investigationalProduct(id),"
					+ "FOREIGN KEY(trial_id) REFERENCES trial(id));";
			stmt.executeUpdate(sql);
			
		}catch(SQLException e) {
			if(!e.getMessage().contains("already exists")) 
			{
				e.printStackTrace();
			}			
		}
		
	}
	
	public Connection getConnection() {
		return c;
	}

	
	public void disconnect(){
		try {
			c.close();
		}catch(SQLException e){ 
			e.printStackTrace();
		}		
	}
	
}

