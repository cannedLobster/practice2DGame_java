package sunx.SaD.Game.level.tile;

import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.gfx.Sprite;
import sunx.SaD.Game.level.tile.forest.BlackTile;
import sunx.SaD.Game.level.tile.forest.BushTile;
import sunx.SaD.Game.level.tile.forest.GrassTile;
import sunx.SaD.Game.level.tile.forest.StoneTile;

public abstract class Tile {

	public int x, y;
	public Sprite sprite;
	
	//Tiles
	public static Tile grass1_Tile = new GrassTile(Sprite.grass1_Sprite);
	public static Tile grass2_Tile = new GrassTile(Sprite.grass2_Sprite);
	public static Tile grass3_Tile = new GrassTile(Sprite.grass3_Sprite);
	public static Tile stoneTile = new StoneTile(Sprite.stoneSprite);
	public static Tile blackTile = new BlackTile(Sprite.blackSprite);
	public static Tile bushTile = new BushTile(Sprite.bushSprite);
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	//Respective Colors
	public static final int COL_BLACK = 0xFF000000;
	public static final int COL_GRASS1 = 0xFF00FF00;
	public static final int COL_GRASS2 = 0xFF00FE00;
	public static final int COL_GRASS3 = 0xFF00FC00;
	public static final int COL_STONE = 0xFF808080;
	public static final int COL_BUSH = 0xFF005500;
	
	
	public Tile(Sprite sprite) {
		this.sprite= sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		
	}
	
	public boolean solid() {
		return false;
	}
}
