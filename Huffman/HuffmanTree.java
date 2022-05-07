import java.util.*;
import java.io.*;


class Node implements Comparable<Node> {
	int weight;
	int c;
	Node left;
	Node right;
	public Node(int weight) {
		this.weight = weight;
		this.c = -1;
	}

	public Node(int c, int weight) {
		this.c = c;
		this.weight = weight;
	}

	public Node(Node n1, Node n2) {
		this(n1.weight + n2.weight);
		this.left = n1;
		this.right = n2;
	}

	public Node combineWith(Node n) {
		return new Node(this,n);
	}

	public int compareTo(Node n) {
		return weight - n.weight;
	}

	@Override
	public String toString() {
		if (isLeaf())
			return "" + (char) c;
		return weight + "";
	}

	public boolean isLeaf() {
		return left == right && right == null;
	}
}

public class HuffmanTree {
	public static int EOF = 256;
	PriorityQueue<Node> tree;
   HashMap<Integer,char[]> characterToBits;

	public static void main(String[] args) {
		int[] freq = new int[256];
		freq[97] = 3;
		freq[98] = 6;
		freq[99] = 8;
		freq[100] = 8;
		freq[101] = 1;
		HuffmanTree tree1 = new HuffmanTree(freq);
		tree1.write("test.code");
		tree1.printTree();

		HuffmanTree tree2 = new HuffmanTree("test.code");
		tree2.printTree();

	}


	public HuffmanTree(int[] count) {
		tree = new PriorityQueue<>();
      characterToBits = new HashMap<>();
      
		for (int i = 0; i < count.length; i++)
			if (count[i] > 0)
				tree.offer(new Node(i,count[i]));
		tree.offer(new Node(EOF,1));
		makeTree();
      generateCharacterToBitsMappings();
	}

	public void makeTree() {
		while (tree.size() > 1)
			tree.offer(tree.poll().combineWith(tree.poll()));
	}

	public HuffmanTree(String codeFile) {
		tree = new PriorityQueue<>();
      characterToBits = new HashMap<>();
		tree.offer(new Node(0));
		try {
			Scanner reader = new Scanner(new File(codeFile));
			while (reader.hasNext()) {
				int c = reader.nextInt(); reader.nextLine();
				String path = reader.next();
            characterToBits.put(c,path.toCharArray());
				addNode(c,path);
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addNode(int c, String path) {
		Node curNode = tree.peek();
		for (char character : path.toCharArray())
			if (character == '0') {
				if (curNode.left == null)
					curNode.left = new Node(curNode.weight);
				curNode = curNode.left;
			} else {
				if (curNode.right == null)
					curNode.right = new Node(curNode.weight);
				curNode = curNode.right;
			}
		curNode.c = c;
	}

	public void encode(BitOutputStream out, String txtFile) {
      try {
			//Scanner reader = new Scanner(new File(txtFile)).useDelimiter("");
			BufferedReader reader = new BufferedReader(new FileReader(txtFile));
         char[] c;
			while (reader.ready()) {
				c = characterToBits.get((int) reader.read());
				for (int i = 0; i < c.length; i++)
               out.writeBit(c[i]-48);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (char bitC : characterToBits.get(EOF))
			if (bitC == '0')
				out.writeBit(0);
			else
				out.writeBit(1);
		out.close();
	}

	private void generateCharacterToBitsMappings() {
		generateCharacterToBitsMappings(tree.peek(),"");
	}

	private void generateCharacterToBitsMappings(Node cur, String path) {
		if (cur == null) return;
		generateCharacterToBitsMappings(cur.left,path + "0");
		if (cur.isLeaf())
			characterToBits.put(cur.c,path.toCharArray());
		generateCharacterToBitsMappings(cur.right, path + "1");
	}

	public void decode(BitInputStream in, String outFile) {
		Node cur = tree.peek();
      StringBuilder str = new StringBuilder("");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
			while(true) {
				if (cur.isLeaf()) {
					if (cur.c == EOF)
						break;
               str.append((char) cur.c);
					cur = tree.peek();
				}
				int bit = in.readBit();
				if (bit == -1)
					break;
				cur = bit == 0 ? cur.left : cur.right;
			}
         writer.write(str.toString());
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void printTree() {
		TreePrinter.printTree(tree.peek());
	}

	public void write(String fileName) {
		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(writeTree(tree.peek(),""));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String writeTree(Node current, String path) {
		if (current == null)
			return "";
		String str = writeTree(current.left, path + "0");
		if (current.isLeaf())
			str = str + current.c + "\n" + path + "\n";
		str = str + writeTree(current.right,path + "1");
		return str;
	}
}