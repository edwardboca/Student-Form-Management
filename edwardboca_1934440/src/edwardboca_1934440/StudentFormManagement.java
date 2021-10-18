package edwardboca_1934440;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentFormManagement {

	private JFrame frmStudentManagement;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldFees;
	private JTextField textSearch;
	private JTable table;
	private Connection connection;
	private JTextField textFieldDate;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFormManagement window = new StudentFormManagement();
					window.frmStudentManagement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	private void loadTable() {
		

		try {
			
			String query = "select ID, Name, Fees, DOB from StudentInfo";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			textFieldID.setText(null);
			textFieldName.setText(null);
			textFieldFees.setText(null);
			textFieldDate.setText(null);

			//dateChooser.setCalendar(null);
			
			
			
			rs.close();
			pst.close();
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2);
		}
		
		
		}

	/**
	 * Create the application.
	 */
	public StudentFormManagement() {
		initialize();
		connection = SqliteConnection.dbConnector();
		loadTable();
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		
		frmStudentManagement = new JFrame();
		frmStudentManagement.getContentPane().setBackground(Color.YELLOW);
		frmStudentManagement.getContentPane().setLayout(null);
		
		/*
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MMM-yyyy");
		dateChooser.setBounds(309, 336, 168, 24);
		*/
		
		
		JLabel lblTitle1 = new JLabel("Lasalle College - Student Dashboard");
		lblTitle1.setForeground(Color.RED);
		lblTitle1.setFont(new Font("Tahoma", Font.BOLD, 38));
		lblTitle1.setBounds(106, 11, 704, 57);
		frmStudentManagement.getContentPane().add(lblTitle1);
		
		JLabel lblTitle2 = new JLabel("International School - Montreal Canada");
		lblTitle2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitle2.setBounds(186, 81, 488, 50);
		frmStudentManagement.getContentPane().add(lblTitle2);
		
		JLabel lblInfo = new JLabel("Student Information");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblInfo.setBounds(309, 153, 185, 38);
		frmStudentManagement.getContentPane().add(lblInfo);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblID.setBounds(156, 228, 109, 25);
		frmStudentManagement.getContentPane().add(lblID);
		
		JLabel lblName = new JLabel("Student Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(156, 264, 109, 25);
		frmStudentManagement.getContentPane().add(lblName);
		
		JLabel lblFees = new JLabel("Fees:");
		lblFees.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFees.setBounds(156, 300, 109, 25);
		frmStudentManagement.getContentPane().add(lblFees);
		
		JLabel lblDate = new JLabel("Date Of Birth:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(156, 336, 109, 25);
		frmStudentManagement.getContentPane().add(lblDate);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(309, 228, 168, 24);
		frmStudentManagement.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(309, 264, 168, 24);
		frmStudentManagement.getContentPane().add(textFieldName);
		
		textFieldFees = new JTextField();
		textFieldFees.setColumns(10);
		textFieldFees.setBounds(309, 300, 168, 24);
		frmStudentManagement.getContentPane().add(textFieldFees);
		
		JButton btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon("C:\\Users\\EDY\\Desktop\\files for java\\edwardboca_1934440\\img\\button_violet_delete.png"));
		btnNew.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnNew.setBounds(577, 194, 137, 50);
		frmStudentManagement.getContentPane().add(btnNew);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
					
					String query = "insert into StudentInfo(ID,Name,Fees,DOB) values (?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textFieldID.getText());
					pst.setString(2, textFieldName.getText());
					pst.setString(3, textFieldFees.getText());
					pst.setString(4, textFieldDate.getText());
					//pst.setString(4, dateChooser.getDate().toGMTString());
					
					
					
					
				
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Data saved");
					pst.close();
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
				
				loadTable();
				
			}
		});
		btnInsert.setIcon(new ImageIcon("C:\\Users\\EDY\\Desktop\\files for java\\edwardboca_1934440\\img\\button_blue_add.png"));
		btnInsert.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnInsert.setBounds(577, 251, 137, 50);
		frmStudentManagement.getContentPane().add(btnInsert);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					

					String query = "update StudentInfo set ID='"+ textFieldID.getText() + "', Name='" + textFieldName.getText() +
							"', Fees='" + textFieldFees.getText() + "',DOB='"+ textFieldDate.getText() +
							"' where ID='" +  textFieldID.getText() + "'";
					
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
			
			
		} catch (Exception e2) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, e2);
		}
		
		loadTable();
		
		
	
			}
		});
			
		btnUpdate.setIcon(new ImageIcon("C:\\Users\\EDY\\Desktop\\files for java\\edwardboca_1934440\\img\\button_pink_close.png"));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnUpdate.setBounds(577, 311, 137, 50);
		frmStudentManagement.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(null, "Would you like to delete?", "Delete",JOptionPane.YES_NO_OPTION);
				
				if (action == 0) {
					
					
					try {
						

						String query = "delete from StudentInfo where ID='" + textFieldID.getText() + "'";
						
						PreparedStatement pst = connection.prepareStatement(query);
						
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data was Removed");
						pst.close();
				
				
					} catch (Exception e2) {
						// TODO: handle exception
						
						JOptionPane.showMessageDialog(null, e2);
					}
					
					
					
				}
				
				
		
				loadTable();
			}
		});
			
		btnDelete.setIcon(new ImageIcon("C:\\Users\\EDY\\Desktop\\files for java\\edwardboca_1934440\\img\\button_red_stop.png"));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setBounds(577, 372, 137, 50);
		frmStudentManagement.getContentPane().add(btnDelete);
		
		JLabel lblSearch = new JLabel("Search Student By Name:");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSearch.setBounds(73, 439, 205, 30);
		frmStudentManagement.getContentPane().add(lblSearch);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					
					
					
					

					String query = "select ID, Name, Fees, DOB from StudentInfo where " + "Name"  + "=?";
					
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textSearch.getText());
					
					ResultSet rs = pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
			
			
				} catch (Exception e2) {
					// TODO: handle exception
					
					JOptionPane.showMessageDialog(null, e2);
				}
				
				
		
		
			
		
				
			}
		});
		textSearch.setBounds(288, 446, 185, 38);
		frmStudentManagement.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 496, 639, 265);
		frmStudentManagement.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				try {
				int row = table.getSelectedRow();
				String EmId = (table.getModel().getValueAt(row, 0)).toString();
				
				String query = "select * from StudentInfo where ID='" + EmId + "'";
				PreparedStatement pst = connection.prepareStatement(query);
				ResultSet rs = pst.executeQuery();
				
				while (rs.next()) {
					
					
					textFieldID.setText(rs.getString("ID"));
					textFieldName.setText(rs.getString("Name"));
					textFieldFees.setText(rs.getString("Fees"));
					textFieldDate.setText(rs.getString("DOB"));
					
					
					
				}
				
				
				rs.close();
				pst.close();
				
				
				
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e2);
			}
			
		}
	});
		scrollPane.setViewportView(table);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("C:\\Users\\EDY\\Desktop\\files for java\\edwardboca_1934440\\img\\download.png"));
		lblLogo.setBounds(0, 54, 240, 190);
		frmStudentManagement.getContentPane().add(lblLogo);
		
		
		
		
		//frmStudentManagement.getContentPane().add(dateChooser);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(309, 338, 168, 24);
		frmStudentManagement.getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		frmStudentManagement.setTitle("Student Management");
		frmStudentManagement.setBounds(100, 100, 929, 823);
		frmStudentManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
