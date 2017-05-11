package rules;

import products.Bundle;
import products.Product;

public class FixedDiscount extends RangeBasedDiscount {

	private FixedDiscount fdLow;
	private FixedDiscount fdMiddle;
	private FixedDiscount fdHigh;

	private FixedDiscount fdBundleLow;
	private FixedDiscount fdBundleMiddle;
	private FixedDiscount fdBundleHigh;

	public FixedDiscount() {
		fdLow = new FixedDiscount(10, 20, 5);
		fdMiddle = new FixedDiscount(20.01, 30, 7);
		fdHigh = new FixedDiscount(30.01, 40, 10);
		fdBundleLow = new FixedDiscount(50, 100, 15);
		fdBundleMiddle = new FixedDiscount(100.01, 150, 20);
		fdBundleHigh = new FixedDiscount(150.01, 200, 25);
	}

	public FixedDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {

		double price = p.getPrice();
		if (p instanceof Bundle) {
			if (price >= fdBundleHigh.getLow() && price <= fdBundleHigh.getHigh()) {
				price -= fdBundleHigh.getDiscount();
			} else if (price >= fdBundleMiddle.getLow() && price <= fdBundleMiddle.getHigh()) {
				price -= fdBundleMiddle.getDiscount();
			} else if (price >= fdBundleLow.getLow() && price <= fdBundleLow.getHigh()) {
				price -= fdBundleLow.getDiscount();
			}

		} else {

			if (price >= fdHigh.getLow() && price <= fdHigh.getHigh()) {
				price -= fdHigh.getDiscount();
			} else if (price >= fdMiddle.getLow() && price <= fdMiddle.getHigh()) {
				price -= fdMiddle.getDiscount();
			} else if (price >= fdLow.getLow() && price <= fdLow.getHigh()) {
				price -= fdLow.getDiscount();
			}
		}

		p.setPrice(price);
	}

}
