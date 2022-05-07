/**
 * Class representing a tile in the Ultima game
 * 
 * @author Keith Vertanen
 * @author Michele Van Dyne - added commenting
 * @author Kris McCoy - altered TileType and removed opacity options
 *
 */

import java.util.*;
public class Tile extends Location{
	public static HashMap<String,TileType> stringToTileType = new HashMap<>();
	public static final int SIZE = 32;           // Size of a tile in pixels
	public static final int TILE_DAMAGE = 2;     // If a TileType causes damage, how much damage it should cause

	protected TileType type;                       // Type of tile this is
	protected boolean lit = false;                 // Is the tile currently illuminated?     
	protected boolean occupied = false;
	/**
	 * Constructor for the tile class
	 * converts a character from the file into our enumerated type
	 * @param code - letter code that determines the type of tile
	 */
	public Tile(String code) {
		this(code, 0, 0);        
	}

	/**
	 * Constructor for the tile class
	 * converts a character from the file into our enumerated type
	 * @param code - letter code that determines the type of tile
	 * @param x - horizontal location of this Tile within the matrix
	 * @param y - vertical location of this Tile within the matrix
	 */
	public Tile(String code, int x, int y) {
		super(x,y);
		type = TileType.getTileType(code);
	}

	/**
	 * Get whether this tile is lit or not
	 * @return true if lit, false otherwise
	 */
	public boolean getLit() {
		return lit;
	}

	/**
	 * Set whether the tile is lit or not
	 */
	public void setLit(boolean value) {
		lit = value;
	}

	/**
	 * Get the amount of damage caused by this tile
	 * @return the damage caused
	 */
	public int getDamage() {
		if (type == TileType.LAVA)
			return TILE_DAMAGE;
		return 0;
	}

	public void setOccupied(boolean value) {
		occupied = value;
	}

	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Can the hero walk through this tile
	 */
	public boolean isPassable() {      
		switch (type) {
		case PATH:
		case GRASS:
		case LAVA:
			return true;   
		default:    
			return false;  
		}       
	}
	
	public void tick() {
		
	}

	/**
	 * Draw the tile at the given location
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 */
	public void draw(int x, int y) {
		double drawX = (x + 0.5 - World.offSetX) * Tile.SIZE;
		double drawY = (y + 0.5 - World.offSetY) * Tile.SIZE;

		if (lit) {
			StdDraw.picture(drawX, drawY, type.filepath(), Tile.SIZE, Tile.SIZE);           
		} else {
			StdDraw.picture(drawX, drawY, "img-blank.gif", Tile.SIZE, Tile.SIZE);     
		}
	}
	
	public void draw() {
		draw(x + World.offSetX,y + World.offSetX);
	}

	/**
	 * Test main method to ensure tile methods are correct
	 */
	public static void main(String[] args){      
		final int WIDTH = 10;  
		final int HEIGHT = 2;

		StdDraw.setCanvasSize(WIDTH * SIZE, HEIGHT * SIZE);
		StdDraw.setXscale(0.0, WIDTH * SIZE);
		StdDraw.setYscale(0.0, HEIGHT * SIZE);

		String [] codes = {"P", "B", "L", "W", "F", "G", "T", "S", "C", "R"};
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				Tile tile = new Tile(codes[i]);
				if ((i + j) % 2 == 0)
					tile.setLit(true);
				System.out.printf("%d %d : lit %s  \tpassable %s\n", i, j, tile.getLit(), tile.isPassable()); 
				tile.draw(i, j);
			}
		}       
	}

	/**
	 * 
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Tile))
			return false;
		Tile to = (Tile) o;
		return to.x == x && to.y == y;
	}

	public enum TileType {
		PATH("P", "img-path.png"), LAVA("L", "img-lava.png"), WATER("W", "img-water.png"),
		BUSH("B", "img-bush.png"), TOMBSTONE("T", "img-tombstone.png"), GRASS("G", "img-grass.png"),
		ROCK("R", "img-rock.png"), STONEWALL("S", "img-stonewall-top.png"),
		STONEWALLFRONT("F", "img-stonewall-front.png"), CRATE("C", "img-crate.png"),
		BLANK(" ", "img-blank.gif");

		private final String filepath;
		private final String code;
		TileType(final String code, final String filepath) {
			this.code = code;
			this.filepath = filepath;
			Tile.stringToTileType.put(code,this);
		}

		public String filepath() {
			return filepath;
		}

		public String code() {
			return code;
		}

		public static TileType getTileType(String code) {
			return Tile.stringToTileType.get(code) != null ? Tile.stringToTileType.get(code) : GRASS;
		}
	}
}