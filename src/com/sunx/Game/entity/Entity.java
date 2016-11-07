package com.sunx.Game.entity;

import java.util.Random;

import com.sunx.Game.gfx.Screen;
import com.sunx.Game.level.Level;

public abstract class Entity {
	// anything that is in the level ie time and setpieces

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {

	}

	public void render(Screen screen) {

	}

	public void remove() {
		// Remove from level
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}

}
