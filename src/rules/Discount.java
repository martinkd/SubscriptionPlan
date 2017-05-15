package rules;

public enum Discount {

	PRODUCT_FIX("product_fix"),
	PRODUCT_PERC("product_perc"),
	BUNDLE_FIX("bundle_fix"),
	BUNDLE_ITEMS("bundle_items");

	private String type;

	private Discount(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
