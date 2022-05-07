import java.util.*;
public class MazeSolverStack extends MazeSolver {
   
   public static MyStack worklist = new MyStack();
   
   
   public MazeSolverStack(Maze maze) {
      super(maze);
   }
   
   @Override
   public void add(Square s) {
      worklist.push(s);
   }
   
   @Override
   public void makeEmpty() {
      worklist.clear();
   }
   
   @Override
   public boolean isEmpty() {
      return worklist.isEmpty();
   }
   
   @Override
   public Square next() {
      return worklist.pop();
   }
}