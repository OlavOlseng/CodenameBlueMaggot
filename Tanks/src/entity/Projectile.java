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
		super(x, y, size, size, level);
		this.angle = angle;
		this.dx = (speedPercent * Math.cos(angle % 360 * 2 * Math.PI / 360));
		this.dy = (speedPercent * Math.sin(angle % 360 * 2 * Math.PI / 360));
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
	public boolean intersectsEntity() {
		for (Entity ent : level.getEntities()) {

			double[] p = ent.getLocation();
			double xLeft = p[0] - ent.xr;
			double xRight = p[0] + ent.xr;
			double yTop = p[1] - ent.yr;
			double yBot = p[1] + ent.yr;
			if (!(x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot))
				return true;
		}
		return false;
	}

	@Override
	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()),
					(int) (y + point.getY())))
				return true;
		}
		return false;
	}

	@Override
	public void tick() {
		super.tick();
		if (intersectsTerrain() || intersectsEntity()){
			explode();
			remove();
		}
	}
}
