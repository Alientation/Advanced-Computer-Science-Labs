import java.io.*;
import java.util.*;

public class Maze {
   public static final Integer[][] directions = {
      {-1,0},  //up
      {0,-1},  //left
      {0,1},   //right
      {1,0}    //down
   }; 
   
   private Square[][] maze;
   
   private Square start, exit;
   
   public Maze() {
      
   }
   
   public ArrayList<Integer[]> getOptimalDirections(Square s) {
      ArrayList<Integer[]> optimal = new ArrayList<Integer[]>();
      
      if (s.getRow() > exit.getRow()) {
         optimal.add(directions[0]);
      }  else if (s.getRow() < exit.getRow()) {
         optimal.add(directions[3]);
      }
      
      if (s.getCol() > exit.getCol()) {
         optimal.add(directions[1]);
      }  else if (s.getCol() < exit.getCol()) {
         optimal.add(directions[2]);
      }
      
      for (int i = 0; i < directions.length; i++) {
         if (optimal.contains(directions[i]) == false) {
            optimal.add(directions[i]);
         }
      }
      return optimal;
   }
   
   public boolean loadMaze(String fileName) {
      try {
         File file = new File(fileName);
         Scanner fileReader = new Scanner(file);
         
         int rows = fileReader.nextInt();
         int cols = fileReader.nextInt();
         
         this.maze = new Square[rows][cols];
         
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
              this.maze[i][j] = new Square(i,j,fileReader.nextInt());
              if (this.maze[i][j].getType() == 2) {
                  this.start = this.maze[i][j];
              } else if (this.maze[i][j].getType() == 3) {
                  this.exit = this.maze[i][j];
              }
            }
         }
         return true;
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      return false;
   }
   
   public void reset() {
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze[i].length; j++) {
            this.maze[i][j].setStatus('\u0000');
         }
      }
   }
   
   public List<Square> getNeighbors(Square s) {
      List<Square> list = new ArrayList<Square>();
      ArrayList<Integer[]> optimal = getOptimalDirections(s);
      for (int i = 0; i < optimal.size(); i++) {
         Integer[] direction = optimal.get(optimal.size()-i-1);
         if (s.getRow() + direction[0] >= 0 && s.getRow() + direction[0] < maze.length && s.getCol() + direction[1] < maze[0].length && s.getCol() + direction[1] >= 0) {
            list.add(maze[s.getRow() + direction[0]][s.getCol() + direction[1]]);
         }
      }
      return list;
   }
   
   public Square getStart() {
      return this.start;
   }
   
   public Square getExit() {
      return this.exit;
   }
   
   public String toString() {
      String layout = "";
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze[i].length; j++) {
            layout = layout + this.maze[i][j];
         }
         layout = layout + "\n";
      }
      return layout;
   }
   
}