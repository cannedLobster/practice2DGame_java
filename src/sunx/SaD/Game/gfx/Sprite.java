package sunx.SaD.Game.gfx;

public class Sprite {

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] spritePixels;
	protected SpriteSheet sheet;

	public static Sprite voidSprite = new Sprite(16, 0xff00ff);
	public static Sprite grass1_Sprite = new Sprite(16, 1, 0, SpriteSheet.forest);
	public static Sprite grass2_Sprite = new Sprite(16, 2, 0, SpriteSheet.forest);
	public static Sprite grass3_Sprite = new Sprite(16, 3, 0, SpriteSheet.forest);
	public static Sprite bushSprite = new Sprite(16, 0, 0, SpriteSheet.forest);
	public static Sprite blackSprite = new Sprite(16, 2, 1, SpriteSheet.forest);
	public static Sprite stoneSprite = new Sprite(16, 4, 0, SpriteSheet.forest);

	// 01 Square Sprite Constructor
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		width = size;
		height = size;
		spritePixels = new int[width * height];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	// 02 Default Sprite
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		SIZE = -1;
		spritePixels = new int[width * height];
		setColor(color);
	}

	// 03 Default Square Sprite
	public Sprite(int size, int color) {
		SIZE = size;
		this.width = size;
		this.height = size;
		spritePixels = new int[SIZE * SIZE];
		setColor(color);
	}

	// 04 Animated and Sheet Sprite Constructor
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.spritePixels = pixels;
	}
	
	// 05 Animated Sprite Constructor
	public Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	// Getter Methods
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// Default 02 03 Sprite Helper
	private void setColor(int color) {
		for (int i = 0; i < spritePixels.length; i++) {
			spritePixels[i] = color;
		}
	}

	// Loader for 01 Constructor
	private void load() {
		for (int y = 0; y < width; y++) {
			for (int x = 0; x < height; x++) {
				spritePixels[x + y * width] = sheet.sheetPixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
