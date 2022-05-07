
public class MyBST<T extends Comparable<T>> {
	private BSTNode root;
	private class BSTNode {
		T val;
		BSTNode left, right;
		public BSTNode(T val) {
			this.val = val;
			this.left = this.right = null;
		}
		
		public boolean isLeaf() {
			return this.left == this.right && this.right == null;
		}
		public int numChildren() {
			return (this.left == null ? 0 : 1) + (this.right == null ? 0 : 1);
		}
		@Override
		public String toString() { return "" + this.val; }
	}
	public MyBST(BSTNode root) { this.root = root; }
	public MyBST(T val) { this.root = new BSTNode(val); }
	public MyBST() { }
	
	
	public int size() {
		return size(this.root);
	}
	
	private int size(BSTNode node) {
		if (node == null)
			return 0;
		return 1 + size(node.left) + size(node.right);
	}
	
	public void insert(T n) {
		if (root == null)
			this.root = new BSTNode(n);
		else
			insert(n, this.root);
	}
	
	private void insert(T n, BSTNode node) {
		if (node == null)
			return;
		if (node.val.compareTo(n) > 0) {
			if (node.left == null)
				node.left = new BSTNode(n);
			insert(n,node.left);
		} else if (node.val.compareTo(n) < 0) {
			if (node.right == null)
				node.right = new BSTNode(n);
			insert(n,node.right);
		}
	}
	
	public boolean contains(Integer n) {
		return contains(n,this.root);
	}
	
	private boolean contains(Integer n, BSTNode node) {
		if (node == null)
			return false;
		if (node.val.equals(n))
			return true;
		return contains(n,node.left) || contains(n,node.right);
	}
	
	public T getMax() {
		return getMax(this.root);
	}
	
	private T getMax(BSTNode node) {
		if (node == null)
			return null;
		return max(node.val,getMax(node.left), getMax(node.right));
	}
	
	@SafeVarargs
	private T max(T ... vals) {
		T max = null;
		for (int i = 0; i < vals.length; i++)
			if (max == null)
				max = vals[i];
			else if (vals[i] != null && max.compareTo(vals[i]) < 0) {
				max = vals[i];
		}
		return max;
	}
	
	
	public T getMin() {
		return getMin(this.root);
	}
	
	private T getMin(BSTNode node) {
		if (node == null)
			return null;
		return min(node.val,getMin(node.left), getMin(node.right));
	}
	
	@SafeVarargs
	private T min(T ... vals) {
		T min = null;
		for (int i = 0; i < vals.length; i++)
			if (min == null)
				min = vals[i];
			else if (vals[i] != null && min.compareTo(vals[i]) > 0)
				min = vals[i];
		return min;
	}
	
	public void delete(T n) {
		delete(n,this.root,null);
	}
	
	private void delete(T n, BSTNode node, BSTNode prevNode) { //find the node to delete, then find the suitable node to replace the deleted node
		if (node == null)
			return;
		if (node.val.compareTo(n) > 0)//node is greater than the target
			delete(n,node.left,node);
		else if (node.val.compareTo(n) < 0)
			delete(n,node.right,node);
		else if (node.val.equals(n)) {
			if (node.isLeaf())
				if (prevNode.right == node)
					prevNode.right = null;
				else
					prevNode.left = null;
			else if (node.numChildren() == 1)
				if (prevNode.right == node)
					prevNode.right = node.right == null ? node.left : node.right;
				else
					prevNode.left = node.right == null ? node.left : node.right;
			else {
				BSTNode prevReplacement = node.right;
				if (prevReplacement.left == null) {
					node.right = prevReplacement.right;
					node.val = prevReplacement.val;
					return;
				}
				while (prevReplacement.left != null && prevReplacement.left.left != null)
					prevReplacement = prevReplacement.left;
				node.val = prevReplacement.left.val;
				prevReplacement.left = prevReplacement.left.right;
			}
		}
	}
	
	public void inOrder() {
		inOrder(this.root);
	}
	
	private void inOrder(BSTNode node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.print(node.val + " ");
		inOrder(node.right);
	}
	
	public void preOrder() {
		preOrder(this.root);
	}
	
	private void preOrder(BSTNode node) {
		if (node == null)
			return;
		System.out.print(node.val + " ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public void postOrder() {
		postOrder(this.root);
	}
	
	private void postOrder(BSTNode node) {
		if (node == null)
			return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.val + " ");
	}
	
	public void print() {
		print(this.root,0);
	}
	
	private void print(BSTNode node, int depth) { //print right, then current, then left (reverse inOrder)
		if (node == null)
			return;
		print(node.right, depth+1);
		for (int i = 0; i < depth; i++)
			System.out.print("    ");
		System.out.println(node.val);
		print(node.left, depth+1);
	}
	
	public BSTNode getRoot() {
	   return this.root;
	}
}