import static java.lang.System.*;
import java.io.*;
import java.util.*;

public class PhoneBookTester {

   public static PhoneBook pb;  //stored in static variable to add persistence for the TestGUI
   public static Person[] people = {
         new Person("Michael Scott"),
         new Person("Dwight Schrute"),
         new Person("Jim Halpert"),
         new Person("Pam Beasley"),
         new Person("Creed Bratton"),
         new Person("Stanley Hudson"),
         new Person("Ryan Howard"),
         new Person("Andy Bernard"),
         new Person("Kevin Malone"),
         new Person("Meredith Palmer")
         };

   public static void main (String[] args) { 
     //to run manually, uncomment (use) only 1 of these test situations at a time      
      defaultSettings(); 
      //frequentCollisionSettings(); 
      //frequentRehashSettings();                 
      
      //Then, it will run these tests on the setup from above
      variousTestsPart1();
      printCurrentTable();
      variousTestsPart2();
      printCurrentTable();                                                               
   } 
      
   public static void defaultSettings() {
      pb = new PhoneBook(); 
   }        

   public static void frequentCollisionSettings() {
      pb = new PhoneBook(4); 
      pb.setLimit(3.00); 
   }
   
   public static void frequentRehashSettings() {
      pb = new PhoneBook(4); 
      pb.setLimit(0.75);   
   }
   
        

   /**
   * Checks size
   * Loads 10 values.
   * Checks size
   * Changes Creed's phone number twice
   * Gets 5 numbers (one of which isn't found)
   * Checks size
   */
   public static void variousTestsPart1() {            
      out.println("*** Current size of PhoneBook (should be 0)***");
      out.println(pb.size());   //prints 0
         
      out.println("\n*** Adding 10 new entries (expecting all null returns)***");
      for (int i = 0; i < people.length; i++) 
         out.println(pb.put(people[i], new PhoneNumber("555-555-000"+i)));  //prints null for each
   
      out.println("\n*** Changing Creed's number twice (shows old numbers)***");
      out.println(pb.put(people[4], new PhoneNumber("555-555-7777"))); //overwrite previous creed, prints old number
      out.println(pb.put(new Person("Creed Bratton"), new PhoneNumber("555-555-9999"))); //overwrite previous creed, prints old number
   
      out.println("\n*** Requesting 5 phone numbers (only last one null) ***");
      out.println(pb.get(people[0]));                  //prints 555-555-0000
      out.println(pb.get(people[3]));                  //prints 555-555-0003
      out.println(pb.get(people[4]));                  //prints 555-555-9999
      out.println(pb.get(new Person("Kevin Malone"))); //prints 555-555-0008
      out.println(pb.get(new Person("Goober"))); //prints null (since not in phonebook)               
         
      out.println("\n*** Current size of PhoneBook (should be 10)***");
      out.println(pb.size());   //prints 10      
   }   
   
   
  /**
   * Attempts 5 removals (last one shouldn't work because record not found)
   * Checks size
   */
   public static void variousTestsPart2() {            
      out.println("\n*** Attempting 5 removals (only 4 will work, last one null) ***");
      out.println(pb.remove(people[9]));   //remove Meredith and print her number 555-555-0009
      out.println(pb.remove(people[0]));   //remove Michael and print his number 555-555-0000
      out.println(pb.remove(new Person("Jim Halpert"))); //remove Jim and print his number 555-555-0002
      out.println(pb.remove(new Person("Ryan Howard"))); //remove Jim and print his number 555-555-0006
      out.println(pb.remove(people[0]));   //tries to remove Michael again, but prints null because not in phonebook
   
      out.println("\n*** Current size of PhoneBook (should be 6) ***");
      out.println(pb.size());   //prints 6      
   }
   
   
   public static void printCurrentTable() {
      // Printing the hashTable will look different for each student because of 
      // using different hash functions.  But it should have the correct number of entries.   
      out.println("\n*** Printing Hash Table ***");
      pb.printHashTable();   
   }
       
}