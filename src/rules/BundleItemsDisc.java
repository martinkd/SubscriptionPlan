package rules;

import java.util.List;

import products.Bundle;
import products.Product;

public class BundleItemsDisc {

	private Bundle bundle;
	private List<RangeBasedDiscount> discounts;

	public BundleItemsDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		this.bundle = bundle;
		this.discounts = discounts;
	}
	
	public void discount() {
		
		double discAmount = discAmount(bundle);
		bundle.setPrice(getBundlePrice() - discAmount);
		System.out.println("bundle items discount - " +discAmount(bundle));
	}
	
	private double getBundlePrice() {
		if (bundle.getPrice() == 0 ) {
			double sum = 0;
			for (Product product : bundle.getProducts()) {
				sum += product.getPrice();
			}
			bundle.setPrice(sum);
		}
		return bundle.getPrice();
	}
	private double discAmount(Bundle bundle) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (bundle.getSize() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount();
			}
		}
		return 0;
	}
}
