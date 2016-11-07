package sunx.SaD.Game.level;

import java.util.ArrayList;
import java.util.List;

import sunx.SaD.Game.entity.Entity;
import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.level.tile.Tile;

public class Level {

	protected int width, height;
	protected int[] tiles;

	// Lists
	private List<Entity> entities = new ArrayList<Entity>();
	
	//Levels
	public static Level firstLevel = new FirstLevel("/Levels/Level_1.png");

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {

	}

	protected void loadLevel(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
	}

	// private void time()

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;

		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);

		int xStart_Screen = xScroll >> 4;
		int xEnd_Screen = (xScroll + screen.width + 16) >> 4;
		int yStart_Screen = yScroll >> 4;
		int yEnd_Screen = (yScroll + screen.height + 16) >> 4;

		for (int y = yStart_Screen; y < yEnd_Screen; y++) {
			for (int x = xStart_Screen; x < xEnd_Screen; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}

	public void add(Entity e) {
		e.init(this);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if (tiles[x + y * width] == Tile.COL_BLACK)	return Tile.blackTile;
		if (tiles[x + y * width] == Tile.COL_GRASS1) return Tile.grass1_Tile;
		if (tiles[x + y * width] == Tile.COL_GRASS2) return Tile.grass2_Tile;
		if (tiles[x + y * width] == Tile.COL_GRASS3) return Tile.grass3_Tile;
		if (tiles[x + y * width] == Tile.COL_STONE) return Tile.stoneTile;
		if (tiles[x + y * width] == Tile.COL_BUSH) return Tile.bushTile;

		//Missing Tiles Print Out
		String hex = Integer.toHexString(tiles[x + y * width]);
		System.out.println("Tiles: " + hex);
		
		return Tile.voidTile;
	}

}
