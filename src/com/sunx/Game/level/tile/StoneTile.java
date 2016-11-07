package com.sunx.Game.level.tile;

import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;

public class StoneTile extends Tile {

	public StoneTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}

	public void render(int x, int y, Screen screen) {
		// Do render stuff here!
		screen.renderTile(x << 4, y << 4, this);
		// re-convert to pixel precision
	}

	public boolean solid() {
		return true;
	}

}
