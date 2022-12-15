import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class HomeForm extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeForm frame = new HomeForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeForm() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		panel.setBorder(new MatteBorder(3, 2, 3, 2, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 483, 525);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(0, 0, 139));
		panel_1.setBounds(0, 0, 483, 155);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tsutaya Ticketing System");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(68, 51, 410, 49);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel_1.setBounds(462, 0, 16, 32);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblRequest = new JLabel("依頼");
		lblRequest.setIcon(new ImageIcon("E:\\workspace\\EmployeeManagementSystem\\icon\\request.png"));
		lblRequest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RequestScreen().setVisible(true);
				dispose();
			}
		});
		lblRequest.setFont(new Font("MS PGothic", Font.BOLD, 36));
		lblRequest.setBounds(147, 166, 213, 77);
		panel.add(lblRequest);
		
		JLabel lblLayer1 = new JLabel("Layer 1");
		lblLayer1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Layer1().setVisible(true);
				dispose();
			}
		});
		lblLayer1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLayer1.setIcon(new ImageIcon("E:\\workspace\\EmployeeManagementSystem\\icon\\layer1.png"));
		lblLayer1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblLayer1.setBounds(138, 248, 172, 84);
		panel.add(lblLayer1);
		
		JLabel lblLayer2 = new JLabel("Layer 2");
		lblLayer2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Layer2().setVisible(true);
				dispose();
			}
		});
		lblLayer2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLayer2.setIcon(new ImageIcon("E:\\workspace\\EmployeeManagementSystem\\icon\\layer2.png"));
		lblLayer2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblLayer2.setBounds(138, 332, 172, 84);
		panel.add(lblLayer2);
		
		JLabel lblLayer3 = new JLabel("Layer 3");
		lblLayer3.setIcon(new ImageIcon("E:\\workspace\\EmployeeManagementSystem\\icon\\layer3.png"));
		lblLayer3.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblLayer3.setBounds(138, 415, 199, 84);
		panel.add(lblLayer3);
	}
}
