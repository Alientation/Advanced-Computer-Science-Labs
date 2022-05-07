import java.util.*;
public class TreeThingy {
   private TreeNode head; //branches into all unique characters at the first index
   
   private class TreeNode {
      private HashMap<Character,TreeNode> branches;
      private char val;
      public TreeNode(char val) {
         this.val = val;
         this.branches = new HashMap<Character,TreeNode>();
      }
      public void addBranch(TreeNode node) {
         branches.put(node.val,node);
      }
      public TreeNode findBranch(char branch) {
         return branches.get(branch);
      }
      public boolean isLeaf() {
         return branches.size() == 0;
      }
   }
   
   public TreeThingy() {
      head = new TreeNode('.');
   }
   
   public void addBranch(String path) {
      TreeNode node = head;
      for (int i = 0; i < path.length(); i++) {
         if (node.findBranch(path.charAt(i)) == null) {
            node.addBranch(new TreeNode(path.charAt(i)));
         }
         node = node.findBranch(path.charAt(i));
      }
   }
   
   public boolean isValidPrefix(String prefix) {
      TreeNode node = head;
      for (int i = 0; i < prefix.length(); i++) {
         if (node == null)
            return false;
         node = node.findBranch(prefix.charAt(i));
      }
      return node != null && !node.isLeaf();
   }
}