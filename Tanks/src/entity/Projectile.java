package entity;

import level.BasicLevel;

public abstract class Projectile extends Entity {

	protected double airResistance;
	protected int maxSpeed;
	protected PixelHitbox hitbox;

	public Projectile(double x, double y, double xr, double yr, BasicLevel level, double speedPercent, double angle) {
		super(x + xr * Math.cos(Math.toDegrees(angle)), y - yr * Math.sin(Math.toDegrees(angle)), xr, yr, level);
		this.angle = angle;
		this.dx = (speedPercent * Math.cos(angle % 360 * 2 * Math.PI / 360));
		this.dy = (speedPercent * -Math.sin(angle % 360 * 2 * Math.PI / 360));
		this.hitbox = new PixelHitbox();
		init();
	}

	public void init() {

		hitbox.addPoint(new FloatingPoint(-xr, 0)); // left
		hitbox.addPoint(new FloatingPoint(0, -yr)); // top

		hitbox.addPoint(new FloatingPoint(0, 0)); // center

		hitbox.addPoint(new FloatingPoint(xr, 0)); // right
		hitbox.addPoint(new FloatingPoint(0, yr)); // bot

	}

	abstract public void explode();

	// @Override
	// public void tick() {
	// super.tick();
	// while(level.getTerrain().hitTestpoint(x, y))
	// }

	@Override
	public String getObject(){
		
		return super.getObject();
	}

	
	@Override
	public void handleMessage(String[] msg){
		super.handleMessage(msg);
		boolean willDie = Boolean.parseBoolean(msg[3]);
		
		
		if(willDie)
			remove();
	}
	
	@Override
	public void tick(double dt) {
		if (level.getTerrain().hitTestpoint((int) (x), (int) (y))) {
			while (level.getTerrain().hitTestpoint((int) (x), (int) (y))) {
				setLocation(x - dx, y - dy);
			}
		}
		super.tick(dt);

	}
}
