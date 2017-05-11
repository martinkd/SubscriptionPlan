package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private DatabaseConnection db;
	private static Connection conn;

	private DatabaseConnection() {
		if (db == null) {
			db = new DatabaseConnection();
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/subscription_plan?"
						+ "user=martin&password=scooby1961&" + "useUnicode=true&characterEncoding=UTF-8&useSSL=false");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
