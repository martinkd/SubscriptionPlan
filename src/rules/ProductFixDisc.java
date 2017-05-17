package rules;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DatabaseConnection;
import products.Bundle;
import products.Product;

public class ProductFixDisc extends ProductDisc {

	private static final String type = "product_fix";
	
	public ProductFixDisc(Bundle bundle) {
		super(bundle);
	}

	@Override
	protected double discAmount(Product p) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (p.getPrice() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount();
			}
		}
		return NO_DISCOUNT;
	}

	{
		try {
			String sql = "SELECT rank, disc_range, discount "
					+ "FROM priority "
					+ "JOIN discounts "
					+ "ON priority.type = discounts.type "
					+ "WHERE priority.type = ? "
					+ "ORDER BY disc_range DESC";
			PreparedStatement prepSt = DatabaseConnection.getConnection().prepareStatement(sql);
			prepSt.setString(1, type);
			ResultSet rs = prepSt.executeQuery();
			discounts = new ArrayList<>();
			while (rs.next()) {
				setPriority(rs.getInt("rank"));
				double range = rs.getDouble("disc_range");
				double discount = rs.getDouble("discount");
				RangeBasedDiscount rbd = new RangeBasedDiscount(type, range, discount);
				discounts.add(rbd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
