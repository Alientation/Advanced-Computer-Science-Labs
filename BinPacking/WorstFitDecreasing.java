import java.util.*;
import java.io.*;
public class WorstFitDecreasing {
   public static void main(String[] args) {
      Queue<Disk> queue = new PriorityQueue<>();
      queue.offer(new Disk());
      long totalSize = 0L;
      Queue<Integer> files = new PriorityQueue<>(Collections.reverseOrder());
      try {
			BufferedReader	in	= new	BufferedReader(new FileReader("input20.txt"));
         String file = in.readLine();
         while (file != null) {
            int size = Integer.parseInt(file);
            totalSize += size;
            files.offer(size);
            file = in.readLine();
         }
		} catch(IOException e) {
			e.printStackTrace();
		}
      while (!files.isEmpty()) {
         int size = files.poll();
         Disk disk = queue.poll();
            if (disk.canStore(size)) {
               disk.addFile(size);
            } else {
               Disk newDisk = new Disk();
               newDisk.addFile(size);
               queue.offer(newDisk);
            }
            queue.offer(disk);
      }
      System.out.println("Total size = " + (totalSize / 1000000.0) + " GB");
      System.out.println("Disks req'd = " + queue.size());
      long totalEmptySpace = 0L;
      int queueSize = queue.size();
      while (!queue.isEmpty()) {
         totalEmptySpace += queue.peek().spaceLeft();
         System.out.println(queue.poll());
      }
      //System.out.println("Total size = " + (totalSize / 1000000.0) + " GB");
      //System.out.println("Total space remaining = " + (totalEmptySpace / 1000000.0) + " GB");
      //System.out.println("Disks req'd = " + queueSize);      
   }
}