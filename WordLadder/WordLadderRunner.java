import java.io.IOException;
import java.io.File;
import java.util.*;
import static java.lang.System.*;

public class WordLadderRunner {
   public static ArrayList<Float> DATA = new ArrayList<Float>();
    public static void main( String args[] ) throws IOException {
         
         int runCount = 1;
         for (int i = 0; i < runCount; i++) {
            run();
         }
         /*
         Collections.sort(DATA);
         float mean = 0;
         float total = 0;
         float max = 0;
         float min = DATA.get(0);
         float median = (DATA.get(DATA.size()/2 - 1) + DATA.get(DATA.size()/2)) / 2;
         for (float f : DATA) {
            total += f;
            if (f > max) {
               max = f;
            } else if (f < min) {
               min = f;
            }
         }
         mean = total / DATA.size();
         System.out.println("FINAL RESULTS\n---------------\nMEAN >>> " + mean + "\nMAX >>> " + max + "\nMIN >>> " + min + "\nMEDIAN >>> " + median + "\nTOTAL RUN COUNT >>> " + DATA.size() + "\n-----------------");
         */
    }
    
    public static void run() throws IOException {
        int counter = 0;
        long start = System.currentTimeMillis();
        Scanner file = new Scanner(new File("200.txt"));
        String results = "";
        //long start = System.currentTimeMillis();
        WordLadder w = new WordLadder();
        while (file.hasNext()) {
        	//long s = System.currentTimeMillis();
        	w.setWords(file.next(), file.next());
        	//out.println("TIME TAKEN >>> " + (System.currentTimeMillis() - s)/1000F);
        	out.println(w);
         //results.concat("\n" + w);
        }
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F;
        DATA.add(sec);
        out.println(results);
        out.println("Run time = " + sec + " seconds");
    }
}