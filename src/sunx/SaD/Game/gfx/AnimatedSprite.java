package sunx.SaD.Game.gfx;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite currentSprite;
	private int rate = 5;
	private int time = 0;
	private int animationLength = -1;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int animationLength) {
		super(sheet, width, height);

		this.animationLength = animationLength;
		currentSprite = sheet.getSprites()[0];
		if (animationLength > sheet.getSprites().length) System.err.println("Out of Bounds Animation length");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= animationLength - 1) frame = 1;
			else
				frame++;
			currentSprite = sheet.getSprites()[frame];
			time = 0;
		}
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1 || index < 0) {
			System.err.println("Index out of bounds in" + this);
			return;
		}
		currentSprite = sheet.getSprites()[index];
	}

	public Sprite getSprite() {
		return currentSprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}
}
