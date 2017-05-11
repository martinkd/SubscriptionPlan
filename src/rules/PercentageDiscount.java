package rules;

import products.Product;

public class PercentageDiscount extends RangeBasedDiscount {

	private PercentageDiscount pdLow;
	private PercentageDiscount pdMiddle;
	private PercentageDiscount pdHigh;

	public PercentageDiscount() {
		pdLow = new PercentageDiscount(15, 25, 20);
		pdMiddle = new PercentageDiscount(25.01, 35, 25);
		pdHigh = new PercentageDiscount(35.01, 45, 30);
	}

	public PercentageDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {

		double price = p.getPrice();

		if (price >= pdHigh.getLow() && price <= pdHigh.getHigh()) {
			price -= discountSum(pdHigh.getDiscount(), price);
		} else if (price >= pdMiddle.getLow() && price <= pdMiddle.getHigh()) {
			price -= discountSum(pdMiddle.getDiscount(), price);
		} else if (price >= pdLow.getLow() && price <= pdLow.getHigh()) {
			price -= discountSum(pdLow.getDiscount(), price);
		}

		p.setPrice(price);

	}

	private double discountSum(double discount, double price) {
		return (discount / 100.0) * price;
	}
}
