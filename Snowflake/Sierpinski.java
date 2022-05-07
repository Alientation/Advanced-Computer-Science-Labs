import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SierpinskiPanel extends JPanel
{  

 	public SierpinskiPanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		/*
		 * DRAWING CODE BELOW
		 */
       g.setColor(Color.BLUE);
		drawTriangle(g,new Point(0,height/2),new Point(width/2,height/2), new Point(width/2,0),height, width);
	}
   
   public void drawTriangle(Graphics g, Point left, Point right, Point up, int height, int width) {
      drawLine(g,left,right);
      drawLine(g,left,up);
      drawLine(g,right,up);
      
      if (height < 8 || width < 8) {
         return;
      }
      
      drawTriangle(g,new Point(left.x,left.y - height / 4),new Point(right.x - width / 4,right.y - height / 4), new Point(up.x - width/4,up.y), height/2, width/2);
      drawTriangle(g,new Point(left.x,left.y + height/4),new Point(right.x - width/4,right.y + height/4), new Point(up.x - width/4,up.y + height/2), height/2, width/2);
      drawTriangle(g,new Point(left.x + width/2,left.y - height / 4),new Point(right.x + width/4,right.y - height/4), new Point(up.x + width/4,up.y), height/2, width/2);
   }
   public void drawLine(Graphics g, Point point1, Point point2) {
      g.drawLine(point1.x,point1.y,point2.x,point2.y);
   }
   
}

class Point {
   public int x;
   public int y;
   public Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
}

public class Sierpinski
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Sierpinski");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SierpinskiPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
