package entity;

import java.awt.Point;

public abstract class Projectile extends Entity {
	
	protected double airResistance;
	protected int maxSpeed;
	
	public Projectile(double[] origin, int size, double speedPercent, int angle) {
		super(origin[0], origin[1], size, size);
		this.angle = angle;
		this.dx = (speedPercent*Math.cos(angle%360*2*Math.PI/360));
		this.dy = (speedPercent*Math.sin(angle%360*2*Math.PI/360));
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
	}
}
