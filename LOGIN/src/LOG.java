import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LOG {

	private JFrame frame;
	private final JTextField TB1 = new JTextField();
	private JPasswordField TB2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOG window = new LOG();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LOG() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 230, 230));
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN PAGE");
		lblNewLabel.setFont(new Font("Constantia", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(193, 32, 217, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNewLabel_1.setBounds(159, 113, 133, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setFont(new Font("Constantia", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(179, 211, 105, 33);
		frame.getContentPane().add(lblNewLabel_2);
		TB1.setBounds(334, 116, 98, 31);
		frame.getContentPane().add(TB1);
		TB1.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String name=TB1.getText();
			String password=TB2.getText();
			try
			{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","mrec");
				PreparedStatement stn=con.prepareStatement
						("select name,password from users where name=? and password=?");
				stn.setString(1,name);
				stn.setString(2,password);
				ResultSet rs=stn.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(btnNewButton,"valid user");
				}
				
				else
				{
					JOptionPane.showMessageDialog(btnNewButton,"invalid user");
				}
					
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			
			}
			
		});
		btnNewButton.setFont(new Font("Constantia", Font.BOLD, 15));
		btnNewButton.setBounds(272, 314, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		TB2 = new JPasswordField();
		TB2.setBounds(334, 203, 98, 33);
		frame.getContentPane().add(TB2);
		frame.setBounds(100, 100, 604, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
