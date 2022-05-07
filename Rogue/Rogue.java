/*************************************************************************
 *  Compilation:  javac Rogue.java
 * 
 *************************************************************************/
import java.util.*;
 
public class Rogue {
    private Game game;
    private Dungeon dungeon;
    private int N;

    // constructor - save away some useful information
    public Rogue(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
    }


    // TAKE A RANDOM LEGAL MOVE
    // YOUR MAIN TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
      Site monster = game.getMonsterSite();
      Site rogue   = game.getRogueSite();
      
      //Site move = getRandomValidMove();  //This is a bad idea.
      //Use a better strategy to determine the next move.
      //Site move = getLongestPathMove();
      Site move = simpleFartestPathMove();
      return move;
    }
    
    /*
    OPTIMMAL ROGUE MOVE
    
    BFS while keeping track of the visited game states
    whenever the same game state is visited
    ie the monster and the rogue are in the same position
    then that path is a cycle.
    
    find all cycles, then have the rogue traverse to the closest cycle
    where the rogue will reach the same game state
    */
    public static int maxDepth = 50;
    public ArrayList<ArrayList<Site>> findOptimalCycle() {
      boolean[][][][] visited = new boolean[N][N][N][N];
      Queue<LinkedList<Site>> queue = new LinkedList<>();
      LinkedList<Site> path = new LinkedList<>();
      path.add(game.getRogueSite());
      queue.offer(path);
      while (!queue.isEmpty()) {
         path = queue.poll();
         
         
         
      }
      return null;
    }
    
    public int[][] getMonsterDistanceMatrix() {
      int[][] visited = new int[N][N];
      Site monster = game.getMonsterSite();
      Queue<Site> queue = new LinkedList<>();
      Queue<Integer> distances = new LinkedList<>();
      queue.offer(monster);
      distances.offer(0);
      Site path;
      int distance;
      while (!queue.isEmpty()) {
         path = queue.poll();
         distance = distances.poll();
         if (visited[path.i()][path.j()] != 0)
            continue;
         visited[path.i()][path.j()] = distance;
         for (int[] direction : directions) {
            if (direction[0] == direction[1])
               continue;
            if (!dungeon.isLegalMove(path, new Site(path.i() + direction[0],path.j() + direction[1])))
               continue;
            distances.offer(distance+1);
            queue.offer(new Site(path.i() + direction[0],path.j() + direction[1]));
         }
      }
      return visited;
    }
    
    public int scorePosition(Site rogueLocation, Site monsterLocation) {
      if (rogueLocation.i() == monsterLocation.i() && rogueLocation.j() == monsterLocation.j()) //already won
         return -100;
      int score = 0;
      for (int[] dir : directions) {
          Site possibleMove = new Site(rogueLocation.i() + dir[0],rogueLocation.j() + dir[1]);
          if (dungeon.isLegalMove(rogueLocation,possibleMove)) {
               if (dungeon.isLegalMove(monsterLocation,possibleMove))
                  score += 1;
               score-=1;
            }
         }
         return score;
      }
    
    public Site simpleFartestPathMove() {
      int maxScore = -100000;
      Site bestMove = null;
      Site current = game.getRogueSite();
      int[][] distanceMapping = getMonsterDistanceMatrix();
      for (int[] direction : directions) {
         if (!dungeon.isLegalMove(current, new Site(current.i() + direction[0],current.j() + direction[1])))
             continue;
         if (distanceMapping[direction[0] + current.i()][current.j() + direction[1]] > maxScore) {
            maxScore = distanceMapping[direction[0] + current.i()][current.j() + direction[1]];// + scorePosition(new Site(current.i() + direction[0],current.j() + direction[1]),game.getMonsterSite());
            bestMove = new Site(direction[0] + current.i(),current.j() + direction[1]);
         }
      }
      return bestMove == null ? getRandomValidMove() : bestMove;
    }
    
   int[][] directions = new int[][] {{-1,1},{1,1},{-1,-1},{1,-1},{0,1},{1,0},{-1,0},{0,-1}};
   public Site getLongestPathMove() {
      boolean[][] visited = new boolean[N][N];
      Site monster = game.getMonsterSite();
      Site rogue = game.getRogueSite();
      Queue<LinkedList<Site>> queue = new LinkedList<LinkedList<Site>>();
      LinkedList<Site> path = new LinkedList<>();
      path.addLast(rogue);
      queue.offer(path);
      while (!queue.isEmpty()) {
         path = queue.poll();
         if (monster.i() == path.getLast().i() && monster.j() == path.getLast().j())
            continue;
         if (visited[path.getLast().i()][path.getLast().j()])
            continue;
         visited[path.getLast().i()][path.getLast().j()] = true;
         for (int[] direction : directions) {
            if (!dungeon.isLegalMove(path.getLast(), new Site(path.getLast().i() + direction[0],path.getLast().j() + direction[1])))
               continue;
            LinkedList<Site> newPath = new LinkedList<>(path);
            newPath.addLast(new Site(path.getLast().i() + direction[0],path.getLast().j() + direction[1]));
            queue.offer(newPath);
         }
         if (queue.isEmpty()) {
            if (path.size() > 1)
               return path.get(1);
            else
               return getRandomValidMove();
         }
      }
      return getRandomValidMove();
   }


    public Site getRandomValidMove() {
        Site monster = game.getMonsterSite();
        Site rogue   = game.getRogueSite();
        Site move    = null;

        // take random legal move
        int n = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Site site = new Site(i, j);
                if (dungeon.isLegalMove(rogue, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        return move;
    }
    
}
