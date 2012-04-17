//Hovedklasse for alt som puttes på brettet,
//skal bli abstrakt

package entity;

import java.awt.Point;
import java.util.Random;

public class Entity {
	
	public static final double G = 9.81;
//	public static final Point UniverseCenter; Denne brukes senere til å sette gravitasjonssenter.
	protected final Random rand = new Random();
	protected int x, y;
	protected int xr = 10, yr = 10;
	protected double dx = 0, dy = 0;
	protected boolean removed = false;
	
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
	
	public void setSpeed(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
//	Denne må overskrives senere, bli abstrakt
	public void accelerate(int ddx, int ddy){
		this.dx += ddx;
		this.dy += ddy;
	}
	
	public void move(){
		this.setLocation((int)(this.x + this.dy), (int)(this.y + this.dy));
	}
	
//	denne skal bli abstrakt, brukes til alt som skal gjøres på en tick
	public void tick(){
		move();
	}
	
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
