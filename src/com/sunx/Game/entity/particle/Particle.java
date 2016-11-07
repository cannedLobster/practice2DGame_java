package com.sunx.Game.entity.particle;

import com.sunx.Game.entity.Entity;
import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;

public class Particle extends Entity {

	private Sprite sprite;

	private int life;
	private int time = 0;

	protected double xx, yy, zz;
	protected double xa, ya, za;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 4.0; // start of arc
	}

	public void update() {
		time++;
		if (time >= Integer.MAX_VALUE - 1) time = 0;
		if (time > life) remove();
		za -= 0.1; // changes rate of which it goes down

		if (zz <= 0) {
			zz = 0; // set "ground" level
			// switch direction to "up" and lesson velocity
			za *= -0.5;
			// slows particles after every bounce
			xa *= .8;
			ya *= .8;
		}

		move(xx + xa, (yy + ya) + (zz + za));

	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -.5;
			this.ya *= -.5;
			this.za *= -.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		// Offsets are the distance from top left corner to actual picture
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xTile = (x - c % 2 * 16) / 16;
			double yTile = (y - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xTile);
			int iy = (int) Math.ceil(yTile);
			if (c % 2 == 0) ix = (int) Math.floor(xTile);
			if (c / 2 == 0) iy = (int) Math.floor(yTile);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz, sprite, true);
	}

}
