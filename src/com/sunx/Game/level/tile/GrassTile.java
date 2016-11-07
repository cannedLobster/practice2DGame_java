package com.sunx.Game.level.tile;

import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		// Do render stuff here!
		screen.renderTile(x << 4, y << 4, this);
		// re-convert to pixel precision
	}

}
