import java.util.*;
import java.io.*;

public class TestRunnerClosedHashing extends TestGUI.TestData{ 
   public static void main (String[] args) {        
      new TestGUI();   
   }

   public static void runTests() {  //Your test sequence must be within a method called runTests()
      setTimeOutSec(5);
   
      header("Closed Hashing");
      srcButton("Employee, EmployeeDatabase");
   
   
      message("This testGUI makes calls to various test methods in Tester.java.  To pass these tests, ensure that your Tester.java is unaltered from its original state.");
      message("NOTE: Just because you pass all these test doesn't mean you get a 100.  I'll also be looking at your code to ensure that you are actually switching from linear to quadratic probing (and not just pretending.)");
   
      testMethod("Tester", "simpleTest", new Object[] {false}, "SIMPLE TEST - using linear probing\nPam Beasley\nCreed\nFred Java\nJim Halpert\nnull");
      testMethod("Tester", "simpleTest", new Object[] {true}, "SIMPLE TEST - using quadratic probing\nPam Beasley\nCreed\nFred Java\nJim Halpert\nnull");
      testMethod("Tester", "biggerTest", new Object[] {false}, "BIGGER TEST - using linear probing\nJames Butt\nPatsy Lerper\nScott Zellers\nKent Grishaber");
      testMethod("Tester", "biggerTest", new Object[] {true}, "BIGGER TEST - using quadratic probing\nJames Butt\nPatsy Lerper\nScott Zellers\nKent Grishaber");
   
   
   
   }    

}