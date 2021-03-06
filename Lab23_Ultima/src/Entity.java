public class Entity {
	public enum Direction {UP, DOWN, LEFT, RIGHT}

	protected int hp, damage;
	protected Location location;
	protected String name, renderImage;
	protected Stats timer;
	protected Direction direction;



	public Entity(int x, int y, int hp, int damage, String name, String renderImage, Stats timer, Direction direction) {
		this.location = new Location(x,y);
		this.hp = hp;
		this.damage = damage;
		this.name = name;
		this.renderImage = renderImage;
		this.timer = timer;
		this.direction = direction;
	}

	public int getX() {
		return location.getX();
	}

	public int setX(int x) {
		return this.location.setX(x);
	}

	public int getY() {
		return location.getY();
	}

	public int setY(int y) {
		return this.location.setY(y);
	}

	public Location getLocation() {
		return location;
	}

	public Location setLocation(Location location) {
		return location.setLocation(location.getX(),location.getY());
	}

	public int getHP() {
		return hp;
	}

	public int setHP(int hp) {
		int prevHP = this.hp;
		this.hp = hp;
		return prevHP;
	}

	public int incrementHP(int increment) {
		return setHP(hp + increment);
	}

	public int getDamage() {
		return damage;
	}

	public int setDamage(int damage) {
		int prevDamage = this.damage;
		this.damage = damage;
		return prevDamage;
	}


	public String getName() {
		return name;
	}

	public String setName(String name) {
		String prevName = this.name;
		this.name = name;
		return prevName;
	}

	public String getRenderImage() {
		return renderImage;
	}

	public String setRenderImage(String renderImage) {
		String prevRenderImage = this.renderImage;
		this.renderImage = renderImage;
		return prevRenderImage;
	}

	public Direction getDirection() {
		return direction;
	}

	public Direction setDirection(Direction direction) {
		Direction prevDirection = this.direction;
		this.direction = direction;
		return prevDirection;
	}
}