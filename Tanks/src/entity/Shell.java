package entity;

import java.awt.Point;

import level.BasicLevel;

public class Shell extends Projectile {

	public Shell(double x, double y, BasicLevel level,
			double speedPercent, int angle) {
		super(x, y, 5, level, speedPercent, angle);
		this.maxSpeed = 40;
		this.airResistance = 0.02;
		this.angle = angle;
		this.dx = dx*maxSpeed;
		this.dy = dy*maxSpeed;

	}

	@Override
	public void gravitate() {
		this.accelerate(0, 0.05);
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) x, (int) y, 20);
	}

	public void tick() {
		super.tick();
		accelerate(-dx * airResistance, -dy*airResistance);
		gravitate();
		
	}
}
