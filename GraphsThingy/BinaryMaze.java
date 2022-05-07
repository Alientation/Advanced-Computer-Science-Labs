import java.util.*;
import java.io.*;
public class BinaryMaze {
   static int[][] directions = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(new File("maze.txt"));
      int[][] matrix = new int[console.nextInt()][console.nextInt()];
      for (int r = 0; r < matrix.length; r++)
         for (int c = 0; c < matrix[r].length; c++)
            matrix[r][c] = console.nextInt();
      boolean[][] visited = new boolean[matrix.length][matrix[0].length];
      Queue<PathNode> queue = new LinkedList<>();
      queue.offer(new PathNode(console.nextInt(),console.nextInt(),0));
      int[] destination = new int[] {console.nextInt(), console.nextInt()};
      int distance = -1;
      PathNode obj;
      while (!queue.isEmpty()) {
         obj = queue.poll();
         if (obj.i < 0 || obj.j < 0 || obj.i >= matrix.length || obj.j >= matrix[obj.i].length || visited[obj.i][obj.j] || matrix[obj.i][obj.j] == 0)
            continue;
         if (obj.i == destination[0] && obj.j == destination[1]) {
            distance = obj.distance;
            break;
         }
         visited[obj.i][obj.j] = true;
         for (int[] direction : directions)
            queue.offer(new PathNode(obj.i+direction[0],obj.j+direction[1],obj.distance+1));
      }
      System.out.println(distance);
   }
}
class PathNode {
   int i, j, distance;
   public PathNode(int i, int j, int distance) {
      this.i = i;
      this.j = j;
      this.distance = distance;
   }
}