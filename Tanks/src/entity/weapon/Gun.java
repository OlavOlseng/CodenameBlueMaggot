package entity.weapon;

public enum Gun {

	SHELLGUN(0), GRENADE(1), ROCKETLAUNCHER(2), MINELAUNCHER(3), AIRSTRIKE(4), MINIGUN(5);
	private int i;

	private Gun(int i) {
		this.i = i;
	}
}
