package rules;

import java.util.List;

import products.Bundle;

public class BundleFixDisc extends BundleDisc{
	
	public BundleFixDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		super(bundle, discounts);
	}
	
	@Override
	protected double discAmount(Bundle bundle) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (bundle.getPrice() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount();
			}
		}
		return NO_DISCOUNT;
	}
}
