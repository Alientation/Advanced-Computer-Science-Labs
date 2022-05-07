   public class KnightTour {
      public static void main(String[] args) {
         KnightTourSolver s = new KnightTourSolver(29);
         s.solve();
         System.out.println(s);
      }
      
      
   }
   
   class KnightTourSolver {
      int[][] board;
      int size;
      boolean solved;
      
      public KnightTourSolver(int size) {
         this.size = size;
         board = new int[size][size];
      }
      
      static int[][] directions = {
      {2,1},{-2,1},{-2,-1},{2,-1},{1,2},{-1,2},{-1,-2},{1,-2}
      };
      
      static int[] sortedstuff = new int[directions.length];
      
      public void solve() {
         solved = false;
         solve(0,0);
      }
      
      public void solve(int x, int y) {
         board[y][x] = 1;
         solveHelper(x,y,2);
      }
      
      public int countNeighbors(int x, int y, int[] dir) {
         x += dir[0];
         y += dir[1];
         int i = 0;
         for (int[] dir1 : directions) {
            if (inBounds(x,y,dir1)) {
               i++;
            }
         }
         return i;
      }
      
      public void solveHelper(int x, int y, int moveNum) {
         if (solved) {
             return;
         }
         if (moveNum == size * size + 1) {
            solved = true;
            return;
         }
         
         
         for (int i = 0; i < directions.length; i++) {
            sortedstuff[i] = Integer.MAX_VALUE;
            if (inBounds(x,y,directions[i])) {
               sortedstuff[i] = countNeighbors(x,y,directions[i]);
            }
         }
         
         for (int i = 0; i <= 8; i++) {
            for (int ii = 0; ii < sortedstuff.length; ii++) {
               if (sortedstuff[ii] == i) {
                  board[y+directions[ii][1]][x+directions[ii][0]] = moveNum;
                  solveHelper(x + directions[ii][0], y + directions[ii][1], moveNum + 1);
                  if (solved) {
                     return;
                  }
                  board[y+directions[ii][1]][x+directions[ii][0]] = 0;
               }
            }
         }
      }
            
      public boolean inBounds(int x, int y, int[] dir) {
         return x + dir[0] >= 0 && x + dir[0] < size && y + dir[1] >= 0 && y + dir[1] < size && board[y + dir[1]][x + dir[0]] == 0;
      }
      
      @Override
      public String toString() {
         String res = "";
         for (int[] i : board) {
            for (int ii : i) {
               res += ii + "\t\t";
            }
            res += "\n";
         }
         return res;
      }
   }