package rules;

import java.util.List;

import products.Bundle;

public class BundleItemsDisc extends BundleDisc{

	public BundleItemsDisc(Bundle bundle, List<RangeBasedDiscount> discounts) {
		super(bundle, discounts);
	}
	
	@Override
	protected double discAmount(Bundle bundle) {
		for (RangeBasedDiscount rangeBasedDiscount : discounts) {
			if (bundle.getSize() >= rangeBasedDiscount.getRange()) {
				return rangeBasedDiscount.getDiscount();
			}
		}
		return 0;
	}
}
