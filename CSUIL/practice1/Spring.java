import java.util.*;
import java.io.*;
public class Spring {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      /*Scanner input;
      try {
         input = new Scanner(new File("octagon.dat"));
      } catch(FileNotFoundException e) {
         e.printStackTrace();
      }*/
      
      int numStudents = input.nextInt();
      input.nextLine();
      for (int s = 0; s < numStudents; s++) {
         String name = input.next();
         int numTrans = input.nextInt();
         input.nextLine();
         double val = 0;
         for (int t = 0; t < numTrans; t++) {
            String[] in = input.nextLine().split(" ");
            for (int ii = 0; ii < in.length; ii++) {
               int amount = Integer.parseInt(in[ii].substring(1));
               switch(in[ii].charAt(0)) {
                  case 'T':
                  val += 0.45 * amount * 15;
                  break;
                  case 'P':
                  val += 0.48 * amount * 14;
                  break;
                  case 'D':
                  val += amount;
                  break;
               }
            }
         }
         val = Math.round(val*100)/100.0;
         if (val > 500)
            System.out.println(name + " OVER $" + String.format("%.2f",val - 500));
         else
            System.out.println(name + " $" + String.format("%.2f",val));
      }
      
   }
}