package entity;

import level.BasicLevel;

public class Grenade extends Projectile {

	private int liveTime = 0;
	private int explosionTime = 150;
	int explosionRadius = 25;

	public Grenade(double x, double y, BasicLevel level, double speedPercent,
			int angle) {
		super(x, y, 4, level, speedPercent, angle);
		this.maxSpeed = 15;
		this.frictionConstant = 0.001;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius),
				(int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level, 50));
	}

	@Override
	public void applyFriction() {
		accelerate(-dx * frictionConstant, -dy * frictionConstant);
	}
	
	public void handleIntersects(){
		FloatingPoint point = hitbox.getPoint(0);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(-dx * 0.55, dy);
			this.setLocation(x + 1, y);
		}
		point = hitbox.getPoint(4);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(-dx * 0.55, dy);
			this.setLocation(x - 1, y);
		}

		point = hitbox.getPoint(1);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(dx*0.8, -dy * 0.55);
			this.setLocation(x, y + 1);
		}
		point = hitbox.getPoint(3);
		if (level.getTerrain().hitTestpoint((int) (point.getX() + x),
				(int) (point.getY() + y))) {
			this.setSpeed(dx*0.5, -dy * 0.55);
			this.setLocation(x, y - 1);
		}
	}
	

	public void tick() {
		super.tick();
		liveTime++;
		handleIntersects();
		if (liveTime >= explosionTime) {
			explode();
			remove();
		}
	}
}
