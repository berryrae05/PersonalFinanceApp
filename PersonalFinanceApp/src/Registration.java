
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Registration implements ActionListener 
{

	JFrame frame = new JFrame("REGISTER");
	JButton btnReg = new JButton("Register");
	JButton btnCancel = new JButton("Cancel");
	
	private JTextField reg_userField = new JTextField(); 
	private JTextField reg_emailField = new JTextField();
	private JTextField reg_fullNameField = new JTextField();
	private JPasswordField reg_passField = new JPasswordField();
	private JPasswordField reg_confPassField = new JPasswordField();
	
	JLabel reg_userLabel = new JLabel("Username:");
	JLabel reg_emailLabel = new JLabel("Email:");
	JLabel reg_passwordLabel = new JLabel("Password:");
	JLabel reg_confPassLabel = new JLabel("Confirm Password:");
	JLabel reg_fullNameLabel = new JLabel("Full Name:");
	File file;

	File files = new File("c:\\RegInfo\\ExpenseTracker");
	ArrayList<String> usernames = new ArrayList<>();
	
	String username;
	Registration()
	{
		
		JLabel registerLabel = new JLabel("REGISTER");
		registerLabel.setBounds(200, 10, 100, 50);
		registerLabel.setFont(new Font("Helvetica", Font.BOLD,15));
		
		reg_userLabel.setBounds(10, 60, 75, 25);
		reg_userLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		reg_userField.setBounds(10, 80, 450, 25);
		
		reg_fullNameLabel.setBounds(10, 100, 75, 25);
		reg_fullNameLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		reg_fullNameField.setBounds(10, 120, 450, 25);
		
		reg_emailLabel.setBounds(10, 140, 75, 25);
		reg_emailLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		reg_emailField.setBounds(10, 160, 450, 25);
		
		reg_passwordLabel.setBounds(10, 180, 75, 25);
		reg_passwordLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		reg_passField.setBounds(10, 200, 450, 25);
		
		reg_confPassLabel.setBounds(10, 220, 140, 25);
		reg_confPassLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		reg_confPassField.setBounds(10, 240, 450, 25);
		
		btnReg.setBounds(100, 300, 100,25);
		btnReg.setFocusable(false);
		btnReg.addActionListener(this);
		
		btnCancel.setBounds(300,300,100,25);
		btnCancel.setFocusable(false);
		btnCancel.addActionListener(this);
		
		frame.add(registerLabel);
		frame.add(reg_fullNameLabel);
		frame.add(reg_userLabel);
		frame.add(reg_emailLabel);
		frame.add(reg_passwordLabel);
		frame.add(reg_confPassLabel);
		
		frame.add(reg_fullNameField);
		frame.add(reg_userField);
		frame.add(reg_emailField);
		frame.add(reg_passField);
		frame.add(reg_confPassField);
		frame.add(btnReg);
		frame.add(btnCancel);
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()== btnReg) 
		{
			file = new File("c:\\RegInfo\\Info");
			
			String fullname = reg_fullNameField.getText();
			username = reg_userField.getText();
			String email = reg_emailField.getText();
			String password = String.valueOf(reg_passField.getPassword());
			String confirmPassword = String.valueOf(reg_confPassField.getPassword());
			
			
			createFolder();
			createAFile();
			
			try {
				FileWriter writes = new FileWriter(files+ "\\" + username +".txt",true);
				writes.write("Date, \tDescription, \tCategory, \tAmount, \tType");
				writes.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				FileWriter writer = new FileWriter("c:\\RegInfo\\Info\\register.txt",true);
				
				getUsers();
			    if(username.equals("")||fullname.equals("")||email.equals("")||confirmPassword.equals("")||username.equals(""))
			    {
			    	JOptionPane.showMessageDialog(null,"One or more fields are empty");
			    	
    				
			    }else{
			    	 if(password.equals(confirmPassword))
			    	{
			    		 if(!usernameExistCheck(username))
			    		 {
			    			 writer.write("Username: " + username);
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.write("FullName: " + fullname);
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.write("Email: " + email);
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.write("Password: " + password);
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.write("-----");
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.close();
			    			 
			    			 
			    			    reg_fullNameField.setText("");
			    				reg_userField.setText("");
			    				reg_emailField.setText("");
			    				reg_passField.setText("");
			    				reg_confPassField.setText("");
			    				
			    				JOptionPane.showMessageDialog(null,"Successfully Registered");
			    		 }
			    		 else
			    		 {
			    			 JOptionPane.showMessageDialog(null,"Username already exists, try another one");
			    
			    			 reg_userField.setText("");
			    		 }
			    	}
			    	 else
			    	 {
			    		 JOptionPane.showMessageDialog(null,"Password Confirmation Error");
			    		 reg_passField.setText("");
			    		 reg_confPassField.setText("");
			    	 }
			    }
				
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
				
		}
		
		if(e.getSource()==btnCancel) 
		{
			frame.dispose();
			 new LoginPage();
		}
		
	}

	public void getUsers()
	{
		
		File file = new File("C:\\RegInfo\\Info\\register.txt");
		String username = " ";
		String password = " ";
		
		try {
			FileReader reader = new FileReader("C:\\RegInfo\\Info\\register.txt");
			BufferedReader bfreader = new BufferedReader(reader);
			
			Object[]lines = bfreader.lines().toArray();
			for(int i = 0 ;i <lines.length;i++)
			{
				String[]row = lines[i].toString().split(": ");
				
				if(row[0].equals("Username"))
				{
					username = row[1];
					usernames.add(username);
				}
				
			}
			
		} catch (FileNotFoundException e1) 
		{
			System.out.println("File not found");
			e1.printStackTrace();
		}
	}
	
	public boolean usernameExistCheck(String user)
	{
		boolean exist = false;
		
		for(String usern: usernames)
		{
			if(usern.equals(user))
			{
				exist = true;
			}
		}
		
		return exist;
	}
	
	void createFolder() 
	{
		if(!file.exists()) {
			file.mkdirs();
		}
		if(!files.exists())
		{
			files.mkdirs();
		}
		
	}
	
	void createAFile() 
	{
		try {
			FileReader reader = new FileReader(file+ "\\register.txt");
			FileReader freader = new FileReader(files+ "\\" + username +".txt");
		} catch (FileNotFoundException e) {
			try {
				FileWriter fwriter = new FileWriter(file+ "\\register.txt",true);
				
				FileWriter fw = new FileWriter(files+ "\\" + username +".txt",true);

			} catch (IOException e1) {
				System.out.println("File does not exists");
				e1.printStackTrace();
			}
		}	
	}	
}

	
	

