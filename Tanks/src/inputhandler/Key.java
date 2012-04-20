package inputhandler;

import java.util.List;

public class Key {
	public int presses, absorbs;
	public boolean down, clicked;

	public Key(List<Key> keys) {
		keys.add(this);
	}

	public void toggle(boolean pressed) {
		if (pressed != down) {
			down = pressed;
		}
		if (pressed) {
			presses++;
		}
	}

	public void tick() {
		if (absorbs < presses) {
			absorbs++;
			clicked = true;
		} else {
			clicked = false;
		}
	}
}