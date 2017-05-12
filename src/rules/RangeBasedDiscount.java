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
	
	public void setRange(double range) {
		this.range = range;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
