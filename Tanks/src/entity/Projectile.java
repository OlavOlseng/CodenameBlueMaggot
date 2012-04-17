package entity;

import java.awt.Point;

public abstract class Projectile extends Entity {
	
	protected double airResistance;
	protected int maxSpeed;
	
	public Projectile(Point origin, int size, double speedPercent, int angle) {
		super(origin.x, origin.y, size, size);
		this.angle = angle;
		this.dx = (speedPercent*Math.cos(angle%360*2*Math.PI/360));
		this.dy = (speedPercent*Math.sin(angle%360*2*Math.PI/360));
	}
	
	public abstract void tick();
	
}
