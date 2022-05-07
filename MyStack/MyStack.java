import java.util.EmptyStackException;
import java.util.Stack;

class StackGetMinRunner {
/*
Tests the MyStack.getMin();
*/
   public static void main(String[] args) {
      MyStack stack = new MyStack();
      for (int i = 0; i < 8; i++) {
         stack.push(7-i);
      }
      for (int i = 0; i < 8; i++) {
         System.out.println("==============\n" + stack + "\n===========");
         System.out.println(stack.getMin());
         stack.pop();
      }
   }
}


public class MyStack {
	
	private int[] stack;
	private int size;
   
   /*
   stack for constant time minimum
   */
   Stack<Integer> minimumStack = new Stack<Integer>();
   int minimum = Integer.MAX_VALUE;
	
	public MyStack() {
		this(16);
	}
	
	public MyStack(int initCap) {
		stack = new int[initCap];
		size = 0;
	}
	

	public boolean isEmpty() {
		return size == 0;
	}

	public int pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		size--;
		int num = stack[size];
      if (num == minimum) {
         if (minimumStack.size() != 0) {
            minimum = minimumStack.pop();
         } else {
            minimum = Integer.MAX_VALUE;
         }
      }
 		stack[size] = 0;
		return num;
	}

	public void push(int i) {
		if (size == stack.length - 1) {
			doubleCapacity();
		}
      if (minimum >= i) {
         minimumStack.push(minimum);
         minimum = i;
      }
		stack[size] = i;
		size++;
	}

	public int peek() {
      if (size == 0) {
			throw new EmptyStackException();
		}
		return stack[size-1];
	}
	
	private void doubleCapacity() {
		int[] tempStack = new int[stack.length];
		for (int i = 0; i < stack.length; i++) {
			tempStack[i] = stack[i];
		}
		stack = new int[stack.length * 2];
		for (int i = 0; i < tempStack.length; i++) {
			stack[i] = tempStack[i];
		}
	}
   
   public int getMin() {
      return minimum;
   }
	
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < size; i++) {
			output = output + stack[i];
			if (i != size -1) {
				output = output + ", ";
			}
		}
		return "{" + output + "}";
	}
}
