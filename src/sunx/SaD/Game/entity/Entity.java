package sunx.SaD.Game.entity;

import java.util.Random;

import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.level.Level;

public abstract class Entity {
	
	public int xMap, yMap;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
