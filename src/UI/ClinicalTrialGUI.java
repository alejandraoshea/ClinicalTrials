package UI;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ClinicalTrialInterfaces.AdministratorManager;
import ClinicalTrialInterfaces.DoctorManager;
import ClinicalTrialInterfaces.EngineerManager;
import ClinicalTrialInterfaces.PatientManager;
import ClinicalTrialInterfaces.SponsorManager;
import ClinicalTrialInterfaces.UserManager;
import ClinicalTrialInterfaces.XMLManager;
import ClinicalTrialJDBC.JDBCAdministratorManager;
import ClinicalTrialJDBC.JDBCDoctorManager;
import ClinicalTrialJDBC.JDBCEngineerManager;
import ClinicalTrialJDBC.JDBCManager;
import ClinicalTrialJDBC.JDBCPatientManager;
import ClinicalTrialJDBC.JDBCSponsorManager;
import ClinicalTrialJPA.JPAUserManager;
import ClinicalTrialsXML.XMLManagerImpl;
import clinicaltrialsPOJO.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import ClinicalTrialsXMLutils.SQLDateAdapter;


public class ClinicalTrialGUI extends JFrame {
   /**
	 *
	 */
	private static final long serialVersionUID = -5520129674273221802L;
	private static JDBCManager jdbcmanager;
	private static AdministratorManager adminmanager;
	private static DoctorManager doctormanager;
	private static PatientManager patientmanager;
	private static SponsorManager sponsormanager;
	private static EngineerManager engineermanager;
	private static UserManager usermanager;
	private static XMLManager xmlmanager;
   // Icons
   private static final Icon loginIcon = new ImageIcon("path/to/loginIcon.png");
   private static final Icon registerIcon = new ImageIcon("path/to/registerIcon.png");
   private static final Icon logoutIcon = new ImageIcon("path/to/logoutIcon.png");
   private JFrame menuFrame;
   private JTable table;
   private JPanel contentPanel;
  
