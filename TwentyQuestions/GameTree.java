import java.io.FileWriter;
import java.io.*;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree {
   private Node root;
   private String file;
   private Node cur;
   private class Node {
      String val;
      Node left;
      Node right;
      boolean isQuestion;
      public Node(String str) {
         val = str;
         isQuestion = str.charAt(str.length()-1) == '?';
         left = right = null;
      }
   }
   
   
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName) {
		try {
			BufferedReader	in	= new	BufferedReader(new FileReader(fileName));
         root = new Node(in.readLine().trim());
         cur = root;
         makeTree(root,in);
		} catch(IOException e) {
			e.printStackTrace();
		}
      file = fileName;
	}
   
   public void makeTree(Node parent, BufferedReader in) throws IOException{
      if (parent.isQuestion) {
         String line = in.readLine();
         if (line == null) return;
         parent.left = new Node(line.trim());
         makeTree(parent.left,in);
         line = in.readLine();
         if (line == null) return;
         parent.right = new Node(line.trim());
         makeTree(parent.right,in);
      }
   }

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA) {
      cur.left = new Node(newA);
      cur.right = new Node(cur.val);
      cur.val = newQ;
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer() {
		return !cur.isQuestion;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent() {
		return cur.val;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo) {
      cur = yesOrNo == Choice.Yes ? cur.left : cur.right;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart() {
		cur = root;
   }

	@Override
	public String toString() {
		return toString(root, 0);
	}
   
   public String toString(Node node, int level) {
      if (node == null)
         return "";
      String str = "";
      for (int i = 0; i < level; i++)
         str = str + "- ";
      return (node.right != null ? toString(node.right,level+1) + "\n" : "") + str + node.val + (node.left != null ? "\n" + toString(node.left,level+1) : "");
   }

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame() {
		String str = root.val + saveGame(root.left) + saveGame(root.right);
      try {
         FileWriter fileWriter = new FileWriter(file);
         fileWriter.write(str);
         fileWriter.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
	}
   
   public String saveGame(Node node) {
      if (node == null)
         return "";
      return "\n" + node.val + saveGame(node.left) + saveGame(node.right);
   }
}
