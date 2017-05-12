package rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import connection.DatabaseConnection;

public class Priority {
	
	private Map<Integer, String> priorityOrder;
	
	public Map<Integer, String> getPriorityOrder() throws SQLException {
		
		String query = "SELECT * FROM priority";
		Statement stat = DatabaseConnection.getConnection().createStatement();
		ResultSet rs = stat.executeQuery(query);
		priorityOrder = new HashMap<Integer, String>();
		while (rs.next()) {
			int rank = rs.getInt("rank");
			String type = rs.getString("type");
			priorityOrder.put(rank, type);
		}
		
		return priorityOrder;
		
	}
}
