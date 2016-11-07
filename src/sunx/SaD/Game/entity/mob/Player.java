package sunx.SaD.Game.entity.mob;

import sunx.SaD.Game.gfx.AnimatedSprite;
import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.gfx.Sprite;
import sunx.SaD.Game.gfx.SpriteSheet;
import sunx.SaD.Game.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;

	private boolean walking = false;

	// Animated Sprites
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 64, 64, 9);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16, 16, 9);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 64, 64, 9);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 64, 64, 9);
	/*private AnimatedSprite upRight = new AnimatedSprite(SpriteSheet.player_upRight, 16, 16, 4);
	private AnimatedSprite upLeft = new AnimatedSprite(SpriteSheet.player_upLeft, 16, 16, 4);
	private AnimatedSprite downRight = new AnimatedSprite(SpriteSheet.player_downRight, 16, 16, 4);
	private AnimatedSprite downLeft = new AnimatedSprite(SpriteSheet.player_downLeft, 16, 16, 4);*/

	private AnimatedSprite animSprite = null;

	public Player(int x, int y, Keyboard input) {
		xMap = x;
		yMap = y;
		this.input = input;
		animSprite = down;
	}

	public void update() {

		if (walking) animSprite.update();
		else
			animSprite.setFrame(0);

		int xNext = 0, yNext = 0;

		if (input.up) {
			yNext--;
			animSprite = up;
		}

		else if (input.down) {
			yNext++;
			animSprite = down;
		}
		
		else if (input.right) {
			xNext++;
			animSprite = right;
		}
		
		else if (input.left) {
			xNext--;
			animSprite = left;
		}
		
		if (xNext != 0 || yNext != 0) {
			move(xNext, yNext);
			walking = true;
		} else
			walking = false;
		
		clear();
		updateShooting();
	}

	private void clear() {
		//CLear Projectiles
	}

	private void updateShooting() {
		// Shooting mech
	}

	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderPlayer(xMap - 32, yMap - 32, sprite, 0);
	}

}
