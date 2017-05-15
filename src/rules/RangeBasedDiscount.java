package rules;

public class RangeBasedDiscount {

	private String type;
	private double range;
	private double discount;

	public RangeBasedDiscount() {
	}

	public RangeBasedDiscount(String type, double range, double discount) {

		this.type = type;
		this.range = range;
		this.discount = discount;

	}

	public double getRange() {
		return range;
	}

	public double getDiscount() {
		return discount;
	}

	public String getType() {
		return type;
	}

}
