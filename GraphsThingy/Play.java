import java.util.*;
import java.io.*;
public class Play {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(new File("play2.dat"));
      for (int testCase = console.nextInt(); testCase>0; testCase--) {
         int n = console.nextInt(), m = console.nextInt(), l = console.nextInt();
         ArrayList<Integer>[] adjacencyList = new ArrayList[n + 1];
         for (int i = 0; i < n + 1; i++)
            adjacencyList[i] = new ArrayList<>();
         for (int i = 0; i < m; i++)
            adjacencyList[console.nextInt()].add(console.nextInt());
	      int res = 0;
	      boolean[] visited = new boolean[n+1];
	      for (int i = 0; i < l; i++)
        	   res += recur(console.nextInt(),visited,adjacencyList);
	      System.out.println(res);
      }
   }
   public static int recur(int cur, boolean[] visited, ArrayList<Integer>[] adjacencyList) {
      if (visited[cur])
         return 0;
      visited[cur] = true;
      int sum = 1;
      for (int i : adjacencyList[cur])
         sum += recur(i,visited,adjacencyList);
      return sum;
   }
}