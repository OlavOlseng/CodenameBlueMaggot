package entity;

import java.awt.Point;

import level.BasicLevel;

public class Shell extends Projectile {

	int explosionRadius = 20;

	public Shell(double x, double y, BasicLevel level, double speedPercent,
			int angle) {
		super(x, y, 4, level, speedPercent, angle);
		this.maxSpeed = 25;
		this.frictionConstant = 0.008;
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

	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()),
					(int) (y + point.getY())))
				return true;
		}
		return false;
	}

	public boolean handleIntersections(){
		for(int i =0; i< level.getPlayers().size(); i++){
			if (intersectsEntity((Entity) level.getPlayers().get(i)))
			return true;
		}
		return false;
	}

	public void tick() {
		super.tick();
		if (intersectsTerrain() || handleIntersections()) {
			explode();
			remove();
		}
		gravitate();

	}
}
