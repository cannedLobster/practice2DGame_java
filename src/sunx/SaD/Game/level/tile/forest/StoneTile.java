package sunx.SaD.Game.level.tile.forest;

import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.gfx.Sprite;
import sunx.SaD.Game.level.tile.Tile;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	public boolean solid() {
		return true;
	}

	public boolean breakable() {
		return true;
	}

}
