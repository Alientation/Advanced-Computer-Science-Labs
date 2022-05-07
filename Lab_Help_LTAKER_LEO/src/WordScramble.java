import java.util.*;
public class WordScramble {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.println("Type a word, sentence, or entire paragraph followed by enter.");
		String input = console.nextLine();
		System.out.println("\nHere is a scrambled version:");
		scramble(input);
	}
	public static void scramble(String line) {
		Random randomGen = new Random();
		int rando = 0;
		String[] words = line.split(" ");
		char storage = ' ';
		StringBuilder fini = new StringBuilder("");
		for (String word : words) {
			char[] cara = word.toCharArray();
			int sub = Character.isLetter(cara[cara.length - 1]) ? 0 : 1;
			for (int e = 1; e < (cara.length - 1 - sub); e++) {
				storage = cara[e];
				rando = randomGen.nextInt(cara.length - 2 - sub) + 1;
				cara[e] = cara[rando];
				cara[rando] = storage;
			}
			fini.append(String.copyValueOf(cara) + " ");
		}
		System.out.println(fini.toString());
	}
}
