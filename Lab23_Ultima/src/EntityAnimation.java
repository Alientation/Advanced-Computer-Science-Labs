public class EntityAnimation implements Animation {
	protected String[] images;
	protected int currentImage, speed, ticks;
	protected AnimationUpdater updater;
	
	public EntityAnimation(String[] images, int speed, AnimationUpdater updater) {
		this.images = images;
		this.currentImage = 0;
		this.speed = speed;
		this.ticks = 0;
		this.updater = updater;
	}
	
	public EntityAnimation(String[] images, int speed) {
		this(images,speed,AnimationUpdater.NORMAL);
	}
	
	public void setImages(String[] images) {
		this.images = images;
	}

	public int currentImage() {
		return currentImage;
	}

	public void setCurrentImage(int index) {
		this.currentImage = index % images.length;
	}
	
	public void setUpdater(AnimationUpdater updater) {
		this.updater = updater;
	}
	
	@Override
	public void tick(Monster monster) {
		ticks++;
		if (ticks < speed)
			return;
		ticks = 0;
		updater.update(monster, this);
		monster.draw();
	}

	@Override
	public void tick(Avatar avatar) {
		ticks++;
		if (ticks < speed)
			return;
		ticks = 0;
		updater.update(avatar, this);
		avatar.draw();
	}
}

interface Animation {
	public abstract void tick(Monster monster);
	public abstract void tick(Avatar avatar);
}

class AnimationUpdater {
	public static final AnimationUpdater NORMAL = new AnimationUpdater() {};
	
	public void update(Monster monster, EntityAnimation animation) {
		animation.setCurrentImage(animation.currentImage()+1);
	}
	
	public void update(Avatar avatar, EntityAnimation animation) {
		animation.setCurrentImage(animation.currentImage()+1);
	}
}