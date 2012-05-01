package inputhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {

	private List<Key> keys = new ArrayList<Key>();

	public Key menu = new Key(keys);
	public Key menuOff = new Key(keys);
	public Key tab = new Key(keys);

	public Key up1 = new Key(keys);
	public Key down1 = new Key(keys);
	public Key left1 = new Key(keys);
	public Key right1 = new Key(keys);
	public Key fire1 = new Key(keys);
	public Key rotateR1 = new Key(keys);
	public Key rotateL1 = new Key(keys);

	public Key up2 = new Key(keys);
	public Key down2 = new Key(keys);
	public Key left2 = new Key(keys);
	public Key right2 = new Key(keys);
	public Key fire2 = new Key(keys);
	public Key rotateR2 = new Key(keys);
	public Key rotateL2 = new Key(keys);
	public Key grenadeSpam = new Key(keys);

	public void releaseAll() {
		for (Key key : keys) {
			key.down = false;
		}
	}

	public void tick(double dt) {
		for (Key key : keys) {
			key.tick(dt);
		}
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		toggle(ke, true);
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	public void toggle(KeyEvent e, boolean pressed) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			menu.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_2)
			menuOff.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			tab.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_0)
			grenadeSpam.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_W)
			up1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_S)
			down1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_A)
			left1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_D)
			right1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_2)
			fire1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_3)
			rotateR1.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_1)
			rotateL1.toggle(pressed);

		
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
			up2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			down2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_K)
			fire2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_L)
			rotateR2.toggle(pressed);
		if (e.getKeyCode() == KeyEvent.VK_J)
			rotateL2.toggle(pressed);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
