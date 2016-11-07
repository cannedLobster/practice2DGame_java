
package com.sunx.Game.gfx;

public class AnimatedSprite extends Sprite {

	private int frame = 0;
	private Sprite sprite;
	private int rate = 5;
	private int time = 0;
	private int animationLength = -1;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int animationLength) {
		super(sheet, width, height);

		this.animationLength = animationLength;
		sprite = sheet.getSprites()[0];
		if (animationLength > sheet.getSprites().length) System.err.println("Error! Length of animation is too long.");
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= animationLength - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
			time = 0;
		}
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Inxes out of bounds in" + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

}
