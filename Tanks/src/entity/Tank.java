package entity;

public class Tank extends Entity {

	private int muzzleAngle;
	private int muzzleLength;
	private int hitpoints;
	
	public Tank(int x, int y) {
		super(x, y, 15, 7);
		muzzleAngle = 0;
		muzzleLength = 20;
		hitpoints = 200;
	}

	public void incrementMuzzleAngle(int degrees) {
		this.muzzleAngle += degrees;
	}

	public void changeHp(int amount) {
		if (hitpoints + amount > 200)
			hitpoints = 200;
		else if (hitpoints + amount < 200) {
			hitpoints = 0;
			remove();
		} else {
			hitpoints += amount;
		}
	}

	
	
}
