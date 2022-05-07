import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
/** The view (graphical) component */
public class LifeView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static int SIZE = 100;
   private Color aliveColor = Color.BLUE;
   private boolean randomColor = false;
   

	/** store a reference to the current state of the grid */
    private LifeCell[][] grid;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
               if (Life.model != null) {
                  Life.model.mousePressed(e);
               }
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
               if (Life.model != null) {
                  Life.model.mouseReleased(e);
               }
            }
        });
    }
    public void setColor(Color color) {
      aliveColor = color;
    }
    
    public void toggleRandomColor() {
      randomColor = !randomColor;
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }
    
    private Color getRandomColor() {
      return new Color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);

        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if ( grid[r][c].isAliveNow() )
                        if (randomColor) {
                           g.setColor(getRandomColor());
                        } else {
                           g.setColor(aliveColor);
                        }
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}
