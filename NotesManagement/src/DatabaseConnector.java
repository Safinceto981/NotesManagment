import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector
{
	static Connection conn = null;
	static ResultSet result = null;
	static Model model = null;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/C:/Users/safi_/Desktop/projects/NotesManagment", "safi", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Model getAllUsers() {
		String sql = "SELECT * FROM USERS;";
		conn = getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new Model(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	
	public static Model join() {
		conn = DatabaseConnector.getConnection();
		String sql = "SELECT users.userNAME as username , users.firSTNAME as firstname, users.lastname as lastname,notes.noTESTEXT as notes,users.country as country\r\n" + 
				"FROM users\r\n" + 
				"INNER JOIN notes\r\n" + 
				"ON users.username=notes.username;\r\n" + 
				"";
		try {
			PreparedStatement state = conn.prepareStatement(sql);

			result = state.executeQuery();
			model = new Model(result);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	public static Model join2() {
		conn = DatabaseConnector.getConnection();
		String sql = "SELECT users.userNAME as username , users.firSTNAME as firstname , users.lastname as lastname,users.country as country,city.cityname as city\r\n" + 
				"FROM users\r\n" + 
				"INNER JOIN city\r\n" + 
				"ON users.country=city.countryname;\r\n" + 
				"";
		try {
			PreparedStatement state = conn.prepareStatement(sql);

			result = state.executeQuery();
			model = new Model(result);
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	public static Model getAllNotes() {
		String sql = "SELECT * FROM NOTES;";
		conn = getConnection();
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new Model(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	
}
