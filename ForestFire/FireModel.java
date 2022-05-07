public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;
    
    private boolean isOnettSafe = true;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */
    
    static int[][] direction = {
      {1,0},{0,1},{-1,0},{0,-1}
    };
    public void recursiveFire(int x, int y) {
         if (!isValidPos(x,y) || myGrid[y][x].getStatus() != FireCell.GREEN) {
            return;
         }
         if (y == 0)
            isOnettSafe = false;
         myGrid[y][x].setStatus(FireCell.BURNING);
         myView.updateView(myGrid);
         
         try {
            Thread.sleep(5);
         } catch(Exception e) {
            e.printStackTrace();
         }
         
         for (int[] dir : direction) {
            recursiveFire(x + dir[0], y + dir[1]);
         }
    }
    
    public boolean isValidPos(int x, int y) {
      return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    public void solve()
    {
        // student code here
        for (int i = 0; i < SIZE; i++) {
            recursiveFire(i,SIZE-1);
        }
        if (isOnettSafe) {
            System.out.println("Onett is safe");
        } else {
            System.out.println("Onett is in trouble!");
        }
        myView.updateView(myGrid);
    }

}
