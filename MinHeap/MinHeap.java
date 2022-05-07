import java.util.*;
public class MinHeap {
   static final int DEFAULT_CAPACITY = 8;
   private Integer[] heap;
   private int size;
   
   public MinHeap() {
      this(DEFAULT_CAPACITY);
   }
   
   public MinHeap(int capacity) {
      this.heap = new Integer[capacity + 1];
      this.size = 0;
   }
   
   public MinHeap(Integer... nums) {
      this(nums.length);
      for (Integer i : nums) {
         heap[size + 1] = i;
         size++;
      }
      for (int i = size/2; i >= 1; i--)
         siftDown(i);
   }
      
   public int getSize() {
      return this.size;
   }
   
   public boolean isEmpty() {
      return this.size == 0;
   }
   
   public int peekMinimum() {
      return heap[1];
   }
   
   private int getLeftChildIndex(int index) {
      return index * 2;
   }
   
   private int getRightChildIndex(int index) {
      return index * 2 + 1;
   }
   
   private int getParentIndex(int index) {
      return index / 2;
   }
   
   private void doubleCapacity() {
      Integer[] newArray = new Integer[size * 2 + 1];
      for (int i = 1; i < heap.length; i++)
         newArray[i] = heap[i];
      this.heap = newArray;
   }
   
   public void insert(int value) {
      if (size + 1 >= heap.length)
         doubleCapacity(); 
      heap[size + 1] = value;
      bubbleUp(size + 1);
      size++;
   }
   
   private void bubbleUp(int index) {
      if (index > 1 && heap[getParentIndex(index)] > heap[index]) {
         swap(index, getParentIndex(index));
         bubbleUp(getParentIndex(index));
      }
   }
   
   private void swap(int i1, int i2) {
      Integer temp = heap[i1];
      heap[i1] = heap[i2];
      heap[i2] = temp;
   }
   
   public int popMinimum() {
      int i = peekMinimum();
      heap[1] = heap[size];
      size--;
      siftDown(1);
      return i;
   }
   
   private void siftDown(int index) {
      if (index*2 > size)
         return; 
      if (getRightChildIndex(index) > size) {
         if (heap[getLeftChildIndex(index)] < heap[index])
            swap(index,getLeftChildIndex(index));
      } else if (heap[index] > heap[getLeftChildIndex(index)] || heap[index] > heap[getRightChildIndex(index)]) {
         if (heap[getLeftChildIndex(index)] < heap[getRightChildIndex(index)]) {
            swap(index,getLeftChildIndex(index));
            siftDown(getLeftChildIndex(index));
         } else {
            swap(index,getRightChildIndex(index));
            siftDown(getRightChildIndex(index));
         }
      }
   }
   @Override
	public String toString() {  
      if (getSize() == 0)
         return "";
		String output = "";
		for (int i = 1; i <= getSize(); i++) 
			output += heap[i] + ", ";
		return output.substring(0, output.lastIndexOf(",")); //lazily truncate last comma
	}

	/** method borrowed with minor modifications from the internet somewhere, for printing */
	public void display() {
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);      
		while (j <= this.getSize())
		{
			if (column == 0)                 
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');

			System.out.print((heap[j] == null)? "" : heap[j]);
			
			if (++column == itemsPerRow) {
				nBlanks /= 2;                 
				itemsPerRow *= 2;             
				column = 0;                   
				System.out.println();         
			}
			else                             
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
			
			j++;
		}
		System.out.println("\n" + dots + dots); 
	}
}