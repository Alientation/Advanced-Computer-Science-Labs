import java.util.*;
public class QueueProbs {
   public Queue<Integer> evenFirst(Queue<Integer> nums) {
      Queue<Integer> evens = new LinkedList<Integer>();
      Queue<Integer> odds = new LinkedList<Integer>();
      while(nums.peek() != null) {
         if (nums.peek() % 2 == 0) {
            evens.offer(nums.poll());
         } else {
            odds.offer(nums.poll());
         }
      }
      while(evens.peek() != null) {
         nums.offer(evens.poll());
      }
      while (odds.peek() != null) {
         nums.offer(odds.poll());
      }
      return nums;
   }
   
   public Stack<Integer> getPrimes(int n) {
      Queue<Integer> queue = new LinkedList<Integer>();
      for (int i = 2; i <= n; i++) {
         queue.offer(i);
      }
      Stack<Integer> primes = new Stack<Integer>();
      while (queue.peek() != null) {
         int p = queue.poll();
         int size = queue.size();
         for (int i = 0; i < size; i++) {
            int t = queue.poll();
            if (t % p != 0) {
               queue.offer(t);
            }
         }
         primes.push(p);
      }
      return primes;
   }
}