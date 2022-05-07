import java.lang.reflect.Array;
public class MyHashTable<K,V>{
   class Entry {
      K key;
      V value;
      Entry next;
      
      public Entry(K key, V value) {
         this(key,value,null);
      }
      
      public Entry(K key, V value, Entry next) {
         this.key = key;
         this.value = value;
         this.next = next;
      }
   }
   public final static int INIT_CAPACITY = 11;
   
   Entry[] entries;
   int size = 0;
   double doubleUpReq = 0.1;
   
   public MyHashTable() {
      this(INIT_CAPACITY);
   }
   
   public MyHashTable(int cap) {
      entries = (Entry[]) Array.newInstance(Entry.class,cap);
   }
   
   public void setLimit(double percentage) {
      this.doubleUpReq = percentage;
   }
   
	public V put(K key, V value) {
      resizeAndRehash();
      return put(key,value,entries);
   }
   
   private V put(K key, V value, Entry[] entries) {
      int index = key.hashCode() % entries.length;
      if (entries[index] == null) {
         entries[index] = new Entry(key,value);
         size++;
         return null;
      }
      Entry node = entries[index];
      V prev;
      if (node.key.equals(key)) {
         prev = node.value;
         node.value = value;
         return prev;
      }
      while(node.next != null && !node.next.key.equals(key))
         node = node.next;
      prev = node.next == null ? null : node.next.value;
      node.next = new Entry(key,value, node.next == null ? null : node.next.next);
      if (prev == null)
         size++;
      return prev;
   }

	public V get(K key) {
      int index = key.hashCode() % entries.length;
      Entry node = entries[index];
      while (node != null && !node.key.equals(key))
         node = node.next;
      return node == null ? null : node.value;
   }
   
   public void resizeAndRehash() {
      if (size < entries.length * doubleUpReq)
         return;
      Entry[] newEntries = (Entry[]) Array.newInstance(Entry.class,entries.length * 2);
      size = 0;
      for (Entry e : entries)
         while (e != null) {
            put(e.key,e.value,newEntries);
            e = e.next;
         }
      entries = newEntries;
   }

	public int size() {
      return size;
   }
	
	public V remove(K key) {
      int index = key.hashCode() % entries.length;
      if (entries[index] == null)
         return null;
      V temp;
      if (entries[index].key.equals(key)) {
         temp = entries[index].value;
         entries[index] = entries[index].next;
         size--;
         return temp;
      }
      Entry node = entries[index];
      while (node.next != null && !node.next.key.equals(key))
         node = node.next;
      if (node.next == null)
         return null;
      temp = node.next.value;
      node.next = node.next.next;
      size--;
      return temp;
   }
   
   public void printHashTable() {
      Entry node;
      for (int i = 0; i < entries.length; i++) {
         System.out.print("Index " + i);
         node = entries[i];
         if (node == null)
            System.out.print(" >> Empty");
         while (node != null) {
            System.out.print(" >> " + node.key + " " + node.value);
            node = node.next;
         }
         System.out.println();
      }
   }
}