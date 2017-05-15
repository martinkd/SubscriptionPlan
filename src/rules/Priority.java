package rules;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;

public class Priority {

	private List<Discount> priorityOrder;

	public List<Discount> getPriorityOrder() throws SQLException {

		String query = "SELECT type FROM priority ORDER BY rank";
		Statement stat = DatabaseConnection.getConnection().createStatement();
		ResultSet rs = stat.executeQuery(query);
		priorityOrder = new ArrayList<Discount>();
		while (rs.next()) {
			String type = rs.getString("type").toUpperCase();
			priorityOrder.add(Discount.valueOf(type));
		}

		return priorityOrder;

	}
}
