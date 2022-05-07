public class Location {
	protected int x, y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int setX(int x) {
		int prevX = this.x;
		this.x = x;
		return prevX;
	}

	public int setY(int y) {
		int prevY = this.y;
		this.y = y;
		return prevY;
	}

	public Location setLocation(int x, int y) {
		Location prevLoc = new Location(this.x,this.y);
		this.x = x;
		this.y = y;
		return prevLoc;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Location))
			return false;
		Location to = (Location) o;
		return to.x == x && to.y == y;
	}
}