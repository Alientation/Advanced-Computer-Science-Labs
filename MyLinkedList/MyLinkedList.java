import java.util.*;

public class MyLinkedList implements Iterable<Integer> {
   ListNode head;
   ListNode tail;
   int size;
   
   private class ListNode {
      int val;
      ListNode next;
      public ListNode(int val) { this.val = val; }
      
      @Override
      public String toString() { return "" + this.val; }
      
   }
   
   public MyLinkedList() {
      head = null;
      tail = null;
      size = 0;
   }
   
   public MyLinkedList(int val) {
      head = new ListNode(val);
      tail = head;
      size = 1;
   }
   
   public Iterator<Integer> iterator() {
      return new Iterator<Integer>() {
         ListNode node = head;
         
         @Override
         public boolean hasNext() {
            return node != null;
         }
         
         @Override
         public Integer next() {
            if (hasNext()) {
               Integer val = node.val;
               node = node.next;
               return val;
            }
            return null;
         }
         
         @Override
         public void remove() {
            throw new UnsupportedOperationException("L");
         }
      };
   }
   
   public void add(int newVal) {
      //size == 0 thus set head and tail to a node, else then add the node behind tail, and update tail
      if (head == null) {
         head = new ListNode(newVal);
         tail = head;
      } else {
         tail.next = new ListNode(newVal);
         tail = tail.next;
      }
      size++;
   }
   
   public boolean contains(int target) {
      //loop through all nodes starting from head
      ListNode node = head;
      while (node != null) {
         if (node.val == target) {
            return true;
         }
         node = node.next;
      }
      return false;
   }
   
   public int get(int index) {
      if (index >= size || index < 0) {
         throw new IndexOutOfBoundsException();
      }
      ListNode node = head;
      for (int i = 0; i < index; i++) {
         node = node.next;
      }
      return node.val;
   }
   
   public int indexOf(int target) {
      ListNode node = head;
      int index = 0;
      while (node != null) {
         if (node.val == target) {
            return index;
         }
         index++;
         node = node.next;
      }
      return -1;
   }
   
   public void set(int newVal, int index) {
      if (index >= size || index < 0) {
         throw new IndexOutOfBoundsException();
      }
      ListNode node = head;
      for (int i = 0; i < index; i++) {
         node = node.next;
      }
      node.val = newVal;
   }
   
   public int size() {
      return size;
   }
   
   public int sizeRecursive(ListNode node) {
      if (node == null) {
         return 0;
      }
      return 1 + sizeRecursive(node.next);
   }
   
   public int sizeRecursive() {
      return sizeRecursive(head);
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public int remove(int index) {
      if (index >= size || index < 0) {
         throw new IndexOutOfBoundsException();
      }
      ListNode node = head;
      size--;
      if (index == 0) {
         if (size == 0) {
            tail = null;
         }
         head = head.next;
         return node.val;
      }
      for (int i = 0; i < index-1; i++) {
         node = node.next;
      }
      if (index == size) {
         int val = tail.val;
         tail = node;
         tail.next = null;
         return val;
      }
      int val = node.next.val;
      node.next = node.next.next;
      return val;
   }
   
   public void add(int newVal, int index) {
      if (index > size || index < 0) {
         throw new IndexOutOfBoundsException();  
      }
      size++;
      if (head == null) {
         head = new ListNode(newVal);
         tail = head;
         return;
      }
      if (index == 0) {
         ListNode node = new ListNode(newVal);
         node.next = head;
         head = node;
         return;
      }
      if (index == size-1) {
         tail.next = new ListNode(newVal);
         tail = tail.next;
         return;
      }
      ListNode node = head;
      for (int i = 0; i < index - 1; i++) {
         node = node.next;
      }
      ListNode temp = node.next;
      node.next = new ListNode(newVal);
      node.next.next = temp;
   }
   
   @Override
   public String toString() {
      String str = "[";
      ListNode node = head;
      if (head != null) {
         str = str + head.val;
         node = head.next;
      }
      while (node != null) {
         str = str + ", " + node.val;
         node = node.next;
      }
      //System.out.println(str + "]\n(head: " + head.val + " , tail: " + tail.val + ")");
      return str + "]";
   }
}