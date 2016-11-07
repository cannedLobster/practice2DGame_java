package com.sunx.Game.entity.spawner;

import com.sunx.Game.entity.Entity;
import com.sunx.Game.entity.particle.Particle;
import com.sunx.Game.level.Level;

public class Spawner extends Entity {

	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
