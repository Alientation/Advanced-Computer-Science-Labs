import java.util.*;
import java.io.*;
public class Clock {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      /*Scanner input;
      try {
         input = new Scanner(new File("clock.dat"));
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }*/
      for (int tests = input.nextInt(); tests > 0; tests--) {
         input.nextLine();
         String[] time = input.next().split(":");
         int h = Integer.parseInt(time[0]);
         int m = Integer.parseInt(time[1]);
         h = 12 - h - 1;
         m = 60 - m;
         if (m == 60) {
            h++;
            m = 0;
         }
         System.out.println(h + ":" + String.format("%02d",m));
      }
   }
}