package com.sunx.Game.level;

import java.util.ArrayList;
import java.util.List;

import com.sunx.Game.entity.Entity;
import com.sunx.Game.entity.particle.Particle;
import com.sunx.Game.entity.projectile.Projectile;
import com.sunx.Game.gfx.Screen;
import com.sunx.Game.level.tile.Tile;

public class Level {

	// organizes tiles
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles; // contain all level tiles

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();

	public static Level spawn = new SpawnLevel("/levels/spawn_level.png");

	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[this.width * this.height];
		generateLevel();
	}

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
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		remove();
	}

	private void remove() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	private void time() {

	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		//Offsets are the distance from top left corner to actual picture
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xTile = (x - c % 2 * size + xOffset) >> 4;
			int yTile = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xTile, yTile).solid()) solid = true;
		}
		return solid;
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		// scroll variables are where the player is at
		// left side of the SCREEN where we start rendering x
		int x0 = xScroll >> 4; // dealing into tiles instead of pixels
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		// define render region; corner pins
		// these are the tiles that we are going to render in

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else {
			entities.add(e);
		}
	}

	public Tile getTile(int x, int y) {
		// position of the tile we are trying to retrieve
		// return tile we are going to render
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == Tile.COL_SPAWNGRASS) return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.COL_SPAWNBUSH) return Tile.spawn_bush;
		if (tiles[x + y * width] == Tile.COL_SPAWNFLOOR) return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.COL_SPAWNWAL1) return Tile.spawn_wall1;
		if (tiles[x + y * width] == Tile.COL_SPAWNWALL2) return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.COL_SPAWNWATER) return Tile.spawn_water;
		if (tiles[x + y * width] == Tile.COL_SPAWNSAND) return Tile.spawn_sand;
		return Tile.voidTile;
	}

}
