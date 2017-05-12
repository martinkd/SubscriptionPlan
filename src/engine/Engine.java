package engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connection.DatabaseConnection;
import products.Bundle;
import products.Product;
import rules.BundleFixDisc;
import rules.BundleItemsDisc;
import rules.Priority;
import rules.ProductFixDisc;
import rules.ProductPercDisc;
import rules.RangeBasedDiscount;

public class Engine {

	public static void main(String[] args) throws SQLException {

		Engine e = new Engine();
		Bundle bundle = e.getBundle("mobile_net_l");
		Engine.printBundle(bundle);
		e.discount(bundle);
		Engine.printBundle(bundle);
		System.out.printf("%s price after total discount %.2f%n", bundle.getName(), bundle.getPrice());
	}

	public void discount(Bundle b) {

		Priority priority = new Priority();
		try {
			Map<Integer, String> order = priority.getPriorityOrder();
			for (int i = 1; i <= order.size(); i++) {

				doDiscount(b, order.get(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doDiscount(Bundle bundle, String type) throws SQLException {

		List<RangeBasedDiscount> discounts = getDiscounts(type);

		switch (type) {
		case "product_fix":
			new ProductFixDisc(bundle, discounts).discount();
			break;
		case "product_perc":
			new ProductPercDisc(bundle, discounts).discount();
			break;
		case "bundle_fix":
			new BundleFixDisc(bundle, discounts).discount();
			break;
		case "bundle_items":
			new BundleItemsDisc(bundle, discounts).discount();
			break;
		default:
			break;
		}

	}

	private static void printBundle(Bundle bundle) {
		System.out.println(bundle.getName());
		for (Product p : bundle.getProducts()) {
			System.out.println(p);
		}
		System.out.println("-------------");
	}

	public Bundle getBundle(String name) throws SQLException {
		Bundle bundle = new Bundle();
		bundle.setName(name);
		setProductsForBundle(bundle);
		return bundle;
	}

	public List<Bundle> getAllBundles() throws SQLException {

		String getBundles = "SELECT name FROM bundles";
		Statement stat = DatabaseConnection.getConnection().createStatement();
		ResultSet rs = stat.executeQuery(getBundles);

		List<Bundle> bundles = new ArrayList<Bundle>();
		while (rs.next()) {
			String name = rs.getString("name");
			Bundle bundle = new Bundle(name);
			bundles.add(bundle);
		}

		setProductsForAllBundles(bundles);

		return bundles;
	}

	private void setProductsForAllBundles(List<Bundle> bundles) throws SQLException {

		for (Bundle bundle : bundles) {
			setProductsForBundle(bundle);
		}
	}

	private void setProductsForBundle(Bundle bundle) throws SQLException {

		String getProductsFromBundle = "SELECT products.name, products.price " + "FROM products "
				+ "JOIN bundle_products " + "ON products.id = bundle_products.product_id " + "JOIN bundles "
				+ "ON bundles.id = bundle_products.bundle_id " + "WHERE bundles.name = ?";
		PreparedStatement prepSt = DatabaseConnection.getConnection().prepareStatement(getProductsFromBundle);
		prepSt.setString(1, bundle.getName());
		ResultSet rs = prepSt.executeQuery();

		while (rs.next()) {
			String name = rs.getString("name");
			double price = rs.getDouble("price");
			Product product = new Product(name, price);
			bundle.add(product);
		}
	}

	private List<RangeBasedDiscount> getDiscounts(String type) throws SQLException {

		String query = "SELECT disc_range, discount " + "FROM discounts " + "WHERE type = ?"
				+ "ORDER BY disc_range DESC";
		PreparedStatement prepSt = DatabaseConnection.getConnection().prepareStatement(query);
		prepSt.setString(1, type);
		ResultSet rs = prepSt.executeQuery();
		List<RangeBasedDiscount> discounts = new ArrayList<RangeBasedDiscount>();
		while (rs.next()) {
			double range = rs.getDouble("disc_range");
			double discount = rs.getDouble("discount");
			RangeBasedDiscount rbd = new RangeBasedDiscount(type, range, discount);
			discounts.add(rbd);
		}

		return discounts;
	}
}
