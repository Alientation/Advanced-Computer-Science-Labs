import java.util.Stack;

public class StackProbs {
   public StackProbs() {
      
   }
   
   public Stack<Integer> doubleUp(Stack<Integer> nums) {
      Stack<Integer> newStack = Runner.reverseStack(nums);
      while (!newStack.empty()) {
         int num =  newStack.pop();
         nums.push(num);
         nums.push(num);
      }
      return nums;
   }
   
   public Stack<Integer> posAndNeg(Stack<Integer> nums) {
      Stack<Integer> posStack = new Stack<Integer>();
      Stack<Integer> negStack = new Stack<Integer>();
      while (!nums.isEmpty()) {
         int num = nums.pop();
         if (num >= 0) {
            posStack.push(num);
         } else {
            negStack.push(num);
         }
      }
      while(!negStack.isEmpty()) {
         nums.push(negStack.pop());
      }
      while(!posStack.isEmpty()) {
         nums.push(posStack.pop());
      }
      return nums;
   }
   
   public Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
      Stack<Integer> topStack = new Stack<Integer>();
      Stack<Integer> bottomStack = new Stack<Integer>();
      for (int x = nums.size(); x > n; x--) {
         topStack.push(nums.pop());
      }
      while(!nums.isEmpty()) {
         bottomStack.push(nums.pop());
      }
      while(!topStack.isEmpty()) {
         nums.push(topStack.pop());
      }
      while(!bottomStack.isEmpty()) {
         nums.push(bottomStack.pop());
      }
      return nums;
   }
   
   public String reverseVowels(String str) {
      String result = "";
      Stack<Character> vowels = new Stack<Character>();
      for (int i = 0; i < str.length(); i++) {
         if ("aeiou".indexOf((str.charAt(i) + "").toLowerCase()) != -1) {
            vowels.push(str.charAt(i));
         }
      }
      for (int i = 0; i < str.length(); i++) {
    	  if ("aeiou".indexOf((str.charAt(i) + "").toLowerCase()) != -1) {
              result = result + vowels.pop();
          } else {
        	  result = result + str.charAt(i);
          }
      }
      return result;
   }
   
   public boolean bracketBalance(String s) {
	   Stack<Character> brackets = new Stack<Character>();
	   for (int i = 0; i < s.length(); i++) {
		   if (s.charAt(i) == '(' || s.charAt(i) == '[') {
			   brackets.push(s.charAt(i));
		   } else if (s.charAt(i) == ')' || s.charAt(i) == ']') {
			   if (brackets.isEmpty()) {
				   return false;
			   }
			   char c = brackets.pop();
			   if (s.charAt(i) == ')') {
				   if (c != '(') {
					   return false;
				   }
			   } else {
				   if (c != '[') {
					   return false;
				   }
			   }
		   }
	   }
	   return true;
   }
}