package engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DatabaseConnection;
import products.Bundle;
import products.Product;
import rules.BundleFixDisc;
import rules.BundleItemsDisc;
import rules.DiscountType;
import rules.Priority;
import rules.ProductFixDisc;
import rules.ProductPercDisc;
import rules.RangeBasedDiscount;

public class Engine {

	public static void main(String[] args) throws SQLException {

		Engine e = new Engine();
		Bundle bundle = e.getBundle("tv_net_xl");
		
		Engine.printBundle(bundle);
		
		double before = bundle.getPrice();
		System.out.printf("%s price before discount %.2f%n",bundle.getName(), before);
		System.out.println("-------------");
		
		e.discount(bundle);
		Engine.printBundle(bundle);
		System.out.printf("%s price after product discounts %.2f%n",bundle.getName(), bundle.getPrice());
		System.out.println("-------------");
		
		double after = before - bundle.getDiscAmount();
		System.out.printf("%s price after total discount %.2f%n", bundle.getName(), after);
	}

	public void discount(Bundle bundle) {

		try {
			
			Priority priority = new Priority();
			List<DiscountType> order = priority.getPriorityOrder();
			
			for (DiscountType type : order) {
				doDiscount(bundle, type);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void doDiscount(Bundle bundle, DiscountType type) throws SQLException {

		List<RangeBasedDiscount> discounts = getDiscounts(type.getType());

		switch (type) {
		case PRODUCT_FIX:
			new ProductFixDisc(bundle, discounts).discount();
			break;
		case PRODUCT_PERC:
			new ProductPercDisc(bundle, discounts).discount();
			break;
		case BUNDLE_FIX:
			new BundleFixDisc(bundle, discounts).discount();
			break;
		case BUNDLE_ITEMS:
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
