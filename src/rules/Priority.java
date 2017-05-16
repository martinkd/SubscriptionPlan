package rules;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;

public class Priority {

	private List<DiscountType> priorityOrder;

	public List<DiscountType> getPriorityOrder() throws SQLException {

		String query = "SELECT type FROM priority ORDER BY rank";
		PreparedStatement stat = DatabaseConnection.getConnection().prepareStatement(query);
		ResultSet rs = stat.executeQuery(query);
		priorityOrder = new ArrayList<DiscountType>();
		while (rs.next()) {
			String type = rs.getString("type").toUpperCase();
			priorityOrder.add(DiscountType.valueOf(type));
		}

		return priorityOrder;

	}
}
