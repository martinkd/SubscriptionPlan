package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public abstract class BundleDisc {

	protected Bundle bundle;
	protected List<RangeBasedDiscount> discounts;

	public BundleDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		this.bundle = bundle;
		this.discounts = discounts;
	}

	public void discount() {

		double discAmount = discAmount(bundle);
		bundle.setPrice(getBundlePrice() - discAmount);
		System.out.printf("%s discount - %.2f%n", discounts.get(0).getType(), discAmount);
		System.out.println("-------------");

	}

	protected double getBundlePrice() {
		if (bundle.getPrice() == 0) {
			double sum = 0;
			for (Product product : bundle.getProducts()) {
				sum += product.getPrice();
			}
			bundle.setPrice(sum);
		}
		return bundle.getPrice();
	}

	protected abstract double discAmount(Bundle b);
}
