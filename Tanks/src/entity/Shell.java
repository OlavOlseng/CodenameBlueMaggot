package entity;

import java.awt.Point;

import level.BasicLevel;

public class Shell extends Projectile {
	
	int explosionRadius = 20;
	
	
	public Shell(double x, double y, BasicLevel level, double speedPercent,
			int angle) {
		super(x, y, 5, level, speedPercent, angle);
		this.maxSpeed = 25;
		this.frictionConstant = 0.01;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;

	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius), (int) (y - explosionRadius), explosionRadius);
	}
	
	@Override
	public void applyFriction(){
		accelerate(-dx*frictionConstant,-dy*frictionConstant);
	}
	public void tick() {
		super.tick();
//		accelerate(-Math.pow(dx, 2)*airResistance * Math.signum(dx), -Math.pow(dy,2)*airResistance*Math.signum(dy));
		gravitate();

	}
}
