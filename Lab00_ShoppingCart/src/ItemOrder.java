
public class ItemOrder {
	private Item item;
	private int qty;
	public ItemOrder(Item item, int qty) {
		this.item = item;
		this.qty = qty;
	}
	
	public double getPrice() {
		return item.priceFor(qty);
	}
	
	public Item getItem() {
		return item;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemOrder) {
			return ((ItemOrder) obj).getItem().equals(item);
		}
		return false;
	}
}
