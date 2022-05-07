import java.util.*;
import java.io.*;
public class Octagon {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      /*Scanner input;
      try {
         input = new Scanner(new File("octagon.dat"));
      } catch(FileNotFoundException e) {
         e.printStackTrace();
      }*/
      while (input.hasNext()) {
         int size = input.nextInt();
         int minWidth = size;
         int maxWidth = size * 3 - 2;
         
         //top
         for (int i = 0; i < (maxWidth - minWidth) / 2; i++)
            System.out.print(" ");
         for (int i = 0; i < minWidth; i++)
            System.out.print("x");
         for (int i = 0; i < (maxWidth - minWidth) / 2; i++)
            System.out.print(" ");
         System.out.println();
         //slants
         for (int i = 0; i  < size - 2; i++) {
            for (int j = 0; j < size - 2 - i; j++)
               System.out.print(" ");
            System.out.print("x");
            for (int j = 0; j < size - 2 + 2 * (i + 1); j++)
               System.out.print(" ");
            System.out.println("x");
         }
         
         //middle
         for (int i = 0; i < size - 2; i++) {
            System.out.print("x");
            for (int j = 1; j < maxWidth-1; j++)
               System.out.print(" ");
            System.out.println("x");
         }
         
         //slants
         for (int i = size - 2 -1; i >= 0; i--) {
            for (int j = 0; j < size - 2 - i; j++)
               System.out.print(" ");
            System.out.print("x");
            for (int j = 0; j < size - 2 + 2 * (i + 1); j++)
               System.out.print(" ");
            System.out.println("x");
         }
         
         //bottom
         for (int i = 0; i < (maxWidth - minWidth) / 2; i++)
            System.out.print(" ");
         for (int i = 0; i < minWidth; i++)
            System.out.print("x");
         for (int i = 0; i < (maxWidth - minWidth) / 2; i++)
            System.out.print(" ");
      }
   }
} 