package sunx.SaD.Game.entity.projectile;

import java.util.Random;

import sunx.SaD.Game.gfx.Sprite;

public class Projectile {

	protected final int xOrigin, yOrigin;
	protected Sprite sprite;
	protected double angle;
	
	protected double nextX, nextY;
	protected double lastX, lastY;
	
	protected double speed, range, damage;
	
	protected final Random random = new Random();
	
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.lastX = x;
		this.lastY = y;
	}
}
