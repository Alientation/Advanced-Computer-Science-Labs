import java.util.ArrayList;
import java.util.List;

public class Catalog {
	private String name;
	private List<Item> items;
	public Catalog(String name) {
		this.name = name;
		this.items = new ArrayList<Item>();
	}
	
	public void add(Item item) {
		this.items.add(item);
	}
	
	public int size() {
		return this.items.size();
	}
	
	public Item get(int index) {
		return this.items.get(index);
	}
	
	public String getName() {
		return this.name;
	}
}
