package entity;

import level.BasicLevel;

public class Grenade extends Projectile {
	
	private int liveTime = 0;
	private int explosionTime = 150;
	int explosionRadius = 25;

	public Grenade(double x, double y, BasicLevel level,
			double speedPercent, int angle) {
		super(x, y, 5, level, speedPercent, angle);
		this.maxSpeed = 20;
		this.frictionConstant = 0.012;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius),
				(int) (y - explosionRadius), explosionRadius);
	}
	
	@Override
	public void applyFriction() {
		accelerate(-dx * frictionConstant, -dy * frictionConstant);
	}
	
	@Override
	public boolean intersectsTerrain() {
		FloatingPoint point = hitbox.getPoint(0);
		if(level.getTerrain().hitTestpoint((int)(point.getX() + x), (int)(point.getY() + y))){
			this.setSpeed(-dx*0.4, dy);
		}
		point = hitbox.getPoint(4);
		if(level.getTerrain().hitTestpoint((int)(point.getX() + x), (int)(point.getY() + y)))
			this.setSpeed(-dx*0.4, dy);
		
		point = hitbox.getPoint(1);
		if(level.getTerrain().hitTestpoint((int)(point.getX() + x), (int)(point.getY() + y)))
			this.setSpeed(dx, -dy*0.4);
		
		point = hitbox.getPoint(3);
		if(level.getTerrain().hitTestpoint((int)(point.getX() + x), (int)(point.getY() + y)))
			this.setSpeed(dx, -dy*0.4);
		return false;
	}

	public void tick() {
		super.tick();
		liveTime++;
		intersectsTerrain();
		if (liveTime >= explosionTime) {
			explode();
			remove();
		}
	}
}
