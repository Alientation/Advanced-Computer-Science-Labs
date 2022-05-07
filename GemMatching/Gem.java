import java.awt.Font;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{	
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
   public static int[] pointValues = {0,5,10,15,20,25,30,35,40,45,50};
   private int points;
   private GemType type;
   
   public Gem() {
      points = pointValues[(int)(Math.random()*11)];
      switch((int)(Math.random() * 3)) {
         case 0:
            type = GemType.GREEN;
         break;
         case 1:
            type = GemType.BLUE;
         break;
         case 2:
            type = GemType.ORANGE;
         break;
      }
   }
   
   public Gem(GemType type, int points) {
      this.type = type;
      this.points = points;
   }
   
   public Gem(int points) {
      switch((int)(Math.random() * 3)) {
         case 0:
            type = GemType.GREEN;
         break;
         case 1:
            type = GemType.BLUE;
         break;
         case 2:
            type = GemType.ORANGE;
         break;
      }
      this.points = points;
   }
   
   @Override
   public String toString() {
      return type + " " + points;
   }
   
   public GemType getType() {
      return type;
   }
   
   public int getPoints() {
      return points;
   }
   
   public void draw(double x, double y) {
      StdDraw.picture(x,y,"gem_" + type + ".png");
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.setFont(new Font("SansSerif", Font.BOLD,14));
      StdDraw.text(x,y,"" + points);
   }
}
