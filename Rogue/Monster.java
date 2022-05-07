/*************************************************************************
 *  Compilation:  javac Monster.java
 * 
 *************************************************************************/
import java.util.*;

public class Monster {
    private Game game;
    private Dungeon dungeon;
    private int N;
    private Site[][] map;
      
    private Site[][][][] enemyNextMove;  //monster loc i, monster loc j, rogue loc i, rogue loc j
    private Site prevMonsterLocation, prevRogueLocation;
    
    // constructor - save away some useful information
    public Monster(Game game) {
        this.game    = game;
        this.dungeon = game.getDungeon();
        this.N       = dungeon.size();
        map = new Site[N][N];
        enemyNextMove = new Site[N][N][N][N];
        for (int r = 0; r < N; r++)
            for (int c = 0; c < N; c++)
                map[r][c] = new Site(r, c);  
    }

    // TAKE A RANDOM LEGAL MOVE
    // YOUR MAIN TASK IS TO RE-IMPLEMENT THIS METHOD TO DO SOMETHING INTELLIGENT
    public Site move() {
        Site monster = map[game.getMonsterSite().i()][game.getMonsterSite().j()];
        Site rogue   = map[game.getRogueSite().i()][game.getRogueSite().j()];
        
        //Site move = getRandomValidMove();  //This is a bad idea.
        //Use a better strategy to determine the next move.
         //Site move = getShortestPathMove();
         Site move = getMoreOptimalPathMove();
        return move;
    }
    
    public void trackEnemyMoves() {
      if (prevMonsterLocation == null || prevRogueLocation == null)
         return;
      enemyNextMove[prevMonsterLocation.i()][prevMonsterLocation.j()][prevRogueLocation.i()][prevRogueLocation.j()] = game.getRogueSite();
    }
    
   public Site getMoreOptimalPathMove() {
      trackEnemyMoves();
      prevMonsterLocation = game.getMonsterSite();
      prevRogueLocation = game.getRogueSite();
      
      boolean[][][][] visited = new boolean[N][N][N][N]; //monster loc i, monster loc j, rogue loc i, rogue loc j
      
      Queue<LinkedList<Site>> monsterQueue = new LinkedList<>();
      LinkedList<Site> monsterPath = new LinkedList<>();
      monsterPath.add(game.getMonsterSite());
      monsterQueue.offer(monsterPath);
      
      Queue<LinkedList<Site>> rogueQueue = new LinkedList<>();
      LinkedList<Site> roguePath = new LinkedList<>();
      roguePath.add(game.getRogueSite());
      rogueQueue.offer(roguePath);
      
      int maxScore = 80;//Integer.MIN_VALUE;
      Site bestMove = null;
      
      while(!monsterQueue.isEmpty()) {
         monsterPath = monsterQueue.poll();
         roguePath = rogueQueue.poll();
         if (visited[monsterPath.getLast().i()][monsterPath.getLast().j()][roguePath.getLast().i()][roguePath.getLast().j()])
            continue;
         visited[monsterPath.getLast().i()][monsterPath.getLast().j()][roguePath.getLast().i()][roguePath.getLast().j()] = true;
         if (monsterPath.size() > 1 && maxScore < scorePosition(monsterPath.getLast(),roguePath.getLast())) {
            maxScore = scorePosition(monsterPath.getLast(),roguePath.getLast());
            bestMove = monsterPath.get(1);
         }
         if (enemyNextMove[monsterPath.getLast().i()][monsterPath.getLast().j()][roguePath.getLast().i()][roguePath.getLast().j()] != null) {
            roguePath.add(enemyNextMove[monsterPath.getLast().i()][monsterPath.getLast().j()][roguePath.getLast().i()][roguePath.getLast().j()]);
            for (int[] direction : directionsWithZero) {
               if (!dungeon.isLegalMove(monsterPath.getLast(), new Site(monsterPath.getLast().i() + direction[0],monsterPath.getLast().j() + direction[1])))
                  continue;
               LinkedList<Site> newPath = new LinkedList<>(monsterPath);
               newPath.addLast(new Site(monsterPath.getLast().i() + direction[0],monsterPath.getLast().j() + direction[1]));
               monsterQueue.offer(newPath);
               rogueQueue.offer(new LinkedList<>(roguePath));
            }
         }
      }
      return bestMove == null ? getShortestPathMove() : bestMove;
   }
   
   public int scorePosition(Site rogueLocation, Site monsterLocation) {
      if (rogueLocation.i() == monsterLocation.i() && rogueLocation.j() == monsterLocation.j()) //already won
         return 100;
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
   int[][] directionsWithZero = new int[][] {{-1,1},{1,1},{-1,-1},{1,-1},{0,1},{1,0},{-1,0},{0,-1}};
   int[][] directions = new int[][] {{-1,1},{1,1},{-1,-1},{1,-1},{0,1},{1,0},{-1,0},{0,-1}};
   public Site getShortestPathMove() {
      return getShortestPathMove(game.getMonsterSite(),game.getRogueSite());
   }
   //IDEA save previous searches
   public Site getShortestPathMove(Site monster, Site rogue) {
      boolean[][] visited = new boolean[N][N];
      Queue<LinkedList<Site>> queue = new LinkedList<LinkedList<Site>>();
      LinkedList<Site> path = new LinkedList<>();
      path.addLast(monster);
      queue.offer(path);
      while (!queue.isEmpty()) {
         path = queue.poll();
         if (rogue.i() == path.getLast().i() && rogue.j() == path.getLast().j()) {
            if (path.size() > 1)
               return path.get(1);
            else
               return getRandomValidMove();
         }
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
                if (dungeon.isLegalMove(monster, site)) {
                    n++;
                    if (Math.random() <= 1.0 / n) move = site;
                }
            }
        }
        return move;
    }

}
