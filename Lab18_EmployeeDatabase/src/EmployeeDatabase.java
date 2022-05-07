import java.util.*;
import java.io.*;
public class EmployeeDatabase {
   /*
   1) (input)/6 mod 11??
   2) O(1)
   3) O(n)
   */
   
   private class Entry {
      int ID;
      Employee employee;
      public Entry(int ID, Employee employee) {
         this.ID = ID;
         this.employee = employee;
      }
      
      @Override
      public String toString() {
         return ID + " - " + employee; 
      }
   }
   
   public static int INIT_CAPACITY = 100;
   
   private boolean collisionType; //true quadratic, false linear
   private Entry[] entries;
   private int capacity;
   int count;
   int collisions;
   int searches;
   
   public EmployeeDatabase(boolean collisionType) {
      this(INIT_CAPACITY,collisionType);
   }
   
   public EmployeeDatabase(int initCapacity, boolean collisionType) {
      this.collisionType = collisionType;
      entries = new Entry[initCapacity];
      capacity = initCapacity;
      count = 0;
      collisions = 0;
      searches = 0;
   }
   
   public int count() {
      return count;
   }
   
   public int collisions() {
      return collisions;
   }
   
   public int searches() {
      return searches;
   }
   
   public int hashCode(int key) {
      return key % capacity;
   }
   
   public String hashFunctionName() {
      return "Simple Modulus";
   }
   
   public void put(int key, Employee value) {
      resizeAndRehash();
      put(key,value,entries);
   }
   
   private void put(int key, Employee value, Entry[] array) {
      int index = hashCode(key);
      int step = 1;
      int increment = collisionType ? 2 : 1;
      while (array[index] != null && array[index].ID != key) {
         index = (index + step) % capacity;
         step *= increment;
      }
      if (index != hashCode(key))
         collisions++;
      array[index] = new Entry(key,value);
      count++;
   }
   
   public Employee get(int key) {
      int index = hashCode(key);
      int step = 1;
      int increment = collisionType ? 2 : 1;
      while (entries[index] != null) {
         if (entries[index].ID == key)
            return entries[index].employee;
         searches++;
         index = (index + step) % capacity;
         step *= increment;
      }
      return null;
   }
   
   public void resizeAndRehash() {
      if (count < capacity / 10)
         return;
      Entry[] newEntries = new Entry[entries.length * 2];
      capacity = newEntries.length;
      collisions = 0;
      count = 0;
      for (Entry e : entries)
         if (e != null)
            put(e.ID,e.employee,newEntries);
      entries = newEntries;
   }
}