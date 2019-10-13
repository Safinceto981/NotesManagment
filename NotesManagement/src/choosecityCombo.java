import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class choosecityCombo {

	static Connection conn = null;
	static PreparedStatement state = null;
	static ResultSet result = null;
	public String[] choosecityCombo() {
		List<String> cityList = new ArrayList<>();
		
		conn = DatabaseConnector.getConnection();
		String sql = "select id, cityname,countryname from city";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				cityList.add(result.getString("cityname"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] cnames = new String[cityList.size() + 1];
		for(int i = 0; i < cityList.size(); i++){
			cnames[i+1] = cityList.get(i);
		}
		
		return cnames;
	}
	

}
