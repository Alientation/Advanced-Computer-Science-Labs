import java.util.*;
import java.io.*;
public class Maze {
   static boolean found;
   
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      /*Scanner input;
      try {
         input = new Scanner(new File("octagon.dat"));
      } catch(FileNotFoundException e) {
         e.printStackTrace();
      }*/
      int numMazes = input.nextInt(); input.nextLine();
      for (int nm = 1; nm <= numMazes; nm++) {
         found = false;
         char[][] maze = new char[20][20];
         int startI = 0, startJ = 0;
         for (int i = 0; i < 20; i++) {
            String s = input.nextLine();
            for (int j = 0; j < 20; j++) {
               maze[i][j] = s.charAt(j);
               if (maze[i][j] == 'S') {
                  startI = i;
                  startJ = j;
               }
            }
         }
         boolean[][] visited = new boolean[20][20];
         recur(maze,startI,startJ,visited);
      }
   }
   
   public static void recur(char[][] maze, int i, int j, boolean[][] visited) {
      if (found)
         return;
      if (i < 0 || i >= maze.length || j < 0 || j >= maze[i].length)
         return;
      if (visited[i][j])
         return;
      if (maze[i][j] == 'E') {
         found = true;
         return;
      }
      visited[i][j] = true;
      recur(maze,i-1,j,visited);
      recur(maze,i_1,j,visited);
      recur(maze,i,j-1,visited);
      recur(maze,i,j+1,visited);
   }
}