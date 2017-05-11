package engine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DatabaseConnection;
import products.Product;
import rules.FixedDiscount;
import rules.PercentageDiscount;

public class Engine {

	public static void main(String[] args) throws SQLException {
		Statement stat = DatabaseConnection.getConnection().createStatement();
		ResultSet rs = stat.executeQuery("select * from products where name = 'tv_xl'");
		Product p = null;
		while (rs.next()) {
			p = new Product(rs.getString("name"), rs.getDouble("price"));
		}
		
		System.out.println(p);
		FixedDiscount fd = new FixedDiscount();
		PercentageDiscount pd = new PercentageDiscount();
		
		
		pd.discount(p);
		System.out.println("after percentage discount" + p);
		
		fd.discount(p);
		System.out.println("after fixed discount " + p);
	}
}
