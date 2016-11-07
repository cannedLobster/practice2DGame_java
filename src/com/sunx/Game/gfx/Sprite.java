package com.sunx.Game.gfx;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;

	// important
	// Tile Sheet
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite bush = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 3, 0, SpriteSheet.tiles);

	// VOID
	public static Sprite voidSprite = new Sprite(16, 0x77B9FF);

	// Spawn Level Assets
	public static Sprite spawnGrass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawnBush = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawnWater = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawnWall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawnFloor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawnWall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	public static Sprite spawnSand = new Sprite(16, 3, 0, SpriteSheet.spawn_level);

	// Player Sprite
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_foward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_foward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	// counting by 32x32

	// Projectile Sprites
	public static Sprite projectile_king = new Sprite(16, 0, 0, SpriteSheet.projectile_king);

	// Particles
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE; // x coordinate of target sprite in sheet
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		SIZE = -1;
		pixels = new int[width * height];
		setColor(color);
	}

	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	// Animated Sprite
	protected Sprite(SpriteSheet sheet, int width, int height) {
		if (width == height) SIZE = width;
		else
			SIZE = -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	// Anim|Sheet Class constructor
	public Sprite (int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	private void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
				// extracting single sprite out of sprite sheet
				// adding this.x to x to the target sprite
				// sheet.SIZE for rows for height access
			}
		}
	}

}
