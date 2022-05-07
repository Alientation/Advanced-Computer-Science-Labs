import java.io.*;
import java.util.*;

public class Tester {

   /*  PLEASE READ
      
  There is more than 1 way to implement all of this, so don't worry 
  about your code looking different that other students.  I won't put 
  emphasis on efficiency. (Closed hashing are inefficient by nature!)

  At minimum:
   You should have an Employee class
   and an EmployeeDatabase class (with inner class of Entry)
   
   I will look at your EmployeeDatabase class to see if you 
   have correctly implemented both Linear probing and Quadratic probing.      
   
   Linear probing means that you first get your hashCode.  If that spot in
   your array is taken, just increment to the next one until you find
   an empty spot. 
   
   Quadratic probing means that you first get your hashCode.  If that spot
   in your array is taken, you use a quadratic equation to figure out
   where to go next.
   int spot = hashCode(key); //try the obvious spot
   if it's unavailable, try
   spot = hashCode(key) + 1 + 1*1; // h(k) + i + i^2, where i is 1
   if that's unavailable, try
   spot = hashCode(key) + 2 + 2*2; // h(k) + i + i^2, where i is 2 
   
   Other tips: I admit, my teacher solution isn't very
   efficient.  I start with an array size of 11 and simply resizeAndRehash 
   anytime "spot" exceeds the size.  Using Linear probing on the
   biggerTest() I get over 52 million collision.  By using the quadratic
   probing, that is reduced to .7 million.  This can probably be improved by 
   calling resizeAndRehash earlier (but not too frequently) and by keeping 
   capacity at prime numbers.
   
   I have not yet coded a full performance test, and probably won't.  You
   peeps who are bored are welcome to try it though.       
   
   */


   public static void main (String[] args) throws FileNotFoundException {            
      
      //This should work for everyone
      simpleTest(false); //simple test with linear probing
      simpleTest(true);  //simple test with quadratic probing
      
      //Optional
      biggerTest(false); //large test (reads data file) using linear probing
      biggerTest(true); //large test (reads data file) using quadratic probing
      
      //Super Optional
      //performanceTest();                     
   }     
   
   
   public static void simpleTest(boolean useQuad) {
      System.out.print("SIMPLE TEST - ");
      System.out.println(useQuad ? "using quadratic probing" : "using linear probing");
      EmployeeDatabase ed = new EmployeeDatabase(useQuad);
      ed.put(123, new Employee("Fred Java"));
      ed.put(234, new Employee("Jim Halpert"));
      ed.put(133, new Employee("Pam Beasley"));
      ed.put(224, new Employee("Stanley Hudson"));
      ed.put(224, new Employee("Creed")); //overwrite stanley with creed
   
      System.out.println(ed.get(133)); //pam
      System.out.println(ed.get(224)); //creed
      System.out.println(ed.get(123)); //fred
      System.out.println(ed.get(234)); //jim
      System.out.println(ed.get(999)); //null
      System.out.println();
   }
   
   
   public static void biggerTest(boolean useQuad) {
      System.out.print("BIGGER TEST - ");
      System.out.println(useQuad ? "using quadratic probing" : "using linear probing");
      EmployeeDatabase ed = new EmployeeDatabase(useQuad);
      
      try {
         Scanner file = new Scanner(new File("Large Data Set.txt"));
         while (file.hasNextInt()) {
            int id = file.nextInt();
            String name = file.nextLine().trim();
            ed.put(id, new Employee(name));
         }  
      } catch (IOException e) { System.out.println("Something went wrong in file read."); }
      
      System.out.println(ed.get(10000)); // James Butt
      System.out.println(ed.get(99999)); // Patsy Lerper 
      System.out.println(ed.get(59659)); // Scott Zellers
      System.out.println(ed.get(87919)); // Kent Grishaber  
      
      //System.out.println(ed.collisions());
      //System.out.println();
   }
   
   
   public static void performanceTest() throws FileNotFoundException { 
      //code this part (if you want) like it says in EmployeeDatabase.docx
      String[] files = {"Large Data Set.txt","Successful Search Records.txt", "Unsuccessful Search Records.txt"};
      double[] loadFactors = {0.1,0.5,0.8,0.9,0.99};
      for (String fileName : files) {
         Scanner file = new Scanner(new File(fileName));
         int id;
         String name;
         ArrayList<Employee> employees = new ArrayList<>();
         while (file.hasNext()) {
            id = file.nextInt();
            name = file.nextLine().trim();
            employees.add(new Employee(name,id));
         }
         int size;
         boolean colType = false;
         for (double loadFactor : loadFactors) {
            size = (int)(employees.size() / loadFactor);
            do {
               EmployeeDatabase map = new EmployeeDatabase(size,colType);
               long start = System.currentTimeMillis();
               for (Employee employee : employees)
                  map.put(employee.id(),employee);
               if (fileName.charAt(0) != 'L') {
                  for (Employee employee : employees)
                     map.get(employee.id());
               }
               System.out.println(fileName + " | time taken: " + (int)((System.currentTimeMillis()-start)/1000f) + "s");
               System.out.println("Is Quadratic: " + colType + "\nHash Function: " + map.hashFunctionName());
               System.out.println("Capacity >>" + size + "\nInserts >> " + map.count() + "\nCollisions >> " + map.collisions() + "\tCollision Insertion Ratio >> " + (map.collisions() / (double) employees.size()) + "\nSearches >> " + map.searches() + "\n");
               colType = !colType;
            } while (colType == true);
         }
      }
   }
   
}