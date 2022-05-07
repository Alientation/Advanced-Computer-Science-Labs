import java.util.EmptyStackException;

public class MyStack implements StackADT{
	
	private Square[] stack;
	private int size;
   	
	public MyStack() {
		this(16);
	}
	
	public MyStack(int initCap) {
		stack = new Square[initCap];
		size = 0;
	}
   
   public int size() {
      return this.size;
   }
	

	public boolean isEmpty() {
		return size == 0;
	}
   
   public void clear() {
      for (int i = 0; i < size; i++) {
         stack[size] = null;
      }
      size = 0;
   }

	public Square pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		size--;
		Square num = stack[size];
 		stack[size] = null;
		return num;
	}

	public void push(Square i) {
		if (size == stack.length - 1) {
			doubleCapacity();
		}
  		stack[size] = i;
		size++;
	}

	public Square peek() {
      if (size == 0) {
			throw new EmptyStackException();
		}
		return stack[size-1];
	}
	
	private void doubleCapacity() {
		Square[] tempStack = new Square[stack.length * 2];
		for (int i = 0; i < stack.length; i++) {
			tempStack[i] = stack[i];
		}
		stack = tempStack;
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
