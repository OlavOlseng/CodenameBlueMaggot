package inputhandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

		if (e.getKeyCode() == Action.MENU.getBind())
			menu.toggle(pressed);

		if (e.getKeyCode() == KeyEvent.VK_0)
			grenadeSpam.toggle(pressed);
		// if (e.getKeyCode() == KeyEvent.VK_S)
		// down1.toggle(pressed);

		if (e.getKeyCode() == Action.JETPACK.getBind())
			up1.toggle(pressed);
		if (e.getKeyCode() == Action.LEFT.getBind())
			left1.toggle(pressed);
		if (e.getKeyCode() == Action.RIGHT.getBind())
			right1.toggle(pressed);
		if (e.getKeyCode() == Action.SHOOT.getBind())
			fire1.toggle(pressed);
		if (e.getKeyCode() == Action.ANGLE_CLOCKWISE.getBind())
			rotateR1.toggle(pressed);
		if (e.getKeyCode() == Action.ANGLE_COUNTERCLOCKWISE.getBind())
			rotateL1.toggle(pressed);
		if (e.getKeyCode() == Action.CYCLE_WEAPON.getBind())
			down1.toggle(pressed);

		if (e.getKeyCode() == Action.JETPACK2.getBind())
			up2.toggle(pressed);
		if (e.getKeyCode() == Action.LEFT2.getBind())
			left2.toggle(pressed);
		if (e.getKeyCode() == Action.RIGHT2.getBind())
			right2.toggle(pressed);
		if (e.getKeyCode() == Action.SHOOT2.getBind())
			fire2.toggle(pressed);
		if (e.getKeyCode() == Action.ANGLE_CLOCKWISE2.getBind())
			rotateR2.toggle(pressed);
		if (e.getKeyCode() == Action.ANGLE_COUNTERCLOCKWISE2.getBind())
			rotateL2.toggle(pressed);
		if (e.getKeyCode() == Action.CYCLE_WEAPON2.getBind())
			down2.toggle(pressed);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void resetSingle() {
		Action.RIGHT.setBind(KeyEvent.VK_D);
		Action.LEFT.setBind(KeyEvent.VK_A);
		Action.JETPACK.setBind(KeyEvent.VK_W);
		Action.SHOOT.setBind(KeyEvent.VK_2);
		Action.CYCLE_WEAPON.setBind(KeyEvent.VK_S);
		Action.ANGLE_CLOCKWISE.setBind(KeyEvent.VK_3);
		Action.ANGLE_COUNTERCLOCKWISE.setBind(KeyEvent.VK_1);

		Action.RIGHT2.setBind(KeyEvent.VK_RIGHT);
		Action.LEFT2.setBind(KeyEvent.VK_LEFT);
		Action.JETPACK2.setBind(KeyEvent.VK_UP);
		Action.SHOOT2.setBind(KeyEvent.VK_K);
		Action.CYCLE_WEAPON2.setBind(KeyEvent.VK_DOWN);
		Action.ANGLE_CLOCKWISE2.setBind(KeyEvent.VK_L);
		Action.ANGLE_COUNTERCLOCKWISE2.setBind(KeyEvent.VK_J);

		Action.MENU.setBind(KeyEvent.VK_ESCAPE);
	}

	public void resetLan() {
		Action.RIGHT.setBind(KeyEvent.VK_D);
		Action.LEFT.setBind(KeyEvent.VK_A);
		Action.JETPACK.setBind(KeyEvent.VK_W);
		Action.SHOOT.setBind(KeyEvent.VK_K);
		Action.CYCLE_WEAPON.setBind(KeyEvent.VK_S);
		Action.ANGLE_CLOCKWISE.setBind(KeyEvent.VK_L);
		Action.ANGLE_COUNTERCLOCKWISE.setBind(KeyEvent.VK_J);

		Action.RIGHT2.setBind(KeyEvent.VK_0);
		Action.LEFT2.setBind(KeyEvent.VK_0);
		Action.JETPACK2.setBind(KeyEvent.VK_0);
		Action.SHOOT2.setBind(KeyEvent.VK_0);
		Action.CYCLE_WEAPON2.setBind(KeyEvent.VK_0);
		Action.ANGLE_CLOCKWISE2.setBind(KeyEvent.VK_0);
		Action.ANGLE_COUNTERCLOCKWISE2.setBind(KeyEvent.VK_0);

		Action.MENU.setBind(KeyEvent.VK_ESCAPE);
	}

	public void writeConfig() {
		File config = new File("config.txt");
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(config));
			for (Action action : Action.values()) {
				out.println(action.name() + ":" + action.getBind());
				System.out.println(action.name() + ":" + action.getBind());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				System.out.println("couldn't close file: " + out);
			}
		}
	}

	public void readConfig() {
		BufferedReader in = null;
		File config = new File("config.txt");
		if (!config.exists())
			return;
		try {
			in = new BufferedReader(new FileReader(config));
			String c;
			while ((c = in.readLine()) != null) {
				String key = c.split(":")[0];
				int value = Integer.parseInt(c.split(":")[1]);
				for (Action action : Action.values()) {
					if (action.name().equals(key))
						action.setBind(value);
				}
			}
		} catch (IOException e) {
			System.out.println("couldn't find file: " + config.getAbsolutePath());
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				System.out.println("couldn't close file: " + in);
			}
		}
		for (Action action : Action.values()) {
			System.out.println(action.name() + " - " + action.getBind());
		}
	}
}
