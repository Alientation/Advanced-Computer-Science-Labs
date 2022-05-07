import java.util.*;
public class Escape {
   public static int lowestCost = Integer.MAX_VALUE;
   public static void main(String[] args) {
      System.out.println(lowest(
         new String[] {"500 0 0 500"},
         new String[] {"0 0 0 0"}
      ));
      
      System.out.println(lowest(
         new String[] {"0 0 250 250","250 250 500 500"},
         new String[] {"0 251 249 500","251 0 500 249"}
      ));
      
       System.out.println(lowest(
         new String[] {"0 0 250 250","250 250 500 500"},
         new String[] {"0 250 250 500","250 0 500 250"}
      ));
   }
   
   public static int lowest(String[] harmful, String[] deadly) {
      int[][] grid = new int[501][501];
      int[][] memo = new int[501][501];
      for (int i = 0; i < memo.length; i++)
         for (int j = 0; j < memo[i].length; j++)
            memo[i][j] = Integer.MAX_VALUE;
      String[] temp;
      lowestCost = Integer.MAX_VALUE;
      for (String cost : harmful) {
         temp = cost.split(" ");
         int i1 = Integer.parseInt(temp[0]);
         int j1 = Integer.parseInt(temp[1]);
         int i2 = Integer.parseInt(temp[2]);
         int j2 = Integer.parseInt(temp[3]);
         for (int i = Math.min(i1,i2); i <= Math.max(i1,i2); i++)
            for (int j = Math.min(j1,j2); j <= Math.max(j1,j2); j++)
               grid[i][j] = 1;
      }
      for (String dead : deadly) {
         temp = dead.split(" ");
         int i1 = Integer.parseInt(temp[0]);
         int j1 = Integer.parseInt(temp[1]);
         int i2 = Integer.parseInt(temp[2]);
         int j2 = Integer.parseInt(temp[3]);
         for (int i = Math.min(i1,i2); i <= Math.max(i1,i2); i++)
            for (int j = Math.min(j1,j2); j <= Math.max(j1,j2); j++)
               grid[i][j] = -1;
      }
      recur(grid,0,0,memo,0);
      System.out.print(count + " "); count = 0;
      return lowestCost == Integer.MAX_VALUE ? -1 : lowestCost;
   }
   public static int[][] directions = new int[][] {
      {1,0},
      {-1,0},
      {0,1},
      {0,-1}
   };
   static int count = 0;
   public static void recur(int[][] grid, int i, int j, int[][] memo, int cost) {
      count++;
      if (cost >= lowestCost)
         return;
      if (i == 500 && j == 500) {
         lowestCost = cost;
         return;
      }
      for (int[] direction : directions) {
         if (inBounds(i + direction[0],j + direction[1]) && memo[i + direction[0]][j + direction[1]] == Integer.MAX_VALUE) {
            memo[i + direction[0]][j + direction[1]] = cost;
            recur(grid,i + direction[0],j + direction[1], memo, cost + grid[i + direction[0]][j + direction[1]]);
            memo[i + direction[0]][j + direction[1]] = Integer.MAX_VALUE;
         }
      }
      
      
   }
   public static boolean inBounds(int i, int j) {
      return i >= 0 && j >= 0 && i <= 500 && j <= 500;
   }
}