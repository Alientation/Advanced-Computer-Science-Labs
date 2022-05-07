import java.util.*;
public class Runner { 
   public static void main(String[] args) {
      QueueProbs problems = new QueueProbs();
      
      System.out.println(problems.evenFirst(makeQueue(new int[] {3,5,4,17,6,83,1,84,16,37})));
      System.out.println(problems.getPrimes(12));
   }
   
   public static Queue<Integer> makeQueue(int[] nums) {
      Queue<Integer> result = new LinkedList<Integer>();
      for (int i : nums) {
         result.offer(i);
      }
      return result;
   }
}