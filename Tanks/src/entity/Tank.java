package entity;

public class Tank extends Entity {

	private int muzzleAngle;
	private int muzzleLength;
	private int hitpoints;
	private int maxHitpoints = 200;

	public Tank(double x, double y) {
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
	public boolean intersectsEntity(Entity other) {
		double[] p = other.getLocation();
		double xLeft = p[0] - other.xr;
		double xRight = p[0] + other.xr;
		double yTop = p[1] - other.yr;
		double yBot = p[1] + other.yr;
		return !(x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot);
	}
	
	@Override
	public void intersectsTerrain() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void gravitate() {
	}

	@Override
	public void tick() {
		super.tick();
	}
}
