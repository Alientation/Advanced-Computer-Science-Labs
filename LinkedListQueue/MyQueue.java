public class MyQueue<T> implements QueueADT<T>{
   private MyGenericLinkedList<T> queue;
   
   public MyQueue() {
      queue = new MyGenericLinkedList<T>();
   }
   
   public boolean isEmpty() {
      return queue.size() == 0;
   }
   
   public void offer(T item) {
      queue.add(item);
   }
   
   public T poll() {
      return queue.remove(0);
   }
   
   public T peek() {
      return queue.get(0);
   }
   
   public int size() {
      return queue.size();
   }
   
   public void clear() {
      queue.clear();
   }
   
}