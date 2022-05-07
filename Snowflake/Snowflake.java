import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
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
		//g.drawLine(0, 0, width - 1, height - 1);
      Random rand = new Random();
      for (int i = 0; i < 10; i++) {
         g.setColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
         int size = Math.max(width / ((int) (Math.random() * 4) + 2),((int) (Math.random() * 4) + 2));
         drawStar(g,(int) (Math.random() * (width - 20)), (int) (Math.random() * (width - 20)),size,size);
      }
	}
   
   public void drawStar(Graphics g, int x, int y, int width, int height) {
      if (width < 15 || height < 15) {
         return;
      }
      int size = width/4;
      
      for (int i = 0; i < 6; i++) {
         g.drawLine(x,y,x + getX(size, i * (Math.PI / 3)), y + getY(size,i * (Math.PI / 3)));
      }
      for (int i = 0; i < 6; i++) {
         drawStar(g,x + getX(size, i * (Math.PI / 3)), y + getY(size,i * (Math.PI / 3)),width/4,height/4);
      }
   }
   
   
   public int getX(int size, double theta) {
      return (int) Math.round(Math.cos(theta) * size);
   }
   
   public int getY(int size, double theta) {
      return (int) Math.round(Math.sin(theta) * size);
   }
   
   /*
   public void drawStar(Graphics g, int x, int y, int width, int height) {
      if (width < 15 || height < 15) {
         return;
      }
      g.drawLine(x, y, x - width/5, y);
      g.drawLine(x, y, x - width/7, y - height/6);
      g.drawLine(x, y, x + width/7, y - height/6);
      g.drawLine(x, y, x + width/5, y);
      g.drawLine(x, y, x + width/7, y + height/6);
      g.drawLine(x, y, x - width/7, y + height/6);
      
      drawStar(g,x-width/5, y, width/3, height/3);
      drawStar(g,x-width/7, y - height/6, width/3, height/3);
      drawStar(g,x+width/7, y - height/6, width/3, height/3);
      drawStar(g,x+width/5, y, width/3, height/3);
      drawStar(g,x+width/7, y + height/6, width/3, height/3);
      drawStar(g,x-width/7, y + height/6, width/3, height/3);
   }
   */
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
