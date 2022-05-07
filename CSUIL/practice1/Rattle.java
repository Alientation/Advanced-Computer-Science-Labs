import java.util.*;
import java.io.*;
public class Rattle {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      /*Scanner input;
      try {
         input = new Scanner(new File("rattle.dat"));
      } catch(FileNotFoundException e) {
         e.printStackTrace();
      }*/
      int tests = input.nextInt();
      input.nextLine();
      for (; tests > 0; tests--) {
         String in = input.nextLine();
         int size = (int)Math.sqrt(in.length());
         String[] matrix = new String[size];
         for (int j = 0; j < size; j++) {
            String str = "";
            for (int i = 0; i < size; i++)
               str += in.charAt(i * size + j);
            if (j % 2 != 0)
               str = str.charAt(size-1) + str.substring(0,size-1);
            else
               str = str.substring(1,size) + str.charAt(0);
            matrix[j] = str;
         }
         
         String ans = "";
         boolean asterick = false;
         for (int i = 0; i < size; i++) {
            if (asterick)
               break;
            for (int j = 0; j < size; j++) {
               if (matrix[j].charAt(i) == '*') {
                  asterick = true;
                  break;
               }
               ans += matrix[j].charAt(i);
            }
         }
         System.out.println(ans);
      }
   }
}