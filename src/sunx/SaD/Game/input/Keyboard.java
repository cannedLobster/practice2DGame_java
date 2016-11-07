package sunx.SaD.Game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, escape;
	
	public void update() {
		up = keys[KeyEvent.VK_W]||keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S]||keys[KeyEvent.VK_DOWN];
		right = keys[KeyEvent.VK_D]||keys[KeyEvent.VK_RIGHT];
		left = keys[KeyEvent.VK_A]||keys[KeyEvent.VK_LEFT];
		escape = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {

	}

}
