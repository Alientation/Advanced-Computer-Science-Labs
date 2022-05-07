import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

import java.awt.event.MouseEvent;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */
	private static int SIZE = 100;
	private LifeCell[][] grid;
   private boolean mousePressed = false;
   private HashMap<Integer,Boolean> changed = new HashMap<Integer,Boolean>();
	private String startFile;
	LifeView myView;
	Timer timer;
   
   public void constructGrid(String fileName){
      int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			startFile = null;
         for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.95) //5% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
               if (numNeighbors(c,r) >= 2 && numNeighbors(c,r) < 4 && Math.random() > 0.70) { //Increased chances if u have between 2 and 4 neighbors
                  grid[r][c].setAliveNow(true);
               }
				}
			}
         oneGeneration();
		}
		else
		{                 
			try {
         Scanner input = new Scanner(new File(fileName));
         startFile = fileName;
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
         } catch(IOException e) {
            e.printStackTrace();
         }
		}
      if (myView != null) {
         myView.updateView(grid);
      }
   }
   
	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
      constructGrid(fileName);
		myView = view;
      
		myView.updateView(grid);

	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
   
   public void reset() {
      if (timer != null) {
         pause();
      }
   }
   
   public void setGridSlot(int x, int y) {
      grid[y][x].setAliveNow(true);
      grid[y][x].setAliveNext(true);
   }  
   
   public void color() {
      myView.toggleRandomColor();
      myView.updateView(grid);
   }
   
   public void clear() {
      for (int y = 0; y < grid.length; y++) {
         for (int x = 0; x < grid[y].length; x++) {
            grid[y][x].setAliveNow(false);
            grid[y][x].setAliveNext(false);
         }
      }
      myView.updateView(grid);
   }
   
   public void shift() {
      for (int y = 0; y < grid.length; y++) {
         for (int x = 0; x < grid[y].length; x++) {
            grid[y][x].setAliveNow(grid[y][x].isAliveNext());
         }
       }
   }
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		/* 
		 * student code here 
		 */
       for (int y = 0; y < grid.length; y++) {
         for (int x = 0; x < grid[y].length; x++) {
            int neighbors = numNeighbors(x,y);
            if (neighbors == 3 && !grid[y][x].isAliveNow()) {
               grid[y][x].setAliveNext(true);
               continue;
            }
            if (!grid[y][x].isAliveNow()) {
               continue;
            }
            if (neighbors <= 1) {
               //die
               grid[y][x].setAliveNext(false);
            } else if (neighbors <= 3) {
               grid[y][x].setAliveNext(true);
               //live
            } else {
               grid[y][x].setAliveNext(false);
               //die
            }
         }
       }
       shift();
 	}
   
   public int numNeighbors(int x, int y) {
      int sum = 0;
      for (int yy = -1; yy <= 1; yy++) {
         for (int xx = -1; xx <= 1; xx++) {
            if (xx == 0 && yy == 0) {
               continue;
            }
            if (aliveCell(xx + x, yy + y)) {
               sum++;
            }
         }
      }
      return sum;
   }
   
   private boolean aliveCell(int x, int y) {
      if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length) {
         return false;
      }
      return grid[y][x].isAliveNow();
   }
   
   
   public void mousePressed(MouseEvent e) {
      changed.clear();
      int SIZE = LifeModel.SIZE;
      int testWidth = myView.getWidth() / (SIZE+1);
      int testHeight = myView.getHeight() / (SIZE+1);
      int boxSize = Math.min(testHeight, testWidth);
      int y = e.getY() / boxSize - 1;
      int x = e.getX() / boxSize - 1;
      toggle(x,y);
      mousePressed = true;
   }
   
   private void toggle(int x, int y) {
      if (y < 0 || y >= SIZE || x < 0 || x >= SIZE) {
         return;
      }
      grid[y][x].toggle();
      changed.put(x + y * SIZE,true);
      myView.updateView(grid);
   }
   
   public void mouseReleased(MouseEvent e) {
      mousePressed = false;
   }
}

