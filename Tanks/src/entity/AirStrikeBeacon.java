package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class AirStrikeBeacon extends Projectile{
	
	private boolean touchingGround = false;
	private boolean triggered = false;
	private double calldownTime = 100;
	
	public AirStrikeBeacon(double x, double y, BasicLevel level, double speedPercent, double angle) {
		super(x, y, 6, 6, level, speedPercent, angle);
		this.maxSpeed = 9;
		this.frictionConstant = 0.005;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}
	
	public void handleIntersections(){
		touchingGround = false;
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()), (int) (y + point.getY()))) {
				while (level.getTerrain().hitTestpoint((int) (x), (int) (y))) {
					setLocation(x - dx/2, y - dy/2);
				}
				setSpeed(0, 0);
				touchingGround = true;
				triggered = true;
			}
		}
	}
	
	@Override
	public void gravitate() {};
	
	@Override
	public void explode() {
		System.out.println("exploded");
	}
	
	public void tick(double dt){
		super.tick(dt);
		handleIntersections();
		if(triggered && calldownTime > 0){
			calldownTime -= dt;
		}
		if(calldownTime <= 0){
			explode();
		}
		if (!touchingGround) {
			accelerate(0, 0.1);
		}
	}
	
	@Override
	public void render(Renderer renderer) {
		RGBImage img = ResourceManager.SHELL;
		renderer.DrawImage(img, -1, (int) (x - getXr()), (int) (y - getYr()), img.getWidth(), img.getHeight());
	}

	@Override
	public void initNetworkValues() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
