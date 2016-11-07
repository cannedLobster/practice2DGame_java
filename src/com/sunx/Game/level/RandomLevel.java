package com.sunx.Game.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random RANDOM = new Random();

	public RandomLevel(int width, int height) {
		super(width, height);
	}

	// protected -> override the Level method
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = RANDOM.nextInt(4); //0-3
			}
		}

	}

}
