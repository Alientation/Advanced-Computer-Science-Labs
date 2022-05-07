import java.util.*;
public class Disk implements Comparable<Disk> {
   static int num = 0;
   List<Integer> files;
   int used;
   int id;
   public Disk() {
      files = new ArrayList<Integer>();
      used = 0;
      id = num;
      num++;
   }
   public void addFile(int size) {
      files.add(size);
      used += size;
   }
   public boolean canStore(int size) {
      return 1000000 - used - size >= 0;
   }
   public int compareTo(Disk o) {
      return used - o.used;
   }
   public int id() {
      return id;
   }
   public int spaceLeft() {
      return 1000000 - used;
   }
   @Override
   public String toString() {
      String str = "\t" + String.format("%1$" + (num + "").length() + "s",id + "") + " " + String.format("%1$7s",(1000000 - used) + "") + ":";
      if (files.size() <= 100) 
         for (Integer file : files)
            str = str + " " + file;
      return str;
   }
}