   public ClinicalTrialGUI() {
       super("Clinical Trial Database");
       jdbcmanager = new JDBCManager();
       adminmanager = new JDBCAdministratorManager(jdbcmanager);
       doctormanager = new JDBCDoctorManager(jdbcmanager);
       patientmanager = new JDBCPatientManager(jdbcmanager);
       sponsormanager = new JDBCSponsorManager(jdbcmanager);
       engineermanager = new JDBCEngineerManager(jdbcmanager);
       usermanager = new JPAUserManager();
       xmlmanager = new XMLManagerImpl();
      
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setPreferredSize(new Dimension(800, 600)); // Set initial size of the frame
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       this.setTitle("Clinical Trial Database");
       JPanel mainPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "40[]20[][][]20[]"));
       mainPanel.setBackground(new Color(167, 192, 189));
       JLabel titleLabel = new JLabel("Clinical Trial Database");
       titleLabel.setFont(new Font("Cambria", Font.BOLD, 35));
       titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
      
       //Login/Register
       JLabel emailLabel = new JLabel("Email:");
       JTextField emailField = new JTextField(20);
       customizeTextField(emailField);
       JLabel passwordLabel = new JLabel("Password:");
       JPasswordField passwordField = new JPasswordField(20);
       customizeTextField(passwordField);
       JButton loginButton = new JButton("Login", loginIcon);
       customizeButton(loginButton);
       loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
               	String email = emailField.getText();
                   String password = new String(passwordField.getPassword());
               	
                   User u = usermanager.checkPassword(email, password);
                  
                  
                   String role = null;
                  
                   if (u==null) {
                      JOptionPane.showMessageDialog(ClinicalTrialGUI.this,
                               "Invalid email or password. Please try again.",
                               "Login Failed", JOptionPane.ERROR_MESSAGE);
                   }
                  
                   if( u!= null && u.getRole().getName().equals("administrator")) {
                   	role = "admin";
                   	showMenu(u, role);
                   	
                   }else if( u!= null && u.getRole().getName().equals("doctor")) {
                   	role = "doctor";
                   	showMenu(u, role);
                   }else if( u!= null && u.getRole().getName().equals("patient")) {
                   	role = "patient";
                   	showMenu(u, role);
                   }else if( u!= null && u.getRole().getName().equals("sponsor")) {
                   	role = "sponsor";
                   	showMenu(u, role);
                   }else if( u!= null && u.getRole().getName().equals("engineer")) {
                   	role = "engineer";
                   	showMenu(u, role);
                   }
                  
                  
               } catch (Exception e1) {
                   e1.printStackTrace();
                   JOptionPane.showMessageDialog(ClinicalTrialGUI.this,
                           "An error occurred during login. Please try again later.",
                           "Login Error", JOptionPane.ERROR_MESSAGE);
               }
           }
       });
       JButton registerButton = new JButton("Register", registerIcon);
       customizeButton(registerButton);
       registerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showUserTypeSelection();
           }
       });
       
       
       JButton changePasswordButton = new JButton("Change Password");
       customizeButton(changePasswordButton);
       changePasswordButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showChangePasswordDialog();
           }
       });
       
       
       mainPanel.add(titleLabel, "span, center, wrap 40");
       mainPanel.add(emailLabel);
       mainPanel.add(emailField, "growx, wrap");
       mainPanel.add(passwordLabel);
       mainPanel.add(passwordField, "growx, gapbottom 30, wrap");
       mainPanel.add(loginButton, "span, center, growx, gapbottom 10, wrap");
       mainPanel.add(registerButton, "span, center, growx, gapbottom 10, wrap");
       mainPanel.add(changePasswordButton, "span, center, growx, wrap");
       getContentPane().add(mainPanel, BorderLayout.CENTER);
       pack();
       setLocationRelativeTo(null);
       setVisible(true);
   }
  
   private static void signUpUser(String email, String password, String name, Map<String, String> additionalInfo) {
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(password.getBytes());
           byte[] pass = md.digest();
           Integer rol = null;
          
           if (name.equals("administrator")) {
               Administrator admin = new Administrator(additionalInfo.get("name"), email, Integer.parseInt(additionalInfo.get("phone")));
               adminmanager.createAdmin(admin);
               rol = 1;
           } else if (name.equals("doctor")) {
               Doctor doctor = new Doctor(additionalInfo.get("name"), Integer.parseInt(additionalInfo.get("phone")), email, additionalInfo.get("specialization"));
               doctormanager.createDoctor(doctor);
               rol = 2;
           } else if (name.equals("patient")) {
               // Create patient logic
           	rol = 3;
           } else if (name.equals("sponsor")) {
               // Create sponsor logic
           	Sponsor sponsor = new Sponsor(additionalInfo.get("name"), email, Integer.parseInt(additionalInfo.get("phone")), Integer.parseInt(additionalInfo.get("cardNumber")));
               sponsormanager.createSponsor(sponsor);
           	rol = 4;
           } else if (name.equals("engineer")) {
           	Engineer enigneer = new Engineer(additionalInfo.get("name"), email, Integer.parseInt(additionalInfo.get("phone")));
               engineermanager.createEngineer(enigneer);
           	rol = 5;
           }
           Role r = usermanager.getRole(rol);
           User u = new User(email, pass, r);
           usermanager.newUser(u);
          
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
  
   private void showUserTypeSelection() {
       JFrame userTypeFrame = new JFrame("Select User Type");
       JPanel userTypePanel = new JPanel(new MigLayout("wrap 1", "[]", "[]20[]"));
       userTypePanel.setBackground(new Color(167, 192, 189));
       JLabel userTypeLabel = new JLabel("Select User Type:");
       userTypePanel.add(userTypeLabel, "wrap");
       JButton adminButton = new JButton("Administrator");
       JButton doctorButton = new JButton("Doctor");
       JButton patientButton = new JButton("Patient");
       JButton sponsorButton = new JButton("Sponsor");
       JButton engineerButton = new JButton("Engineer");
       customizeButton(adminButton);
       customizeButton(doctorButton);
       customizeButton(patientButton);
       customizeButton(sponsorButton);
       customizeButton(engineerButton);
       adminButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showRegistrationDialog("administrator");
               userTypeFrame.dispose();
           }
       });
       doctorButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showRegistrationDialog("doctor");
               userTypeFrame.dispose();
           }
       });
       patientButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showRegistrationDialog("patient");
               userTypeFrame.dispose();
           }
       });
       sponsorButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showRegistrationDialog("sponsor");
               userTypeFrame.dispose();
           }
       });
       engineerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               showRegistrationDialog("engineer");
               userTypeFrame.dispose();
           }
       });
       userTypePanel.add(adminButton);
       userTypePanel.add(doctorButton);
       userTypePanel.add(patientButton);
       userTypePanel.add(sponsorButton);
       userTypePanel.add(engineerButton);
       userTypeFrame.getContentPane().add(userTypePanel);
       userTypeFrame.pack();
       userTypeFrame.setLocationRelativeTo(this);
       userTypeFrame.setVisible(true);
   }
  
  
  
  
   private void showRegistrationDialog(String userType) {
       JFrame registrationDialog = new JFrame("Registration - " + userType);
       JPanel registrationPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "[][][][]20[]"));
       registrationPanel.setBackground(new Color(167, 192, 189));
       JLabel emailLabel = new JLabel("Email:");
       JTextField emailField = new JTextField(20);
       customizeTextField(emailField);
       JLabel passwordLabel = new JLabel("Password:");
       JPasswordField passwordField = new JPasswordField(20);
       customizeTextField(passwordField);
       Map<String, JTextField> additionalFields = new HashMap<>();
       JRadioButton curedYes = null;
       JRadioButton curedNo = null;
       JComboBox<String> bloodTypeComboBox = null;
       
       if (userType.equals("administrator")) {
           JLabel nameLabel = new JLabel("Name:");
           JTextField nameField = new JTextField(20);
           customizeTextField(nameField);
           additionalFields.put("name", nameField);
           JLabel phoneLabel = new JLabel("Phone:");
           JTextField phoneField = new JTextField(20);
           customizeTextField(phoneField);
           additionalFields.put("phone", phoneField);
           registrationPanel.add(nameLabel);
           registrationPanel.add(nameField, "growx, wrap");
           registrationPanel.add(phoneLabel);
           registrationPanel.add(phoneField, "growx, wrap");
          
          
       } else if (userType.equals("doctor")) {
           JLabel nameLabel = new JLabel("Name:");
           JTextField nameField = new JTextField(20);
           customizeTextField(nameField);
           additionalFields.put("name", nameField);
           JLabel phoneLabel = new JLabel("Phone:");
           JTextField phoneField = new JTextField(20);
           customizeTextField(phoneField);
           additionalFields.put("phone", phoneField);
           JLabel specializationLabel = new JLabel("Specialization:");
           JTextField specializationField = new JTextField(20);
           customizeTextField(specializationField);
           additionalFields.put("specialization", specializationField);
           registrationPanel.add(nameLabel);
           registrationPanel.add(nameField, "growx, wrap");
           registrationPanel.add(phoneLabel);
           registrationPanel.add(phoneField, "growx, wrap");
           registrationPanel.add(specializationLabel);
           registrationPanel.add(specializationField, "growx, wrap");
     
       } else if (userType.equals("patient")) {
    	   JLabel nameLabel = new JLabel("Name:");
           JTextField nameField = new JTextField(20);
           customizeTextField(nameField);
           additionalFields.put("name", nameField);
           JLabel phoneLabel = new JLabel("Phone:");
           JTextField phoneField = new JTextField(20);
           customizeTextField(phoneField);
           additionalFields.put("phone", phoneField);

           JLabel doBLabel = new JLabel("Date of Birth:");
           JTextField dobField = new JTextField(20);
           customizeTextField(dobField);
           additionalFields.put("dateOfBirth", dobField);
           
           JLabel bloodTypeLabel = new JLabel("Blood Type:");
           String[] bloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
           bloodTypeComboBox = new JComboBox<>(bloodTypes);
           
           JLabel nameOfDiseaseLabel = new JLabel("Name of Disease:");
           JTextField nameDiseaseField = new JTextField(20);
           customizeTextField(nameDiseaseField);
           additionalFields.put("nameOfDisease", nameDiseaseField);
           
           JLabel curedLabel = new JLabel("Cured:");
           curedYes = new JRadioButton("Yes");
           curedNo = new JRadioButton("No");
           ButtonGroup curedGroup = new ButtonGroup();
           curedGroup.add(curedYes);
           curedGroup.add(curedNo);

           registrationPanel.add(nameLabel);
           registrationPanel.add(nameField, "growx, wrap");
           registrationPanel.add(phoneLabel);
           registrationPanel.add(phoneField, "growx, wrap");
           registrationPanel.add(doBLabel);
           registrationPanel.add(dobField, "growx, wrap");
           registrationPanel.add(bloodTypeLabel);
           registrationPanel.add(bloodTypeComboBox, "growx, wrap");           
           registrationPanel.add(nameOfDiseaseLabel);
           registrationPanel.add(nameDiseaseField, "growx, wrap");
           registrationPanel.add(curedLabel);
           registrationPanel.add(curedYes, "split 2");
           registrationPanel.add(curedNo, "wrap");
          
       } else if (userType.equals("sponsor")) {
           JLabel nameLabel = new JLabel("Name:");
           JTextField nameField = new JTextField(20);
           customizeTextField(nameField);
           additionalFields.put("name", nameField);
           JLabel phoneLabel = new JLabel("Phone:");
           JTextField phoneField = new JTextField(20);
           customizeTextField(phoneField);
           additionalFields.put("phone", phoneField);
           JLabel cardNumberLabel = new JLabel("Card number:");
           JTextField cardNumberField = new JTextField(20);
           customizeTextField(cardNumberField);
           additionalFields.put("cardNumber", cardNumberField);
           registrationPanel.add(nameLabel);
           registrationPanel.add(nameField, "growx, wrap");
           registrationPanel.add(phoneLabel);
           registrationPanel.add(phoneField, "growx, wrap");
           registrationPanel.add(cardNumberLabel);
           registrationPanel.add(cardNumberField, "growx, wrap");
     
       } else if (userType.equals("engineer")) {
           JLabel nameLabel = new JLabel("Name:");
           JTextField nameField = new JTextField(20);
           customizeTextField(nameField);
           additionalFields.put("name", nameField);
           JLabel phoneLabel = new JLabel("Phone:");
           JTextField phoneField = new JTextField(20);
           customizeTextField(phoneField);
           additionalFields.put("phone", phoneField);
           registrationPanel.add(nameLabel);
           registrationPanel.add(nameField, "growx, wrap");
           registrationPanel.add(phoneLabel);
           registrationPanel.add(phoneField, "growx, wrap");
          
       }
      
       JButton registerButton = new JButton("Register");
       customizeButton(registerButton);
       registerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String email = emailField.getText();
               String password = new String(passwordField.getPassword());
               Map<String, String> additionalInfo = new HashMap<>();
               for (Map.Entry<String, JTextField> entry : additionalFields.entrySet()) {
                   additionalInfo.put(entry.getKey(), entry.getValue().getText());
               }
               signUpUser(email, password, userType, additionalInfo);
               registrationDialog.dispose();
           }
       });
       registrationPanel.add(emailLabel);
       registrationPanel.add(emailField, "growx, wrap");
       registrationPanel.add(passwordLabel);
       registrationPanel.add(passwordField, "growx, gapbottom 20, wrap");
       registrationPanel.add(registerButton, "span, center, growx, wrap");
       registrationDialog.getContentPane().add(registrationPanel);
       registrationDialog.pack();
       registrationDialog.setLocationRelativeTo(this);
       registrationDialog.setVisible(true);
   }
  
   
   
   
   private void showChangePasswordDialog() {
	   JFrame changePasswordDialog = new JFrame("Change Password");
	    JPanel changePasswordPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "[][][][]20[]"));
	    changePasswordPanel.setBackground(new Color(167, 192, 189));

	    JLabel emailLabel = new JLabel("Email:");
	    JTextField emailField = new JTextField(20);
	    customizeTextField(emailField);

	    JLabel currentPasswordLabel = new JLabel("Current Password:");
	    JPasswordField currentPasswordField = new JPasswordField(20);
	    customizeTextField(currentPasswordField);

	    JLabel newPasswordLabel = new JLabel("New Password:");
	    JPasswordField newPasswordField = new JPasswordField(20);
	    customizeTextField(newPasswordField);

	    JButton changePasswordButton = new JButton("Change Password");
	    customizeButton(changePasswordButton);
	    changePasswordButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String email = emailField.getText();
	            String currentPassword = new String(currentPasswordField.getPassword());
	            String newPassword = new String(newPasswordField.getPassword());
	            try {
	                usermanager.changePassword(email, newPassword);
	                JOptionPane.showMessageDialog(changePasswordDialog, "Password changed successfully.");
	                changePasswordDialog.dispose();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(changePasswordDialog, "Failed to change password. Please try again.",
	                        "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });

	    changePasswordPanel.add(emailLabel);
	    changePasswordPanel.add(emailField, "growx, wrap 20");
	    changePasswordPanel.add(currentPasswordLabel);
	    changePasswordPanel.add(currentPasswordField, "growx, wrap 20");
	    changePasswordPanel.add(newPasswordLabel);
	    changePasswordPanel.add(newPasswordField, "growx, wrap 20");
	    changePasswordPanel.add(changePasswordButton, "span, center, growx, wrap 20");

	    changePasswordDialog.getContentPane().add(changePasswordPanel);
	    changePasswordDialog.pack();
	    changePasswordDialog.setLocationRelativeTo(this);
	    changePasswordDialog.setVisible(true);
   }
  
   
   private static void customizeButton(JButton button) {
       button.setFont(new Font("Cambria", Font.PLAIN, 18));
       button.setBackground(new Color(75, 75, 75));
       button.setForeground(Color.WHITE);
       button.setFocusPainted(false);
       button.setBorder(new EmptyBorder(10, 20, 10, 20));
       button.setCursor(new Cursor(Cursor.HAND_CURSOR));
   }
   
   
   private static void customizeTextField(JTextField textField) {
       textField.setFont(new Font("Cambria", Font.PLAIN, 18));
       textField.setBorder(BorderFactory.createCompoundBorder(
               BorderFactory.createLineBorder(new Color(173, 150, 149), 1),
               BorderFactory.createEmptyBorder(10, 10, 10, 10)));
   }
   
   private void customizeComboBox(JComboBox<String> comboBox) {
	    comboBox.setFont(new Font("Cambria", Font.PLAIN, 14));
	}
   
   
   
  
   private void showMenu(User u, String role) {
       menuFrame = new JFrame("Clinical Trial Database");
       menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       menuFrame.setPreferredSize(new Dimension(1200, 800));
       menuFrame.setBackground(new Color(167, 192, 189));
       menuFrame.setSize(400,300);
       menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       menuFrame.setVisible(true);
       menuFrame.setLayout(new BorderLayout());
      
      
       JPanel menuPanel = new JPanel(new BorderLayout());
       menuPanel.setBackground(Color.WHITE);
       // Sidebar for actions
       JPanel sidebar = new JPanel(new MigLayout("wrap 1", "[grow]", "[]30[]10[]10[]10[]10[]10[]"));
       sidebar.setBackground(new Color(167, 192, 189));
       sidebar.setPreferredSize(new Dimension(300, menuFrame.getHeight()));
       JLabel homePageLabel = new JLabel("Home page");
       homePageLabel.setHorizontalAlignment(SwingConstants.CENTER);
       homePageLabel.setFont(new Font("Cambria", Font.BOLD, 16));
       sidebar.add(homePageLabel, "grow, wrap");
      
       JButton[] buttons;
       switch (role) {
       	case "admin":
       		buttons = new JButton[]{new JButton("Add a new Clinical Trial"),
       				new JButton("Add a new Administrator"), new JButton("Show all the patients in DB"),
       				new JButton("Show all the admins in DB"), new JButton("Show amount invested"),
       				new JButton("Update acceptance state of a patient"), new JButton("Assign patient to a CT"),
       				new JButton("Delete a patient of a CT"), new JButton("Show all patients of CT"),
       				new JButton("Print admin to xml"), new JButton("Load admin from xml")};
       		break;
          
       	 case "doctor":
                buttons = new JButton[]{new JButton("Add new doctor"), new JButton("Show all the doctors in DB"),
               		 new JButton("Assign doctor to a patient"), new JButton("Update speciality of a doctor"),
               		 new JButton("Create a report"), new JButton("Assign report to patient"),
               		 new JButton("Show all reports of patient"), new JButton("Choose investigational product"),
               		 new JButton("Print doctor to xml"), new JButton("Load doctor from xml")};
                break;
               
           case "patient":
               buttons = new JButton[]{new JButton("Apply to a CT"), new JButton("Get the state of request"),
              		 new JButton("Show all reports of patient"), new JButton("Show all Clinical Trials"),
              		 new JButton("Print patient to xml"), new JButton("Load patient from xml")};
               break;  
              
           case "sponsor":
           	buttons = new JButton[]{new JButton("Add a new sponsor"), new JButton("Show all Clinical Trials"),
                 		 new JButton("Show all the sponsors"), new JButton("Create an investment"),
                 		new JButton("Update an investment"), new JButton("Show all reports of patient"),
                 		 new JButton("Print sponsor to xml"), new JButton("Load sponsor from xml")};
               break;
              
           case "engineer":
           	buttons = new JButton[]{new JButton("Add a new engineer"), new JButton("Show all engineers in DB"),
                 		 new JButton("Add a new Inv Product"), new JButton("Show all Inv Pr"),
                 		 new JButton("Print engineer to xml"), new JButton("Load engineer from xml")};
           	break;
              
           default:
               buttons = new JButton[]{new JButton("Option 1"), new JButton("Option 2"), new JButton("Option 3")};
               break;
       }
       for (JButton button : buttons) {
           customizeButton(button);
           button.setFont(new Font("Cambria", Font.PLAIN, 11));
           sidebar.add(button, "growx, wrap");
           button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String buttonText = button.getText();
	                switch (buttonText) {
	                    case "Add a new Clinical Trial":
	                        addNewClinicalTrial();
	                        break;
	                    case "Add a new Administrator":
	                    	addNewAdministrator();
	                        break;
	                    case "Show all the patients in DB":
	                        showAllPatients();
	                        break;
	                    case "Show all the admins in DB":
	                        showAllAdmins();
	                        break;
	                    case "Show amount invested":
	                        showAmountInvested();
	                        break;
	                    case "Update acceptance state of a patient":
	                        updateAcceptanceState();
	                        break;
	                    case "Assign patient to a CT":
	                        assignPatientToCT();
	                        break;
	                    case "Delete a patient of a CT":
	                        deletePatientOfCT();
	                        break;
	                    case "Show all patients of CT":
	                        showAllPatientsOfCT();
	                        break;
	                    case "Print admin to xml":
	                        printToXML("admin");
	                        break;
	                    case "Load admin from xml":
	                        loadFromXML("admin");
	                        break;
	                    case "Add new doctor":
	                        addNewDoctor();
	                        break;
	                    case "Show all the doctors in DB":
	                        showAllDoctors();
	                        break;
	                    case "Assign doctor to a patient":
	                        assignDoctorToPatient();
	                        break;
	                    case "Update speciality of a doctor":
	                        updateDoctorSpeciality();
	                        break;
	                    case "Create a report":
	                        createReport();
	                        break;
	                    case "Assign report to patient":
	                        assignReportToPatient();
	                        break;
	                    case "Show all reports of patient":
	                        showAllReportsOfPatient();
	                        break;
	                    case "Choose investigational product":
	                        chooseInvestigationalProduct();
	                        break;
	                    case "Print doctor to xml":
	                        printToXML("doctor");
	                        break;
	                    case "Load doctor from xml":
	                        loadFromXML("doctor");
	                        break;
	                    case "Apply to a CT":
	                        applyToCT(u);
	                        break;
	                    case "Get the state of request":
	                        getStateOfRequest();
	                        break;
	                    case "Print patient to xml":
	                        printToXML("patient");
	                        break;
	                    case "Load patient from xml":
	                        loadFromXML("patient");
	                        break;
	                    case "Add a new sponsor":
	                        addNewSponsor();
	                        break;
	                    case "Show all Clinical Trials":
	                        showAllTrials();
	                        break;
	                    case "Show all the sponsors":
	                        showAllSponsors();
	                        break;
	                    case "Create an investment":
	                        createInvestment();
	                        break;
	                    case "Update an investment":
	                        updateInvestment();
	                        break;
	                    case "Print sponsor to xml":
	                        printToXML("sponsor");
	                        break;
	                    case "Load sponsor from xml":
	                        loadFromXML("sponsor");
	                        break;
	                    case "Add a new engineer":
	                        addNewEngineer();
	                        break;
	                    case "Show all engineers in DB":
	                        showAllEngineers();
	                        break;
	                    case "Add a new Inv Product":
	                        addNewInvProduct();
	                        break;
	                    case "Show all Inv Pr of trial":
	                        showAllInvPr();
	                        break;
	                    case "Print engineer to xml":
	                        printToXML("engineer");
	                        break;
	                    case "Load engineer from xml":
	                        loadFromXML("engineer");
	                        break;
	                    default:
	                        System.out.println("Action not defined for this button");
	                        break;
	                }
	            }
           });
       }
      
      
      
       //top bar with search and logout
       JPanel topBar = new JPanel(new MigLayout("fillx", "[grow][]", "[]"));
       topBar.setBackground(new Color(106, 139, 136));
       JTextField searchBar = new JTextField(30);
       customizeTextField(searchBar);
       JButton searchButton = new JButton("Search");
       customizeButton(searchButton);
       topBar.add(searchBar, "growx");
       topBar.add(searchButton, "wrap");
       JButton logoutButton = new JButton("Logout", logoutIcon);
       customizeButton(logoutButton);
       logoutButton.setPreferredSize(new Dimension(120, 40));
       logoutButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               setVisible(true); //we show the login frame
               menuFrame.dispose(); //and then we close the menu frame
           }
       });
       topBar.add(logoutButton, "cell 1 0, growx");
       this.contentPanel = new JPanel(new BorderLayout());
       JLabel welcomeUser = new JLabel();
       ImageIcon welcomeIcon = new ImageIcon("src/resources/WelcomeUserIcon.png");
       welcomeUser.setIcon(welcomeIcon);
       welcomeUser.setHorizontalAlignment(JLabel.CENTER);
       welcomeUser.setVerticalAlignment(JLabel.CENTER);
       contentPanel.add(welcomeUser, BorderLayout.CENTER);
       menuPanel.add(sidebar, BorderLayout.WEST);
       menuPanel.add(topBar, BorderLayout.NORTH);
       menuPanel.add(contentPanel, BorderLayout.CENTER);
       menuFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
       menuFrame.getContentPane().add(menuPanel);
       menuFrame.pack();
       menuFrame.setLocationRelativeTo(null);
       menuFrame.setVisible(true);
   }
   
  
   
 
   private void addNewClinicalTrial() {
   	contentPanel.removeAll();
   	
   	JPanel formPanel = new JPanel();
   	formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
  
   	JLabel requirementsLabel = new JLabel("Requirements:");
       JTextArea requirementsTextArea = new JTextArea(5, 20);
       JScrollPane requirementsScrollPane = new JScrollPane(requirementsTextArea);
       JLabel amountLabel = new JLabel("Amount Invested:");
       JTextField amountTextField = new JTextField(20);
   	
       JButton createButton = new JButton("Create");
       customizeButton(createButton);
      
       createButton.addActionListener(e -> {
           String requirements = requirementsTextArea.getText();
           String amountText = amountTextField.getText();
           try {
               Integer amount = Integer.parseInt(amountText);
              
               Trial trial = new Trial(requirements, amount);
               adminmanager.createTrial(trial);
               JOptionPane.showMessageDialog(menuFrame, "Clinical trial added successfully!");
               requirementsTextArea.setText("");
               amountTextField.setText("");
           } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(menuFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
           }
       });
       formPanel.add(requirementsLabel);
       formPanel.add(requirementsScrollPane, "growx, wrap");
       formPanel.add(amountLabel);
       formPanel.add(amountTextField, "growx, wrap");
       formPanel.add(createButton, "span, align center");
       contentPanel.add(formPanel, BorderLayout.CENTER);
       contentPanel.revalidate();
       contentPanel.repaint();
   	
   }
  
  
   private void addNewAdministrator() {
   	contentPanel.removeAll();
   	JPanel formPanel = new JPanel();
   	formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
  
   	JLabel nameLabel = new JLabel("Name:");
   	JTextField nameField = new JTextField(20);
   	customizeTextField(nameField);
    JLabel phoneLabel = new JLabel("Phone:");
    JTextField phoneField = new JTextField(20);
    customizeTextField(phoneField);
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField(20);
    customizeTextField(emailField);
   	
       JButton createButton = new JButton("Create");
       customizeButton(createButton);
      
       createButton.addActionListener(e -> {
           String name = nameField.getText();
           String phone = phoneField.getText();
           String email = emailField.getText();
          
           try {
               Integer phoneNumber = Integer.parseInt(phone);
               Administrator admin = new Administrator(name, email, phoneNumber);
              
               adminmanager.createAdmin(admin);
           }catch (Exception exp) {
               exp.printStackTrace();
           }
       });
       formPanel.add(nameLabel);
       formPanel.add(nameField, "growx, wrap");
       formPanel.add(phoneLabel);
       formPanel.add(phoneField, "growx, wrap");
       formPanel.add(emailLabel);
       formPanel.add(emailField, "growx, wrap");
       formPanel.add(createButton, "span, align center");
       contentPanel.add(formPanel, BorderLayout.CENTER);
       contentPanel.revalidate();
       contentPanel.repaint();
   }
  
  
   private void showAllPatients() {
   	List<Patient> patients = adminmanager.getPatients();
		String[] columnNames = {"ID", "Name", "Email", "Phone", "Date of birth", "Cured", "Blood Type", "Name of disease"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Patient patient: patients) {
	        Object[] rowData = {
	        	patient.getPatient_id(),
	        	patient.getEmail(),
	        	patient.getPhone(),
	        	patient.getDateOfBirth(),
	        	patient.isCured(),
	        	patient.getBloodType(),
	            patient.getDisease()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
  
   
   private void showAllAdmins() {
		List<Administrator> admins = adminmanager.getListOfAdmins();
		String[] columnNames = {"ID", "Name", "Email", "Phone"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Administrator admin : admins) {
	        Object[] rowData = {
	            admin.getAdmin_id(),
	            admin.getName(),
	            admin.getEmail(),
	            admin.getPhone()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
  
   
   private void showAmountInvested() {
   	List<Trial> trials = adminmanager.getListOfTrials();
   	String[] columnNames = {"Trial ID", "Amount Invested"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for(Trial trial:trials) {
	    	Integer amount = adminmanager.getAmountInvested(trial.getTrial_id());
	    	Object[] rowData = {
   				trial.getTrial_id(),
   	            amount
   	        };
   		 	model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();    	
      
   }
  
   private void updateAcceptanceState() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel();
	    formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][]20[]"));

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    customizeTextField(patientIdField);

	    JButton updateButton = new JButton("Update");
	    customizeButton(updateButton);

	    updateButton.addActionListener(e -> {
	        try {
	            Integer patientId = Integer.parseInt(patientIdField.getText());
	            adminmanager.updateAcceptancePatient(patientId);
	            JOptionPane.showMessageDialog(contentPanel, "Acceptance state updated successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter a valid patient ID!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while updating the acceptance state.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField, "growx");
	    formPanel.add(updateButton, "span, align center");
	    
	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}


   private void assignPatientToCT() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel();
	    formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][]20[]"));

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    customizeTextField(patientIdField);

	    JLabel trialIdLabel = new JLabel("Trial ID:");
	    JTextField trialIdField = new JTextField(20);
	    customizeTextField(trialIdField);

	    JButton assignButton = new JButton("Assign");
	    customizeButton(assignButton);

	    assignButton.addActionListener(e -> {
	        try {
	            Integer patientId = Integer.parseInt(patientIdField.getText());
	            Integer trialId = Integer.parseInt(trialIdField.getText());

	            Patient patient = patientmanager.searchPatientById(patientId);
	            Trial trial = adminmanager.getTrialByID(trialId);

	            if (patient != null && trial != null) {
	                trial.getPatients().add(patient);
	                adminmanager.assignPatientToTrial(patientId, trialId);
	                JOptionPane.showMessageDialog(contentPanel, "Patient assigned to trial successfully!");
	            } else {
	                JOptionPane.showMessageDialog(contentPanel, "Patient or trial not found!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid IDs!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while assigning the patient.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField, "growx");
	    formPanel.add(trialIdLabel);
	    formPanel.add(trialIdField, "growx");
	    formPanel.add(assignButton, "span, align center");
	    
	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
     
   }
   
   
   private void deletePatientOfCT() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    JButton deleteButton = new JButton("Delete");
	    customizeButton(deleteButton);

	    deleteButton.addActionListener(e -> {
	        try {
	            Integer patientId = Integer.parseInt(patientIdField.getText());
	            adminmanager.deletePatientbyId(patientId);
	            JOptionPane.showMessageDialog(contentPanel, "Patient deleted from trial successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter a valid patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while deleting the patient from trial.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField);
	    formPanel.add(deleteButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void showAllPatientsOfCT() {
   	contentPanel.removeAll();
   	
   	JPanel inputPanel = new JPanel();
       inputPanel.setLayout(new FlowLayout());
       JLabel trialIDLabel = new JLabel("Clinical Trial ID:");
       JTextField trialIDField = new JTextField(20);
       customizeTextField(trialIDField);
       JButton getIDButton = new JButton("Get ID");
       customizeButton(getIDButton);
       inputPanel.add(trialIDLabel);
       inputPanel.add(trialIDField);
       inputPanel.add(getIDButton);
       contentPanel.add(inputPanel, BorderLayout.NORTH);
       contentPanel.revalidate();
       contentPanel.repaint();
   	
       getIDButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String trialIDStr = trialIDField.getText();
               try {
                   Integer trial_id = Integer.parseInt(trialIDStr);
                   List<Patient> patients = adminmanager.getPatientsOfTrial(trial_id);
                   if (patients != null && !patients.isEmpty()) {
                       String[] columnNames = {"ID", "Name", "Email", "Phone", "Date of birth", "Cured", "Blood Type", "Name of disease"};
                       DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                       for (Patient patient : patients) {
                           Object[] rowData = {
                               patient.getPatient_id(),
                               patient.getName(),
                               patient.getEmail(),
                               patient.getPhone(),
                               patient.getDateOfBirth(),
                               patient.isCured(),
                               patient.getBloodType(),
                               patient.getDisease()
                           };
                           model.addRow(rowData);
                       }
                       JTable table = new JTable(model);
                       JScrollPane scrollPane = new JScrollPane(table);
                       contentPanel.removeAll();
                       contentPanel.add(inputPanel, BorderLayout.NORTH);
                       contentPanel.add(scrollPane, BorderLayout.CENTER);
                       contentPanel.revalidate();
                       contentPanel.repaint();
                   } else {
                       JOptionPane.showMessageDialog(menuFrame, "No patients found for the given Clinical Trial ID.", "Info", JOptionPane.INFORMATION_MESSAGE);
                   }
               } catch (NumberFormatException ex) {
                   JOptionPane.showMessageDialog(menuFrame, "Please enter a valid Clinical Trial ID.", "Error", JOptionPane.ERROR_MESSAGE);
               } catch (Exception ex) {
                   ex.printStackTrace();
                   JOptionPane.showMessageDialog(menuFrame, "An error occurred while fetching the patients.", "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
       });
   }
  
  
  
   private void addNewDoctor() {
	   contentPanel.removeAll();
	   JPanel formPanel = new JPanel();
	   formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
	  
	   JLabel nameLabel = new JLabel("Name:");
       JTextField nameField = new JTextField(20);
       customizeTextField(nameField);
       JLabel phoneLabel = new JLabel("Phone:");
       JTextField phoneField = new JTextField(20);
       customizeTextField(phoneField);
       JLabel emailLabel = new JLabel("Email:");
       JTextField emailField = new JTextField(20);
       customizeTextField(emailField);
       
       JLabel specializationLabel = new JLabel("Specialization:");
       JTextField specializationField = new JTextField(20);
       customizeTextField(specializationField);
   	
       JButton createButton = new JButton("Create");
       customizeButton(createButton);
      
       createButton.addActionListener(e -> {
       String name = nameField.getText();
       String phone = phoneField.getText();
       String email = emailField.getText();
       String specialization = specializationField.getText();
	          
       try {
           Integer phoneNumber = Integer.parseInt(phone);
           Doctor doctor = new Doctor(name, phoneNumber, email, specialization);
           doctormanager.createDoctor(doctor);
       	}catch (Exception exp) {
           exp.printStackTrace();
       	}
       });
       formPanel.add(nameLabel);
       formPanel.add(nameField, "growx, wrap 20");
       formPanel.add(phoneLabel);
       formPanel.add(phoneField, "growx, wrap 20");
       formPanel.add(emailLabel);
       formPanel.add(emailField, "growx, wrap 20");
       formPanel.add(specializationLabel);
       formPanel.add(specializationField, "growx, wrap 20");
       
       formPanel.add(createButton, "span, align center");
       contentPanel.add(formPanel, BorderLayout.CENTER);
       contentPanel.revalidate();
       contentPanel.repaint();
   }
   
   
   private void showAllDoctors() {
       // Implement the method to show all doctors
	   List<Doctor> doctors = doctormanager.getListOfDoctors();
		String[] columnNames = {"ID", "Name", "Email", "Phone", "Specialization"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Doctor doctor : doctors) {
	        Object[] rowData = {
	            doctor.getDoctor_id(),
	            doctor.getName(),
	            doctor.getEmail(),
	            doctor.getPhone(),
	            doctor.getSpecialization()
	        };
	        model.addRow(rowData);
	    }
	    
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   
   private void assignDoctorToPatient() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel();
	    formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][]20[]"));

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    customizeTextField(patientIdField);

	    JLabel doctorIdLabel = new JLabel("Doctor ID:");
	    JTextField doctorIdField = new JTextField(20);
	    customizeTextField(doctorIdField);

	    JButton assignButton = new JButton("Assign");
	    customizeButton(assignButton);

	    assignButton.addActionListener(e -> {
	        try {
	            Integer patientId = Integer.parseInt(patientIdField.getText());
	            Integer doctorId = Integer.parseInt(doctorIdField.getText());

	            Doctor doctor = doctormanager.searchDoctorById(doctorId);
	            Patient patient = patientmanager.searchPatientById(patientId);

	            if (doctor != null && patient != null) {
	                doctor.getPatients().add(patient);
	                JOptionPane.showMessageDialog(contentPanel, "Doctor assigned to patient successfully!");
	            } else {
	                JOptionPane.showMessageDialog(contentPanel, "Doctor or patient not found!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid IDs!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while assigning the doctor.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField, "growx");
	    formPanel.add(doctorIdLabel);
	    formPanel.add(doctorIdField, "growx");
	    formPanel.add(assignButton, "span, align center");

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   
   private void updateDoctorSpeciality() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

	    JLabel specialityLabel = new JLabel("New Specialty:");
	    JTextField specialityField = new JTextField(20);
	    customizeTextField(specialityField);
	    JLabel doctorIdLabel = new JLabel("Doctor ID:");
	    JTextField doctorIdField = new JTextField(20);
	    customizeTextField(doctorIdField);
	    JButton updateButton = new JButton("Update");
	    customizeButton(updateButton);

	    updateButton.addActionListener(e -> {
	        try {
	            String newSpeciality = specialityField.getText();
	            int doctorId = Integer.parseInt(doctorIdField.getText());
	            doctormanager.updateSpeciality(doctorId, newSpeciality);
	            JOptionPane.showMessageDialog(contentPanel, "Doctor specialty updated successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter a valid doctor ID!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while updating the doctor's specialty.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(specialityLabel);
	    formPanel.add(specialityField);
	    formPanel.add(doctorIdLabel);
	    formPanel.add(doctorIdField);
	    formPanel.add(updateButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void createReport() {
	   contentPanel.removeAll();
	   	
	   	JPanel formPanel = new JPanel();
	   	formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
	  
	   	JLabel medicalHLabel = new JLabel("Medical History:");
	    JTextArea medHTextArea = new JTextArea(5, 20);
	    JScrollPane mHScrollPane = new JScrollPane(medHTextArea);
	    
	    JLabel treatmentLabel = new JLabel("Treatment:");
	    JTextArea treatmentTextArea = new JTextArea(5, 20);
	    JScrollPane trmtScrollPane = new JScrollPane(treatmentTextArea);
	    
	    JLabel docIDLabel = new JLabel("Doctor ID:");
	    JTextField docIDField = new JTextField(20);
	    customizeTextField(docIDField);
	
	    JButton createButton = new JButton("Create");
   	    customizeButton(createButton);
	  
	    createButton.addActionListener(e -> {
	       String medHist = medHTextArea.getText();
	       String treatmentT = treatmentTextArea.getText();
	       String docIDtext = docIDField.getText();
	       try {
	           Integer doctor_id = Integer.parseInt(docIDtext);
	           Doctor doctor = doctormanager.searchDoctorById(doctor_id);
	           Reports report = new Reports(medHist, treatmentT, doctor);
	           doctormanager.createReport(report);
	           JOptionPane.showMessageDialog(menuFrame, "Report added successfully!");
	           
	           medHTextArea.setText("");
	           treatmentTextArea.setText("");
	       } catch (NumberFormatException ex) {
	           JOptionPane.showMessageDialog(menuFrame, "Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
	       }
	   });
	    
       
       formPanel.add(medicalHLabel);
       formPanel.add(medHTextArea, "growx, wrap");
       formPanel.add(treatmentLabel);
       formPanel.add(treatmentTextArea, "growx, wrap");
       formPanel.add(docIDLabel);
       formPanel.add(docIDField, "growx, wrap");
       formPanel.add(createButton, "span, align center");
       contentPanel.add(formPanel, BorderLayout.CENTER);
       contentPanel.revalidate();
       contentPanel.repaint();
   }
   
   
   
   private void assignReportToPatient() {
	    contentPanel.removeAll();
	    JPanel formPanel = new JPanel();
	    formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[]"));

	    JLabel reportIdLabel = new JLabel("Report ID:");
	    JTextField reportIdField = new JTextField(20);
	    customizeTextField(reportIdField);

	    JLabel doctorIdLabel = new JLabel("Doctor ID:");
	    JTextField doctorIdField = new JTextField(20);
	    customizeTextField(doctorIdField);

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    customizeTextField(patientIdField);

	    JButton assignButton = new JButton("Assign");
	    customizeButton(assignButton);

	    assignButton.addActionListener(e -> {
	        try {
	            Integer reportId = Integer.parseInt(reportIdField.getText());
	            Integer doctorId = Integer.parseInt(doctorIdField.getText());
	            Integer patientId = Integer.parseInt(patientIdField.getText());

	            Reports report = adminmanager.getReportByID(reportId);
	            Doctor doctor = doctormanager.searchDoctorById(doctorId);
	            Patient patient = patientmanager.searchPatientById(patientId);

	            if (report != null && doctor != null && patient != null) {
	                doctormanager.assignReportToPatient(reportId, patientId);
	                doctor.getReports().add(report);
	                patient.getReports().add(report);
	                JOptionPane.showMessageDialog(contentPanel, "Report assigned to patient successfully!");
	            } else {
	                JOptionPane.showMessageDialog(contentPanel, "Report, doctor, or patient not found!", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid IDs!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while assigning the report.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });


	    formPanel.add(reportIdLabel);
	    formPanel.add(reportIdField, "growx");
	    formPanel.add(doctorIdLabel);
	    formPanel.add(doctorIdField, "growx");
	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField, "growx");
	    formPanel.add(assignButton, "span, align center");
	    
	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
	}
	   
   
   
   
   
   private void chooseInvestigationalProduct() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

	    JLabel doctorIdLabel = new JLabel("Doctor ID:");
	    JTextField doctorIdField = new JTextField(20);
	    customizeTextField(doctorIdField);
	    JLabel invProductIdLabel = new JLabel("Investigational Product ID:");
	    JTextField invProductIdField = new JTextField(20);
	    customizeTextField(invProductIdField);
	    JButton chooseButton = new JButton("Choose");
	    customizeButton(chooseButton);

	    chooseButton.addActionListener(e -> {
	        try {
	            int doctorId = Integer.parseInt(doctorIdField.getText());
	            int invProductId = Integer.parseInt(invProductIdField.getText());
	            doctormanager.chooseInvProductById(doctorId, invProductId);
	            JOptionPane.showMessageDialog(contentPanel, "Investigational product chosen successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid IDs!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while choosing the investigational product.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(doctorIdLabel);
	    formPanel.add(doctorIdField);
	    formPanel.add(invProductIdLabel);
	    formPanel.add(invProductIdField);
	    formPanel.add(chooseButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
  
   
   
   private void applyToCT(User u) {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));

	    JLabel adminIdLabel = new JLabel("Administrator ID:");
	    JTextField adminIdField = new JTextField(20);
	    JLabel trialIdLabel = new JLabel("Clinical Trial ID:");
	    JTextField trialIdField = new JTextField(20);
	    JButton applyButton = new JButton("Apply");

	    applyButton.addActionListener(e -> {
	        try {
	            Integer adminId = Integer.parseInt(adminIdField.getText());
	            Integer trialId = Integer.parseInt(trialIdField.getText());
	            String uEmail = u.getEmail();
	            Patient patient = patientmanager.searchPatientByEmail(uEmail);
	            
	            Integer patientId = patient.getPatient_id();
	            
	            patientmanager.applyToClinicalTrial(adminId, trialId, patientId);
	            JOptionPane.showMessageDialog(contentPanel, "Application submitted successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid IDs!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while applying to the clinical trial.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(adminIdLabel);
	    formPanel.add(adminIdField);
	    formPanel.add(trialIdLabel);
	    formPanel.add(trialIdField);
	    formPanel.add(applyButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void getStateOfRequest() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));

	    JLabel patientIdLabel = new JLabel("Patient ID:");
	    JTextField patientIdField = new JTextField(20);
	    JButton getStateButton = new JButton("Get State");

	    getStateButton.addActionListener(e -> {
	        try {
	            int patientId = Integer.parseInt(patientIdField.getText());
	            boolean state = patientmanager.getStateRequest(patientId);
	            JOptionPane.showMessageDialog(contentPanel, "The state of the request of the patient is: " + state);
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter a valid patient ID!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while getting the state of the request.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(patientIdLabel);
	    formPanel.add(patientIdField);
	    formPanel.add(getStateButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
  
   
   
   private void addNewSponsor() {
	   contentPanel.removeAll();
	   JPanel formPanel = new JPanel();
	   formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
	  
	   JLabel nameLabel = new JLabel("Name:");
       JTextField nameField = new JTextField(20);
       customizeTextField(nameField);
       JLabel phoneLabel = new JLabel("Phone:");
       JTextField phoneField = new JTextField(20);
       customizeTextField(phoneField);
       JLabel emailLabel = new JLabel("Email:");
       JTextField emailField = new JTextField(20);
       customizeTextField(emailField);
       
       JLabel cardNLabel = new JLabel("Card Number:");
       JTextField cNbField = new JTextField(20);
       customizeTextField(cNbField);
   	
       JButton createButton = new JButton("Create");
       customizeButton(createButton);
      
       createButton.addActionListener(e -> {
       String name = nameField.getText();
       String phone = phoneField.getText();
       String email = emailField.getText();
       String cardStr = cNbField.getText();
	          
       try {
           Integer phoneNumber = Integer.parseInt(phone);
           Integer cardNumber = Integer.parseInt(cardStr);
           Sponsor sponsor = new Sponsor(name, email, phoneNumber, cardNumber);
           sponsormanager.createSponsor(sponsor);
       	}catch (Exception exp) {
           exp.printStackTrace();
       	}
       });
       formPanel.add(nameLabel);
       formPanel.add(nameField, "growx, wrap 20");
       formPanel.add(phoneLabel);
       formPanel.add(phoneField, "growx, wrap 20");
       formPanel.add(emailLabel);
       formPanel.add(emailField, "growx, wrap 20");
       formPanel.add(cardNLabel);
       formPanel.add(cNbField, "growx, wrap 20");
       
       formPanel.add(createButton, "span, align center");
       contentPanel.add(formPanel, BorderLayout.CENTER);
       contentPanel.revalidate();
       contentPanel.repaint();
   }
   
   
   private void showAllTrials() {
	   List<Administrator> admins = adminmanager.getListOfAdmins();
		String[] columnNames = {"ID", "Name", "Email", "Phone"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Administrator admin : admins) {
	        Object[] rowData = {
	            admin.getAdmin_id(),
	            admin.getName(),
	            admin.getEmail(),
	            admin.getPhone()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void showAllSponsors() {
	   List<Sponsor> sponsors = sponsormanager.getListOfSponsor();
		String[] columnNames = {"ID", "Name", "Email", "Phone", "Card Number"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Sponsor sponsor : sponsors) {
	        Object[] rowData = {
	            sponsor.getSponsor_id(),
	            sponsor.getName(),
	            sponsor.getEmail(),
	            sponsor.getPhone(),
	            sponsor.getCardNumber()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void createInvestment() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));

	    JLabel trialIdLabel = new JLabel("Trial ID:");
	    JTextField trialIdField = new JTextField(20);
	    JLabel sponsorIdLabel = new JLabel("Sponsor ID:");
	    JTextField sponsorIdField = new JTextField(20);
	    JLabel amountLabel = new JLabel("Amount Invested:");
	    JTextField amountField = new JTextField(20);
	    JButton createButton = new JButton("Create");

	    createButton.addActionListener(e -> {
	        try {
	            int trialId = Integer.parseInt(trialIdField.getText());
	            int sponsorId = Integer.parseInt(sponsorIdField.getText());
	            int amount = Integer.parseInt(amountField.getText());
	            sponsormanager.createInvestment(trialId, sponsorId, amount);
	            JOptionPane.showMessageDialog(contentPanel, "Investment created successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while creating the investment.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(trialIdLabel);
	    formPanel.add(trialIdField);
	    formPanel.add(sponsorIdLabel);
	    formPanel.add(sponsorIdField);
	    formPanel.add(amountLabel);
	    formPanel.add(amountField);
	    formPanel.add(createButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
	   
   }
   
   
   private void updateInvestment() {
	   contentPanel.removeAll();
	    JPanel formPanel = new JPanel(new GridLayout(4, 1, 10, 10));

	    JLabel trialIdLabel = new JLabel("Trial ID:");
	    JTextField trialIdField = new JTextField(20);
	    JLabel sponsorIdLabel = new JLabel("Sponsor ID:");
	    JTextField sponsorIdField = new JTextField(20);
	    JLabel amountLabel = new JLabel("Amount Invested:");
	    JTextField amountField = new JTextField(20);
	    JButton updateButton = new JButton("Update");

	    updateButton.addActionListener(e -> {
	        try {
	            int trialId = Integer.parseInt(trialIdField.getText());
	            int sponsorId = Integer.parseInt(sponsorIdField.getText());
	            int amount = Integer.parseInt(amountField.getText());
	            sponsormanager.updateInvestment(trialId, sponsorId, amount);
	            JOptionPane.showMessageDialog(contentPanel, "Investment updated successfully!");
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(contentPanel, "Please enter valid values!", "Error", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(contentPanel, "An error occurred while updating the investment.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    formPanel.add(trialIdLabel);
	    formPanel.add(trialIdField);
	    formPanel.add(sponsorIdLabel);
	    formPanel.add(sponsorIdField);
	    formPanel.add(amountLabel);
	    formPanel.add(amountField);
	    formPanel.add(updateButton);

	    contentPanel.add(formPanel, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void showAllReportsOfPatient() {
	   contentPanel.removeAll();
	   	
	   	JPanel inputPanel = new JPanel();
	       inputPanel.setLayout(new FlowLayout());
	       JLabel patientIDLabel = new JLabel("Patient ID: ");
	       JTextField patientIDField = new JTextField(20);
	       customizeTextField(patientIDField);
	       JButton getIDButton = new JButton("Get ID");
	       customizeButton(getIDButton);
	       inputPanel.add(patientIDLabel);
	       inputPanel.add(patientIDField);
	       inputPanel.add(getIDButton);
	       contentPanel.add(inputPanel, BorderLayout.NORTH);
	       contentPanel.revalidate();
	       contentPanel.repaint();
	   	
	       getIDButton.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	               String patientIDStr = patientIDField.getText();
	               try {
	                   Integer patient_id = Integer.parseInt(patientIDStr);
	                   List<Reports> reports = sponsormanager.getReportsOfAPatient(patient_id);
	                   if (reports != null && !reports.isEmpty()) {
	                       String[] columnNames = {"ID", "Medical History", "Treatment", "Doctor ID", "Patient ID"};
	                       DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	                       for (Reports rep : reports) {
	                           Object[] rowData = {
	                               rep.getReport_id(),
	                               rep.getMedicalHistory(),
	                               rep.getTreatment(), 
	                               rep.getDoctor().getDoctor_id(),
	                               rep.getPatient().getPatient_id()
	                           };
	                           model.addRow(rowData);
	                       }
	                       JTable table = new JTable(model);
	                       JScrollPane scrollPane = new JScrollPane(table);
	                       contentPanel.removeAll();
	                       contentPanel.add(inputPanel, BorderLayout.NORTH);
	                       contentPanel.add(scrollPane, BorderLayout.CENTER);
	                       contentPanel.revalidate();
	                       contentPanel.repaint();
	                   } else {
	                       JOptionPane.showMessageDialog(menuFrame, "No reports found for the given Patient ID.", "Info", JOptionPane.INFORMATION_MESSAGE);
	                   }
	               } catch (NumberFormatException ex) {
	                   JOptionPane.showMessageDialog(menuFrame, "Please enter a valid Patient ID.", "Error", JOptionPane.ERROR_MESSAGE);
	               } catch (Exception ex) {
	                   ex.printStackTrace();
	                   JOptionPane.showMessageDialog(menuFrame, "An error occurred while fetching the patients.", "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	       });
   }
   
   
   private void addNewEngineer() {
	   contentPanel.removeAll();
	   	JPanel formPanel = new JPanel();
	   	formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
	  
	   	JLabel nameLabel = new JLabel("Name:");
	   	JTextField nameField = new JTextField(20);
	   	customizeTextField(nameField);
	    JLabel phoneLabel = new JLabel("Phone:");
	    JTextField phoneField = new JTextField(20);
	    customizeTextField(phoneField);
	    JLabel emailLabel = new JLabel("Email:");
	    JTextField emailField = new JTextField(20);
	    customizeTextField(emailField);
	   	
	       JButton createButton = new JButton("Create");
	       customizeButton(createButton);
	      
	       createButton.addActionListener(e -> {
	           String name = nameField.getText();
	           String phone = phoneField.getText();
	           String email = emailField.getText();
	          
	           try {
	               Integer phoneNumber = Integer.parseInt(phone);
	               Engineer eng = new Engineer(name, email, phoneNumber);
	               engineermanager.createEngineer(eng);
	           }catch (Exception exp) {
	               exp.printStackTrace();
	           }
	       });
	       formPanel.add(nameLabel);
	       formPanel.add(nameField, "growx, wrap");
	       formPanel.add(phoneLabel);
	       formPanel.add(phoneField, "growx, wrap");
	       formPanel.add(emailLabel);
	       formPanel.add(emailField, "growx, wrap");
	       formPanel.add(createButton, "span, align center");
	       contentPanel.add(formPanel, BorderLayout.CENTER);
	       contentPanel.revalidate();
	       contentPanel.repaint();
   }
   
   
   private void showAllEngineers() {
	   List<Engineer> engineers = engineermanager.getListOfEnginners();
		String[] columnNames = {"ID", "Name", "Email", "Phone"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (Engineer eng : engineers) {
	        Object[] rowData = {
	            eng.getEngineer_id(),
	            eng.getName(),
	            eng.getEmail(),
	            eng.getPhone()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
	    
	    
   }
   
   
   private void addNewInvProduct() {
	   contentPanel.removeAll();
	   	JPanel formPanel = new JPanel();
	   	formPanel.setLayout(new MigLayout("wrap 2", "[][grow]", "[][][]20[][]"));
	  
	   	JLabel descriptionLabel = new JLabel("Description:");
	   	JTextField descriptionField = new JTextField(20);
	   	customizeTextField(descriptionField);
	   	
	    JLabel typeLabel = new JLabel("Type:");
	    JTextField typeField = new JTextField(20);
	    customizeTextField(typeField);
	    
	    JLabel moneyLabel = new JLabel("Money:");
	    JTextField moneyField = new JTextField(20);
	    customizeTextField(moneyField);
	   	
	       JButton createButton = new JButton("Create");
	       customizeButton(createButton);
	      
	       createButton.addActionListener(e -> {
	           String description = descriptionField.getText();
	           String type = typeField.getText();
	           String moneyStr = moneyField.getText();
	          
	           try {
	               Integer moneyAmount = Integer.parseInt(moneyStr);
	               InvestigationalProduct iP = new InvestigationalProduct(moneyAmount, type, description);
	              engineermanager.createInvPr(iP);
	           }catch (Exception exp) {
	               exp.printStackTrace();
	           }
	       });
	       formPanel.add(descriptionLabel);
	       formPanel.add(descriptionField, "growx, wrap");
	       formPanel.add(typeLabel);
	       formPanel.add(typeField, "growx, wrap");
	       formPanel.add(moneyLabel);
	       formPanel.add(moneyField, "growx, wrap");
	       formPanel.add(createButton, "span, align center");
	       contentPanel.add(formPanel, BorderLayout.CENTER);
	       contentPanel.revalidate();
	       contentPanel.repaint();
   }
   
   
   private void showAllInvPr() {
	   List<InvestigationalProduct> invPr = doctormanager.getlistInvProd();
		String[] columnNames = {"ID", "Description", "Type", "Amount Money", "Engineer ID", "Doctor ID"}; //column names
	    DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Create table model
	    for (InvestigationalProduct invProduct : invPr) {
	        Object[] rowData = {
	            invProduct.getInvProduct_id(),
	            invProduct.getDescription(),
	            invProduct.getType(),
	            invProduct.getAmount(),
	            invProduct.getEngineer().getEngineer_id(),
	            invProduct.getDoctor().getDoctor_id()
	        };
	        model.addRow(rowData);
	    }
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    contentPanel.removeAll();
	    contentPanel.add(scrollPane, BorderLayout.CENTER);
	    contentPanel.revalidate();
	    contentPanel.repaint();
   }
   
   
   private void printToXML(String role) {
	   try {
	        switch (role.toLowerCase()) {
	        	case "administrator":
	        		String uEmail = u.getEmail();
	 	            Patient patient = patientmanager.searchPatientByEmail(uEmail);
	 	            
	        		printAdminToXML();
	        		break;
	        	case "doctor":
	                printDoctorToXML();
	                break;
	        	case "patient":
	                printPatientToXML();
	                break;
	            case "sponsor":
	                printSponsorToXML();
	                break;
	            case "engineer":
	                printEngineerToXML();
	                break;
	            default:
	                JOptionPane.showMessageDialog(contentPanel, "Unsupported role: " + role, "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(contentPanel, "An error occurred while printing to XML.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
   }
   
   
   
   private void printSponsorToXML() {
	    try {
	        xmlmanager.sponsor2xml(SponsorID);
	        JOptionPane.showMessageDialog(contentPanel, "Sponsor data printed to XML successfully!");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(contentPanel, "An error occurred while printing sponsor data to XML.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
   
   
   private void loadFromXML(String role) {
       // Implement the method to load from XML based on the role
   }
 
  
  
  
  
   public static void main(String[] args) {
       SwingUtilities.invokeLater(ClinicalTrialGUI::new);
   }
  
  
}
