package rules;

import products.Bundle;
import products.Product;

public class NumberItemsDiscount extends FixedDiscount {

	private FixedDiscount fdNumberItemsFew;
	private FixedDiscount fdNumberItemsMany;

	public NumberItemsDiscount() {
		 fdNumberItemsFew = new NumberItemsDiscount(2, 3, 10);
		 fdNumberItemsMany = new NumberItemsDiscount(4, 5, 20);
	}
	
	public NumberItemsDiscount(double low, double high, double discount) {
		super(low, high, discount);
	}

	@Override
	public void discount(Product p) {

		if (p instanceof Bundle) {

			int itemsCount = ((Bundle) p).getSize();
			double price = p.getPrice();

			if (itemsCount >= fdNumberItemsFew.getLow() && itemsCount <= fdNumberItemsFew.getHigh()) {
				price -= fdNumberItemsFew.getDiscount();
			} else if (itemsCount >= fdNumberItemsMany.getLow() && itemsCount <= fdNumberItemsMany.getHigh()) {
				price -= fdNumberItemsMany.getDiscount();
			}

			p.setPrice(price);
			
		}

	}
}
