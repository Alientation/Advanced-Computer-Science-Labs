public class MazeSolverQueue extends MazeSolver{
   public static MyQueue<Square> worklist = new MyQueue<Square>();
   
   public MazeSolverQueue(Maze maze) {
      super(maze);
   }
   
   @Override
   public void add(Square s) {
      worklist.offer(s);
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
      return worklist.poll();
   }

   
   
}