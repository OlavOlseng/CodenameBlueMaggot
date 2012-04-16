package entity;

import java.awt.Point;

public abstract class Projectile extends Entity {
	
	private int angle;
	private double airResistance;
	
	public Projectile(Point origin, int size, int initialSpeed, int angle) {
		super(origin.x, origin.y, size, size);
		this.angle = angle;
		this.dx = (initialSpeed*Math.cos(angle%360*2*Math.PI/360));
		this.dy = (initialSpeed*Math.sin(angle%360*2*Math.PI/360));
	}
	
	@Override
	public void tick(){
//		Denne koden må applisere luftmotstand
	}
	
}
