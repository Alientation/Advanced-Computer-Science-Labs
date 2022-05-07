import java.util.*;
import java.io.*;
public class PrimePath {
   static ArrayList<Integer> primeNumbers = primeNumbersBruteForce(1001,10000);
   static HashMap<Integer,ArrayList<Integer>> graph = new HashMap<>();
   public static void main(String[] args) throws FileNotFoundException {
      for (int i = 0; i < primeNumbers.size() - 1; i++) { //build graph
         for (int j = i + 1; j < primeNumbers.size(); j++) {
            if (oneOff(primeNumbers.get(i),primeNumbers.get(j))) {
               if (graph.get(primeNumbers.get(i)) == null)
                  graph.put(primeNumbers.get(i),new ArrayList<Integer>());
               if (graph.get(primeNumbers.get(j)) == null)
                  graph.put(primeNumbers.get(j),new ArrayList<Integer>());
               graph.get(primeNumbers.get(i)).add(primeNumbers.get(j));
               graph.get(primeNumbers.get(j)).add(primeNumbers.get(i));
            }
         }
      }
      test(1033,8179);
      test(1373,8017);
      test(1033,1033);
   }
   public static void test(int num, int goal) {
      HashMap<Integer,Boolean> visited = new HashMap<>();
      Queue<PathNode> queue = new LinkedList<>();
      queue.offer(new PathNode(num,0));
      int distance = -1;
      PathNode obj;
      while (!queue.isEmpty()) {
         obj = queue.poll();
         if (visited.getOrDefault(obj.num,false) == true)
            continue;
         visited.put(obj.num,true);
         if (obj.num == goal) {
            distance = obj.distance;
            break;
         }
         for (int i : graph.get(obj.num))
            queue.offer(new PathNode(i,obj.distance+1));
      }
      System.out.println(distance);
   }
   public static boolean oneOff(int num1, int num2) {
      boolean foundOneOff = false;
      while (num1 > 0 && num2 > 0) {
         if (num1 % 10 != num2 % 10) {
            if (foundOneOff)
               return false;
            foundOneOff = true;
         }
         num1/=10;
         num2/=10;
      }
      return true;
   }
   public static ArrayList<Integer> primeNumbersBruteForce(int s, int n) {
      ArrayList<Integer> primeNumbers = new ArrayList<>();
      for (int i = s; i <= n; i += 2)
         if (isPrimeBruteForce(i))
            primeNumbers.add(i);
       return primeNumbers;
   }
   private static boolean isPrimeBruteForce(int number) {
      for (int i = 2; i*i <= number; i++)
         if (number % i == 0)
            return false;
      return true;
   }
}
class PathNode {
   int num, distance;
   public PathNode(int num, int distance) {
      this.num = num;
      this.distance = distance;
   }
}