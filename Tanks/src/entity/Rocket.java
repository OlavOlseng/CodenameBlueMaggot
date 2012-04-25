package entity;

import gfx.ResourceManager;
import baseGame.Rendering.RGBImage;
import baseGame.Rendering.Renderer;
import level.BasicLevel;

public class Rocket extends Projectile {

	int explosionRadius = 40;
	double explosionPower = 200;
	double thrust = 0.3;
	int fuel = 90;
	int ticks = 0;
	
	public Rocket(double x, double y, BasicLevel level, double speedPercent,
			double angle) {
		super(x, y, 4, 4, level, speedPercent, angle);
		this.maxSpeed = 10;
		this.frictionConstant = 0.004;
		this.angle = angle;
		this.dx = dx * maxSpeed;
		this.dy = dy * maxSpeed;
	}

	@Override
	public void explode() {
		level.getTerrain().addExplosion((int) (x - explosionRadius),
				(int) (y - explosionRadius), explosionRadius);
		level.addEntity(new Explosion(x, y, explosionRadius + 2, level,
				explosionPower));
	}

	public boolean intersectsTerrain() {
		for (FloatingPoint point : hitbox) {
			if (level.getTerrain().hitTestpoint((int) (x + point.getX()),
					(int) (y + point.getY()))){
				System.out.println("Traff bakke");
				return true;
			}
		}
		return false;
	}

	public boolean handleIntersections() {
		for (int i = 0; i < level.getEntities().size(); i++) {
			Entity ent = level.getEntities().get(i);
			if (ent == this)
				continue;
			if (intersectsEntity(level.getEntities().get(i)))
				return true;
		}
		return false;
	}

	public void tick(double dt) {
		super.tick(dt);
		 if(Math.sqrt(dx*dx + dy*dy) < maxSpeed && fuel > ticks){
		this.accelerate(thrust * Math.cos(Math.toRadians(angle)),
				-thrust* Math.sin(Math.toRadians(angle)));
		 }
		 if (intersectsTerrain() || handleIntersections()) {
		 explode();
		 remove();
		 }
	
		 ticks++;
	}

	@Override
	public void render(Renderer renderer) {
		// TODO Auto-generated method stub
		RGBImage img = ResourceManager.ROCKET;
		renderer.DrawImage(img ,-1, (int) (x - getXr()),(int) (y - getYr()), img.getWidth(), img.getHeight());
		
	}

}
