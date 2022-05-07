import java.util.*;
public class Melody {
   private Queue<Note> notes;
      
   public Melody(Queue<Note> song) {
      this.notes = song;
   }
   
   public double getTotalDuration() {
      boolean isCurrRepeat = false;
      double duration = 0;
      for (int i = 0; i < notes.size(); i++) {
         Note temp = notes.poll();
         if (isCurrRepeat || temp.isRepeat()) {
            duration += 2 * temp.getDuration();
         } else {
            duration += temp.getDuration();
         }
         if (temp.isRepeat()) {
            isCurrRepeat = !isCurrRepeat;
         }
         notes.offer(temp);
      }
      return duration;
   }
   
   @Override
   public String toString() {
      String output = "";
      for (int i = 0; i < notes.size(); i++) {
         Note temp = notes.poll();
         output = output + temp + "\n";
         notes.offer(temp);
      }
      return output;
   }
   
   public void changeTempo(double tempo) {
      for (int i = 0; i < notes.size(); i++) {
         Note temp = notes.poll();
         temp.setDuration(temp.getDuration() / tempo);
         notes.offer(temp);
      }
   }
   
   public void reverse() {
      Stack<Note> temp = new Stack<Note>();
      
      while (notes.peek() != null) {
         temp.push(notes.poll());
      }
      
      while (!temp.isEmpty()) {
         notes.offer(temp.pop());
      }
   }
   
   public void append(Melody other) {
      for (int i = 0; i < other.getNotes().size(); i++) {
         Note temp = other.getNotes().poll();
         other.getNotes().offer(temp);
         notes.offer(temp);
      }
   }
   
   public void play() {
      System.out.println("Begin Play");
      Queue<Note> repeatedSection = new LinkedList<Note>();
      boolean isRepeat = false;
      for (int i = 0; i < notes.size(); i++) {
         Note currNote = notes.poll();
         notes.offer(currNote);
         if (currNote.isRepeat()) {
            isRepeat = !isRepeat;
         }
         System.out.println("Playing -> " + currNote);
         currNote.play();         
         if (isRepeat || currNote.isRepeat()) {
            repeatedSection.offer(currNote);
         }
         if (isRepeat == false && currNote.isRepeat()) {
            //play repeated section
            while (repeatedSection.peek() != null) {
               currNote = repeatedSection.poll();
               System.out.println("Playing -> " + currNote);
               currNote.play();
            }
         }
      }
      System.out.println("End Play");
   }
   
   public Queue<Note> getNotes() {
      return this.notes;
   }
   
}

class SomeRandomStuffNotImportant {
   
   public static void main(String[] args) {
      int sum = 0;
      int upToNum = 15;
      int multiple1 = 3;
      int multiple2 = 5;
      int subMultiple = 15;
      
      sum += (int) (((upToNum / multiple1 + 1) / 2.0) * (upToNum/multiple1) * multiple1);
      sum += (int) (((upToNum / multiple2 + 1) / 2.0) * (upToNum/multiple2) * multiple2);
      sum -= (int) (((upToNum / subMultiple + 1) / 2.0) * (upToNum/subMultiple) * subMultiple);
      3 + 5 + 6 + 9 + 10 + 12 + 15
      System.out.println("SUM >> " + sum);
   }
}