import java.util.*;
import java.io.*;

public class TestRunnerOpenHashing extends TestGUI.TestData{ 
   public static void main (String[] args) {        
      new TestGUI();   
   }

   public static void runTests() {  //Your test sequence must be within a method called runTests()
      setTimeOutSec(3);
   
      header("Open Hashing");
      srcButton("PhoneBook, Person, PhoneNumber");
   
      message("For each of the 3 tests situations below, we are using the same test script sequence from PhoneBookTester.java.  If something doesn't work correctly, look at PhoneBookTester.java to see how the tests are being run so you can fix your mistake.");
      message("Because different students use different hashing funtions, printing the table will look different for each student.  It should show you exactly what is in your structure, just like is shown in the instructions.");
      header("Default PhoneBook");
      message("Testing with default phonebook settings.  (Initial capacity and fill limit not set.  Coder chosen defaults are used.)");          
      testMethod("PhoneBookTester", "defaultSettings", new Object[]{});         
      testMethod("PhoneBookTester", "variousTestsPart1", new Object[]{}, "*** Current size of PhoneBook (should be 0)***\n0\n\n*** Adding 10 new entries (expecting all null returns)***\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\n\n*** Changing Creed's number twice (shows old numbers)***\n555-555-0004\n555-555-7777\n\n*** Requesting 5 phone numbers (only last one null) ***\n555-555-0000\n555-555-0003\n555-555-9999\n555-555-0008\nnull\n\n*** Current size of PhoneBook (should be 10)***\n10");                     
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
      testMethod("PhoneBookTester", "variousTestsPart2", new Object[]{}, "\n*** Attempting 5 removals (only 4 will work, last one null) ***\n555-555-0009\n555-555-0000\n555-555-0002\n555-555-0006\nnull\n\n*** Current size of PhoneBook (should be 6) ***\n6");                           
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
   
      header("Collision PhoneBook");
      message("Testing a PhoneBook with an initial capacity of 4 and LoadFactor of 300% (3.0).  This will mean that it will NOT resize/rehash when loading 10 entries.  We are forcing frequent collisions.");         
      testMethod("PhoneBookTester", "frequentCollisionSettings", new Object[]{});         
      testMethod("PhoneBookTester", "variousTestsPart1", new Object[]{}, "*** Current size of PhoneBook (should be 0)***\n0\n\n*** Adding 10 new entries (expecting all null returns)***\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\n\n*** Changing Creed's number twice (shows old numbers)***\n555-555-0004\n555-555-7777\n\n*** Requesting 5 phone numbers (only last one null) ***\n555-555-0000\n555-555-0003\n555-555-9999\n555-555-0008\nnull\n\n*** Current size of PhoneBook (should be 10)***\n10");                     
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
      testMethod("PhoneBookTester", "variousTestsPart2", new Object[]{}, "\n*** Attempting 5 removals (only 4 will work, last one null) ***\n555-555-0009\n555-555-0000\n555-555-0002\n555-555-0006\nnull\n\n*** Current size of PhoneBook (should be 6) ***\n6");
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
               
      header("Rehash Phonebook");
      message("Testing a PhoneBook with an initial capacity of 4 and LoadFactor of 75%.  This will mean that it WILL resize/rehash (probably twice) when loading 10 entries.");
      testMethod("PhoneBookTester", "frequentRehashSettings", new Object[]{});         
      testMethod("PhoneBookTester", "variousTestsPart1", new Object[]{}, "*** Current size of PhoneBook (should be 0)***\n0\n\n*** Adding 10 new entries (expecting all null returns)***\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\nnull\n\n*** Changing Creed's number twice (shows old numbers)***\n555-555-0004\n555-555-7777\n\n*** Requesting 5 phone numbers (only last one null) ***\n555-555-0000\n555-555-0003\n555-555-9999\n555-555-0008\nnull\n\n*** Current size of PhoneBook (should be 10)***\n10");                     
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
      testMethod("PhoneBookTester", "variousTestsPart2", new Object[]{}, "\n*** Attempting 5 removals (only 4 will work, last one null) ***\n555-555-0009\n555-555-0000\n555-555-0002\n555-555-0006\nnull\n\n*** Current size of PhoneBook (should be 6) ***\n6");
      testMethod("PhoneBookTester", "printCurrentTable", new Object[]{});
         
   
   }    

}