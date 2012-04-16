package entity;

import java.awt.Point;
import java.util.Random;

public class Entity {
	
	protected final Random rand = new Random();
	protected int x, y;
	public int xr = 10, yr = 10;
	protected int dx = 0, dy = 0;
	
	
	public Entity(){
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
	
//	Denne må overskrives senere, bli akstrakt
	public void accelerate(int ddx, int ddy){
		this.dx += ddx;
		this.dy += ddy;
	}
	
	public void move(){
		this.setLocation(this.x + this.dy, this.y + this.dy);
	}
	
//	denne skal bli abstrakt, brukes til movement
	public void tick(){
		move();
	}
	
	public boolean intersects(Entity other){
		//dette kan eventuelt gjøres om til et hitbox objekt senere;
		
		Point p = other.getLocation();
		int xLeft = p.x - other.xr;
		int xRight = p.x + other.xr;
		int yTop = p.y - other.yr; 
		int yBot = p.y + other.yr; 
		
		return (x + xr < xLeft || y + yr > yTop || x - xr > xRight || y - yr < yBot);
		
	}
	
	
}
