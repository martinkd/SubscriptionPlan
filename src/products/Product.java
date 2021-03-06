package products;

public class Product {

	private String name;
	private double price;

	public Product() {
	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("%s | %.2f", name, price);
	}

}
