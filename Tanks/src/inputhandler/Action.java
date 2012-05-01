package inputhandler;

import java.awt.event.KeyEvent;
import java.util.List;

public enum Action {
	LEFT(0), //
	RIGHT(0), //
	JETPACK(0), //
	SHOOT(0), //
	CYCLE_WEAPON(0), //
	ANGLE_CLOCKWISE(0), //
	ANGLE_COUNTERCLOCKWISE(0), //

	LEFT2(0), //
	RIGHT2(0), //
	JETPACK2(0), //
	SHOOT2(0), //
	CYCLE_WEAPON2(0), //
	ANGLE_CLOCKWISE2(0), //
	ANGLE_COUNTERCLOCKWISE2(0), //

	MENU(KeyEvent.VK_ESCAPE);//

	int bind;

	Action(int bind) {
		this.bind = bind;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}


}
