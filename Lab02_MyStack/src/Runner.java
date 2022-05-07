import java.util.Stack;

public class Runner {
   public static void main(String[] args) {
      StackProbs stackProbs = new StackProbs();
      
      printStack(stackProbs.doubleUp(makeStack(new int[] {1,3,5,0,-1})));
      printStack(stackProbs.posAndNeg(makeStack(new int[] {2,9,-4,3,-1,0,-6})));
      printStack(stackProbs.shiftByN(makeStack(new int[] {7,23,-7,0,22,-8,4,5}),3));
      System.out.println(stackProbs.reverseVowels("hello how are you"));
      System.out.println(stackProbs.bracketBalance("(([()])))"));
      System.out.println(stackProbs.bracketBalance("([()[]()]) ()"));
   }
   
   public static Stack<Integer> makeStack(int[] nums) {
      Stack<Integer> stack = new Stack<>();
      for (int num : nums)
         stack.push(num);
      return stack;
   }
   
   public static void printStack(Stack<Integer> stack) {
      stack = reverseStack(stack);
      while (!stack.isEmpty()) {
         System.out.print(stack.pop() + " ");
      }
      System.out.println();
   }
   
   public static Stack<Integer> reverseStack(Stack<Integer> stack) {
      Stack<Integer> newStack = new Stack<Integer>();
      while(!stack.isEmpty()) {
         newStack.push(stack.pop());
      }
      return newStack;
   }
}