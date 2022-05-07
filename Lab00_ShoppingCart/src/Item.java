
public class Item {
	
	private String name;
	private double price, bulkPrice;
	private int bulkQty;
	
	private boolean hasBulkDiscount;
	
	public Item(String name, double price) {
		this(name, price, 0, 0.0);
		this.hasBulkDiscount = false;
	}
	
	public Item(String name, double price, int bulkQty, double bulkPrice) {
		if (price < 0 || bulkQty < 0 || bulkPrice < 0) {
			throw new IllegalArgumentException("error");
		}
		this.name = name;
		this.price = price;
		this.bulkQty = bulkQty;
		this.bulkPrice = bulkPrice;
		this.hasBulkDiscount = true;
	}
	
	public double priceFor(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("error");
		}
		if (this.hasBulkDiscount && quantity >= this.bulkQty) {
			return this.bulkPrice * quantity;
		} else {
			return this.price * quantity;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			return this.name.equals(((Item) obj).name);
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (this.hasBulkDiscount) {
			return this.name + ", " + "$" + this.price + " (" + this.bulkQty + " for " + this.bulkPrice + ")";
		} else {
			return this.name + ", " + "$" + this.price;
		}
	}
}
