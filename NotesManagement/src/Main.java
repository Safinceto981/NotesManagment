import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Main extends JFrame   
{
	private static final long serialVersionUID = 1L;

	 private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	
	private static int id = -1;
	
	private JLabel userName = new JLabel("Username:");
	private JLabel firstName = new JLabel("Firstname:");
	private JLabel lastName = new JLabel("Lastname:");
	private JLabel city = new JLabel ("City:");
	private JLabel countryLabel = new JLabel("Country:");
	private JLabel searchByNameLabel = new JLabel("Search by username");
	private JLabel searchByCountryLabel = new JLabel("Search by country");
	private JLabel searchByNCLabel = new JLabel("Search by First Name and country");
	
	
	private JTextField userNameTextField = new JTextField(10);
	private JTextField firstNameTextField = new JTextField(10);
	private JTextField lastNameTextField = new JTextField(10);
	
	
	private JPanel containerUsers = new JPanel();
	
	private JPanel containerAddNotes = new JPanel();
	private JPanel containerJoin = new JPanel();
	private JPanel containerJoin2 = new JPanel();
	private JPanel searchContainer = new JPanel();
	
	
	
	private JPanel orderedNotes= new JPanel();
	
	private JButton addUserBtn = new JButton("Add");
	private JButton deleteUserBtn = new JButton("Delete");
	private JButton editUserBtn = new JButton("Edit");
	private JButton refreshBtn = new JButton("Refresh");
	private JButton searchButton = new JButton("Search");
	private JButton searchButton1 = new JButton("Search");
	private JButton searchButton2 = new JButton("Search");
	
	private JTable usersTable = new JTable();
	private JScrollPane usersScroller = new JScrollPane(usersTable);

	private JTable notesByUser = new JTable();
	private JTable searchTable= new JTable();
	
	
	private JLabel userNameNote = new JLabel("Username note:");
	private JLabel userNames = new JLabel("Users");
	private JLabel chooseUser	= new JLabel("Choose user:");
	
	private JLabel statusLabel = new JLabel();

	userCombo userBox = new userCombo();

	private JTextArea userNameNoteTextField = new JTextArea(4, 8);
	
	JComboBox<String> usersCombo = new JComboBox<String>(userBox.userCombo());
	
	JComboBox<String> usersCombo1 = new JComboBox<String>(userBox.userCombo());

	userCombo chooseCombo1 = new userCombo();
	JComboBox<String> chooseCombo = new JComboBox<String>(chooseCombo1.userCombo());
	
	//countryCombo countryCombo1 = new countryCombo();
	JComboBox<String> choosecountry = new JComboBox<String>();{
	
	choosecountry.addItem("");
	choosecountry.addItem("Albania");
	choosecountry.addItem("Austria");
	choosecountry.addItem("Belarus");
	choosecountry.addItem("Belgium");
	choosecountry.addItem("Bulgaria");
	choosecountry.addItem("France");
	choosecountry.addItem("Germany");
	choosecountry.addItem("Greece");
	choosecountry.addItem("Russia");
	choosecountry.addItem("Turkey");}
JComboBox<String> choosecountry1 = new JComboBox<String>();{
		
		choosecountry1.addItem("");
		choosecountry1.addItem("Albania");
		choosecountry1.addItem("Austria");
		choosecountry1.addItem("Belarus");
		choosecountry1.addItem("Belgium");
		choosecountry1.addItem("Bulgaria");
		choosecountry1.addItem("France");
		choosecountry1.addItem("Germany");
		choosecountry1.addItem("Greece");
		choosecountry1.addItem("Russia");
		choosecountry1.addItem("Turkey");}
	
	
	fName fnameBox = new fName();
	fName chooseFName = new fName();
	JComboBox<String>fNameCombo = new JComboBox<String>(fnameBox.fName());

	
	choosecityCombo choosecity1 = new choosecityCombo();
	JComboBox<String>choosecity = new JComboBox<String>(choosecity1.choosecityCombo());


	private JButton addNoteBtn = new JButton("Add");
	private JButton deleteNoteBtn = new JButton("Delete");
	private JTable notesTable = new JTable();
	private JScrollPane notesScroller = new JScrollPane (notesTable);
	private JScrollPane addnoteScroller = new JScrollPane();

	private JTable NandU = new JTable();
	private JTable UandC = new JTable();
	private JScrollPane NandUScroller = new JScrollPane(NandU);
	private JScrollPane UandCScroller = new JScrollPane(UandC);
	private JButton showButton = new JButton("Show");
	

	ResultSet result = null;
	PreparedStatement state = null;
		
		
		
	public static void main(String[] args) 
	{
		Main mainFrame = new Main(); 
	}

	public Main()
	{
		
		setTitle("Notes Management System");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		setLayout(new GridLayout(1, 2));
		setJTabbedPane();
		usersTable.setModel(DatabaseConnector.getAllUsers());
		usersTable.addMouseListener(new MouseTableAction());
		
		NandU.setModel(DatabaseConnector.join());
		UandC.setModel(DatabaseConnector.join2());
		notesTable.setModel(DatabaseConnector.getAllNotes());
		
		//NandU.addMouseListener(new MouseTableAction() );
	}
	
	private void setJTabbedPane()
	{
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Join1", joinPanel());
		tabbedPane.add("Users", usersPanel());
		tabbedPane.add("Add note", addNotesPanel());
		tabbedPane.add("Notes by users",orderedNotes());
		tabbedPane.add("U&C",joinPanel2());
		tabbedPane.add("Search",SearchPanel());
		add(tabbedPane);
	}
	
	private JPanel joinPanel()
	{
		
		
		
		JPanel joinPanel = new JPanel();
		joinPanel.add(containerJoin);
		containerJoin.add(NandU);
		containerJoin.add(NandUScroller);
		containerJoin.setLayout(new GridLayout(1,1));
		NandUScroller.setPreferredSize(new Dimension(650, 300));
		NandU.setModel(DatabaseConnector.join());
		
	//	NandU.addMouseListener(new MouseTableActionJoin() );
		

		

		NandU.add(NandUScroller);
	
		return joinPanel;
	}
	
	private JPanel usersPanel()
	{
		JPanel usersPanel = new JPanel();
		usersPanel.setLayout(new GridLayout(3,1));
		JPanel upPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel downPanel = new JPanel();
		usersPanel.add(upPanel);
		usersPanel.add(midPanel);
		usersPanel.add(downPanel);
		
		//up
		upPanel.setLayout(new GridLayout(4, 2));
		upPanel.add(userName);
		upPanel.add(userNameTextField);
		upPanel.add(firstName);
		upPanel.add(firstNameTextField);
		upPanel.add(lastName);
		upPanel.add(lastNameTextField);;
		upPanel.add(countryLabel);
		upPanel.add(choosecountry);
		//mid
		
		midPanel.add(addUserBtn);
		midPanel.add(deleteUserBtn);
		midPanel.add(editUserBtn);
		
		addUserBtn.addActionListener(new AddUserAction());
		deleteUserBtn.addActionListener(new DeleteUserAction());
		editUserBtn.addActionListener(new EditUserAction());
		
		//down
		downPanel.add(usersScroller);
		usersScroller.setPreferredSize(new Dimension(350, 100));
		downPanel.add(containerUsers);

		usersTable.addMouseListener(new MouseTableAction() );
		
		return usersPanel;
	}
	
	private JPanel addNotesPanel()
	{
		

		JPanel addNotesPanel = new JPanel();
		addNotesPanel.setLayout(new GridLayout(3,1));
		
		JPanel upPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel downPanel = new JPanel();
		
		addNotesPanel.add(upPanel);
		addNotesPanel.add(midPanel);
		addNotesPanel.add(downPanel);
		upPanel.setLayout(new GridLayout(2, 2));
		upPanel.add(userNameNote);
		upPanel.add(userNameNoteTextField);
		userNameNoteTextField.add(addnoteScroller);
		upPanel.add(userNames);
		upPanel.add(usersCombo);
		
		
	//mid
		midPanel.add(addNoteBtn);
		midPanel.add(deleteNoteBtn);
		midPanel.add(refreshBtn);
		
		addNoteBtn.addActionListener(new AddNoteAction());
		deleteNoteBtn.addActionListener(new DeleteNoteAction());
		refreshBtn.addActionListener(new RefreshNoteAction());
	
		
	//down
		downPanel.add(notesScroller);
		notesScroller.setPreferredSize(new Dimension(350, 100));
	downPanel.add(containerAddNotes);
		

	notesTable.addMouseListener(new MouseTableAction1());
		
		return addNotesPanel;
	}
	
	
	private JPanel orderedNotes() {
		

		JPanel orderedNotes	 = new JPanel();
		orderedNotes.setLayout(new GridLayout(3,1));
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		
		orderedNotes.add(panel1);
		orderedNotes.add(panel2);
		orderedNotes.add(panel3);
		//up
		panel1.setLayout(new GridLayout(1, 2));
		panel1.add(chooseUser);
		panel1.add(chooseCombo);
		//mid
		panel2.add(showButton);
		showButton.setSize(100, 100);
		showButton.addActionListener(new ShowButtonAction());
		//down
		panel3.add(notesByUser);
		
		return orderedNotes;
	}
	
	private JPanel joinPanel2()
	{
		
		
		
		JPanel joinPanel2 = new JPanel();
		joinPanel2.add(containerJoin2);
		containerJoin2.add(UandC);
		containerJoin2.add(UandCScroller);
		containerJoin2.setLayout(new GridLayout(1,1));
		UandCScroller.setPreferredSize(new Dimension(650, 300));
		
		//.addMouseListener(new MouseTableActionJoin2() );

		UandC.add(UandCScroller);
		UandC.setModel(DatabaseConnector.join2());
	
		return joinPanel2;
	}
	
	private JPanel SearchPanel()
	{
		JPanel SearchPanel = new JPanel();
		SearchPanel.setLayout(new GridLayout(4,1));
		JPanel firstPanel = new JPanel();
		JPanel secondPanel = new JPanel();
		JPanel thirdPanel = new JPanel();
		JPanel fourthPanel = new JPanel();
		SearchPanel.add(firstPanel);
		SearchPanel.add(secondPanel);
		SearchPanel.add(thirdPanel);
		SearchPanel.add(fourthPanel);
		
		firstPanel.setLayout(new GridLayout(1, 3));
		firstPanel.add(searchByNameLabel);
		firstPanel.add(usersCombo1);
		firstPanel.add(searchButton);
		searchButton.addActionListener(new searchButtonAction());
		
		secondPanel.setLayout(new GridLayout(1,3));
		secondPanel.add(searchByCountryLabel);
		secondPanel.add(choosecountry1);
		secondPanel.add(searchButton1);
		searchButton1.addActionListener(new searchButtonAction1());
		
		thirdPanel.setLayout(new GridLayout(1,4));
		thirdPanel.add(searchByNCLabel);
		thirdPanel.add(fNameCombo);
		thirdPanel.add(choosecity);
		thirdPanel.add(searchButton2);
		searchButton2.addActionListener(new searchButtonAction2());
		
		fourthPanel.add(searchContainer);
		searchContainer.add(searchTable);

		fourthPanel.setSize(WIDTH, 200);
		//searchTable.setModel(DatabaseConnector.);
		
	
		
		

		
		
		
		return SearchPanel;
	}
	
	
	public void refreshChooseCombo() {
		chooseCombo.removeAllItems();
	String sql = "SELECT USERNAME FROM USERS;";
	connection = DatabaseConnector.getConnection();
	try {
		PreparedStatement state = connection.prepareStatement(sql);
		result = state.executeQuery();
		String item = null;
		while (result.next()) {
			item = result.getObject(1).toString();
			chooseCombo.addItem(item);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	  
	public void refreshComboUsers() {
		usersCombo.removeAllItems();
	String sql = "SELECT USERNAME FROM USERS;";
	connection = DatabaseConnector.getConnection();
	try {
		PreparedStatement state = connection.prepareStatement(sql);
		result = state.executeQuery();
		String item = null;
		while (result.next()) {
			item = result.getObject(1).toString();
			usersCombo.addItem(item);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	  
	public void refreshusersCombo1() {
		usersCombo1.removeAllItems();
	String sql = "SELECT USERNAME FROM USERS;";
	connection = DatabaseConnector.getConnection();
	try {
		PreparedStatement state = connection.prepareStatement(sql);
		result = state.executeQuery();
		String item = null;
		while (result.next()) {
			item = result.getObject(1).toString();
			usersCombo1.addItem(item);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public void refreshfNameCombo() {
		fNameCombo.removeAllItems();
	String sql = "SELECT firstname FROM USERS;";
	connection = DatabaseConnector.getConnection();
	try {
		PreparedStatement state = connection.prepareStatement(sql);
		result = state.executeQuery();
		String item = null;
		while (result.next()) {
			item = result.getObject(1).toString();
			fNameCombo.addItem(item);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	
	class MouseTableAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = usersTable.getSelectedRow();
			id = Integer.parseInt(usersTable.getValueAt(row, 0).toString());
		System.out.println(id);
			if(e.getClickCount()>1) {
				userNameTextField.setText(usersTable.getValueAt(row, 1).toString());
				firstNameTextField.setText(usersTable.getValueAt(row, 2).toString());
				lastNameTextField.setText(usersTable.getValueAt(row, 3).toString());
				choosecountry.setSelectedItem(usersTable.getValueAt(row, 4).toString());
			}
					
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MouseTableAction1 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = notesTable.getSelectedRow();
			id = Integer.parseInt(notesTable.getValueAt(row, 0).toString());
		System.out.println(id);
			if(e.getClickCount()>1) {
				usersCombo.setSelectedItem(notesTable.getValueAt(row, 1).toString());	
				userNameNoteTextField.setText(notesTable.getValueAt(row, 2).toString());
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
/*	class MouseTableActionJoin implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = NandU.getSelectedRow();
			id = Integer.parseInt(NandU.getValueAt(row, 0).toString());
		System.out.println(id);
			if(e.getClickCount()>1) {
				
				userNameTextField.setText(NandU.getValueAt(row, 1).toString());
				firstNameTextField.setText(NandU.getValueAt(row, 2).toString());
				lastNameTextField.setText(NandU.getValueAt(row, 3).toString());
				userNameNoteTextField.setText(NandU.getValueAt(row, 4).toString());
				chooseCombo.setSelectedItem(NandU.getValueAt(row, 5).toString());

			}
					
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	class MouseTableActionJoin2 implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row = UandC.getSelectedRow();
			id = Integer.parseInt(UandC.getValueAt(row, 0).toString());
		System.out.println(id);
			if(e.getClickCount()>1) {
				
				userNameTextField.setText(UandC.getValueAt(row, 1).toString());
				firstNameTextField.setText(UandC.getValueAt(row, 2).toString());
				lastNameTextField.setText(UandC.getValueAt(row, 3).toString());
				userNameNoteTextField.setText(UandC.getValueAt(row, 4).toString());

			}
					
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}*/

	class AddUserAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String userName = userNameTextField.getText();
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String countrychoice = choosecountry.getSelectedItem().toString();
			
			String sql = "INSERT INTO users VALUES (null, ?, ?, ?,?);";
			
			connection = DatabaseConnector.getConnection();
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, countrychoice);
				preparedStatement.execute();
				statusLabel.setText("The user is added!");
				usersTable.setModel(DatabaseConnector.getAllUsers());
				UandC.setModel(DatabaseConnector.join2());
				NandU.setModel(DatabaseConnector.join());
				refreshChooseCombo();
				refreshComboUsers();
				refreshusersCombo1();
				refreshfNameCombo();
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
					connection.close();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	class DeleteUserAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String sql = "DELETE  FROM users WHERE id = ?;";
			connection = DatabaseConnector.getConnection();
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, id);
				preparedStatement.execute();
				id = -1;
				usersTable.setModel(DatabaseConnector.getAllUsers());
				notesTable.setModel(DatabaseConnector.getAllNotes());
				UandC.setModel(DatabaseConnector.join2());
				NandU.setModel(DatabaseConnector.join());
				refreshChooseCombo();
				refreshComboUsers();
				refreshusersCombo1();
				refreshfNameCombo();


			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				try {
					preparedStatement.close();
					connection.close();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
	
	public Model getDbContent() {
		connection = DatabaseConnector.getConnection();
	String sql = "select * from users";

		try {
			state = connection.prepareStatement(sql);
			result = state.executeQuery();
			return (new Model(result));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public  Model getAllNotes() {
		connection = DatabaseConnector.getConnection();
		String sql = "SELECT * FROM NOTES;";
		
		try {
		state = connection.prepareStatement(sql);
			result = state.executeQuery();
			return(new Model(result));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	class EditUserAction implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String userName = userNameTextField.getText();
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String country = choosecountry.getSelectedItem().toString();
		
			connection = DatabaseConnector.getConnection();
			String sql = "update users set username=?,firstname=?, lastname=? , country=? where id=? ";
			try {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userName);
				preparedStatement.setString(2, firstName);
				preparedStatement.setString(3, lastName);
				preparedStatement.setString(4, country);
				preparedStatement.setInt(5,id);
				preparedStatement.execute();
				clearForm();
			
				usersTable.setModel(DatabaseConnector.getAllUsers());
				UandC.setModel(DatabaseConnector.join2());
				NandU.setModel(DatabaseConnector.join());
				refreshChooseCombo();
				refreshComboUsers();
				refreshusersCombo1();
				refreshfNameCombo();

				statusLabel.setText("Edited");
				id = 0;	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	class RefreshNoteAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			refreshComboUsers();
	}
	}
	
	public Model getNotes() {
		connection = DatabaseConnector.getConnection();
		String sql = "SELECT username,notestext FROM NOTES where username = ?;";
		String userchoice1 = chooseCombo.getSelectedItem().toString();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userchoice1);
			result = preparedStatement.executeQuery();
			return(new Model(result));
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
	}
		return null;
		}
	public Model searchusername() {
		connection = DatabaseConnector.getConnection();
		String sql = "SELECT username,firstname,lastname,country FROM users where username = ?;";
		String searchuser = usersCombo1.getSelectedItem().toString();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchuser);
			result = preparedStatement.executeQuery();
			clearsearchuser();
			return(new Model(result));
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
	}
		return null;
		}
	public void clearsearchuser() {
		usersCombo1.setSelectedIndex(0);
	}
	public Model searchcountry() {
		connection = DatabaseConnector.getConnection();
		String sql = "SELECT username,firstname,lastname,country FROM users where country = ?;";
		String searchcountry = choosecountry1.getSelectedItem().toString();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchcountry);
			result = preparedStatement.executeQuery();
			clearsearcountry();
			return(new Model(result));
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
	}
		return null;
		}
	
	public void clearsearcountry() {
		choosecountry1.setSelectedIndex(0);
	}
	public Model searchfnamec() {
		connection = DatabaseConnector.getConnection();
		
		String searchfname = fNameCombo.getSelectedItem().toString();
		String searchcity = choosecity.getSelectedItem().toString();
		String sql = "select username ,firstname ,lastname ,country ,cityname as city\r\n" + 
				"from users join city\r\n" + 
				"on users.country = city.countryname\r\n" + 
				"where firstname=? and cityname = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchfname);
			preparedStatement.setString(2, searchcity);
			result = preparedStatement.executeQuery();
			
			clearsearchdouble();
			return(new Model(result));
		}catch (SQLException e1) {
			e1.printStackTrace();
		}catch (Exception e1) {
			e1.printStackTrace();
	}
		return null;
		}
	public void clearsearchdouble() {
		fNameCombo.setSelectedIndex(0);
		choosecity.setSelectedIndex(0);
	}
	
	class ShowButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			String userchoice1 = chooseCombo.getSelectedItem().toString();
			
			String sql = "SELECT username as username,notestext as notes FROM NOTES where USERNAME = ?";
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, userchoice1);
					preparedStatement.execute();
					notesByUser.setModel(getNotes());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		}

	class searchButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String searchuser = usersCombo1.getSelectedItem().toString();
			
			String sql = "SELECT username,firstname,lastname,country FROM users where username = ?";
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, searchuser);
					preparedStatement.execute();
					searchTable.setModel(searchusername());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			
			
		}
		
	}
	class searchButtonAction1 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String searchcountry = choosecountry1.getSelectedItem().toString();
			
			String sql = "SELECT username,firstname,lastname,country FROM users where country = ?";
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, searchcountry);
					preparedStatement.execute();
					searchTable.setModel(searchcountry());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			
			
		}
		
	}
	class searchButtonAction2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String searchfname = fNameCombo.getSelectedItem().toString();
			String searchcity = choosecity.getSelectedItem().toString();
			String sql = "select username ,firstname ,lastname ,country ,cityname as city\r\n" + 
					"from users join city\r\n" + 
					"on users.country = city.countryname\r\n" + 
					"where firstname=? and cityname = ?;";
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, searchfname);
					preparedStatement.setString(2, searchcity);
					preparedStatement.execute();
					searchTable.setModel(searchfnamec());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			
			
		}
		
	}
	
	
	class AddNoteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
	
			String userchoice = usersCombo.getSelectedItem().toString();
			String noteField = userNameNoteTextField.getText();
			String sql = "INSERT INTO notes VALUES (null, ?, ?);";
				
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, userchoice);
					preparedStatement.setString(2, noteField);
					
					preparedStatement.execute();
					notesTable.setModel(DatabaseConnector.getAllNotes());
					NandU.setModel(DatabaseConnector.join());
					refreshComboUsers();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						preparedStatement.close();
						connection.close();
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}clearForm1();
			}
			
		}
	class DeleteNoteAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM notes WHERE idnote = ?";
				connection = DatabaseConnector.getConnection();
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, id);
					preparedStatement.execute();
					id = -1;
					
					NandU.setModel(DatabaseConnector.join());
					notesTable.setModel(DatabaseConnector.getAllNotes());
					refreshComboUsers();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						preparedStatement.close();
						connection.close();
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	
	private void clearForm()
	{
		userNameTextField.setText("");
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		chooseCombo.setSelectedIndex(0);
	}
	
	private void clearForm1()
	{
		userNameNoteTextField.setText("");
		usersCombo.setSelectedIndex(0);
	}
}
