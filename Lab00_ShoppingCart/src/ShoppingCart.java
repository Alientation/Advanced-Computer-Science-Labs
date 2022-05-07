import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<ItemOrder> itemOrders;
	public ShoppingCart() {
		 this.itemOrders = new ArrayList<ItemOrder>();
	}
	
	public void add(ItemOrder newOrder) {
		if (this.itemOrders.contains(newOrder)) {
			for (int i = 0; i < this.itemOrders.size(); i++) {
				if (this.itemOrders.get(i).equals(newOrder)) {
					this.itemOrders.remove(i);
					break;
				}
			}
		}
		this.itemOrders.add(newOrder);
	}
	
	public double getTotal() {
		double sum = 0;
		for (int i = 0; i < this.itemOrders.size(); i++) {
			sum += itemOrders.get(i).getPrice();
		}
		return sum;
	}
}
