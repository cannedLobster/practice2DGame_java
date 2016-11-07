package com.sunx.Game.entity.mob;

import com.sunx.Game.entity.Entity;
import com.sunx.Game.entity.projectile.FireProjectile;
import com.sunx.Game.entity.projectile.Projectile;
import com.sunx.Game.gfx.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	// Only mobs can access the sprite variable
	protected int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		// xa -> xaxis movement; 3 states +1/0/-1
		if (xa > 0) dir = 1;// E
		if (xa < 0) dir = 3;// W
		if (ya > 0) dir = 2;// S
		if (ya < 0) dir = 0;// N

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void update() {

	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new FireProjectile(x, y, dir);
		level.add(p);
	}

	private boolean collision(int xa, int ya) {
		boolean solid = false;
		// c for corners
		for (int c = 0; c < 4; c++) {
			int xTile = ((x + xa) + c % 2 * 14 - 8) / 16;
			// x % 2 * A - B; A and B is adjustable to collision *distance
			// c%2 between 1 and 0; all 4 combos considered
			// * A considers the c=1 distance
			// - B considers the c=0 distance
			int yTile = ((y + ya) + c / 2 * 14 + 0) / 16;
			if (level.getTile(xTile, yTile).solid()) solid = true;
		}

		return solid;
	}

	public void render() {

	}

}
