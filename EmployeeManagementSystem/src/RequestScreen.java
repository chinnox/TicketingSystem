import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class RequestScreen extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField txtRequestDate;
	private JTextField txtEstimatedHours;
	private JTextField txtExpectedAnswerDate;
	private JTextField txtTitle;
	private DefaultListCellRenderer listRenderer;
	private JTextField txtNumberSystem;
	private JTextField txtStatus;
	
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
					RequestScreen frame = new RequestScreen();
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
	public RequestScreen() {
		
		Connection conn = myConnection.getConnection();
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 848, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(3, 2, 3, 2, (Color) new Color(0, 0, 0)));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		panel.setBorder(new MatteBorder(3, 2, 3, 2, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 847, 624);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 847, 155);
		panel_1.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(0, 0, 139));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tsutaya Ticketing System");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(237, 47, 410, 49);
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
		lblNewLabel_1.setBounds(821, 0, 16, 32);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblClassification = new JLabel("種別 :");
		lblClassification.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblClassification.setBounds(20, 204, 46, 22);
		panel.add(lblClassification);
		
		String [] classification = {"---選択---", "問い合わせ対応", "定型作業"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox classificationCB = new JComboBox(classification);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		classificationCB.setRenderer(listRenderer);
		classificationCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		classificationCB.setBounds(113, 205, 175, 22);
		panel.add(classificationCB);
		
		
		JLabel lblCustomer = new JLabel("お客様 :");
		lblCustomer.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblCustomer.setBounds(20, 237, 58, 22);
		panel.add(lblCustomer);
		
		String [] customer = {"---選択---", "CCC", "その他"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox customerCB = new JComboBox(customer);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		customerCB.setRenderer(listRenderer);
		customerCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		customerCB.setBounds(113, 238, 113, 22);
		panel.add(customerCB);
		
		JLabel lblRequestDate = new JLabel("依頼日時 :");
		lblRequestDate.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblRequestDate.setBounds(20, 270, 80, 22);
		panel.add(lblRequestDate);
		
		txtRequestDate = new JTextField();
		txtRequestDate.setBounds(113, 272, 114, 20);
		panel.add(txtRequestDate);
		txtRequestDate.setColumns(10);
		
		JLabel lblEsimatedHours = new JLabel("想定作業工数 :");
		lblEsimatedHours.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblEsimatedHours.setBounds(20, 303, 103, 22);
		panel.add(lblEsimatedHours);
		
		txtEstimatedHours = new JTextField();
		txtEstimatedHours.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txtEstimatedHours.setColumns(10);
		txtEstimatedHours.setBounds(133, 305, 114, 20);
		panel.add(txtEstimatedHours);
		
		JLabel lblExpectedAnswerDate = new JLabel("回答希望日時 :");
		lblExpectedAnswerDate.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblExpectedAnswerDate.setBounds(20, 336, 103, 22);
		panel.add(lblExpectedAnswerDate);
		
		txtExpectedAnswerDate = new JTextField();
		txtExpectedAnswerDate.setColumns(10);
		txtExpectedAnswerDate.setBounds(133, 338, 114, 20);
		panel.add(txtExpectedAnswerDate);
		
		JLabel lblSystem = new JLabel("システム・機能 :");
		lblSystem.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblSystem.setBounds(20, 369, 103, 22);
		panel.add(lblSystem);
		
		String [] function = {"---選択---", "PPT", "CELL"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox systemCB = new JComboBox(function);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		systemCB.setRenderer(listRenderer);
		systemCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		systemCB.setBounds(133, 370, 113, 22);
		panel.add(systemCB);
		
		JLabel lblPriority = new JLabel("優先度 :");
		lblPriority.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblPriority.setBounds(20, 402, 103, 22);
		panel.add(lblPriority);
		
		JLabel lblUrgency = new JLabel("緊急度 :");
		lblUrgency.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblUrgency.setBounds(20, 435, 103, 22);
		panel.add(lblUrgency);
		
		String [] priority = {"---選択---", "低", "中", "高"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox priorityCB = new JComboBox(priority);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		priorityCB.setRenderer(listRenderer);
		priorityCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		priorityCB.setBounds(96, 403, 113, 22);
		panel.add(priorityCB);
		
		String [] urgency = {"---選択---", "低", "中", "高"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox urgencyCB = new JComboBox(urgency);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		urgencyCB.setRenderer(listRenderer);
		urgencyCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		urgencyCB.setBounds(96, 436, 113, 22);
		panel.add(urgencyCB);
		
		JLabel lblTitle = new JLabel("タイトル :");
		lblTitle.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblTitle.setBounds(401, 204, 103, 22);
		panel.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("MS PGothic", Font.PLAIN, 11));
		txtTitle.setColumns(10);
		txtTitle.setBounds(479, 206, 318, 20);
		panel.add(txtTitle);
		
		JLabel lblRequestContent = new JLabel("依頼内容 :");
		lblRequestContent.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblRequestContent.setBounds(401, 237, 103, 22);
		panel.add(lblRequestContent);
		
		JTextArea requestContentTA = new JTextArea();
		requestContentTA.setFont(new Font("MS Gothic", Font.PLAIN, 13));
		requestContentTA.setBackground(new Color(248, 248, 255));
		requestContentTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		requestContentTA.setBounds(1, 1, 846, 182);
		requestContentTA.setLineWrap(true);
		requestContentTA.setWrapStyleWord(true);
		panel.add(requestContentTA);
		
		JLabel lblAttachment = new JLabel("格納先 :");
		lblAttachment.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblAttachment.setBounds(401, 468, 103, 22);
		panel.add(lblAttachment);
		
		JScrollPane scrollPane = new JScrollPane(requestContentTA);
		scrollPane.setBounds(411, 272, 403, 184);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);
		
		JLabel lblUrgency_1 = new JLabel("メッセージ :");
		lblUrgency_1.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblUrgency_1.setBounds(20, 468, 103, 22);
		panel.add(lblUrgency_1);
		
		JTextArea messageTA = new JTextArea();
		messageTA.setFont(new Font("MS PGothic", Font.PLAIN, 13));
		messageTA.setBackground(new Color(248, 248, 255));
		messageTA.setWrapStyleWord(true);
		messageTA.setLineWrap(true);
		messageTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		messageTA.setBounds(68, 501, 305, 62);
		panel.add(messageTA);
		
		JTextArea storePathTA = new JTextArea();
		storePathTA.setFont(new Font("MS PGothic", Font.PLAIN, 13));
		storePathTA.setWrapStyleWord(true);
		storePathTA.setLineWrap(true);
		storePathTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		storePathTA.setBackground(new Color(248, 248, 255));
		storePathTA.setBounds(411, 501, 403, 62);
		panel.add(storePathTA);
		
		String [] layer = {"---選択---", "Layer 1", "Layer 2", "Layer 3"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox layerCB = new JComboBox(layer);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		layerCB.setRenderer(listRenderer);
		layerCB.setFont(new Font("MS PGothic", Font.BOLD, 11));
		layerCB.setBounds(272, 172, 114, 22);
		panel.add(layerCB);
		
		JButton btnNewButton = new JButton("登録");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String number_id = txtNumberSystem.getText();
				String status = txtStatus.getText();
				String category = classificationCB.getSelectedItem().toString();
				String customer = customerCB.getSelectedItem().toString();
				String req_date = txtRequestDate.getText();
				String est_wrk_hrs = txtEstimatedHours.getText();
				String expct_ans_date = txtExpectedAnswerDate.getText();
				String sys_function = systemCB.getSelectedItem().toString();
				String priority = priorityCB.getSelectedItem().toString();
				String urgency = urgencyCB.getSelectedItem().toString();
				String title = txtTitle.getText();
				String content = requestContentTA.getText();
				String message = messageTA.getText();
				String storage = storePathTA.getText();
				String layer = layerCB.getSelectedItem().toString();
				
				String query = "INSERT INTO incidents (number_id, status, category, customer, req_date, est_wrk_hrs, expct_ans_date, sys_function, priority_lvl, urgency_lvl, title, req_content, message, store_path, layer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				try {
					pst = conn.prepareStatement(query);
					pst.setString(1, number_id);
					pst.setString(2, status);
					pst.setString(3, category);
					pst.setString(4, customer);
					pst.setString(5, req_date);
					pst.setString(6, est_wrk_hrs);
					pst.setString(7, expct_ans_date);
					pst.setString(8, sys_function);
					pst.setString(9, priority);
					pst.setString(10, urgency);
					pst.setString(11, title);
					pst.setString(12, content);
					pst.setString(13, message);
					pst.setString(14, storage);
					pst.setString(15, layer);
					
					int k = pst.executeUpdate();
					if(k==1) {
						JOptionPane.showMessageDialog(null, "登録できました！");
						
						txtNumberSystem.setText("");
						classificationCB.setSelectedIndex(0);
						customerCB.setSelectedIndex(0);
						txtRequestDate.setText("");
						txtEstimatedHours.setText("");
						txtExpectedAnswerDate.setText("");
						systemCB.setSelectedIndex(0);
						priorityCB.setSelectedIndex(0);
						urgencyCB.setSelectedIndex(0);
						txtTitle.setText("");
						requestContentTA.setText("");
						messageTA.setText("");
						storePathTA.setText("");
						layerCB.setSelectedIndex(0);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}	
			}
		});
		btnNewButton.setFont(new Font("MS PGothic", Font.BOLD, 12));
		btnNewButton.setBounds(725, 574, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblClassification_1 = new JLabel("採番 :");
		lblClassification_1.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblClassification_1.setBounds(20, 171, 46, 22);
		panel.add(lblClassification_1);
		
		txtNumberSystem = new JTextField();
		txtNumberSystem.setColumns(10);
		txtNumberSystem.setBounds(68, 174, 114, 20);
		panel.add(txtNumberSystem);
		
		JLabel lblBackButton = new JLabel("");
		lblBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new HomeForm().setVisible(true);
				dispose();
			}
		});
		lblBackButton.setIcon(new ImageIcon("E:\\workspace\\EmployeeManagementSystem\\icon\\back-icon.png"));
		lblBackButton.setBounds(20, 574, 105, 38);
		panel.add(lblBackButton);
		
		JLabel lblStatus = new JLabel("ステータス :");
		lblStatus.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblStatus.setBounds(401, 173, 103, 22);
		panel.add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setText("新規依頼");
		txtStatus.setFont(new Font("MS PGothic", Font.PLAIN, 14));
		txtStatus.setColumns(10);
		txtStatus.setBounds(479, 173, 318, 20);
		panel.add(txtStatus);
		
		JLabel lblAssignee = new JLabel("アサイン先 :");
		lblAssignee.setFont(new Font("MS PGothic", Font.BOLD, 14));
		lblAssignee.setBounds(192, 172, 80, 22);
		panel.add(lblAssignee);
		
		
	}
}
