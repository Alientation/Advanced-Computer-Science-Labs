import java.util.*;
import java.io.*;
public class WorstFit {
   public static void main(String[] args) {
      Queue<Disk> queue = new PriorityQueue<>();
      queue.offer(new Disk());
      long totalSize = 0L;
      try {
			BufferedReader	in	= new	BufferedReader(new FileReader("input20.txt"));
         String file = in.readLine();
         while (file != null) {
            int size = Integer.parseInt(file);
            totalSize += size;
            Disk disk = queue.poll();
            if (disk.canStore(size)) {
               disk.addFile(size);
            } else {
               Disk newDisk = new Disk();
               newDisk.addFile(size);
               queue.offer(newDisk);
            }
            queue.offer(disk);
            file = in.readLine();
         }
		} catch(IOException e) {
			e.printStackTrace();
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