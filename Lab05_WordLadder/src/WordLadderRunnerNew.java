import java.io.IOException;
import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordLadderRunnerNew {

   public static void main( String args[] ) throws IOException {
        
      //Choose your data file.  200.txt  2k.txt  20k.txt
      Scanner file = new Scanner(new File("input.txt"));          
      Queue<String[]> inputs = new LinkedList<String[]>();         
      while (file.hasNext()) 
         inputs.offer(new String[] { file.next(), file.next() });
                              
      double counter = 0;
      int percent = 0;
      int laddersInFile = inputs.size();
      String results = ""; 
      System.out.println("Starting...");       
      long start = System.currentTimeMillis();         
      WordLadder w = new WordLadder();
      while (! inputs.isEmpty()) {             
         String[] currentLadder = inputs.poll();                       
         w.setWords(currentLadder[0], currentLadder[1]);
         results = results.concat(w.toString() + "\n");            
         counter++;
         
         if (counter / laddersInFile  > .1) {
            counter = 0;
            percent += 10;
            System.out.println(percent + "% complete");
         } 
                  
      }
      long end = System.currentTimeMillis();
      float sec = (end - start) / 1000F;
      out.println("Done.");
               
      out.println(results);
      out.println("Char count: " + results.length());
      out.println("Run time = " + sec + " seconds");
   
   }
    
}