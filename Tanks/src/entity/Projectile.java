package entity;

import java.awt.Point;

import level.BasicLevel;
import level.Terrain;

public abstract class Projectile extends Entity {

	protected double airResistance;
	protected int maxSpeed;
	protected PixelHitbox hitbox;

	public Projectile(double x, double y, int size, BasicLevel level,
			double speedPercent, int angle) {
		super(x + size * Math.cos(Math.toDegrees(angle)), y - size
				* Math.sin(Math.toDegrees(angle)), size, size, level);
		this.angle = angle;
		this.dx = (speedPercent * Math.cos(angle % 360 * 2 * Math.PI / 360));
		this.dy = (speedPercent * -Math.sin(angle % 360 * 2 * Math.PI / 360));
		this.hitbox = new PixelHitbox();
		init();
	}

	public void init() {
		hitbox.addPoint(new FloatingPoint(-xr, 0));
		hitbox.addPoint(new FloatingPoint(0, -yr));
		hitbox.addPoint(new FloatingPoint(0, 0));
		hitbox.addPoint(new FloatingPoint(0, yr));
		hitbox.addPoint(new FloatingPoint(xr, 0));
	}

	abstract public void explode();
	
	
	@Override
	public void tick() {
		super.tick();
	}
}
