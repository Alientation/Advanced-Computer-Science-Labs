import java.util.*;
import java.io.*;
public class Scooby {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(new File("scooby.dat"));
      int n = console.nextInt();
      console.nextLine();
      for (int test = 0; test < n; test++) {
         char[] input = console.nextLine().toCharArray();
         ArrayList<Integer>[] adjacencyList = new ArrayList[26];
         for (int i = 0; i < 26; i++)
            adjacencyList[i] = new ArrayList<>();
         for (int i = 0; i < input.length; i+=3) {
            adjacencyList[input[i]-65].add(input[i+1]-65);
            adjacencyList[input[i+1]-65].add(input[i]-65);
         }
         char[] find = console.nextLine().toCharArray();
         System.out.println(recur(find[0]-65,find[1]-65,new boolean[26],adjacencyList) ? "yes" : "no");
      }
   }
   public static boolean recur(int cur, int goal, boolean[] visited, ArrayList<Integer>[] adjacencyList) {
      if (visited[cur])
         return false;
      if (cur == goal)
         return true;
      visited[cur] = true;
      for (int i : adjacencyList[cur])
         if (recur(i,goal,visited,adjacencyList))
            return true;
      return false;
   }
}