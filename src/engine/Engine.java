package engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import connection.DatabaseConnection;
import products.Bundle;
import products.Product;
import rules.BundleFixDisc;
import rules.BundleItemsDisc;
import rules.Discount;
import rules.ProductFixDisc;
import rules.ProductPercDisc;

public class Engine {

	public static void main(String[] args) throws SQLException {

		Engine e = new Engine();
		Bundle bundle = e.getBundle("tv_net_mob_l");

		Engine.print(bundle);

		double before = bundle.getPrice();
		System.out.printf("%s price before discount %.2f%n", bundle.getName(), before);
		System.out.println("-------------");

		e.discount(bundle);
		Engine.print(bundle);
		System.out.printf("%s price after product discounts %.2f%n", bundle.getName(), bundle.getPrice());
		System.out.println("-------------");

		double after = before - bundle.getDiscAmount();
		System.out.printf("%s price after total discount %.2f%n", bundle.getName(), after);
	}

	public void discount(Bundle bundle) {

		Queue<Discount> queue = new PriorityQueue<Discount>();
		queue.add(new ProductFixDisc(bundle));
		queue.add(new ProductPercDisc(bundle));
		queue.add(new BundleFixDisc(bundle));
		queue.add(new BundleItemsDisc(bundle));

		while (!queue.isEmpty()) {
			Discount discount = queue.poll();
			discount.calcDiscount();
		}

	}

	private static void print(Bundle bundle) {
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

	private void setProductsForBundle(Bundle bundle) throws SQLException {

		String getProductsFromBundle = "SELECT products.name, products.price " 
				+ "FROM products "
				+ "JOIN bundle_products " 
				+ "ON products.id = bundle_products.product_id " 
				+ "JOIN bundles "
				+ "ON bundles.id = bundle_products.bundle_id " 
				+ "WHERE bundles.name = ?";
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

	public List<Bundle> getAllBundles() throws SQLException {

		String getBundles = "SELECT name FROM bundles";
		PreparedStatement stat = DatabaseConnection.getConnection().prepareStatement(getBundles);
		ResultSet rs = stat.executeQuery(getBundles);

		List<Bundle> bundles = new ArrayList<Bundle>();
		while (rs.next()) {
			String name = rs.getString("name");
			Bundle bundle = new Bundle();
			bundle.setName(name);
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

}
