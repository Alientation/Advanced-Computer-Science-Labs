import java.util.*;
public class ProjectEuler { 
   public static void main(String[] args) {
      System.out.println(p10());
   }
   
   public static long p10() {
      long sum = 2;
      boolean bool;
      for (int i = 2; i < 2000000; i++) {
         bool = true;
         for (int ii = 2; ii <= Math.sqrt(i); ii++) {
            if (i % ii == 0) {
               bool = false;
               break;
            }
         }
         if (bool)
            sum += i;
      }
      return sum;
   }
   
   public static long p9() {
      for (int a = 0; a < 1000; a++) {
         for (int b = a + 1; b < 1000; b++) {
            if (1000 - a - b <= b)
               continue;
            double c = Math.sqrt(a * a + b * b);
            if (Math.floor(c) == c) {
               if (a + b + (int) c == 1000 && (int) c > b) {
                  return a * b * (int) c;
               }
            }
         }
      }
      return 0;
   }
   
   public static long p8() {
      String str = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";
      long product = 0;
      for (int i = 0; i < str.length() - 13; i++) {
         long p = 1;
         for (int ii = 0; ii < 13; ii++) {
            p *= Integer.parseInt(str.charAt(i + ii) + "");
         }
         if (product < p)
            product = p;
      }
      return product;
   }
   
   public static long p7() {
      ArrayList<Integer> al = new ArrayList<Integer>();
      al.add(2);
      boolean bool;
      for (int i = 3; al.size() != 10001; i++) {
         bool = true;
         for (int ii = 0; ii < al.size(); ii++) {
            if (i % al.get(ii) == 0) {
               bool = false;
               break;
            }
         }
         if (bool)
            al.add(i);
      }
      return al.get(10000);
   }
   
   public static long p6() {
      long sumOfSquare = 0;
      long sum = 0;
      for (int i = 1; i <= 100; i++) {
         sum += i;
         sumOfSquare += i * i;
      }
      return sum * sum - sumOfSquare;
   }
   
   public static int p5() {
      boolean bool;
      for (int num = 1; ;num++) {
         bool = true;
         for (int i = 1; i <= 20; i++) {
            if (num % i != 0) {
               bool = false;
               break;
            }
         }
         if (bool)
            return num;
      }
   }
   
   public static int p4() {
      int largest = 0;
      for (int num1 = 0; num1 < 1000; num1++) {
         for (int num2 = num1; num2 < 1000; num2++) {
            if (isPalindrome("" + (num1 * num2))) {
               if (largest < num1 * num2)
                  largest = num1 * num2;
            }
         }
      }
      return largest;
   }
   
   public static boolean isPalindrome(String s1) {
      for (int i = 0; i < s1.length()/2; i++) {
         if (s1.charAt(i) != s1.charAt(s1.length() - i - 1)) {
            return false;
         }
      }
      return true;
   }
   
   public static long p3() {
      long factors = 1;
      long num = 600851475143L;
      for (long i = 2; num != 1; i++) {
         if (num % i == 0) {
            if (factors < i)
               factors = i;
            num /= i;
            i = 2;
         }
      }
      return factors;
   }
   
   public static int p2() {
      int sum = 0;
      int pre2 = 1;
      int pre1 = 2;
      int temp = 0;
      while (pre2 < 4000000) {
         if (pre2 % 2 ==0)
            sum += pre2;
         temp = pre1;
         pre1 = pre1 + pre2;
         pre2 = temp;
      }
      return sum;
   }
   
   public static int p1() {
      int sum = 0;
      for (int i = 0; i < 1000; i++) {
         if (i % 3 == 0 || i % 5 == 0)
            sum += i;
      }
      return sum;
   }
}