package sunx.SaD.Game.entity.mob;

import sunx.SaD.Game.entity.Entity;
import sunx.SaD.Game.gfx.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected boolean walking = false;

	public void move(int xNextDir, int yNextDir) {
		if (xNextDir != 0 && yNextDir != 0) {
			move(xNextDir, 0);
			move(0, yNextDir);
			return;
		}
		
		if (xNextDir > 0) dir = 1;// E
		if (xNextDir < 0) dir = 3;// W
		if (yNextDir > 0) dir = 2;// S
		if (yNextDir < 0) dir = 0;// N
		
		if (!collision(xNextDir * 10, yNextDir * 10)) {
			xMap += xNextDir * 10;
			yMap += yNextDir * 10;
		}
	}

	public void update() {
		
	}

	protected void shoot(int x, int y, double dir) {
		
	}

	private boolean collision(int xNext, int yNext) {
		boolean solid = false;
		
		for (int corner = 0; corner < 4; corner++) {
			int xTile = ((xMap + xNext) + corner % 2 * 14 - 8) / 16;
			int yTile = ((yMap + yNext) + corner / 2 * 14 + 0) / 16;
			if (level.getTile(xTile, yTile).solid()) solid = true;
		}
		
		return solid;
	}

	public void render() {

	}

}
