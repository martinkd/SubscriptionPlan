package rules;

public abstract class Discount implements Comparable<Discount> {
	
	private double priority;

	protected void setPriority(int priority) {
		this.priority = priority;
	}
	
	public abstract void calcDiscount();

	@Override
	public int compareTo(Discount disc) {
		if (this.priority > disc.priority) {
			return 1;
		} else if (this.priority < disc.priority) {
			return -1;
		} else {
			return 0;
		}
	}
}
