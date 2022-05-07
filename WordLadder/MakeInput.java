import java.util.*;
import java.io.*;

public class MakeInput {
   public static void main(String[] args) {
      /*
      2 -> 94
 3 -> 962
 4 -> 3862
 5 -> 8548
 6 -> 14383
 7 -> 21729
 8 -> 26448
 9 -> 18844
 10 -> 12308
 11 -> 7850
 12 -> 5194
 13 -> 3275
 14 -> 1775
 15 -> 954
 16 -> 495
 17 -> 251
 18 -> 89
 19 -> 48
 20 -> 21
 ------
 21 -> 6
 22 -> 3
 24 -> 1
 28 -> 1
 29 -> 1
      */
      WordLadder wl = new WordLadder();
      try {
         FileWriter inputFileWriter = new FileWriter("newinput.txt");
         HashMap<Integer,HashMap<String,Boolean>> bigDictionary = wl.getBigDictionary();
         for (int wordlength = 2; wordlength <= 20; wordlength++) {
            int size = bigDictionary.size();
            int requestlength = (int) Math.sqrt(size);
            
            int numTillNextElement = (int)(Math.random() * requestlength);
            boolean isSecondWord = false;
            for (Map.Entry<String,Boolean> set : wl.getBigDictionary().get(wordlength).entrySet()) {
               if (numTillNextElement == 0) {
                  numTillNextElement = (int)(Math.random() * requestlength);
                  if (isSecondWord) {
                     inputFileWriter.write(" " + set.getKey() + "\n");
                  } else {
                     inputFileWriter.write(set.getKey());
                  }
                  isSecondWord = !isSecondWord;
               } else {
                  numTillNextElement--;
               }
            }
         }
         
         
         inputFileWriter.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      
   }
}