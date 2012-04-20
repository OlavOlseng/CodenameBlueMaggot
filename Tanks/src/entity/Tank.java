package entity;

public class Tank extends Entity {

	private int muzzleAngle;
	private int muzzleLength;
	private int hitpoints;
	private int maxHitpoints = 200;

	public Tank(int x, int y) {
		super(x, y, 100, 100);
		muzzleAngle = 0;
		muzzleLength = 20;
		hitpoints = 200;
	}
	
	public void setMuzzleAngle(int degrees){
		this.muzzleAngle = degrees;
	}
	
	public void incrementMuzzleAngle(int degrees) {
		setMuzzleAngle(this.muzzleAngle + degrees);
		muzzleAngle = muzzleAngle%60;
	}

	public void changeHp(int amount) {
		if (hitpoints + amount > maxHitpoints)
			hitpoints = maxHitpoints;
		else if (hitpoints + amount < 0) {
			hitpoints = 0;
			remove();
		} else
			hitpoints += amount;
	}
	
	public void fire(double speedPercent){
		
	}
	
	@Override
	public void gravitate() {
	}

	@Override
	public void tick() {
		super.tick();
		System.out.println(x);
		System.out.println(dx);
	}
	
}
