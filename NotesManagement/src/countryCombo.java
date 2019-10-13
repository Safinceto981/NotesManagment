
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class countryCombo{
	

		static Connection conn = null;
		static PreparedStatement state = null;
		static ResultSet result = null;
		public String[] countryCombo() {
			List<String> countryList = new ArrayList<>();
			
			conn = DatabaseConnector.getConnection();
			String sql = "select countryName from city";
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				while(result.next()) {
					countryList.add(result.getString("COUNTRYNAME"));
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String[] cnames = new String[countryList.size() + 1];
			for(int i = 0; i < countryList.size(); i++){
				cnames[i+1] =countryList.get(i);
			}
			
			return cnames;
		}
	
}
