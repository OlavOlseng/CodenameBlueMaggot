package inputhandler;

import java.util.List;

public class HeldKey extends Key {

	public int ticksHeld = 0, maxTicksHeld;

	public HeldKey(List<Key> keys, int maxTicksHeld) {
		super(keys);
		this.maxTicksHeld = maxTicksHeld;
	}

	@Override
	public void toggle(boolean pressed) {
		super.toggle(pressed);
		if(pressed)
			ticksHeld = 0;
	}

	@Override
	public void tick() {
		super.tick();
		if (down) {
			ticksHeld++;
			if (ticksHeld >= maxTicksHeld) {
				down = false;
			}
			System.out.println(ticksHeld + " " + maxTicksHeld);
		}
	}
}