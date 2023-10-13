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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

public class ExpenseIncomeTracker implements ActionListener {

	JFrame frame = new JFrame();
	
	JButton addBut = new JButton("Add");
	JButton viewTableBut = new JButton("View Timeline");
	JButton signOut = new JButton("Sign Out");
	
	JLabel userLabel = new JLabel ("Username");
	JLabel userInfo = new JLabel("Enter username to save infomation");
	JTextField userField = new JTextField();
	
	JLabel dateLabel = new JLabel ("Date:");
	JTextField dateField = new JTextField();
	
	JLabel descriptionLabel = new JLabel ("Description");
	JTextField descriptionField = new JTextField();
	
	JLabel amountLabel = new JLabel ("Amount");
	JTextField amountField = new JTextField();
	
	JLabel balLabel = new JLabel("");
	
	JTable table = new JTable();
	DefaultTableModel model;
	
	JLabel combo = new JLabel("Type");
	String[] type = {"Income" , "Expenses"};
	JComboBox comboBox_type = new JComboBox(type);
	
	JLabel comboLabel = new JLabel("Categories:");
	String[] categories = {"Salary","Housing", "Transportation", "Food", "Utilities", "Insurance", "Personal Spending", "Recreation & Entertainment", "Others"};
	JComboBox comboBox_categories = new JComboBox(categories);
	
	JButton backButton = new JButton("Back");
	JButton enterButton = new JButton("Enter");
	JFrame frames = new JFrame();
	JPanel panels = new JPanel();
	JTextField userFields = new JTextField();
	
    File file = new File("c:\\RegInfo\\ExpenseTracker");
	
	ArrayList<String> usernames = new ArrayList<>();
	String username;
	double balance;
	
	ExpenseIncomeTracker()
	{
		JPanel toppanel = new JPanel();
		toppanel.setBounds(0,0,790,58);
		toppanel.setLayout(null);
		
		Object[]columns = {"Date", "Description", "Amount", "Type"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		
		table.setRowHeight(30);
		
		userLabel.setBounds(0,5,75,15);
		userLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		toppanel.add(userLabel);
		userField.setBounds(86,0,130,24);
		toppanel.add(userField);
		userInfo.setBounds(350,5,180,15);
		userInfo.setFont(new Font("Helvetica", Font.BOLD,10));
		toppanel.add(userInfo);
		
		combo.setBounds(10,37,70,15);
		combo.setFont(new Font("Helvetica", Font.PLAIN,15));
		toppanel.add(combo);
		
		comboBox_type.setBounds(86,27,130,24);
		toppanel.add(comboBox_type);
		
		viewTableBut.setBounds(223,0,117,30);
		viewTableBut.setFocusable(false);
		viewTableBut.addActionListener(this);
		toppanel.add(viewTableBut);
		
		signOut.setBounds(650,0,117,30);
		signOut.setFocusable(false);
		signOut.addActionListener(this);
		toppanel.add(signOut);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0,60,790,211);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(0,270,800,90);
		bottomPanel.setLayout(null);
		
		dateLabel.setBounds(5,10,50,15);
		dateLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		bottomPanel.add(dateLabel);
		
		dateField.setBounds(45,5,114,30);
		bottomPanel.add(dateField);
		
		comboLabel.setBounds(160,10,90,15);
		comboLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		bottomPanel.add(comboLabel);
		
		comboBox_categories.setBounds(235, 5, 114, 30);
		bottomPanel.add(comboBox_categories);
		
		descriptionLabel.setBounds(350,10,90,15);
		descriptionLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		bottomPanel.add(descriptionLabel);
		
		descriptionField.setBounds(425, 5, 114, 30);
		bottomPanel.add(descriptionField);
		
		amountLabel.setBounds(540, 10, 90, 15);
		amountLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		bottomPanel.add(amountLabel);
		
		amountField.setBounds(600, 5, 114, 30);
		bottomPanel.add(amountField);
		
		addBut.setBounds(300,39,117,25);
		addBut.setFocusable(false);
		addBut.addActionListener(this);
		bottomPanel.add(addBut);
		
		balLabel.setBounds(22,75,180,15);
		balLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		bottomPanel.add(balLabel);
		
		frame.add(bottomPanel);
		frame.add(scrollPane);
		frame.setBounds(100,100,800,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Expense and Income Tracker");
		frame.add(toppanel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object[] rows = new Object[4];
		username = userField.getText();
		
		if(e.getSource()== signOut)
		{
			frame.dispose();
			new LoginPage();
			
		}
		
		if(e.getSource()== viewTableBut)
		{
			frame.dispose();
			new Timeline();
		}
		
		
		try {	
			if(e.getSource() == addBut)
			{  
				
				String type = (String) comboBox_type.getSelectedItem();
				String date = dateField.getText();
				Double amount = Double.parseDouble(amountField.getText());
				String categories = (String) comboBox_categories.getSelectedItem();
				String description = descriptionField.getText();
		
	
				if(type.equals("Expenses"))
				{
					amount *= -1;
				}
				
				try 
				{ getUsers();
					
					if(date.equals("")||description.equals("")||username.equals(""))
				    {
				    	JOptionPane.showMessageDialog(null,"One or more fields are empty");
				    }
					 else if(usernameExistCheck(username))
					{
						//Add data to table
						rows[0] = date;
						rows[1] = description;
						rows[2] = amount;
						rows[3] = type;
						model.addRow(rows);
							
						balance += amount;
						balLabel.setText("Balance: R" + balance);
						 
						//Write info to file 
						FileWriter writer = new FileWriter(file+ "\\" + username +".txt",true);
					
			    			 writer.write(System.getProperty("line.separator"));
			    			 writer.write(date +";\t" + description+ ";\t"+ categories +";\t"+ amount + ";\t"+ type +";\t"  );
			    			 writer.close();
			    			 
			    			 dateField.setText("");
			    			 amountField.setText("");
			    			 descriptionField.setText("");
			    				
			    			 JOptionPane.showMessageDialog(null,"Expenses and Income successfully entered");
					}		 
					 else JOptionPane.showMessageDialog(null, "Expenses and Income could not be added.Username does not exist");	
					}
				 catch (IOException e1) {
					 
					 JOptionPane.showMessageDialog(null, "Username does not exist");
					e1.printStackTrace();
				}
				
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Incorrect Amount Format");
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
			JOptionPane.showMessageDialog(null,"Username does not exist");
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
		
}

