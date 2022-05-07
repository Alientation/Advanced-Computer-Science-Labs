import java.util.EmptyStackException;

public class MyStack {
	
	private int[] stack;
	private int size;
	
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
		stack[size] = 0;
		return num;
	}

	public void push(int i) {
		if (size == stack.length - 1) {
			doubleCapacity();
		}
		stack[size] = i;
		size++;
	}

	public int peek() {
		return stack[size-1];
	}
	
	private void doubleCapacity() {
		int[] tempStack = new int[stack.length];
		for (int i = 0; i < stack.length; i++) {
			tempStack[i] = stack[i];
		}
		stack = new int[stack.length * 2];
		for (int i = 0; i < stack.length; i++) {
			stack[i] = tempStack[i];
		}
	}
	
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < size; i++) {
			output = stack[i] + output;
			if (i != size -1) {
				output = "\n" + output;
			}
		}
		return output;
	}
}
