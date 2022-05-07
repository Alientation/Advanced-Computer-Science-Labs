import static java.lang.System.*;
import java.util.*;

public class RecursionPractice {

   public static double sumReciprocals(int n)  {
      if (n == 0)
         return 0;
      return 1.0 / n  + sumReciprocals(n-1);
   }
   
   public static int productOfEvens(int n) {
      if (n == 0)
         return 1;
      return 2  * n * productOfEvens(n - 1);
   }
   
   public static void doubleUp(Stack<Integer> nums) {
      if (nums.isEmpty()) {
         return;
      }
      int n = nums.pop();
      doubleUp(nums);
      nums.push(n);
      nums.push(n);
   }
   
   public static void countToBy(int n, int m) {
      if (n <= m) {
         System.out.print(n);
         return;
      }
      countToBy(n-m,m);
      System.out.print(", " + n);
   }
   
   public static int matchingDigits(int a, int b) {
      if (a % 10 == b % 10) {
         if (a/10 == 0 || b/10 == 0) {
            return 1;
         }
         return 1 + matchingDigits(a/10,b/10);
      }
      if (a/10 == 0 || b/10 == 0) {
         return 0;
      }
      return matchingDigits(a/10,b/10);
   }
   
   public static void printThis(int n) {
      if (n == 0) {
         return;
      } else if (n <= 2) {
         switch(n) {
         case 2: System.out.print("*");
         case 1: System.out.print("*");
         }
         return;
      }
      
      System.out.print("<");
      printThis(n - 2);
      System.out.print(">");
   }
   
   public static void printNums2(int n) {
      if (n <= 0) {
         return;
      }
      System.out.print(((n + 1) / 2) + " ");
      printNums2(n-2);
      if ((n + 1) / 2 == 1) {
         if (n % 2 == 0)
            System.out.print(((n + 1) / 2) + " ");
      } else {
         System.out.print(((n + 1) / 2) + " ");
      }
   }

/**
*  Use this main method for running tests.
*/
   public static void main(String[] args) {
      System.out.println(sumReciprocals(10)); //Should print 2.9289682539682538
      System.out.println(productOfEvens(4));
      Stack<Integer> stack = makeStack(new int[] {3,7,12,9});
      doubleUp(stack);
      System.out.println(stack);
      countToBy(34,5);
      System.out.println();
      
      System.out.println(matchingDigits(1000,0));
      System.out.println(matchingDigits(298892,7892));
      
      printThis(1);
      System.out.println();
      printThis(2);
      System.out.println();
      printThis(3);
      System.out.println();
      printThis(4);
      System.out.println();
      printThis(5);
      System.out.println();
      printThis(6);
      System.out.println();
      printThis(7);
      System.out.println();
      printThis(8);
      
      System.out.println();
      printNums2(1);
      System.out.println();
      printNums2(2);
      System.out.println();
      printNums2(3);
      System.out.println();
      printNums2(4);
      System.out.println();
      printNums2(5);
      System.out.println();
      printNums2(6);
      System.out.println();
      printNums2(7);
      System.out.println();
      printNums2(8);
      System.out.println();
      printNums2(9);
      System.out.println();
      printNums2(10);
      System.out.println();
   }
   
   public static Stack<Integer> makeStack(int num[]) {
      Stack<Integer> stack = new Stack<Integer>();
      for (int i : num) {
         stack.push(i);
      }
      return stack;
   }


}