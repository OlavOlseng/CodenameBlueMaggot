package entity;

import java.awt.Point;

public class Shell extends Projectile {
	
	public Shell(Point origin, int size, double speedPercent, int angle) {
		super(origin, size, speedPercent, angle);
		this.maxSpeed = 50;
		this.airResistance = 1;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gravitate() {
		this.accelerate(0, Entity.G);
	}

}
