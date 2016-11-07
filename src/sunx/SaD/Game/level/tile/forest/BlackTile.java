package sunx.SaD.Game.level.tile.forest;

import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.gfx.Sprite;
import sunx.SaD.Game.level.tile.Tile;

public class BlackTile extends Tile {

	public BlackTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public void render(int xMap, int yMap, Screen screen) {
		screen.renderTile(xMap << 4, yMap << 4, this);
	}

}
