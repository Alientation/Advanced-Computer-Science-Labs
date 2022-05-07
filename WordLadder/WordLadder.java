import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class WordLadder	{

	//minimize collisions
	private HashMap<Integer,HashMap<String,Boolean>> bigDictionary;
	private HashMap<String,Boolean> dictionary;

	//saved mutations	from calculations
	private HashMap<Integer,HashMap<String,ArrayList<String>>> allSavedWork;
	private HashMap<String,ArrayList<String>>	savedWork;

	//hashmaps are	supposedly faster	than hashsets
	private HashMap<String,Boolean> visited;

	private Queue<Stack<String>> wordladder;

	//minimize wasted	memory
	private Stack<String> temp; //current ladder
	private ArrayList<String> savedTemp; //saved	mutations from	a specfic word
	private Stack<String> newStack; //helper variable to copy stacks when diverging
	private String	output;
	private int	tempSize; //idk if this	is	necessary
	private char[]	chs;
	private char oldCh;
	private String	peek;	//current word
	private String	word;	//possible mutation from 'peek'
	private ArrayList<String> allPossibleWords; //store possible mutations from a	specific	word

	public WordLadder() {
		//init stuff
		bigDictionary =	new HashMap<Integer,HashMap<String,Boolean>>();
		wordladder =	new LinkedList<Stack<String>>();
		visited =	new HashMap<String,Boolean>();
		temp =	new Stack<String>();
		savedWork	= new	HashMap<String,ArrayList<String>>();
		allSavedWork	= new	HashMap<Integer,HashMap<String,ArrayList<String>>>();
		allPossibleWords =	new ArrayList<String>();
		output	= "";

		String next;
		try {
			BufferedReader	in	= new	BufferedReader(new FileReader("dictionary.txt"));
			next = in.readLine();
			while	(next	!=	null)	{
				if	(bigDictionary.get(next.length())	==	null)	{
					bigDictionary.put(next.length(),	new	HashMap<String,Boolean>());
				}
				bigDictionary.get(next.length()).put(next.toLowerCase(),true);
				next = in.readLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void	setWords(String start, String	end) {
		//Check if start word and end	word exist and if they are of the same length,	if	not then	the ladder is impossible
		if	(start.length() != end.length() || bigDictionary.get(start.length())	==	null || bigDictionary.get(start.length()).get(start)	==	null || bigDictionary.get(end.length()).get(end)	==	null)	{
			output	= "No ladder between " +	start	+ " and " +	end;
			return;
		}
		//reseting stuff
		output	= "";
		dictionary =	bigDictionary.get(start.length());
		wordladder.clear(); //clear()	is	faster than	initializing a	new object since the	latter is costly
		visited.clear();
		temp.clear();
		visited.put(start,true);
		temp.push(start);
		wordladder.offer(temp);
		tempSize	= 0;
		allPossibleWords.clear();

		if	(start.equals(end)) {
		  output	= "Found a ladder! >>> "	+ temp;
			return;
		}

		//check if there is already a	hashmap initialized for	the current	word length
		savedWork	= allSavedWork.get(start.length());
		if	(savedWork == null) {
			savedWork =	new HashMap<String,ArrayList<String>>();
		}

		while	(wordladder.peek()	!=	null)	{
			temp =	wordladder.poll();
			peek = temp.peek();
			savedTemp =	savedWork.get(peek);

			//if the	word has	already been searched before in a different query,	then just used	the stored possible mutations	without having	to	loop a lot
			if	(savedTemp != null) {
				tempSize	=	savedTemp.size();	//idk	if	this is faster	than just putting	it	in	the for loop
				for (int	i = 0; i	<	tempSize; i++)	{
					if	(visited.get(savedTemp.get(i)) == null) {
						if	(savedTemp.get(i).equals(end)) {
							temp.push(end);
							output =	"Found a ladder! >>> " + temp;
							return;
						}
						visited.put(savedTemp.get(i),	true);
						newStack	=	(Stack<String>)temp.clone();
						newStack.push(savedTemp.get(i));
						wordladder.offer(newStack);
					}
				}
			} else {
				//loop through	all possible variations	and find	the valid mutations then store it so that	in	the future 
				//if the	word ladder	ever encounters the same word, it can quickly use the	stored calculations without having to caluclation again
				allPossibleWords	= new	ArrayList<String>();
				chs =	peek.toCharArray();
				for (int	c	= 0; c <	chs.length;	c++) {
					oldCh	=	chs[c];
					for (char ch =	'a';	ch	<=	'z'; ch++) {
						if	(ch == chs[c])	{
							continue;
						}
						chs[c] =	ch;
						word	= String.valueOf(chs);
						if	(dictionary.get(word) != null) {
							allPossibleWords.add(word);
							if	(visited.get(word) == null) {
								if	(word.equals(end)) {
									 temp.push(end);
									 output =	"Found a ladder! >>> " + temp;
									 savedWork.put(peek,	allPossibleWords);
									 return;
								 }
								 visited.put(word,true);
								 newStack =	(Stack<String>)temp.clone();
								 newStack.push(word);
								 wordladder.offer(newStack);
							}
						}
						chs[c] =	oldCh;
					}
				}
            //store saved calculations
			   savedWork.put(peek,	allPossibleWords);
				allSavedWork.put(peek.length(),savedWork);
			}
		}
      //ran out of wordladders in queue
		output =	"No ladder between "	+ start +	" and " + end;
	}

	@Override
	public String toString() {
		return output;
	}
}