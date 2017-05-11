package rules;

import products.Product;

public abstract class RangeBasedDiscount {

	private double low;
	private double high;
	private double discount;

	public RangeBasedDiscount() {
	}
	
	public RangeBasedDiscount(double low, double high, double discount) {

		this.low = low;
		this.high = high;
		this.discount = discount;

	}

	public abstract void discount(Product p);
	
	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
