package sunx.SaD.Game.level.tile;

import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.gfx.Sprite;

public class VoidTile extends Tile {

	public VoidTile(Sprite sprite) {
		super(sprite);
	}
	
	//Render With Pixel Precision
	public void render(int xTilePrecision, int yTilePrecision, Screen screen) {
		screen.renderTile(xTilePrecision << 4,  yTilePrecision << 4, this);
	}

}
