package com.sunx.Game.entity.projectile;

import com.sunx.Game.entity.spawner.ParticleSpawner;
import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;

public class FireProjectile extends Projectile {

	public static final int FIRE_RATE = 5;

	public FireProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(100) + 100;
		damage = 20;
		speed = 5;

		sprite = Sprite.projectile_king;

		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}

	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 100, 30, level));
			remove();
		}
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (distance() >= range) remove();
	}

	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 7, (int) y - 3, this);
	}

}
