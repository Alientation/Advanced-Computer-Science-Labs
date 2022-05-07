import java.util.*;
public class GuitarHeroVisualizer{
   public static final double size = 600;
   public static final double yShift = 0.5;
   public static final double increment = 1.0/size;
   public Queue<Double> queue = new LinkedList<Double>();
   public void drawSound(double sound) {
      queue.offer(sound);
      if (queue.size() >= size) {
         //draw the wave
         double x1 = 0;
         double y1 = 0;
         double x2 = 0;
         double y2 = 0;
         StdDraw.clear();
         //StdDraw.picture(0.5,.1,"keyboard.png",1,.2);
         while (!queue.isEmpty()) {
            y2 = Math.max(-.5,queue.poll());
            y2 = Math.min(.5,y2);
            x2 = x1 + increment;
            StdDraw.line(x1,y1 + yShift,x2,y2 + yShift);
            y1 = y2;
            x1 = x2;
         }
         StdDraw.show();
      }
   }
}
