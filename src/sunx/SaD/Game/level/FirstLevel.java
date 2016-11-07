package sunx.SaD.Game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FirstLevel extends Level {

	public FirstLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(FirstLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cannot load file!");
		}
	}
	
	protected void generateLevel() {
		
	}

}
