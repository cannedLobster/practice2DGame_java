package com.sunx.Game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path; // path to sprite sheet
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/sprite_sheet.png", 256);
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_sheet.png", 128);
	public static SpriteSheet projectile_king = new SpriteSheet("/textures/sheets/projectiles/king.png", 48);

	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 128, 96);

	public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet player_down = new SpriteSheet(player, 2, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 3, 0, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 1, 0, 1, 3, 32);
	
	private Sprite[] sprites;

	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	// Tile Precision parameters
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;

		WIDTH = w;
		HEIGHT = h;
		if (w == h) SIZE = w;
		else
			SIZE = -1;

		pixels = new int[w * h];

		for (int yi = 0; yi < h; yi++) {
			int yPos = yy + yi;
			for (int xi = 0; xi < w; xi++) {
				int xPos = xx + xi;
				pixels[xi + yi * w] = sheet.pixels[xPos + yPos * sheet.WIDTH];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];

		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int yi = 0; yi < spriteSize; yi++) {
					for (int xi = 0; xi < spriteSize; xi++) {
						spritePixels[xi + yi * spriteSize] = pixels[(xi + xa * spriteSize) + (yi + ya * spriteSize) * w];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}

	}

	public Sprite[] getSprites() {
		return sprites;
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w); // get image in pixels array
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
