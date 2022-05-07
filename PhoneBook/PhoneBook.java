public class PhoneBook implements IMap {
   
   class Entry {
      Person person;
      PhoneNumber number;
      Entry next;
      
      public Entry(Person person, PhoneNumber number) {
         this(person,number,null);
      }
      
      public Entry(Person person, PhoneNumber number, Entry next) {
         this.person = person;
         this.number = number;
         this.next = next;
      }
   }
   public final static int INIT_CAPACITY = 10;
   
   Entry[] entries;
   int size = 0;
   double doubleUpReq = 0.1;
   
   public PhoneBook() {
      this(INIT_CAPACITY);
   }
   
   public PhoneBook(int cap) {
      entries = new Entry[cap];
   }
   
   public void setLimit(double percentage) {
      this.doubleUpReq = percentage;
   }
   
	public PhoneNumber put(Person person, PhoneNumber number) {
      resizeAndRehash();
      return put(person,number,entries);
   }
   
   private PhoneNumber put(Person person, PhoneNumber number, Entry[] curEntries) {
      int index = person.hashCode() % curEntries.length;
      if (curEntries[index] == null) {
         curEntries[index] = new Entry(person,number);
         size++;
         return null;
      }
      Entry node = curEntries[index];
      PhoneNumber prev;
      if (node.person.equals(person)) {
         prev = node.number;
         node.number = number;
         return prev;
      }
      while(node.next != null && !node.next.person.equals(person))
         node = node.next;
      prev = node.next == null ? null : node.next.number;
      node.next = new Entry(person,number, node.next == null ? null : node.next.next);
      if (prev == null)
         size++;
      return prev;
   }

	public PhoneNumber get(Person person) {
      int index = person.hashCode() % entries.length;
      Entry node = entries[index];
      while (node != null && !node.person.equals(person))
         node = node.next;
      return node == null ? null : node.number;
   }
   
   public void resizeAndRehash() {
      if (size < entries.length * doubleUpReq)
         return;
      Entry[] newEntries = new Entry[entries.length * 2];
      size = 0;
      for (Entry e : entries)
         while (e != null) {
            put(e.person,e.number,newEntries);
            e = e.next;
         }
      entries = newEntries;
   }

	public int size() {
      return size;
   }
	
	public PhoneNumber remove(Person person) {
      int index = person.hashCode() % entries.length;
      if (entries[index] == null)
         return null;
      PhoneNumber temp;
      if (entries[index].person.equals(person)) {
         temp = entries[index].number;
         entries[index] = entries[index].next;
         size--;
         return temp;
      }
      Entry node = entries[index];
      while (node.next != null && !node.next.person.equals(person))
         node = node.next;
      if (node.next == null)
         return null;
      temp = node.next.number;
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
            System.out.print(" >> " + node.person + " " + node.number);
            node = node.next;
         }
         System.out.println();
      }
   }
}