package com.sunx.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.sunx.Game.entity.mob.Player;
import com.sunx.Game.gfx.Screen;
import com.sunx.Game.input.Keyboard;
import com.sunx.Game.input.Mouse;
import com.sunx.Game.level.Level;
import com.sunx.Game.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static int width = 720;
	private static int height = width / 16 * 9;
	private static int scale = 1;
	public static String title = "Game";

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;
	private Mouse mouse;

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		// initialize
		screen = new Screen(width, height);
		frame = new JFrame();
		level = Level.spawn;
		key = new Keyboard();
		TileCoordinate playerSpawn = new TileCoordinate(20, 63);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);

		addKeyListener(key);

		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

	}

	public static int getWindowWidth() {
		return width * scale;
	}

	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // try catch the thread.join();
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double NSPERTICK = 1000000000D / 60D;
		double delta = 0;

		int frames = 0;
		int updates = 0;

		requestFocus(); // !! Brings up the window!!!

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / NSPERTICK;
			lastTime = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				// System.out.println("Frames: " + frames + "; Ticks: " +
				// updates);
				frame.setTitle(Game.title + " Frames: " + frames + "; Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	public void update() {
		key.update();
		player.update();
		level.update();
	}
		
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		
		//screen.renderSheet(40, 40, SpriteSheet.player_up, false);
		
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 25));
		// g.drawString("X: " + player.x + ", Y: " + player.y, 350, 300);
		// coordinates

		// g.fillRect(Mouse.getX(), Mouse.getY(), 10, 10);
		// if (Mouse.getButton() != -1) {
		// g.drawString("Mouse Button: " + Mouse.getButton(), 20, 25);
		// }

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
 