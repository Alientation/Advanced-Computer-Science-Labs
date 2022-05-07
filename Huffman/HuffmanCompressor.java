import java.io.*;

public class HuffmanCompressor { 
	public static void main(String[] args) {
		String fileName = "War And Peace";
		long start = System.currentTimeMillis();
		System.out.println("Beginning Compression");
		compress(fileName + ".txt",fileName + ".short",fileName + ".code");
		System.out.println("Ending Compression, Beginning Expansion\ttime: " + ((System.currentTimeMillis()-start)/1000.0));
		start = System.currentTimeMillis();
		expand(fileName + ".short",fileName + ".code",fileName + ".new");
		System.out.println("Ending Expansion\ttime: " + ((System.currentTimeMillis()-start)/1000.0));
	}

	public static int[] getFrequency(String filename) {
		int[] freq = new int[HuffmanTree.EOF];
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while (reader.ready())
				freq[reader.read()]++;
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return freq;
	}
   
   /*
   Uses modified BitOutputStream that optimizes writing to file by writing only at the end.
   */
	public static void compress(String txtFile, String nameOfShortFileToBeCreated, String codeFileToBeCreated) {
		HuffmanTree huffman = new HuffmanTree(getFrequency(txtFile));
		huffman.write(codeFileToBeCreated);
		huffman.encode(new BitOutputStream(nameOfShortFileToBeCreated),txtFile);
	}

	public static void expand(String shortFile, String codeFile, String nameOfNewFileToBeCreated) {
		new HuffmanTree(codeFile).decode(new BitInputStream(shortFile),nameOfNewFileToBeCreated);
	}
	
}