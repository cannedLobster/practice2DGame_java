package com.sunx.Game.level.tile.spawn_level;

import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;
import com.sunx.Game.level.tile.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) {
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
