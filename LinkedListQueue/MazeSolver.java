import java.util.*;
public abstract class MazeSolver {
   
   protected Maze maze;
   protected boolean isSolved;
   
   public MazeSolver(Maze maze) {
      this.isSolved = false;
      this.maze = maze;
      makeEmpty();
      add(maze.getStart());
   }
   
   public abstract void makeEmpty();
   public abstract boolean isEmpty();
   public abstract void add(Square s);
   public abstract Square next();
   
   
   public boolean isSolved() {
      if (isEmpty() || isSolved) {
         return true;  
      }
      return false;
   }
   
   public void step() {
      /*
      Algorithm stuff here
      */
      if (!isEmpty()) {
         Square next = next();
         next.setStatus(Square.EXPLORED);
         if (next.equals(maze.getExit())) {
            isSolved = true;
         } else {
            List<Square> list = maze.getNeighbors(next);
            for (Square s : list) {
               if (s.getStatus() == '\u0000' && s.getType() != Square.WALL) {
                  add(s);
                  s.setStatus(Square.WORKLIST);
                  s.setPrevious(next);
               }
            }
         }
      }
   }
   
   public String getPath() {
      if (isSolved) {
         Stack<String> stack = new Stack<String>();
         String str = "";
         Square node = maze.getExit();
         while (node != null) {
            stack.push ("[" + node.getRow() + ", " + node.getCol() + "]");
            node.setStatus(Square.FINAL_PATH);
            node = node.getPrevious();
         }
         while (!stack.isEmpty()) {
            str = str + stack.pop() + " ";
         }
         return str;
      } else if (isEmpty()) {
         return "Unsolvable";
      }
      return "Not Yet Solved";
   }
   
   public void solve() {
      while (!isSolved()) {
         step();
      }
   }
   
}