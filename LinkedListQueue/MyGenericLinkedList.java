import java.util.*;

public class MyGenericLinkedList<T> implements Iterable<T> {
   ListNode head;
   ListNode tail;
   int size;
   
   private class ListNode {
      T val;
      ListNode next;
      public ListNode(T val) { this.val = val; }
      
      @Override
      public String toString() { return "" + this.val; }
      
   }
   
   
   public boolean isCycle() {
      ListNode slow = head;
      ListNode fast = head;
      while (fast != null && fast.next != null && slow != null) {
         if (slow == fast) {
            return true;
         }
         slow = slow.next;
         fast = fast.next.next;
      }
      return false;
   }
   /*
   public MyGenericLinkedList<T> mergeK (MyGenericLinkedList<T>[] lists) {
      MyGenericLinkedList<T> list = new MyGenericLinkedList<T>();
      int listToRemoveFrom = -1;
      while (!isEmpty(lists)) {
         for (int i = 0; i < lists.length; i++) {
            if (lists[i].size() == 0) {
               continue;
            }
            if (listToRemoveFrom == -1 || lists[i].get(lists[i].size() - 1).compareTo(lists[listToRemoveFrom].get(lists[listToRemoveFrom].size() - 1)) == -1) {
               listToRemoveFrom = i;
            }
         }
         list.add(lists[listToRemoveFrom].get(lists[listToRemoveFrom].size()-1));
         lists[listToRemoveFrom].remove(lists[listToRemoveFrom].size()-1);
      }
      return list;
   }
   
   public boolean isEmpty(MyGenericLinkedList<T>[] lists) {
      for (int i = 0; i < lists.length; i++) {
         if (!lists[i].isEmpty()) {
            return false;
         }
      }
      return true;
   }
   */
   public MyGenericLinkedList() {
      
   }
   
   public MyGenericLinkedList(T... vals) {
      for (T i : vals) {
         add(i);
      }
   }
   
   public void clear() {
      head = tail = null;
   }
   
   public Iterator<T> iterator() {
      return new Iterator<T>() {
         ListNode node = head;
         
         @Override
         public boolean hasNext() {
            return node != null;
         }
         
         @Override
         public T next() {
            if (hasNext()) {
               T val = (T) node.val;
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
   
   public void add(T newVal) {
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
   
   public boolean contains(T target) {
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
   
   public T get(int index) {
      if (index >= size || index < 0) {
         throw new IndexOutOfBoundsException();
      }
      if (index == size-1) {
         return (T) tail.val;
      }
      
      ListNode node = head;
      for (int i = 0; i < index; i++) {
         node = node.next;
      }
      return (T) node.val;
   }
   
   public int indexOf(T target) {
      ListNode node = head;
      int index = 0;
      while (node != null) {
         if (node.val.equals(target)) {
            return index;
         }
         index++;
         node = node.next;
      }
      return -1;
   }
   
   public void set(T newVal, int index) {
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
   
   public T remove(int index) {
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
         return (T) node.val;
      }
      for (int i = 0; i < index-1; i++) {
         node = node.next;
      }
      if (index == size) {
         T val = (T) tail.val;
         tail = node;
         tail.next = null;
         return val;
      }
      T val = (T) node.next.val;
      node.next = node.next.next;
      return val;
   }
   
   public void add(T newVal, int index) {
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