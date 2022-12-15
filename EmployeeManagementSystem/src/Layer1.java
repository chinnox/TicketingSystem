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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class Layer1 extends JFrame {

	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JTextField txtCategory;
	private JTextField txtCustomer;
	private JTextField txtReqDate;
	private JTextField txtEstMnHrs;
	private JTextField txtExpctAnsDate;
	private JTextField txtSysFunc;
	private JTextField txtPriorityLvl;
	private JTextField txtUrgencyLvl;
	private JTextField txtTitle;
	private DefaultListCellRenderer listRenderer;
	
	
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
					Layer1 frame = new Layer1();
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
	public Layer1() {
		
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
		panel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		
		JLabel lblNewLabel_2 = new JLabel("List of New Tickets:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 19));
		lblNewLabel_2.setBounds(30, 177, 179, 26);
		panel.add(lblNewLabel_2);
		
		JList list = new JList();
		list.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		list.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		list.setBackground(new Color(248, 248, 255));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		String query1 = "SELECT * FROM incidents WHERE status = '新規依頼'";
		
		try {
			DefaultListModel model = new DefaultListModel();
			pst = conn.prepareStatement(query1);
			rs = pst.executeQuery();
			while(rs.next()) {
				String newIncidents = rs.getString("number_id");
				model.addElement(newIncidents);
			}
			list.setModel(model);
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		list.setBounds(76, 228, 133, 265);
		panel.add(list);
		
		JLabel lblCategory = new JLabel("Category :");
		lblCategory.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblCategory.setBounds(262, 212, 69, 14);
		panel.add(lblCategory);
		
		JLabel lblCustomer = new JLabel("Customer :");
		lblCustomer.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblCustomer.setBounds(262, 237, 69, 14);
		panel.add(lblCustomer);
		
		JLabel lblReqDate = new JLabel("Request Date :");
		lblReqDate.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblReqDate.setBounds(262, 262, 81, 14);
		panel.add(lblReqDate);
		
		JLabel lblEstMnHrs = new JLabel("Estimated Man-hrs :");
		lblEstMnHrs.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEstMnHrs.setBounds(262, 287, 105, 14);
		panel.add(lblEstMnHrs);
		
		JLabel lblExpctAnsDate = new JLabel("Expected Answer Date :");
		lblExpctAnsDate.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblExpctAnsDate.setBounds(262, 312, 123, 14);
		panel.add(lblExpctAnsDate);
		
		JLabel lblSysFunc = new JLabel("System/Function :");
		lblSysFunc.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblSysFunc.setBounds(262, 337, 123, 14);
		panel.add(lblSysFunc);
		
		JLabel lblPriorityLvl = new JLabel("Priority Level :");
		lblPriorityLvl.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPriorityLvl.setBounds(262, 362, 92, 14);
		panel.add(lblPriorityLvl);
		
		JLabel lblUrgencyLvl = new JLabel("Urgency Level :");
		lblUrgencyLvl.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblUrgencyLvl.setBounds(262, 387, 92, 14);
		panel.add(lblUrgencyLvl);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblStatus.setBounds(262, 186, 69, 14);
		panel.add(lblStatus);
		
		JLabel lblTitle = new JLabel("Title :");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTitle.setBounds(534, 212, 47, 14);
		panel.add(lblTitle);
		
		JLabel lblRequestCont = new JLabel("Request Content :");
		lblRequestCont.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblRequestCont.setBounds(534, 237, 105, 14);
		panel.add(lblRequestCont);
		
		JLabel lblMessage = new JLabel("Message/Notes :");
		lblMessage.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblMessage.setBounds(262, 428, 105, 14);
		panel.add(lblMessage);
		
		JLabel lblStoragePath = new JLabel("Storage Path :");
		lblStoragePath.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblStoragePath.setBounds(555, 491, 105, 14);
		panel.add(lblStoragePath);
		
		txtCategory = new JTextField();
		txtCategory.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtCategory.setColumns(10);
		txtCategory.setBounds(321, 209, 133, 20);
		panel.add(txtCategory);
		
		txtCustomer = new JTextField();
		txtCustomer.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtCustomer.setColumns(10);
		txtCustomer.setBounds(321, 234, 133, 20);
		panel.add(txtCustomer);
		
		txtReqDate = new JTextField();
		txtReqDate.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtReqDate.setColumns(10);
		txtReqDate.setBounds(342, 259, 133, 20);
		panel.add(txtReqDate);
		
		txtEstMnHrs = new JTextField();
		txtEstMnHrs.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtEstMnHrs.setColumns(10);
		txtEstMnHrs.setBounds(371, 285, 133, 20);
		panel.add(txtEstMnHrs);
		
		txtExpctAnsDate = new JTextField();
		txtExpctAnsDate.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtExpctAnsDate.setColumns(10);
		txtExpctAnsDate.setBounds(385, 309, 133, 20);
		panel.add(txtExpctAnsDate);
		
		txtSysFunc = new JTextField();
		txtSysFunc.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtSysFunc.setColumns(10);
		txtSysFunc.setBounds(360, 335, 133, 20);
		panel.add(txtSysFunc);
		
		txtPriorityLvl = new JTextField();
		txtPriorityLvl.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtPriorityLvl.setColumns(10);
		txtPriorityLvl.setBounds(342, 359, 133, 20);
		panel.add(txtPriorityLvl);
		
		txtUrgencyLvl = new JTextField();
		txtUrgencyLvl.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtUrgencyLvl.setColumns(10);
		txtUrgencyLvl.setBounds(342, 384, 133, 20);
		panel.add(txtUrgencyLvl);
		
		txtTitle = new JTextField();
		txtTitle.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		txtTitle.setColumns(10);
		txtTitle.setBounds(571, 210, 266, 20);
		panel.add(txtTitle);
		
		JTextArea messageTA = new JTextArea();
		messageTA.setFont(new Font("MS Gothic", Font.PLAIN, 13));
		messageTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		messageTA.setBackground(new Color(248, 248, 255));
		messageTA.setBounds(254, 453, 262, 117);
		panel.add(messageTA);
		
		JTextArea storagePathTA = new JTextArea();
		storagePathTA.setFont(new Font("MS Gothic", Font.PLAIN, 13));
		storagePathTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		storagePathTA.setBackground(new Color(248, 248, 255));
		storagePathTA.setBounds(555, 516, 262, 52);
		panel.add(storagePathTA);
		
		JTextArea reqContTA = new JTextArea();
		reqContTA.setLineWrap(true);
		reqContTA.setWrapStyleWord(true);
		reqContTA.setFont(new Font("MS Gothic", Font.PLAIN, 13));
		reqContTA.setBorder(new MatteBorder(1, 1, 2, 1, (Color) new Color(0, 0, 0)));
		reqContTA.setBackground(new Color(248, 248, 255));
		reqContTA.setBounds(534, 259, 303, 221);
		panel.add(reqContTA);
		
		String [] status = {"Please Select", "New Incident", "Acknowledge", "On-going", "Resolved", "Closed"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox statusCB = new JComboBox(status);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		statusCB.setRenderer(listRenderer);	
		statusCB.setBounds(309, 183, 188, 22);
		panel.add(statusCB);
		
		String [] layer = {"Please Select", "Layer 1", "Layer 2", "Layer 3"};
		listRenderer = new DefaultListCellRenderer();
		JComboBox layerCB = new JComboBox(layer);
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		layerCB.setRenderer(listRenderer);	
		layerCB.setBounds(599, 183, 179, 22);
		panel.add(layerCB);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = list.getSelectedValue().toString();
				String query2 = "SELECT * FROM incidents WHERE number_id = ?";
				
				try {
					pst = conn.prepareStatement(query2);
					pst.setString(1, item);
					rs = pst.executeQuery();
					
					if(rs.next()) {
						txtCategory.setText(rs.getString("category"));
						txtCustomer.setText(rs.getString("customer"));
						txtReqDate.setText(rs.getString("req_date"));
						txtEstMnHrs.setText(rs.getString("est_wrk_hrs"));
						txtExpctAnsDate.setText(rs.getString("expct_ans_date"));
						txtSysFunc.setText(rs.getString("sys_function"));
						txtPriorityLvl.setText(rs.getString("priority_lvl"));
						txtUrgencyLvl.setText(rs.getString("urgency_lvl"));
						txtTitle.setText(rs.getString("title"));
						reqContTA.setText(rs.getString("req_content"));
						messageTA.setText(rs.getString("message"));
						storagePathTA.setText(rs.getString("store_path"));
						statusCB.setSelectedIndex(1);
						layerCB.setSelectedItem(rs.getString("layer"));
					}
				}catch(SQLException e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnLoad.setBounds(98, 504, 89, 23);
		panel.add(btnLoad);

		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String number_id = list.getSelectedValue().toString();
				String status = statusCB.getSelectedItem().toString();
				String category = txtCategory.getText();
				String customer = txtCustomer.getText();
				String req_date = txtReqDate.getText();
				String est_wrk_hrs = txtEstMnHrs.getText();
				String expct_ans_date = txtExpctAnsDate.getText();
				String sys_function = txtSysFunc.getText();
				String priority = txtPriorityLvl.getText();
				String urgency = txtUrgencyLvl.getText();
				String title = txtTitle.getText();
				String content = reqContTA.getText();
				String message = messageTA.getText();
				String storage = storagePathTA.getText();
				String layer = layerCB.getSelectedItem().toString();
				
				String query3 = "UPDATE incidents SET number_id ='"+number_id+"', status = '"+status+"', category = '"+category+"', customer = '"+customer+"', req_date = '"+req_date+"', est_wrk_hrs = '"+est_wrk_hrs+"', expct_ans_date = '"+expct_ans_date+"', sys_function = '"+sys_function+"', priority_lvl = '"+priority+"', urgency_lvl = '"+urgency+"', title = '"+title+"', req_content = '"+content+"', message = '"+message+"', store_path = '"+storage+"', layer = '"+layer+"' WHERE number_id = ?";
				
				
				try {
					DefaultListModel model = new DefaultListModel();
					pst = conn.prepareStatement(query3);
					pst.setString(1, number_id);
					
					int k = pst.executeUpdate();
					String refresh = "SELECT * FROM incidents WHERE status = '新規依頼'";
					
					if(k==1) {
						JOptionPane.showMessageDialog(null, "Record Updated Succesfully！");
						
						txtCategory.setText("");
						statusCB.setSelectedIndex(0);
						txtCustomer.setText("");
						txtReqDate.setText("");
						txtEstMnHrs.setText("");
						txtExpctAnsDate.setText("");
						txtSysFunc.setText("");
						txtPriorityLvl.setText("");
						txtUrgencyLvl.setText("");
						txtTitle.setText("");
						reqContTA.setText("");
						messageTA.setText("");
						storagePathTA.setText("");
						layerCB.setSelectedIndex(0);
						
						pst = conn.prepareStatement(refresh);
						rs = pst.executeQuery();
						while(rs.next()) {
							String newIncidents = rs.getString("number_id");
							model.addElement(newIncidents);	
						}
					}	list.setModel(model);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnSave.setBounds(731, 579, 89, 23);
		panel.add(btnSave);
			
		JScrollPane scrollPane = new JScrollPane(reqContTA);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(534, 262, 303, 215);
		panel.add(scrollPane);
		
		JLabel lblLayer = new JLabel("Assignee :");
		lblLayer.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblLayer.setBounds(534, 187, 69, 14);
		panel.add(lblLayer);
		

	}
}
