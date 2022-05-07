//v2.0
public class TestRunner27 extends TestGUI.TestData{ 
    public static void main (String[] args) {        
        if (VERSION_NUM < 2.172)
            System.out.println("This test script requires TestGUI version 2.172 or higher.\nPlease download a newer version of TestGUI.java.");
        else
            new TestGUI();    
    }

    @SuppressWarnings("unchecked") 
    public static void runTests() {  //Your test sequence must be within a method called runTests()
        //WRITE ALL YOUR TEST CASES IN HERE   
        // Newline Converter: https://onlinestringtools.com/convert-newlines-to-spaces
        setTimeOutSec(1); 
        srcButton("PracticeProblems, WordScramble");

        message("Best possible score: 22 green, 2 yellow, 0 red");

        header("letterGrade"); 
        testMethod("PracticeProblems", "isLetterA", new Object[]{'A'}, "true");
        testMethod("PracticeProblems", "isLetterA", new Object[]{'a'}, "true");
        testMethod("PracticeProblems", "isLetterA", new Object[]{'@'}, "false");        

        header("hasMultipleAs");
        testMethod("PracticeProblems", "hasMultipleAs", new Object[]{"salsa"}, "true");  
        testMethod("PracticeProblems", "hasMultipleAs", new Object[]{"chips"}, "false");  
        testMethod("PracticeProblems", "hasMultipleAs", new Object[]{"guac"}, "false");  

        header("isVowel");
        testMethod("PracticeProblems", "isVowel", new Object[]{'r'}, "false");  
        testMethod("PracticeProblems", "isVowel", new Object[]{'i'}, "true");  

        header("numVowels");
        testMethod("PracticeProblems", "numVowels", new Object[]{"abEcidOgu"}, "5");
        testMethod("PracticeProblems", "numVowels", new Object[]{"cry"}, "0");

        header("evenChars");
        testMethod("PracticeProblems", "evenChars", new Object[]{"abacab", 'a', 'b'}, "false");
        testMethod("PracticeProblems", "evenChars", new Object[]{"babacab", 'a', 'b'}, "true");

        header("hasRepeat");
        testMethod("PracticeProblems", "hasRepeat", new Object[]{"abba", 'b'}, "true");
        testMethod("PracticeProblems", "hasRepeat", new Object[]{"abba", 'a'}, "false");
        testMethod("PracticeProblems", "hasRepeat", new Object[]{"abbaa", 'a'}, "true");

        header("capChar");
        testMethod("PracticeProblems", "capChar", new Object[]{'a'}, "A");
        testMethod("PracticeProblems", "capChar", new Object[]{'Z'}, "Z");

        header("capVowels");
        testMethod("PracticeProblems", "capVowels", new Object[]{"computer"}, "cOmpUtEr");
        testMethod("PracticeProblems", "capVowels", new Object[]{"CoMPuTeR"}, "cOmpUtEr");

        header("Running main in PracticeProblems");
        message("Output should look similar to output in the sampleoutput file");
        testMethod("PracticeProblems", "main", new Object[]{new String[0]},
            "'7' is the character 'a'? >>> false\n'a' is the character 'a'? >>> true\n'F' is the character 'a'? >>> false\n\n\"apple\" has at least 2 'a's? >>> false\n\"Some teachers eat apples.\" has at least 2 'a's? >>> true\n\n'u' is a vowel? >>> true\n'b' is a vowel? >>> false\n'I' is a vowel? >>> true\n\nVowel count in \"salsa\" >>> 2\nVowel count in \"The best teachers eat salsa.\" >>> 9\n\nThe word \"salsa\" has the same number of 'a' and 's'? >>> true\nThe word \"salsa\" has the same number of 'a' and 'f'? >>> false\n\nThe word \"programmer\" has a repeated 'm'? >>> true\nThe word \"programmer\" has a repeated 'r'? >>> false\n\nCapitalizing 'm' gives you >>> M\nCapitalizing 'W' gives you >>> W\n\nCapitalizing vowels in \"salsa\" gives you >>> sAlsA\nCapitalizing vowels in \"SCRUMPTIOUS\" gives you >>> scrUmptIOUs"
        );

        header("WordScramble");
        testMethod("WordScramble", "main", new Object[]{new String[0]}, 
            "Type a word, sentence, or entire paragraph followed by enter.\nabcdef\n\nHere is a scrambled version:\na%word%f \n", 
            "abcdef\n"); 
        testMethod("WordScramble", "main", new Object[]{new String[0]}, 
            "Type a word, sentence, or entire paragraph followed by enter.\na******z\n\nHere is a scrambled version:\na******z\n", 
            "a******z\n"); 
        testMethod("WordScramble", "main", new Object[]{new String[0]}, 
            "Type a word, sentence, or entire paragraph followed by enter.\nbaaaad? caaaaaaat gaaaaaar!\n\nHere is a scrambled version:\nbaaaad? caaaaaaat gaaaaaar!\n", 
            "baaaad? caaaaaaat gaaaaaar!");     
        testMethod("WordScramble", "main", new Object[]{new String[0]}, 
            "Type a word, sentence, or entire paragraph followed by enter.\nthere's some scrambling happening here\n\nHere is a scrambled version:\nt%word%s s%word%e s%word%g h%word%g h%word%e\n", 
            "there's some scrambling happening here");     

    }    
}