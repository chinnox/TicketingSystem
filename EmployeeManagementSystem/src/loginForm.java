import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginForm {

	private JFrame mainFrame;
	private JTextField txtUname;
	private JPasswordField txtPassword;
	
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm window = new loginForm();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loginForm() {
		initialize();
		conn = myConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);
		mainFrame.setBounds(100, 100, 400, 500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new MatteBorder(3, 2, 3, 2, (Color) new Color(0, 0, 0)));
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBounds(0, 0, 400, 500);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		headerPanel.setBackground(new Color(0, 0, 139));
		headerPanel.setBounds(0, 0, 400, 121);
		mainPanel.add(headerPanel);
		headerPanel.setLayout(null);
		
		JLabel lblHeader = new JLabel("Login Here !");
		lblHeader.setForeground(new Color(255, 255, 255));
		lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblHeader.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		lblHeader.setBounds(128, 53, 151, 34);
		headerPanel.add(lblHeader);
		
		JLabel lblCloseWindow = new JLabel("X");
		lblCloseWindow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCloseWindow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.dispose();
			}
		});
		lblCloseWindow.setForeground(new Color(255, 255, 255));
		lblCloseWindow.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblCloseWindow.setBounds(374, 11, 16, 32);
		headerPanel.add(lblCloseWindow);
		
		JLabel lblUname = new JLabel("UserName :");
		lblUname.setForeground(new Color(128, 128, 128));
		lblUname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblUname.setBounds(57, 161, 96, 24);
		mainPanel.add(lblUname);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.GRAY);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPassword.setBounds(57, 244, 96, 24);
		mainPanel.add(lblPassword);
		
		txtUname = new JTextField();
		txtUname.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtUname.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		txtUname.setBounds(74, 196, 247, 30);
		mainPanel.add(txtUname);
		txtUname.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtPassword.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		txtPassword.setBounds(74, 279, 247, 30);
		mainPanel.add(txtPassword);
		
		JLabel lblNewLabel = new JLabel("Forgot");
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setBounds(215, 320, 46, 14);
		mainPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Password ?");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(251, 320, 169, 14);
		mainPanel.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String uname = txtUname.getText();
				String password = String.valueOf(txtPassword.getPassword());
				
				if(uname.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Username field is empty !", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else if (password.equals("")) {
					JOptionPane.showMessageDialog(null, "Password field is empty !", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else {
					String query = "SELECT * FROM user_list WHERE uname=? AND password=?";
					try {
						pst = conn.prepareStatement(query);
						pst.setString(1, uname);
						pst.setString(2, password);
						
						rs = pst.executeQuery();
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "Login Successful !");
							new HomeForm().setVisible(true);
							mainFrame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Username or Password is Invalid !");
							txtUname.setText("");
							txtPassword.setText("");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1);
					}
					
							
				}
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnLogin.setBounds(133, 354, 128, 30);
		mainPanel.add(btnLogin);
		
		JLabel lblNewLabel_2 = new JLabel("Don't have an account yet?");
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_2.setBounds(125, 433, 159, 14);
		mainPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("SIGN UP !");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(152, 454, 121, 24);
		mainPanel.add(lblNewLabel_3);
	}
}
