package entity;

import java.awt.Point;

import level.BasicLevel;

public class Shell extends Projectile {

	public Shell(double x, double y, int size, BasicLevel level,
			double speedPercent, int angle) {
		super(x, y, size, level, speedPercent, angle);
		this.maxSpeed = 10;
		this.airResistance = 0.02;
		this.angle = angle;
		this.dx = dx*maxSpeed;
		this.dy = dy*maxSpeed;

	}

	@Override
	public void gravitate() {
		this.accelerate(0, 0.5);
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
