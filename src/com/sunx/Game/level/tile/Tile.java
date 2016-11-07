package com.sunx.Game.level.tile;

import com.sunx.Game.gfx.Screen;
import com.sunx.Game.gfx.Sprite;
import com.sunx.Game.level.tile.spawn_level.SpawnBushTile;
import com.sunx.Game.level.tile.spawn_level.SpawnFloorTile;
import com.sunx.Game.level.tile.spawn_level.SpawnGrassTile;
import com.sunx.Game.level.tile.spawn_level.SpawnSandTile;
import com.sunx.Game.level.tile.spawn_level.SpawnWallTile;
import com.sunx.Game.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass); //GrassTile extends Tile so this is compatible
	public static Tile stone = new StoneTile(Sprite.stone);
	public static Tile bush = new BushTile(Sprite.bush);
	public static Tile flower = new FlowerTile(Sprite.flower);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawnGrass);
	public static Tile spawn_bush = new SpawnBushTile(Sprite.spawnBush);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawnWater);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawnWall1);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawnFloor);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawnWall2);
	public static Tile spawn_sand = new SpawnSandTile(Sprite.spawnSand);
	
	public static final int COL_SPAWNGRASS = 0xffabff93;
	public static final int COL_SPAWNBUSH = 0xff3abc54;
	public static final int COL_SPAWNWATER = 0xff40babc;
	public static final int COL_SPAWNWAL1 = 0xffbc4061;
	public static final int COL_SPAWNFLOOR = 0xffbabcbb;
	public static final int COL_SPAWNWALL2 = 0xffbc2b48;
	public static final int COL_SPAWNSAND = 0xffbcbc27;
	
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	//screen class is what puts stuff on the screen
	//tile renders itself**
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}

}
