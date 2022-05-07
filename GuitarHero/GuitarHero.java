import java.util.*;

public class GuitarHero {
   public static GuitarString[] strings = new GuitarString[37];
   public static HashMap<Character,Integer> keyboard = new HashMap<Character,Integer>();
   public static char[] keys = {
      'q','2','w','e','4','r','5','t','y','7','u','8','i','9','o','p','-','[','=','z','x','d','c','f','v','g','b','n','j','m','k',',','.',';','/','\'',' '
   };
   public static void main(String[] args) {
      for (int i = 0; i < 37; i++) {
         strings[i] = new GuitarString(440 * Math.pow(1.05956,i - 24));
         keyboard.put(keys[i],i);
      }
      StdDraw.enableDoubleBuffering();
      GuitarHeroVisualizer thing = new GuitarHeroVisualizer();
      while (true) {
         if (StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (keyboard.get(key) != null) {
               strings[keyboard.get(key)].pluck();
            }
         }
         double sample = 0;
         for (int i = 0; i < strings.length; i++) {
            sample += strings[i].sample();
            strings[i].tic();
         }
         StdAudio.play(sample);
         thing.drawSound(sample);
      }
   }

}