package sunx.SaD.Game.entity.spawner;

import sunx.SaD.Game.entity.Entity;
import sunx.SaD.Game.level.Level;

public class Spawner extends Entity{

	public enum Type{
		MOB, PARTICLE
	}
	
	// private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level){
		init(level);
		this.xMap = x;
		this.yMap = y;
		// this.type = type;
	}
	
}
