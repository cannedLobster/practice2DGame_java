package sunx.SaD.Game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] sheetPixels;

	private Sprite[] sprites;

	// Spritesheets

	public static SpriteSheet forest = new SpriteSheet("/textures/sheets/sprite_sheet01.png", 256);
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 576, 512);

	// Animation SpriteSheets
	public static SpriteSheet player_up = new SpriteSheet(player, 0, 0, 9, 1, 64);
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 2, 9, 1, 64);
	public static SpriteSheet player_right = new SpriteSheet(player, 0, 3, 9, 1, 64);
	public static SpriteSheet player_left = new SpriteSheet(player, 0, 1, 9, 1, 64);
	/*public static SpriteSheet player_upRight = new SpriteSheet(player, 4, 0, 1, 4, 16);
	public static SpriteSheet player_upLeft = new SpriteSheet(player, 5, 0, 1, 4, 16);
	public static SpriteSheet player_downRight = new SpriteSheet(player, 6, 0, 1, 4, 16);
	public static SpriteSheet player_downLeft = new SpriteSheet(player, 7, 0, 1, 4, 16);*/
	
	// 01 Default Square Constructor
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		sheetPixels = new int[SIZE * SIZE];
		load();
	}

	// 02 Default Rectangle Constructor
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		WIDTH = width;
		HEIGHT = height;
		SIZE = -1;
		sheetPixels = new int[WIDTH * HEIGHT];
		load();
	}

	// Animation Constructor
	public SpriteSheet(SpriteSheet sheet, int xLoc, int yLoc, int width, int height, int spriteSize) {
		int xPixel = xLoc * spriteSize;
		int yPixel = yLoc * spriteSize;
		int wPixel = width * spriteSize;
		int hPixel = height * spriteSize;

		WIDTH = wPixel;
		HEIGHT = hPixel;

		if (WIDTH == HEIGHT) SIZE = WIDTH;
		else
			SIZE = -1;

		sheetPixels = new int[wPixel * hPixel];

		// Load Animated Sheet Pixels
		for (int y = 0; y < hPixel; y++) {
			int yPos = yPixel + y;
			for (int x = 0; x < wPixel; x++) {
				int xPos = xPixel + x;
				sheetPixels[x + y * wPixel] = sheet.sheetPixels[xPos + yPos * sheet.WIDTH];
			}
		}

		int frame = 0;
		sprites = new Sprite[width * height];

		// Load Animated Sprite Array
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y = 0; y < spriteSize; y++) {
					for (int x = 0; x < spriteSize; x++) {
						spritePixels[x + y * spriteSize] = sheetPixels[(spriteSize * w + x) + (spriteSize * h + y) * wPixel];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}

	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, sheetPixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite[] getSprites() {
		return sprites;
	}
}
