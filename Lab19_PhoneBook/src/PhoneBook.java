public class PhoneBook implements IMap{
   
   private Entry[] entries;
   private int size = 0;
   private double limit = 5;
   
   private class Entry{
      public Person pers;
      public PhoneNumber phon;
      public Entry next;
      
      public Entry(Person p, PhoneNumber pn){
         pers = p;
         phon = pn;
      }
      
      @Override
      public String toString(){
         return "Person: " + pers + "; Phone Number: " + phon;
      }
   }
   
   public PhoneBook(){
      entries = new Entry[10];
   }
   
   public PhoneBook(int num){
      entries = new Entry[num];
   }
   
   public int hashCode(Person key){
      return key.hashCode() % entries.length;
      
   }
   
   public PhoneNumber put(Person key, PhoneNumber value){
      if (size == limit * entries.length)
         resizeAndRehash();
      
      size++;
      int pos = hashCode(key);
      PhoneNumber newNum = null;
      Entry current = entries[pos];
      Entry add = new Entry(key, value);
      
      
      
      if  (current == null){
         entries[pos] = add;
         return null;
      }
      while (current.next != null && !current.pers.equals(key)){
         current = current.next;
      }
      
      if (current == null)
         return null;
      
      if (current.pers.equals(key)){
         newNum = current.phon;
         current.phon = value;
         size--;
      }
      else{
         current.next = new Entry(key,value);
         return null;
      }
      return newNum;
   }
   
   public PhoneNumber get(Person key){
      int pos = hashCode(key);
      Entry current = entries[pos];
      
      if (current == null)
         return null;
      
      if (current.pers.equals(key))
         return current.phon;
      while (current.next != null && !current.pers.equals(key)){
         current = current.next;
      }
      if (current.pers.equals(key) == false)
         return null;
      else{
         return current.phon;
      }
   }
   
   public PhoneNumber remove(Person key){
      int pos = hashCode(key);
      Entry current = entries[pos];
      PhoneNumber ret = null;
      
      if (current == null)
         return null;
         
      if (entries[pos].pers.equals(key)) {
         PhoneNumber temp = entries[pos].phon;
         entries[pos] = entries[pos].next;
         size--;
         return temp;
      }
      
      while (current.next != null){
         if (current.next.pers.equals(key)){
            ret = current.next.phon;
            current.next = current.next.next;
            break;
         }
         else{
            current = current.next;
         }
      }
      size--;
      return ret;
   }
   
   public int size(){
      return size;
   }
   
   public void setLimit(double percentage){
      limit = percentage;
   }
   
   public void resizeAndRehash(){
      
      PhoneBook copy = new PhoneBook(entries.length * 2);
      
      for (int i = 0; i < entries.length; i++){
      Entry current = entries[i];
         while (current != null){
            copy.put(current.pers, current.phon);
            current = current.next;
         }
      }
      this.entries = copy.entries;
   }
   
   public void printHashTable(){
      
      for (int i = 0; i < entries.length; i++){
         Entry current  = entries[i];
         if (entries[i] == null)
            System.out.println("Index " + i + " >> " + "Empty");
         else{
            System.out.print("Index " + i);
            while (current != null){
               System.out.print(" >> " + current.pers.toString() + " " + current.phon.toString());
               current = current.next;
            }
            System.out.println();
         }
      }
   }
}