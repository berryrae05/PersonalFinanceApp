import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener, MouseListener  {

	JFrame frame = new JFrame();
	JLabel log_userLabel = new JLabel("Username:");
	JLabel log_passwordLabel = new JLabel("Password");
	private static JTextField log_userField = new JTextField();
	private static JPasswordField log_passwordField = new JPasswordField();
	JButton btnLogin = new JButton("Login");
	JLabel register = new JLabel("Register new account");
	
	HashMap<String,String>usernameANDPassword = new HashMap<>();
    
      LoginPage()
	{
		JLabel login = new JLabel("LOGIN");
		login.setBounds(180,10,75,50);
		login.setFont(new Font("Helvetica", Font.BOLD,15));
		
		log_userLabel.setBounds(50,100,75,25);
		log_userLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		log_userField.setBounds(125,100,200,25);
		
		log_passwordLabel.setBounds(50,150,75,25);
		log_passwordLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		log_passwordField.setBounds(125,150,200,25);
		
		btnLogin.setBounds(125,200,100,25);
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(this);
		
		register.setBounds(125,240,150,25);
		register.setFont(new Font("Helvetica", Font.PLAIN,15));
		register.addMouseListener(this);
		
		frame.add(login);
		frame.add(log_userLabel);
		frame.add(log_passwordLabel);
		frame.add(log_userField);
		frame.add(log_passwordField);
		frame.add(btnLogin);
		frame.add(register);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		if(e.getSource()==register) 
		{
			frame.dispose();
			new Registration();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource()==btnLogin)
		{
			String username = log_userField.getText();
			String password =String.valueOf(log_passwordField.getPassword()); 
			
			getUsers();
			
			
			for(String usernames:usernameANDPassword.keySet())
			{
	           
				if(usernames.equals(username))
				{   
					if(usernameANDPassword.get(usernames).equals(password))
					{
						
						log_userField.setText("");
						log_passwordField.setText("");
						
						frame.dispose();
						
						JOptionPane.showMessageDialog(null,"Welcome");
						new ExpenseIncomeTracker();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid Password");	
					}
				}
			}
			
		}	
	}
   
	public void getUsers()
	{   
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
				}
				if(row[0].equals("Password"))
				{
					password = row[1];
				}
				if(!username.equals("")&& !password.equals(""))
				{
					usernameANDPassword.put(username, password);
				}
			}
			
		} catch (FileNotFoundException e1) 
		{
			System.out.println("File not found");
			e1.printStackTrace();
		}
	}	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
