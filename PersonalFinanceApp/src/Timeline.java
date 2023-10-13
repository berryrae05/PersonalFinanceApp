import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Timeline implements ActionListener {
	
	JFrame frame = new JFrame("Timeline");
	JTable table = new JTable ();
	JLabel userLabel = new JLabel("Username:");
	JTextField userField = new JTextField();
	JButton enterBut = new JButton("Enter");
	JButton backBut = new JButton("Back");
	File file = new File("c:\\RegInfo\\ExpenseTracker");
	DefaultTableModel model;
	String username;
	
	Timeline()
	{
		userLabel.setBounds(50,10,75,15);
		userLabel.setFont(new Font("Helvetica", Font.PLAIN,15));
		userField.setBounds(140,5,140,30);
		
		enterBut.setBounds(100,50,140,25);
		enterBut.setFocusable(false);
		enterBut.addActionListener(this);
		
		backBut.setBounds(250,50,140,25);
		backBut.setFocusable(false);
		backBut.addActionListener(this);
		
		model = new DefaultTableModel();
		table.setModel(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0,100,500,400);
		
		frame.add(backBut);
		frame.add(enterBut);
		frame.add(userField);
		frame.add(userLabel);
		frame.add(scrollPane);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		username = userField.getText();
		
		String date = "";
		String description = "";
		String amount = "";
		String type = "";
		
		if(e.getSource()==backBut)
		{
			frame.dispose();
			new ExpenseIncomeTracker();
		}
		
		if(e.getSource()==enterBut)
		{
		  
		  FileReader reader;
		try {
			reader = new FileReader(file+ "\\" + username +".txt");
			BufferedReader bfreader = new BufferedReader(reader);
			String firstLine = bfreader.readLine().trim();
			String []coloumnsName = firstLine.split(",");
			model.setColumnIdentifiers(coloumnsName);
			
			Object[]lines = bfreader.lines().toArray();
			
			for(int i = 0;i<lines.length;i++)
			{
				String line = lines[i].toString().trim();
				String[]dataRow = line.split(";");
				model.addRow(dataRow);
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Incorrect Username");
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  
		  
		}
		
	}

}
