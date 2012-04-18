//Hovedklasse for alt som puttes på brettet,
//skal bli abstrakt

package entity;

import java.awt.Point;
import java.util.Random;

public abstract class Entity {
	
	public static final double G = 4;
	protected final Random rand = new Random();
	protected int x, y;
	protected int xr, yr;

	protected int angle = 0;
	protected double dx = 0, dy = 0;
	public boolean removed = false;
	
	public Entity(int x, int y, int xr, int yr){
		setLocation(x, y);
		this.xr = xr;
		this.yr = yr;
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point getLocation(){
		return new Point(this.x, this.y);
	}
	
	public int getXr() {
		return xr;
	}
	
	public int getYr() {
		return yr;
	}
	
	public void setSpeed(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getAngle(){
		return this.angle;
	}
	
	public void accelerate(double ddx, double ddy){
		this.dx += ddx;
		this.dy += ddy;
	}
	
	public abstract void gravitate();
	
	public void move(){
		this.setLocation((int)(this.x + this.dx), (int)(this.y + this.dy));
	}
	
	public abstract void tick();
	
	public boolean intersects(Entity other){
		
		//denne blokken lager hitbox kantene, dette kan eventuelt gjøres om til et hitbox objekt senere;
		Point p = other.getLocation();
		int xLeft = p.x - other.xr;
		int xRight = p.x + other.xr;
		int yTop = p.y - other.yr; 
		int yBot = p.y + other.yr; 
		return !(x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot);
	}
	
	public void remove(){
		this.removed = true;
	}
}
