/*************************************************************************
 *  Compilation:  javac Game.java
 *  Execution:    java Game < input.txt
 *  Dependencies: Dungeon.java Site.java In.java Monster.java Rogue.java
 *
 *************************************************************************/
import java.io.*;

public class Game {
    //
    private final static String DFILE = "dungeonI.txt";   

    // portable newline
    private final static String NEWLINE = System.getProperty("line.separator");    
  
    private Dungeon dungeon;     // the dungeon
    private char MONSTER;        // name of the monster (A - Z)
    private char ROGUE = '@';    // name of the rogue
    private int N;               // board dimension
    private Site monsterSite;    // location of monster
    private Site rogueSite;      // location of rogue
    private Monster monster;     // the monster
    private Rogue rogue;         // the rogue

    // initialize board from file
    public Game(In in) {

        // read in data
        N = Integer.parseInt(in.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = in.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = s.charAt(2*j);

                // check for monster's location
                if (board[i][j] >= 'A' && board[i][j] <= 'Z') {
                    MONSTER = board[i][j];
                    board[i][j] = '.';
                    monsterSite = new Site(i, j);
                }

                // check for rogue's location
                if (board[i][j] == ROGUE) {
                    board[i][j] = '.';
                    rogueSite  = new Site(i, j);
                }
            }
        }
        dungeon = new Dungeon(board);
        monster = new Monster(this);
        rogue   = new Rogue(this);
    }

    // return position of monster and rogue
    public Site getMonsterSite() { return monsterSite; }
    public Site getRogueSite()   { return rogueSite;   }
    public Dungeon getDungeon()  { return dungeon;     }
    
    public void randomlyPlaceRogue() {
        rogueSite = dungeon.getRandomSite();        
    }


    // play until monster catches the rogue
    public void play() {
        int time = 100; 
        int kills = 0;
        for (int t = 1; true; t++) {
            System.out.println('\f');
            System.out.println("Kills " + kills);
            System.out.println("Time " + t);
            System.out.println();
            
            // monster moves
            if (monsterSite.equals(rogueSite)) {
                //System.out.println("Caught by monster");
                kills++;
                randomlyPlaceRogue();
            }
            Site next = monster.move();
            if (dungeon.isLegalMove(monsterSite, next)) monsterSite = next;
            else throw new RuntimeException("Monster caught cheating");
            System.out.println(this);
            try { Thread.sleep(time); }
            catch (Exception e) {  }
            
            System.out.println('\f');
            System.out.println("Kills " + kills);
            System.out.println("Time " + t);
            System.out.println();

            // rogue moves
            if (monsterSite.equals(rogueSite)) {
                //System.out.println("Caught by monster");
                kills++;
                randomlyPlaceRogue();
            }
            next = rogue.move();
            if (dungeon.isLegalMove(rogueSite, next)) rogueSite = next;
            else throw new RuntimeException("Rogue caught cheating");
            System.out.println(this);
            try { Thread.sleep(time); }
            catch (Exception e) {  }
        }
    }


    // string representation of game state (inefficient because of Site and string concat)
    public String toString() {
        String s = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (rogueSite.equals(monsterSite) && (rogueSite.equals(site))) s += "* ";
                else if (rogueSite.equals(site))                               s += ROGUE   + " ";
                else if (monsterSite.equals(site))                             s += MONSTER + " ";
                else if (dungeon.isRoom(site))                                 s += ". ";
                else if (dungeon.isCorridor(site))                             s += "+ ";
                else if (dungeon.isRoom(site))                                 s += ". ";
                else if (dungeon.isWall(site))                                 s += "  ";
            }
            s += NEWLINE;
        }
        return s;
    }


    public static void main(String[] args) {
        Game game = new Game(new In(DFILE));
        System.out.println(game);
        game.play();

    }

}
