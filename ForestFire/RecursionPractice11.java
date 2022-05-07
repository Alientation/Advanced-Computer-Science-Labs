import java.util.*;
public class Runner {
   
   public static void main(String args[]) {
      printBinary(3);
      climbStairs(4);
      campsite(2,1);
      System.out.println(getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19) + "\n");
      
      System.out.println(makeChange(25));
      System.out.println(makeChange(10));
      
      printChange(25);
      printChange(10);
      
      System.out.println(longestCommonSub("ABCDEFG","BGCEHAF") + "\n");
      System.out.println(longestCommonSub("12345","54321 21 54321") + "\n");
   }
   
   public static void printBinary(int digits) {
      String result = "";
      printBinaryHelper(digits, result);
      System.out.println();
   }
   
   public static void printBinaryHelper(int digits, String result) {
      if (result.length() != digits) {
         printBinaryHelper(digits,result + "0");
         printBinaryHelper(digits,result + "1");
      } else {
         System.out.print(result + " ");
      }
   }
   
   public static void climbStairs(int steps) {
      climbStairsHelper(steps, "");
      System.out.println();
   }
   
   public static void climbStairsHelper(int remainingSteps, String result) {
      if (remainingSteps == 0) {
         String[] array = result.split(" ");
         for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (array.length == i + 1) {
               System.out.println();
            } else {
               System.out.print(", ");
            }
         }
         return;
      }
      climbStairsHelper(remainingSteps - 1, result + "1 ");
      if (remainingSteps == 1)
         return;
      climbStairsHelper(remainingSteps - 2, result + "2 ");
   }
   
   public static void campsite(int x, int y) {
      campsiteHelper(x,y,0,0,"");
      System.out.println();
   }
   
   public static void campsiteHelper(int x, int y, int xCur, int yCur, String result) {
      if (x == xCur && y == yCur) {
         String[] array = result.split(" ");
         for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i + 1 == array.length) {
               System.out.println();
            } else {
               System.out.print(", ");
            }
         }
         return;
      }
      if (xCur != x) {
         campsiteHelper(x,y,xCur + 1,yCur,result + "E ");
      }
      if (yCur != y) {
         campsiteHelper(x,y,xCur,yCur + 1,result + "N ");
      }
      if (xCur != x && yCur != y) {
         campsiteHelper(x,y,xCur + 1,yCur + 1,result + "NE ");
      }
   }
   
   public static int getMax(List<Integer> nums, int limit) {
      return getMaxHelper(nums,limit,0,0);
      
   }
   
   public static int getMaxHelper(List<Integer> nums, int limit, int curSum, int pos) {
      if (pos == nums.size()) {
         if (curSum > limit) {
            return Integer.MIN_VALUE;
         }
         return curSum;
      }
      return Math.max(getMaxHelper(nums,limit,curSum + nums.get(pos), pos + 1), getMaxHelper(nums,limit,curSum, pos + 1));
   }
   
   public static int makeChange (int amount) {
      HashMap<String,Boolean> hs = new HashMap<String,Boolean>();
      return makeChangeHelper(amount,0,0,0,0,0, hs);
   }
   
   public static void printChange (int amount) {
      HashMap<String,Boolean> hs = new HashMap<String,Boolean>();
      makeChangeHelper(amount,0,0,0,0,0, hs);
      hs.entrySet().forEach(entry -> { System.out.println(entry.getKey());});
      System.out.println();
   }
   
   public static int makeChangeHelper(int amount, int currentAmount, int p, int n, int d, int q, HashMap<String,Boolean> hs) {
      if (amount == currentAmount) {
         String str = p + " " + n + " " + d + " " + q;
         if (hs.get(str) != null) {
            return 0; 
         }
         hs.put(str,true);
         //System.out.println(str);
         return 1;
      } else if (amount < currentAmount) {
         return 0;
      }
      int a = 0;
      if (amount - currentAmount >= 1)
         a += makeChangeHelper(amount,currentAmount + 1,p+1,n,d,q,hs);
      if (amount - currentAmount >= 5)
         a += makeChangeHelper(amount,currentAmount + 5,p,n+1,d,q,hs);
      if (amount - currentAmount >= 10)
         a += makeChangeHelper(amount,currentAmount + 10,p,n,d+1,q,hs);
      if (amount - currentAmount >= 25)
         a += makeChangeHelper(amount,currentAmount + 25,p,n,d,q+1,hs);
      return a;
   }
   
   public static String longestCommonSub(String a, String b) {
      return longestCommonSubHelper(a,b);
   }
   
   public static String longestCommonSubHelper(String a, String b) {
      if (a.length() == 0 || b.length() == 0) {
         return "";
      }
      if (a.charAt(0) == b.charAt(0)) {
         return a.charAt(0) + longestCommonSubHelper(a.substring(1),b.substring(1));
      }
      return longestString(longestCommonSubHelper(a.substring(1),b),longestCommonSubHelper(a,b.substring(1)));
   }
   
   public static String longestString(String str1, String str2) {
      if (str1.length() > str2.length()) {
         return str1;
      }
      return str2;
   }
   
}