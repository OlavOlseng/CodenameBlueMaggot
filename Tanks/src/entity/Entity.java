//Hovedklasse for alt som puttes på brettet,
//skal bli abstrakt

package entity;

import java.awt.Point;
import java.util.Random;

import baseGame.testGame;

public abstract class Entity {

	public static final double G = 4;
	protected final Random rand = new Random();
	protected double x, y;
	protected double xr, yr;

	protected int angle = 0;
	protected double dx = 0, dy = 0;
	public boolean removed = false;

	public Entity(double x, double y, double xr, double yr) {
		setLocation(x, y);
		this.xr = xr;
		this.yr = yr;
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double[] getLocation() {
		double[] location = {x,y};
		return location;
	}

	public double getXr() {
		return xr;
	}

	public double getYr() {
		return yr;
	}

	public void setSpeed(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getAngle() {
		return this.angle;
	}

	public void accelerate(double ddx, double ddy) {
		this.dx += ddx;
		this.dy += ddy;
	}

	public abstract void gravitate();

	public void move() {
		this.setLocation((int) (this.x + this.dx), (int) (this.y + this.dy));
	}
	
	public void tick() {
		move();
		if (x > testGame.WIDTH + 100 || x < -100 || y > testGame.HEIGHT + 100 || y < -300)
			remove();
	}

	abstract public boolean intersectsEntity(Entity other);
	
	abstract public void intersectsTerrain();		
	
	public void remove() {
		this.removed = true;
	}
}
