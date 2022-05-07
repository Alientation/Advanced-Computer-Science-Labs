import java.util.*;
public class TestRunnerRecursionPractice extends TestGUI.TestData {   
   public static void main (String[] args) {        
      new TestGUI();   
   }

    /**
     * Run customized tests of various classes/method

     * TO CREATE A HEADER IN THE GUI
     *   void header(String name)
     *   
     *   Example:
     *   header("Histogram");
     

     *   TO CREATE A MESSAGE/NOTE IN THE GUI
     *   void message(String information)
     *   
     *   Example:
     *   message("This portion of the assignment is optional and for extra credit only.");

 
     * TO TEST A CONSTRUCTOR AND RECEIVE THE FRESHLY INSTANTIATED OBJECT:
     *   Object makeObject(String className, Object[] args)
     *   Object makeObject(String className, Object[] args, String inputScript)
     *
     *   Parameters:
     *   className - Name of the constructor you are calling
     *   ags - arguments you are passing to the constructor
     *   inputScript - data that will be fed through System.in in the event that the construtor
     *      requests user input
     *      
     *   Returns:
     *   the object constructed from the call
     *      
     *   Examples:
     *   o = makeObject("Person", new Object[]{"Fred", 27});      //  o = new Person("Fred" 27);
     *   o = makeObject("Person", new Object[]{}, "Fred\n27");    //  o = new Person();  (provides "Fred\n27" to 
     *                                                                                     System.in)
                  
     *                                                                                     
     * TO TEST A METHOD  (The testMethod method is very overloaded.  Several options.)
     *   Object testMethod(Object o,     String mName, Object[] args)
     *   Object testMethod(String cName, String mName, Object[] args)
     *   Object testMethod(Object o,     String mName, Object[] args, String expOut)
     *   Object testMethod(String cName, String mName, Object[] args, String expOut)
     *   Object testMethod(Object o,     String mName, Object[] args, String expOut, String inputScript)
     *   Object testMethod(String cName, String mName, Object[] args, String expOut, String inputScript)
     *   
     *   Parameters:
     *   o - reference to the object invoking the method you want to test (for non-static methods)
     *   cName - name of the class invoking method you want to test (for static methods)
     *   mName - name of the method being invoked
     *   args - arguments you are passing to the method
     *   expOut - the expected output or return value from running the method
     *            (If an expOut is provided the GUI will compare this against the actual output/return
     *             value from the test and make a comparison.  Leave this off or pass null to stop if
     *             you don't want to make a comparison.)
     *   inputScript - data that will be fed through System.in in the event that the construtor
     *      requests user input
     *   
     *   Returns:
     *   the return value of the method being invoked (as Object)
     *   or null when testing void methods
     *   
     *   Examples:
     *   testMethod(myHist, "encounter", new Object[]{7});              // myHist.encounter(7);
     *   testMethod("Practice", "printXs", new Object[]{4}, "xxxx")     // Practice.printXs(4); 

   
     * TO SET HOW MANY SECONDS SHOULD BE WAITED BEFORE AN INDIVIDUAL TEST TIMES OUT
     *   setTimeOutSec(int seconds);  //how many seconds to wait before giving up.  (Default 2)
     *   
     */
   public static void runTests() {  //Your test sequence must be within a method called runTests()
        //WRITE ALL YOUR TEST CASES IN HERE
        
      header("LAB - Recursion Practice");
      message("Even if this program says you got the answer correct, you won't get credit if any of your recursive methods contain loops.");
      testMethod("RecursionPractice", "sumReciprocals", new Object[]{10}, "2.9289682539682538");
      testMethod("RecursionPractice", "sumReciprocals", new Object[]{12}, "3.103210678210678");
      testMethod("RecursionPractice", "sumReciprocals", new Object[]{1}, "1.0");
   
   
      testMethod("RecursionPractice", "productOfEvens", new Object[]{4}, "384");
      testMethod("RecursionPractice", "productOfEvens", new Object[]{1}, "2");
      testMethod("RecursionPractice", "productOfEvens", new Object[]{8}, "10321920");
   
      Stack<Integer> stack = new Stack<Integer>();
      stack.push(3);  stack.push(7);  stack.push(12); stack.push(9); 
      Object answer = testMethod("RecursionPractice", "doubleUp", new Object[]{stack});
      if (stack.toString().equals("[3, 3, 7, 7, 12, 12, 9, 9]"))
         message("The stack was altered correctly.  Result = " + stack);
      else
         message("The stack was not altered correctly.  The stack now looks like " + stack + " but I was expecting [3, 3, 7, 7, 12, 12, 9, 9]");
   
      stack = new Stack<Integer>();
      stack.push(1);  stack.push(2);  stack.push(3); 
      testMethod("RecursionPractice", "doubleUp", new Object[]{stack});
      if (stack.toString().equals("[1, 1, 2, 2, 3, 3]"))
         message("The stack was altered correctly.  Result = " + stack);
      else
         message("The stack was not altered correctly.  The stack now looks like " + stack + " but I was expecting [1, 1, 2, 2, 3, 3]");
         
      stack = new Stack<Integer>();   
      testMethod("RecursionPractice", "doubleUp", new Object[]{stack});
      if (stack.toString().equals("[]"))
         message("The stack was altered correctly.  Result = " + stack);
      else
         message("The stack was not altered correctly.  The stack now looks like " + stack + " but I was expecting []");
   
      testMethod("RecursionPractice", "countToBy", new Object[]{34, 5}, "4, 9, 14, 19, 24, 29, 34");
      testMethod("RecursionPractice", "countToBy", new Object[]{10, 2}, "2, 4, 6, 8, 10");
      testMethod("RecursionPractice", "countToBy", new Object[]{3, 1}, "1, 2, 3");
      testMethod("RecursionPractice", "countToBy", new Object[]{64, 20}, "4, 24, 44, 64");
   
   
      testMethod("RecursionPractice", "matchingDigits", new Object[]{1000, 0}, "1");
      testMethod("RecursionPractice", "matchingDigits", new Object[]{298892, 7892}, "3");
      testMethod("RecursionPractice", "matchingDigits", new Object[]{55, 555}, "2");
      testMethod("RecursionPractice", "matchingDigits", new Object[]{81233218, 83211238}, "4");
   
   
      testMethod("RecursionPractice", "printThis", new Object[]{1}, "*");
      testMethod("RecursionPractice", "printThis", new Object[]{2}, "**");
      testMethod("RecursionPractice", "printThis", new Object[]{6}, "<<**>>");
      testMethod("RecursionPractice", "printThis", new Object[]{11}, "<<<<<*>>>>>");
      
      testMethod("RecursionPractice", "printNums2", new Object[]{1}, "1");
      testMethod("RecursionPractice", "printNums2", new Object[]{8}, "4 3 2 1 1 2 3 4");
      testMethod("RecursionPractice", "printNums2", new Object[]{11}, "6 5 4 3 2 1 2 3 4 5 6");
                     
   }
}