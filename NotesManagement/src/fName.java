import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class fName {

	static Connection conn = null;
	static PreparedStatement state = null;
	static ResultSet result = null;
	public String[] fName() {
		List<String> fNameList = new ArrayList<>();
		
		conn = DatabaseConnector.getConnection();
		String sql = "select id,firstname from users";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			while(result.next()) {
				fNameList.add(result.getString("firstname"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] fnames = new String[fNameList.size() + 1];
		for(int i = 0; i < fNameList.size(); i++){
			fnames[i+1] = fNameList.get(i);
		}
		
		return fnames;
	}

}
