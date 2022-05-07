public class GemList 
{
	public static void main(String [] args)
	{
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
	}
   
   private class Node {
      private Gem gem;
      private Node next;
      
      public Node(Gem gem, Node next) {
         this.gem = gem;
         this.next = next;
      }
   }
   
   private Node head;
   private int size;
      
   public int size() {
      return size;
   }
   
   public void draw(double y) {
      Node node = head;
      int count = 1;
      while (node != null) {
         node.gem.draw(count/16.0 - 1.0 / 32,y);
         count++;
         node = node.next;
      }
   }
   
   @Override
   public String toString() {
      if (head == null)
         return "";
      String str = "";
      Node node = head;
      while (node.next != null) {
         str = str + node.gem.getType() + " -> ";
         node = node.next;
      }
      return str + node.gem.getType();
   }
   
   public void insertBefore(Gem gem, int index) {
      Node node = head;
      //index--;
      if (index <= 0 || size == 0) {
         head = new Node(gem,head);
         size++;
         return;
      }
      
      for (int i = 0; i < Math.min(index,size) - 1; i++) {
         node = node.next;
      }
      
      Node temp = node.next;
      node.next = new Node(gem,temp);
      size++;
      
   }
   
   public void deleteAt(int index) {
      if (index < 0 || index >= size) {
         return;
      }
      Node node = head;
      size--;
      if (index == 0) {
         head = head.next;
         return;
      }
      for (int i = 0; i < index-1; i++) {
         node = node.next;
      }
      if (index == size) {
         node.next = null;
         return;
      }
      node.next = node.next.next;
      return;
   }
   
   public int score() {
      int score = 0;
      
      int subScore = 0;
      int count = 0;
      GemType type = null;
      Node node = head;
      while (node != null) {
         if (node.gem.getType() == type) {
            count++;
            subScore += node.gem.getPoints();
         } else {
            score += subScore * count;
            count = 1; subScore = node.gem.getPoints();
            type = node.gem.getType();
         }
         node = node.next;
      }
      return score + subScore * count;
   }
   
   
}
