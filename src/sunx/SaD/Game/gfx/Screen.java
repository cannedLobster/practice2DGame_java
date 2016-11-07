package sunx.SaD.Game.gfx;

import sunx.SaD.Game.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] screenPixels;

	public int xOffset_Screen, yOffset_Screen;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		screenPixels = new int[width * height];
	}

	// Render Methods

	public void renderTile(int xMap, int yMap, Tile tile) {
		xMap -= xOffset_Screen;
		yMap -= yOffset_Screen;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int yScreen = y + yMap;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xScreen = x + xMap;

				if (xScreen < -tile.sprite.SIZE || xScreen >= width || yScreen < 0 || yScreen >= height) break;
				if (xScreen < 0) xScreen = 0;

				screenPixels[xScreen + yScreen * width] = tile.sprite.spritePixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	// Flip: 1&3 xAxis || 2&3 yAxis
	public void renderPlayer(int xMap, int yMap, Sprite sprite, int flip) {
		xMap -= xOffset_Screen;
		yMap -= yOffset_Screen;
		for (int y = 0; y < sprite.getWidth(); y++) {
			int yScreen = y + yMap;
			int yAxis = y;

			if (flip == 2 || flip == 3) yAxis = sprite.getWidth() - 1 - y;

			for (int x = 0; x < sprite.getHeight(); x++) {
				int xScreen = x + xMap;
				int xAxis = x;

				if (flip == 1 || flip == 3) xAxis = sprite.getHeight() - 1 - x;

				if (xScreen < -sprite.getWidth() || xScreen >= width || yScreen < 0 || yScreen >= height) break;
				if (xScreen < 0) xScreen = 0;

				int col = sprite.spritePixels[xAxis + yAxis * sprite.getWidth()];
				if (col != 0xFFFF00FF) screenPixels[xScreen + yScreen * width] = col;
			}
		}
	}

	// Debugging Render Methods

	public void renderSprite(int xMap, int yMap, Sprite sprite, boolean fixed) {
		if (fixed) {
			yMap -= yOffset_Screen;
			xMap -= xOffset_Screen;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int yScreen = y + yMap;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xScreen = x + xMap;
				if (xScreen < 0 || xScreen >= width || yScreen < 0 || yScreen >= height) continue;
				screenPixels[xScreen + yScreen * width] = sprite.spritePixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderSheet(int xMap, int yMap, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xMap -= xOffset_Screen;
			yMap -= yOffset_Screen;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int yScreen = yMap + y;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xScreen = xMap + x;
				if (xScreen < 0 || xScreen >= width || yScreen < 0 || yScreen >= height) continue;
				screenPixels[xScreen + yScreen * width] = sheet.sheetPixels[x + y * sheet.WIDTH];
			}
		}
	}

	// Misc Screen Methods

	public void clear() {
		for (int i = 0; i < screenPixels.length; i++) {
			screenPixels[i] = 0;
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset_Screen = xOffset;
		this.yOffset_Screen = yOffset;
	}
}
