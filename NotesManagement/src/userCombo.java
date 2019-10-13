import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userCombo {
	

		static Connection conn = null;
		static PreparedStatement state = null;
		static ResultSet result = null;
		public String[] userCombo() {
			List<String> usersList = new ArrayList<>();
			
			conn = DatabaseConnector.getConnection();
			String sql = "select id, username from users";
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()) {
					usersList.add(result.getString("username"));
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String[] unames = new String[usersList.size() + 1];
			for(int i = 0; i < usersList.size(); i++){
				unames[i+1] = usersList.get(i);
			}
			
			return unames;
		}
	
}
