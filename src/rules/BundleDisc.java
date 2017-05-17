package rules;

import java.util.List;

import products.Bundle;

public abstract class BundleDisc extends Discount{

	protected Bundle bundle;

	protected List<RangeBasedDiscount> discounts;
	protected static final double NO_DISCOUNT = 0;
	
	public BundleDisc(Bundle bundle) {
		this.bundle = bundle;
	}

	public void calcDiscount() {

		double discAmount = discAmount(bundle);
		bundle.setDiscAmount(bundle.getDiscAmount() + discAmount);
		
		System.out.printf("%s discount - %.2f%n", discounts.get(0).getType(), discAmount);
		System.out.println("-------------");

	}
	
//	public double getPriority() {
//		return priority;
//	}

	protected abstract double discAmount(Bundle b);
	
}
