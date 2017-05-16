package rules;

import java.util.List;

import products.Bundle;

public abstract class BundleDisc {

	protected Bundle bundle;
	protected List<RangeBasedDiscount> discounts;
	protected static final double NO_DISCOUNT = 0;
	
	public BundleDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		this.bundle = bundle;
		this.discounts = discounts;
	}

	public void discount() {

		double discAmount = discAmount(bundle);
		bundle.setDiscAmount(bundle.getDiscAmount() + discAmount);
		
		System.out.printf("%s discount - %.2f%n", discounts.get(0).getType(), discAmount);
		System.out.println("-------------");

	}

	protected abstract double discAmount(Bundle b);
	
}
