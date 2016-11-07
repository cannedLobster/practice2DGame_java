package sunx.SaD.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import sunx.SaD.Game.entity.mob.Player;
import sunx.SaD.Game.gfx.Screen;
import sunx.SaD.Game.input.Keyboard;
import sunx.SaD.Game.input.Mouse;
import sunx.SaD.Game.level.Level;
import sunx.SaD.Game.level.TileCoordinate;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 720;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 1;
	private static final String TITLE = "Game";

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Keyboard key;
	private Mouse mouse;
	private Screen screen;
	private Level level;
	private Player player;
	private Menu menu;

	public enum STATE {
		MENU, GAME
	};

	private STATE State = STATE.MENU;

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);

		// Initialize
		frame = new JFrame();
		menu = new Menu();

		screen = new Screen(WIDTH, HEIGHT);
		level = Level.firstLevel;

		key = new Keyboard();
		addKeyListener(key);

		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		TileCoordinate playerSpawnLocation = new TileCoordinate(280, 680);
		player = new Player(playerSpawnLocation.x(), playerSpawnLocation.y(), key);
		player.init(level);
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
		}
	}

	public void run() {
		long lastTimer = System.nanoTime();
		long timer = System.currentTimeMillis();
		double nsPerTick = 1000000000D / 60D;
		double delta = 0;

		int frames = 0;
		int updates = 0;

		requestFocus();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTimer) / nsPerTick;
			lastTimer = now;

			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(Game.TITLE + "| Frames: " + frames + "; Ticks: " + updates);
				updates = 0;
				frames = 0;
			}
		}

	}

	private void update() {
		if (State == STATE.GAME) {
			key.update();
			player.update();
			level.update();
			if (key.escape) System.exit(1);
		} else if (State == STATE.MENU) {
			if (Mouse.getButton() == 1 && Mouse.getX() >= 480 && Mouse.getX() <= 580) {
				if (Mouse.getY() >= 150 && Mouse.getY() <= 200) State = STATE.GAME;
			}
			if (Mouse.getButton() == 1 && Mouse.getX() >= 480 && Mouse.getX() <=580){
				if (Mouse.getY() >= 250 && Mouse.getY() <= 300) System.exit(1);
			}
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();

		int xScroll = player.xMap - screen.width / 2;
		int yScroll = player.yMap - screen.height / 2;

		level.render(xScroll, yScroll, screen);
		player.render(screen);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.screenPixels[i];
		}

		Graphics g = bs.getDrawGraphics();

		if (State == STATE.GAME) {

			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

			// Debugging Code
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", 0, 25));

			g.drawString("X: " + player.xMap + ", Y: " + player.yMap, 350, 300);

			// End of Debugging Code

		} else if (State == STATE.MENU) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWindowWidth(), getWindowHeight());
			menu.render(g);

			// Debugging Code
			g.setColor(Color.WHITE);
			;
			g.setFont(new Font("Verdana", 0, 25));

			g.drawString("X: " + Mouse.getX() + " Y: " + Mouse.getY(), 50, 50);
			g.drawString("MouseClick Status: " + Mouse.getButton(), 80, 80);
		}

		g.dispose();
		bs.show();
	}

	public static void main(String args[]) {
		Game game = new Game();

		game.frame.setResizable(false);
		game.frame.setTitle(Game.TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}

	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}

}
