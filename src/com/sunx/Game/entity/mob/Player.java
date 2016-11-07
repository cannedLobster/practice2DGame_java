package com.sunx.Game.entity.mob;

import com.sunx.Game.Game;
import com.sunx.Game.entity.projectile.FireProjectile;
import com.sunx.Game.entity.projectile.Projectile;
import com.sunx.Game.gfx.AnimatedSprite;
import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;
import com.sunx.Game.gfx.SpriteSheet;
import com.sunx.Game.input.Keyboard;
import com.sunx.Game.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	// private int anim = 0;
	private boolean walking = false;

	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSprite = null;

	private int fireRate = 0;

	public Player(int x, int y, Keyboard input) {
		// Player spawned at specific location
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = FireProjectile.FIRE_RATE;

		animSprite = down;
	}

	// not used
	/*
	 * public Player(Keyboard input) { this.input = input; sprite =
	 * Sprite.player_forward;
	 * 
	 * animSprite = down; }
	 */

	public void update() {

		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);

		if (fireRate > 0) fireRate--;

		int xa = 0, ya = 0;

		/*
		 * if (anim < 7500) anim++; else anim = 0;
		 */

		if (input.up) {
			ya--;
			animSprite = up;
		} else if (input.down) {
			ya++;
			animSprite = down;
		}
		if (input.right) {
			xa++;
			animSprite = right;
		} else if (input.left) {
			xa--;
			animSprite = left;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
		// change dir

		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = FireProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		// int flip = 0;
		/*
		 * if (dir == 0) { sprite = Sprite.player_forward; if (walking) { if
		 * (anim % 20 > 10) { sprite = Sprite.player_foward_1; } else { sprite =
		 * Sprite.player_foward_2; } } } if (dir == 1) { sprite =
		 * Sprite.player_side; if (walking) {
		 * if (anim % 30 < 10) { sprite = Sprite.player_side_1; } else if (anim
		 * % 30 > 10 && anim % 30 < 20) { sprite = Sprite.player_side; } else {
		 * sprite = Sprite.player_side_2; } } } if (dir == 2) { sprite =
		 * Sprite.player_back; if (walking) { if (anim % 20 > 10) { sprite =
		 * Sprite.player_back_1; } else { sprite = Sprite.player_back_2; } } }
		 * if (dir == 3) { sprite = Sprite.player_side; flip = 1; if (walking) {
		 * if (anim % 30 < 10) { sprite = Sprite.player_side_1; } else if (anim
		 * % 30 > 10 && anim % 30 < 20) { sprite = Sprite.player_side; } else {
		 * sprite = Sprite.player_side_2; } } }
		 */

		sprite = animSprite.getSprite();
		screen.renderPlayer(x - 16, y - 16, sprite, 0);
	}

}